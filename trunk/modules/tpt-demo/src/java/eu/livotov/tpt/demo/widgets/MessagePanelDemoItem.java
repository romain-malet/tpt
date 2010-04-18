/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.livotov.tpt.demo.widgets;

import com.vaadin.terminal.Resource;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import eu.livotov.tpt.TPTApplication;
import eu.livotov.tpt.demo.api.DemoItem;
import eu.livotov.tpt.gui.widgets.TPTMessagePanel;
import eu.livotov.tpt.gui.widgets.TPTSizer;
import eu.livotov.tpt.i18n.TM;
import java.io.Serializable;

/**
 *
 * @author dll
 */
public class MessagePanelDemoItem implements DemoItem, Serializable
{

    public boolean hasSourceCode ()
    {
        return true;
    }

    public boolean hasShowCase ()
    {
        return true;
    }

    public String getItemName ()
    {
        return TM.get ( "mpanel.title" );
    }

    public String getItemDescription ()
    {
        return TM.get ( "mpanel.info" );
    }

    public String getItemSourceCode ()
    {
        return "Window w = new Window( \"My WIndow\" ); \n\n" +
                "w.setWidth ( \"400px\" ); \n" +
                "w.setHeight ( \"200px\" ); \n" +
                "w.setModal ( true ); \n\n" +
                "w.setContent( new TPTMessagePanel(\"Centered text message with a single line of code\"));\n\n" +
                "TPTApplication.getCurrentApplication ().getMainWindow ().addWindow ( w );";
    }

    public void performShowCase ()
    {
        Window w = new Window( TM.get("mpanel.title"));

        w.setWidth ( "400px" );
        w.setHeight ( "200px" );
        w.setModal ( true );

        w.setContent( new TPTMessagePanel("Centered text message with a single line of code"));

        TPTApplication.getCurrentApplication ().getMainWindow ().addWindow ( w );
    }

    public Resource getIcon ()
    {
        return new ThemeResource ( "icons/sizer.png" );
    }


}
