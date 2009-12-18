/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.livotov.tpt.demo.api;

import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;
import eu.livotov.tpt.demo.api.DemoItem;
import eu.livotov.tpt.gui.widgets.TPTSizer;
import eu.livotov.tpt.i18n.TM;

/**
 *
 * @author dll
 */
public class DemonstrationPanel extends Panel
{

    private DemoItem demo;
    private Button btnRunDemo = new Button ( TM.get("demo.run") );
    private Button btnShowSources = new Button ( TM.get("demo.src") );
    private VerticalLayout root = new VerticalLayout ();

    public DemonstrationPanel ( DemoItem demo )
    {
        super ();
        this.demo = demo;
        initUI ();
        initActions ();
    }

    private void initActions ()
    {
        btnRunDemo.addListener ( new Button.ClickListener ()
        {

            public void buttonClick ( ClickEvent event )
            {
                showcase ();
            }
        } );

        btnShowSources.addListener ( new Button.ClickListener ()
        {

            public void buttonClick ( ClickEvent event )
            {
                viewSources ();
            }
        } );
    }

    private void initUI ()
    {
        root.setMargin ( true );
        root.setSpacing ( true );
        root.setWidth ( "100%" );
        root.setHeight ( null );

        setContent ( root );
        setIcon ( demo.getIcon () );

        setCaption ( demo.getItemName () );
        Label info = new Label ( demo.getItemDescription () );
        info.setContentMode ( Label.CONTENT_XHTML );

        btnRunDemo.setIcon ( new ThemeResource ( "icons/run.png" ) );
        btnShowSources.setIcon ( new ThemeResource ( "icons/sources.png" ) );

        TPTSizer sizer = new TPTSizer ( "100%", null );

        HorizontalLayout toolbar = new HorizontalLayout ();
        toolbar.setWidth ( "100%" );
        toolbar.setHeight ( null );
        toolbar.setSpacing ( true );
        toolbar.setMargin ( false );

        root.addComponent ( info );
        root.addComponent ( toolbar );
        root.setExpandRatio ( info, 1.0f );

        toolbar.addComponent ( sizer );
        toolbar.addComponent ( btnRunDemo );
        toolbar.addComponent ( btnShowSources );
        toolbar.setExpandRatio ( sizer, 1.0f );

        btnRunDemo.setStyleName ( Button.STYLE_LINK );
        btnShowSources.setStyleName ( Button.STYLE_LINK );

        btnRunDemo.setEnabled ( demo.hasShowCase () );
        btnShowSources.setEnabled ( demo.hasSourceCode () );
    }

    private void showcase ()
    {
        demo.performShowCase ();
    }

    private void viewSources ()
    {
        new JavaSourcecodeViewer ( demo.getItemSourceCode () );
    }
}
