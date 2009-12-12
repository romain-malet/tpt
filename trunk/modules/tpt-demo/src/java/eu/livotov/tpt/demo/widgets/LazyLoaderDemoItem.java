/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.livotov.tpt.demo.widgets;

import com.vaadin.terminal.Resource;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Component;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import eu.livotov.tpt.TPTApplication;
import eu.livotov.tpt.demo.api.DemoItem;
import eu.livotov.tpt.gui.widgets.TPTLazyLoadingLayout;
import eu.livotov.tpt.gui.widgets.TPTLazyLoadingLayout.LazyLoader;
import eu.livotov.tpt.i18n.TM;
import java.io.Serializable;

/**
 *
 * @author dll
 */
public class LazyLoaderDemoItem implements DemoItem, Serializable
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
        return TM.get ( "lazyloader.title" );
    }

    public String getItemDescription ()
    {
        return TM.get ( "lazyloader.info" );
    }

    public String getItemSourceCode ()
    {
        return "// First, implement a LazyLoader interface in your UI element or panel and move all lenghty initialization thigs into\n" +
                "// lazyLoad() method. Then wrap this UI element into a TPTLazyLoadingLayout when adding to a window:\n\n" +
                "addComponent ( new TPTLazyLoadingLayout ( new LongLoadingPanel(), true) );\n\n" +
                "public class LongLoadingPanel extends VerticalLayout implements LazyLoader\n" +
                "    {\n" +
                "\n" +
                "        public LongLoadingPanel ()\n" +
                "        {\n" +
                "            super ();\n" +
                "            setMargin ( true );\n" +
                "            setSpacing ( true );\n" +
                "            setWidth ( \"100%\" );\n" +
                "            setHeight ( null );\n" +
                "        }\n" +
                "\n" +
                "        public String getLazyLoadingMessage ()\n" +
                "        {\n" +
                "            return \"Loading account history from the database, please wait ...\";\n" +
                "        }\n" +
                "\n" +
                "        public Component lazyLoad ( TPTLazyLoadingLayout layout )\n" +
                "        {\n" +
                "            addComponent ( new Label ( \"01.01.2008 Account created\" ) );\n" +
                "\n" +
                "            try\n" +
                "            {\n" +
                "                Thread.sleep ( 500L );\n" +
                "            }\n" +
                "            catch ( InterruptedException ex )\n" +
                "            {\n" +
                "            }\n" +
                "\n" +
                "            addComponent ( new Label ( \"02.01.2008 + EUR 5000.00 credit\" ) );\n" +
                "\n" +
                "            try\n" +
                "            {\n" +
                "                Thread.sleep ( 1500L );\n" +
                "            }\n" +
                "            catch ( InterruptedException ex )\n" +
                "            {\n" +
                "            }\n" +
                "\n" +
                "            addComponent ( new Label ( \"03.01.2008 - EUR 150.00 Duty free payment\" ) );\n" +
                "\n" +
                "            try\n" +
                "            {\n" +
                "                Thread.sleep ( 500L );\n" +
                "            }\n" +
                "            catch ( InterruptedException ex )\n" +
                "            {\n" +
                "            }\n" +
                "\n" +
                "            addComponent ( new Label ( \"12.01.2008 - EUR 500.00 Cash advanve\" ) );\n" +
                "\n" +
                "            try\n" +
                "            {\n" +
                "                Thread.sleep ( 1500L );\n" +
                "            }\n" +
                "            catch ( InterruptedException ex )\n" +
                "            {\n" +
                "            }\n" +
                "\n" +
                "            addComponent ( new Label ( \"28.01.2008 - EUR 255.12 Tiger restaurant\" ) );\n" +
                "\n" +
                "            try\n" +
                "            {\n" +
                "                Thread.sleep ( 1500L );\n" +
                "            }\n" +
                "            catch ( InterruptedException ex )\n" +
                "            {\n" +
                "            }\n" +
                "\n" +
                "            return this;\n" +
                "        }\n" +
                "    }";
    }

    public void performShowCase ()
    {
        LazyDemoWindow w = new LazyDemoWindow ();
        w.setWidth ( "400px" );
        w.setHeight ( "400px" );
        w.setModal ( true );
        TPTApplication.getCurrentApplication ().getMainWindow ().addWindow ( w );
    }

    public Resource getIcon ()
    {
        return new ThemeResource ( "icons/lazyloader.png" );
    }

    public class LazyDemoWindow extends Window
    {

        private VerticalLayout root = new VerticalLayout ();

        public LazyDemoWindow ()
        {
            super ( TM.get("lazyloader.caption") );
            initUI ();
        }

        private void initUI ()
        {
            setSizeFull ();
            root.setSizeFull ();
            root.setMargin ( true );
            root.setSpacing ( true );
            setContent ( root );

            root.addComponent ( new TPTLazyLoadingLayout ( new LongLoadingPanel (), true ) );
        }
    }

    public class LongLoadingPanel extends VerticalLayout implements LazyLoader
    {

        public LongLoadingPanel ()
        {
            super ();
            setMargin ( true );
            setSpacing ( true );
            setWidth ( "100%" );
            setHeight ( null );
        }

        public String getLazyLoadingMessage ()
        {
            return TM.get("lazyloader.progress");
        }

        public Component lazyLoad ( TPTLazyLoadingLayout layout )
        {
            addComponent ( new Label ( TM.get ( "lazyloader.sample1") ) );

            try
            {
                Thread.sleep ( 500L );
            }
            catch ( InterruptedException ex )
            {
            }

            addComponent ( new Label ( TM.get ( "lazyloader.sample2" ) ) );

            try
            {
                Thread.sleep ( 1500L );
            }
            catch ( InterruptedException ex )
            {
            }

            addComponent ( new Label ( TM.get ( "lazyloader.sample3" ) ) );

            try
            {
                Thread.sleep ( 500L );
            }
            catch ( InterruptedException ex )
            {
            }

            addComponent ( new Label ( TM.get ( "lazyloader.sample4" ) ) );

            try
            {
                Thread.sleep ( 1500L );
            }
            catch ( InterruptedException ex )
            {
            }

            addComponent ( new Label ( TM.get ( "lazyloader.sample5" ) ) );

            try
            {
                Thread.sleep ( 1500L );
            }
            catch ( InterruptedException ex )
            {
            }

            return this;
        }
    }
}
