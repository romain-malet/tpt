/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.livotov.tpt.demo.widgets;

import com.vaadin.terminal.Resource;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.DateField;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.InlineDateField;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.Upload;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import eu.livotov.tpt.TPTApplication;
import eu.livotov.tpt.demo.api.DemoItem;
import eu.livotov.tpt.gui.widgets.TPTCaptcha;
import eu.livotov.tpt.gui.widgets.TPTMultiView;
import eu.livotov.tpt.gui.widgets.TPTMultiView.TPTView;
import eu.livotov.tpt.gui.widgets.TPTSizer;
import eu.livotov.tpt.i18n.TM;
import java.io.Serializable;

/**
 *
 * @author dll
 */
public class MultiViewDemoItem implements DemoItem, Serializable
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
        return TM.get ( "sections.widgets.view.title" );
    }

    public String getItemDescription ()
    {
        return TM.get ( "sections.widgets.view.info" );
    }

    public String getItemSourceCode ()
    {
        return "public class SizerDemoWindow extends Window\n" +
                "    {\n" +
                "\n" +
                "        private VerticalLayout root = new VerticalLayout ();\n" +
                "        private TPTMultiView controller = new TPTMultiView ();\n" +
                "\n" +
                "        public SizerDemoWindow ()\n" +
                "        {\n" +
                "            super ( \"TPTMultiView Demo\" );\n" +
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
                "            controller.addView ( \"view1\", new ViewOne () );\n" +
                "            controller.addView ( \"view2\", new ViewTwo () );\n" +
                "            controller.addView ( \"view3\", new ViewThree () );\n" +
                "\n" +
                "            controller.setSizeFull ();\n" +
                "            root.addComponent ( controller );\n" +
                "\n" +
                "            HorizontalLayout h = new HorizontalLayout ();\n" +
                "            h.setWidth ( \"100%\" );\n" +
                "\n" +
                "            Button b1 = new Button ( \"Go to view 1\" );\n" +
                "            Button b2 = new Button ( \"Go to view 2\" );\n" +
                "            Button b3 = new Button ( \"Go to view 3\" );\n" +
                "\n" +
                "            b1.setStyleName ( Button.STYLE_LINK );\n" +
                "            b2.setStyleName ( Button.STYLE_LINK );\n" +
                "            b3.setStyleName ( Button.STYLE_LINK );\n" +
                "\n" +
                "            b1.addListener ( new Button.ClickListener ()\n" +
                "            {\n" +
                "\n" +
                "                public void buttonClick ( ClickEvent event )\n" +
                "                {\n" +
                "                    controller.switchView ( \"view1\" );\n" +
                "                }\n" +
                "            } );\n" +
                "\n" +
                "            b2.addListener ( new Button.ClickListener ()\n" +
                "            {\n" +
                "\n" +
                "                public void buttonClick ( ClickEvent event )\n" +
                "                {\n" +
                "                    controller.switchView ( \"view2\" );\n" +
                "                }\n" +
                "            } );\n" +
                "\n" +
                "            b3.addListener ( new Button.ClickListener ()\n" +
                "            {\n" +
                "\n" +
                "                public void buttonClick ( ClickEvent event )\n" +
                "                {\n" +
                "                    controller.switchView ( \"view3\" );\n" +
                "                }\n" +
                "            } );\n" +
                "\n" +
                "            h.addComponent ( b1 );\n" +
                "            h.addComponent ( b2 );\n" +
                "            h.addComponent ( b3 );\n" +
                "            h.addComponent ( new TPTSizer ( \"100%\", null ) );\n" +
                "\n" +
                "            root.addComponent ( h );\n" +
                "            root.setExpandRatio ( controller, 1.0f );\n" +
                "        }\n" +
                "    }\n" +
                "\n" +
                "    public class ViewOne extends VerticalLayout implements TPTView\n" +
                "    {\n" +
                "\n" +
                "        public ViewOne ()\n" +
                "        {\n" +
                "            super ();\n" +
                "            setSizeFull ();\n" +
                "            setMargin ( true );\n" +
                "            setSpacing ( true );\n" +
                "            addComponent ( new Label ( \"View 1\" ) );\n" +
                "            addComponent ( new InlineDateField () );\n" +
                "        }\n" +
                "\n" +
                "        public void viewActivated ( String previousViewId, String parameters )\n" +
                "        {\n" +
                "            TPTApplication.getCurrentApplication ().getMainWindow ().showNotification ( previousViewId + \" => view1\" );\n" +
                "        }\n" +
                "\n" +
                "        public void viewDeactivated ( String newViewId )\n" +
                "        {\n" +
                "        }\n" +
                "\n" +
                "        public void viewAttached ()\n" +
                "        {\n" +
                "        }\n" +
                "\n" +
                "        public void viewRemoved ()\n" +
                "        {\n" +
                "        }\n" +
                "    }\n" +
                "\n" +
                "    public class ViewTwo extends VerticalLayout implements TPTView\n" +
                "    {\n" +
                "\n" +
                "        public ViewTwo ()\n" +
                "        {\n" +
                "            super ();\n" +
                "            setSizeFull ();\n" +
                "            setMargin ( true );\n" +
                "            setSpacing ( true );\n" +
                "            addComponent ( new Label ( \"View 2\" ) );\n" +
                "            addComponent ( new Upload (\"Upload file #1\", null) );\n" +
                "            addComponent ( new Upload ( \"Upload file #2\", null ) );\n" +
                "            addComponent ( new Upload ( \"Upload file #3\", null ) );\n" +
                "        }\n" +
                "\n" +
                "        public void viewActivated ( String previousViewId, String parameters )\n" +
                "        {\n" +
                "            TPTApplication.getCurrentApplication ().getMainWindow ().showNotification ( previousViewId + \" => view2\" );\n" +
                "        }\n" +
                "\n" +
                "        public void viewDeactivated ( String newViewId )\n" +
                "        {\n" +
                "        }\n" +
                "\n" +
                "        public void viewAttached ()\n" +
                "        {\n" +
                "        }\n" +
                "\n" +
                "        public void viewRemoved ()\n" +
                "        {\n" +
                "        }\n" +
                "    }\n" +
                "\n" +
                "    public class ViewThree extends VerticalLayout implements TPTView\n" +
                "    {\n" +
                "\n" +
                "        public ViewThree ()\n" +
                "        {\n" +
                "            super ();\n" +
                "            setSizeFull ();\n" +
                "            setMargin ( true );\n" +
                "            setSpacing ( true );\n" +
                "            addComponent ( new Label ( \"View 3\" ) );\n" +
                "            addComponent ( new DateField () );\n" +
                "            addComponent ( new TPTCaptcha () );\n" +
                "        }\n" +
                "\n" +
                "        public void viewActivated ( String previousViewId, String parameters )\n" +
                "        {\n" +
                "            TPTApplication.getCurrentApplication ().getMainWindow ().showNotification ( previousViewId + \" => view3\" );\n" +
                "        }\n" +
                "\n" +
                "        public void viewDeactivated ( String newViewId )\n" +
                "        {\n" +
                "        }\n" +
                "\n" +
                "        public void viewAttached ()\n" +
                "        {\n" +
                "        }\n" +
                "\n" +
                "        public void viewRemoved ()\n" +
                "        {\n" +
                "        }\n" +
                "    }";
    }

    public void performShowCase ()
    {
        SizerDemoWindow w = new SizerDemoWindow ();
        w.setWidth ( "400px" );
        w.setHeight ( "500px" );
        w.setModal ( true );
        TPTApplication.getCurrentApplication ().getMainWindow ().addWindow ( w );
    }

    public Resource getIcon ()
    {
        return new ThemeResource ( "icons/multiview.png" );
    }

    public class SizerDemoWindow extends Window
    {

        private VerticalLayout root = new VerticalLayout ();
        private TPTMultiView controller = new TPTMultiView ();

        public SizerDemoWindow ()
        {
            super ( TM.get ( "multiview.demo.title" ) );
            initUI ();
        }

        private void initUI ()
        {
            setSizeFull ();
            root.setSizeFull ();
            root.setMargin ( true );
            root.setSpacing ( true );
            setContent ( root );

            controller.addView ( "view1", new ViewOne () );
            controller.addView ( "view2", new ViewTwo () );
            controller.addView ( "view3", new ViewThree() );

            controller.setSizeFull ();
            root.addComponent ( controller );

            HorizontalLayout h = new HorizontalLayout ();
            h.setWidth ( "100%" );

            Button b1 = new Button ( TM.get ( "go.to.view.1" ) );
            Button b2 = new Button ( TM.get ( "go.to.view.2" ) );
            Button b3 = new Button ( TM.get ( "go.to.view.3" ) );

            b1.setStyleName ( Button.STYLE_LINK );
            b2.setStyleName ( Button.STYLE_LINK );
            b3.setStyleName ( Button.STYLE_LINK );

            b1.addListener ( new Button.ClickListener ()
            {

                public void buttonClick ( ClickEvent event )
                {
                    controller.switchView ( "view1" );
                }
            } );

            b2.addListener ( new Button.ClickListener ()
            {

                public void buttonClick ( ClickEvent event )
                {
                    controller.switchView ( "view2" );
                }
            } );

            b3.addListener ( new Button.ClickListener ()
            {

                public void buttonClick ( ClickEvent event )
                {
                    controller.switchView ( "view3" );
                }
            } );

            h.addComponent ( b1 );
            h.addComponent ( b2 );
            h.addComponent ( b3 );
            h.addComponent ( new TPTSizer ( "100%", null ) );

            root.addComponent ( h );
            root.setExpandRatio ( controller, 1.0f );
        }
    }

    public class ViewOne extends VerticalLayout implements TPTView
    {

        public ViewOne ()
        {
            super ();
            setSizeFull ();
            setMargin ( true );
            setSpacing ( true );
            addComponent ( new Label ( TM.get ( "view1.title") ) );
            addComponent ( new InlineDateField () );
        }

        public void viewActivated ( String previousViewId, String parameters )
        {
            TPTApplication.getCurrentApplication ().getMainWindow ().showNotification ( previousViewId + " => view1" );
        }

        public void viewDeactivated ( String newViewId )
        {
        }

        public void viewAttached ()
        {
        }

        public void viewRemoved ()
        {
        }
    }

    public class ViewTwo extends VerticalLayout implements TPTView
    {

        public ViewTwo ()
        {
            super ();
            setSizeFull ();
            setMargin ( true );
            setSpacing ( true );
            addComponent ( new Label ( TM.get ( "view2.title" ) ) );
            addComponent ( new Upload ( TM.get ( "view.upload1.title"), null ) );
            addComponent ( new Upload ( TM.get ( "view.upload2.title" ), null ) );
            addComponent ( new Upload ( TM.get ( "view.upload3.title" ), null ) );
        }

        public void viewActivated ( String previousViewId, String parameters )
        {
            TPTApplication.getCurrentApplication ().getMainWindow ().showNotification ( previousViewId + " => view2" );
        }

        public void viewDeactivated ( String newViewId )
        {
        }

        public void viewAttached ()
        {
        }

        public void viewRemoved ()
        {
        }
    }

    public class ViewThree extends VerticalLayout implements TPTView
    {

        public ViewThree ()
        {
            super ();
            setSizeFull ();
            setMargin ( true );
            setSpacing ( true );
            addComponent ( new Label ( TM.get ( "view3.title" ) ) );
            addComponent ( new DateField () );
            addComponent ( new TPTCaptcha () );
        }

        public void viewActivated ( String previousViewId, String parameters )
        {
            TPTApplication.getCurrentApplication ().getMainWindow ().showNotification ( previousViewId + " => view3" );
        }

        public void viewDeactivated ( String newViewId )
        {
        }

        public void viewAttached ()
        {
        }

        public void viewRemoved ()
        {
        }
    }
}
