/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.livotov.tpt.demo.api;

import com.vaadin.terminal.Resource;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;
import eu.livotov.tpt.gui.widgets.TPTSizer;

/**
 *
 * @author dll
 */
public abstract class DemoSection extends VerticalLayout
{

    protected  Label title = new Label ( getSectionTitle () );
    protected  Label info = new Label ( getSectionInformation () );
    protected  Panel demoItemsContent = new Panel ();

    public DemoSection ()
    {
        super ();
        setSizeFull ();
        setMargin ( true );
        setSpacing ( true );

        initUI ();
    }

    public void addDemoItem ( DemoItem item )
    {
        demoItemsContent.addComponent ( new DemonstrationPanel ( item ) );
        demoItemsContent.addComponent ( new TPTSizer ( null, "15px" ) );
    }

    public abstract String getSectionTitle ();

    public abstract String getSectionInformation ();

    public abstract Resource getSectionIcon ();

    private void initUI ()
    {
        this.title.setStyleName ( "h2" );
        this.info.setContentMode ( Label.CONTENT_XHTML );

        setSizeFull ();
        VerticalLayout header = new VerticalLayout ();
        header.setWidth ( "100%" );
        header.setHeight ( null );
        header.setSpacing ( false );
        header.setMargin ( false );
        header.addComponent ( this.title );
        header.addComponent ( this.info );

        demoItemsContent.setStyleName ( Panel.STYLE_LIGHT );
        demoItemsContent.setSizeFull ();
        demoItemsContent.setScrollable ( true );

        addComponent ( header );
        addComponent ( demoItemsContent );

        setExpandRatio ( demoItemsContent, 1.0f );
    }
}
