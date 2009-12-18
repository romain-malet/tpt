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
import eu.livotov.tpt.gui.widgets.TPTSizer;
import eu.livotov.tpt.i18n.TM;
import java.io.Serializable;

/**
 *
 * @author dll
 */
public class SizerDemoItem implements DemoItem, Serializable
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
        return TM.get ( "sizer.title" );
    }

    public String getItemDescription ()
    {
        return TM.get ( "sizer.info" );
    }

    public String getItemSourceCode ()
    {
        return "public class SizerDemoWindow extends Window\n" +
                "    {\n" +
                "\n" +
                "        private VerticalLayout root = new VerticalLayout ();\n" +
                "\n" +
                "        public SizerDemoWindow ()\n" +
                "        {\n" +
                "            super ( \"TPTSizer Demo\" );\n" +
                "            initUI ();\n" +
                "        }\n" +
                "\n" +
                "        private void initUI ()\n" +
                "        {\n" +
                "            setSizeFull ();\n" +
                "            root.setSizeFull ();\n" +
                "            root.setMargin ( true );\n" +
                "            root.setSpacing ( true );\n" +
                "            setContent ( root );\n" +
                "\n" +
                "            addComponent ( new Label ( \"Label 1\") );\n" +
                "            addComponent ( new TPTSizer ( null, \"30px\"));\n" +
                "            addComponent ( new Label ( \"Label 2. Im standing exactly 30 pixels below Label 1, thanks to TPTSizer between us !\"));\n" +
                "        }\n" +
                "    }";
    }

    public void performShowCase ()
    {
        SizerDemoWindow w = new SizerDemoWindow ();
        w.setWidth ( "400px" );
        w.setHeight ( "200px" );
        w.setModal ( true );
        TPTApplication.getCurrentApplication ().getMainWindow ().addWindow ( w );
    }

    public Resource getIcon ()
    {
        return new ThemeResource ( "icons/sizer.png" );
    }

    public class SizerDemoWindow extends Window
    {

        private VerticalLayout root = new VerticalLayout ();

        public SizerDemoWindow ()
        {
            super ( TM.get ( "sizer.caption" ) );
            initUI ();
        }

        private void initUI ()
        {
            setSizeFull ();
            root.setSizeFull ();
            root.setMargin ( true );
            root.setSpacing ( true );
            setContent ( root );

            addComponent ( new Label ( TM.get ( "sizer.label1" ) ) );
            addComponent ( new TPTSizer ( null, "30px" ) );
            addComponent ( new Label ( TM.get ( "sizer.label2" ) ) );
        }
    }
}
