/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package eu.livotov.tpt.gui.vdv.core;

import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.terminal.FileResource;
import com.vaadin.terminal.StreamResource;
import com.vaadin.terminal.StreamResource.StreamSource;
import com.vaadin.ui.Component;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.Table;
import com.vaadin.ui.Table.ColumnGenerator;
import eu.livotov.tpt.TPTApplication;
import eu.livotov.tpt.gui.vdv.api.DocumentRasterProvider;
import eu.livotov.tpt.gui.vdv.api.PageNumber;
import eu.livotov.tpt.gui.vdv.api.PageRotation;
import eu.livotov.tpt.gui.vdv.api.PageZoom;
import eu.livotov.tpt.gui.vdv.api.RasterizedPageProperties;
import java.io.File;
import java.io.InputStream;
import javax.xml.transform.stream.StreamResult;

/**
 *
 * @author dlivotov
 */
public class SinglePageDocumentRenderer extends PageRenderer implements ColumnGenerator
{
    private DocumentRasterProvider source = null;
    private float currentZoom = PageZoom.AS_IS;
    private int currentRotationLevel = PageRotation.NO_ROTATION;
    private int currentPage = 0;
    private Table pagesTable = new Table();

    public SinglePageDocumentRenderer(DocumentRasterProvider source, float initialZoom )
    {
        this.source = source;
        currentZoom = initialZoom;
        setupTableRenderer();
    }

    @Override
    public void goPage(int number)
    {
        currentPage = number;
        pagesTable.setCurrentPageFirstItemIndex(currentPage);
    }

    @Override
    public void setZoom(float zoom)
    {
        currentZoom = zoom;

        int currentPosition = pagesTable.getCurrentPageFirstItemIndex();
        setupTableRenderer();
        pagesTable.setCurrentPageFirstItemIndex(currentPosition);
    }

    @Override
    public void setRotation(int rotation)
    {
    }

    public void viewActivated(String string, String string1) 
    {
    }

    public void viewDeactivated(String string)
    {
    }

    public void viewAttached()
    {
    }

    public void viewRemoved()
    {
    }

    private void setupTableRenderer()
    {
        removeComponent(pagesTable);
        pagesTable = new Table();

        pagesTable.setColumnHeaderMode(Table.COLUMN_HEADER_MODE_HIDDEN);
        pagesTable.setPageLength(1);
        pagesTable.setCacheRate ( 0.1 );
        pagesTable.setImmediate(true);

        BeanItemContainer<SinglePageModePageBean> ds = new BeanItemContainer<SinglePageModePageBean>(SinglePageModePageBean.class);

        for ( int pn=0; pn<=source.getPagesCount(); pn++ )
        {
            ds.addBean(new SinglePageModePageBean(pn));
        }

        pagesTable.setContainerDataSource(ds);
        pagesTable.setVisibleColumns( new String[] {"dummyLeft", "page", "dummyRight"});
        pagesTable.addGeneratedColumn("page", this);
        pagesTable.setColumnWidth("page", source.getPageProperties( PageNumber.FIRST_PAGE, currentZoom, PageRotation.NO_ROTATION).getWidth());
        pagesTable.setSizeFull();

        setSizeFull();
        addComponent(pagesTable);
        setExpandRatio(pagesTable, 1.0f);
    }

    public Component generateCell(Table table, Object itemId, Object columnId)
    {
        final SinglePageModePageBean pageBean = (SinglePageModePageBean)itemId;
        String pageNameId = Utils.md5( "" + source.getDocumentId() + pageBean.getPageNumber() + currentZoom ) + ".jpg";
        
        StreamResource res = new StreamResource(new StreamSource()
        {

            public InputStream getStream()
            {
                return source.getPageData(pageBean.getPageNumber(), currentZoom, PageRotation.NO_ROTATION);
            }
        }, pageNameId, TPTApplication.getCurrentApplication());

        RasterizedPageProperties props = source.getPageProperties(pageBean.getPageNumber(),currentZoom, PageRotation.NO_ROTATION);

        Embedded img = new Embedded("", res);
        img.setType(Embedded.TYPE_IMAGE);
        img.setWidth(props.getWidth() + "px");
        img.setHeight(props.getHeight() + "px");

        return img;
    }



}
