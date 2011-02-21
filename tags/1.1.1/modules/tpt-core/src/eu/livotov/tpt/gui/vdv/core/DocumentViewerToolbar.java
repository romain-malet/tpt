/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package eu.livotov.tpt.gui.vdv.core;

import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;

/**
 *
 * @author dlivotov
 */
public class DocumentViewerToolbar extends HorizontalLayout
{
    private Button zoomUp = new Button("Zoom +");
    private Button zoomDown = new Button("Zoom -");
    private TextField currentPage = new TextField();
    private Label currentPageLabel = new Label("Page ");

    public DocumentViewerToolbar()
    {
        super();
        setWidth("100%");
        setHeight(null);
        initUI();
        initActions();
    }

    private void initUI()
    {
        setSpacing(true);

        addComponent(zoomDown);
        addComponent(zoomUp);
        addComponent(currentPageLabel);
        addComponent(currentPage);
    }

    private void initActions()
    {
        zoomUp.addListener(new Button.ClickListener() {

            public void buttonClick(ClickEvent event)
            {
            }
        });

        zoomDown.addListener(new Button.ClickListener() {

            public void buttonClick(ClickEvent event)
            {
            }
        });

        currentPage.addListener(new ValueChangeListener() {

            public void valueChange(ValueChangeEvent event)
            {

            }
        });
    }


    public interface DocumentViewerToolbarEventsListener
    {
        void zoomChanged(int zoom);

        void pageChanged(int page);
    }

}
