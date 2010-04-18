/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.livotov.tpt.demo;

import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Link;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.VerticalLayout;
import eu.livotov.tpt.demo.api.DemoSection;
import eu.livotov.tpt.gui.windows.TPTWindow;
import eu.livotov.tpt.i18n.TM;

/**
 *
 * @author dll
 */
public class MainWindow extends TPTWindow
{

    VerticalLayout root = new VerticalLayout ();
    VerticalLayout vheader = new VerticalLayout ();
    HorizontalLayout header = new HorizontalLayout ();
    HorizontalLayout header2 = new HorizontalLayout ();
    TabSheet tabs = new TabSheet ();

    public MainWindow ()
    {
        super ( TM.get("main.window.title") );
        initUI ();
    }

    private void initUI ()
    {
        setSizeFull ();
        setContent ( root );
        root.setSizeFull ();
        root.setMargin ( false );
        root.setSpacing ( true );

        vheader.setStyleName ( "black" );
        vheader.setWidth ( "100%" );
        vheader.setHeight ( null );
        vheader.setMargin ( false );
        vheader.setSpacing ( false );

        header.setWidth ( "100%" );
        header.setHeight ( null );
        header.setMargin ( true );
        header.setStyleName ( "black" );

        header2.setWidth ( "100%" );
        header2.setHeight ( null );
        header2.setMargin ( true );
        header2.setStyleName ( "black" );

        Label h1 = new Label ( TM.get ( "main.window.logo") );
        h1.setStyleName ( "h1" );

        Label h2 = new Label ( TM.get("main.window.version") );

        Link manual = new Link ( TM.get("developer.handbook"), new ThemeResource ( "files/handbook.pdf" ) );
        manual.setIcon ( new ThemeResource ( "icons/pdf.png" ) );

        h1.setWidth ( null );
        h2.setWidth ( null );

        header.addComponent ( h1 );
        header.addComponent ( h2 );
        header.setExpandRatio ( h1, 1.0f );
        header.setComponentAlignment ( h2, Alignment.BOTTOM_RIGHT );

        header2.addComponent ( manual );
        header2.setComponentAlignment ( manual, Alignment.TOP_LEFT );

        tabs.setSizeFull ();

        vheader.addComponent ( header );
        vheader.addComponent ( header2 );
        root.addComponent ( vheader );
        root.addComponent ( tabs );
        root.setExpandRatio ( tabs, 1.0f );
    }

    public void addDemoSection ( DemoSection section )
    {
        tabs.addTab ( section, section.getSectionTitle (), section.getSectionIcon () );
    }
}
