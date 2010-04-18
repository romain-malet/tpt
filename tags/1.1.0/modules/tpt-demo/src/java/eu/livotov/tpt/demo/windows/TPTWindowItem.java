/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.livotov.tpt.demo.windows;

import com.vaadin.terminal.Resource;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Label;
import eu.livotov.tpt.TPTApplication;
import eu.livotov.tpt.demo.api.DemoItem;
import eu.livotov.tpt.gui.widgets.TPTSizer;
import eu.livotov.tpt.gui.windows.TPTWindow;
import eu.livotov.tpt.i18n.TM;
import java.io.Serializable;

/**
 *
 * @author dll
 */
public class TPTWindowItem implements DemoItem, Serializable
{

    public TPTWindowItem ()
    {
    }

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
        return TM.get("sections.windows.tptwindow.title");
    }

    public String getItemDescription ()
    {
        return TM.get("sections.windows.tptwindow.info");
    }

    public String getItemSourceCode ()
    {
        return "public class MyWindow extends TPTWindow\n" +
                "    {\n" +
                "\n" +
                "        public MyWindow ()\n" +
                "        {\n" +
                "            super ( \"TPT Window Instance\" );\n" +
                "\n" +
                "            Button b1 = new Button ( \"Show Enchanced Notification\" );\n" +
                "\n" +
                "            addComponent ( new Label ( \"Try pressing ENTER or ESC keys to see how they will be intercepted. Note, that interception will work only if you have any component, such as button below, in focus.\" ) );\n" +
                "            addComponent ( new TPTSizer ( null, \"40px\" ) );\n" +
                "            addComponent ( b1 );\n" +
                "            b1.focus ();\n" +
                "\n" +
                "            b1.addListener ( new Button.ClickListener ()\n" +
                "            {\n" +
                "\n" +
                "                public void buttonClick ( ClickEvent event )\n" +
                "                {\n" +
                "                    showMessage ( \"Enchanced Notificaiton\", \"Text of this notification\\ncan contain line breaks. \" +\n" +
                "                            \"Those breaks will be\\nautomatically converted into html &lt;br&gt\\nin order to be displayed correctly. \" +\n" +
                "                            \"Standard showNotification method of the Window class does not do this.\", true );\n" +
                "                }\n" +
                "            } );\n" +
                "        }\n" +
                "\n" +
                "        @Override\n" +
                "        public void enterKeyPressed ()\n" +
                "        {\n" +
                "            showMessage ( \"ENTER Key Pressed\", \"ENTER key was pressed and keyboard event was automatically\\nintercepted for this window.\", true );\n" +
                "        }\n" +
                "\n" +
                "        @Override\n" +
                "        public void escapeKeyPressed ()\n" +
                "        {\n" +
                "            showMessage ( \"ESC Key Pressed\", \"ESC key was pressed and keyboard event was automatically\\nintercepted for this window.\", true );\n" +
                "        }\n" +
                "    }";
    }

    public void performShowCase ()
    {
        TPTWindow w = new MyWindow ();
        w.setWidth ( "800px" );
        w.setHeight ( "600px" );
        TPTApplication.getCurrentApplication ().getMainWindow ().addWindow ( w );
    }

    public Resource getIcon ()
    {
        return new ThemeResource ( "icons/tptwindow.png" );
    }

    public class MyWindow extends TPTWindow
    {

        public MyWindow ()
        {
            super ( TM.get ( "sections.windows.tptwindow.instance") );

            Button b1 = new Button ( TM.get("sections.windows.tptwindow.button") );

            addComponent ( new Label ( TM.get("sections.windows.tptwindow.label") ) );
            addComponent ( new TPTSizer ( null, "40px" ) );
            addComponent ( b1 );
            b1.focus ();

            b1.addListener ( new Button.ClickListener ()
            {

                public void buttonClick ( ClickEvent event )
                {
                    showMessage ( TM.get("sections.windows.tptwindow.notify.title"), TM.get ( "sections.windows.tptwindow.notify.text"), true );
                }
            } );
        }

        @Override
        public void enterKeyPressed ()
        {
            showMessage ( TM.get("sections.windows.tptwindow.enter.title"), TM.get ( "sections.windows.tptwindow.enter.text"), true );
        }

        @Override
        public void escapeKeyPressed ()
        {
            showMessage ( TM.get ( "sections.windows.tptwindow.esc.title" ), TM.get ( "sections.windows.tptwindow.esc.text" ), true );
        }
    }
}
