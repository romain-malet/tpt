/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.livotov.tpt.demo.windows;

import com.vaadin.terminal.Resource;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.TextField;
import eu.livotov.tpt.TPTApplication;
import eu.livotov.tpt.demo.api.DemoItem;
import eu.livotov.tpt.gui.windows.TPTHtmlWindow;
import eu.livotov.tpt.i18n.TM;
import java.io.Serializable;

/**
 *
 * @author dll
 */
public class TPTHtmlWindowItem implements DemoItem, Serializable
{

    public TPTHtmlWindowItem ()
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
        return TM.get ( "sections.windows.tpthtmlwindow.title");
    }

    public String getItemDescription ()
    {
        return TM.get("sections.windows.tpthtmlwindow.info");
    }

    public String getItemSourceCode ()
    {
        String java = "public class MyHtmlWindow extends TPTHtmlWindow\n" +
                "    {\n" +
                "\n" +
                "        private TextField search = new TextField ();\n" +
                "\n" +
                "        public MyHtmlWindow ()\n" +
                "        {\n" +
                "            super ( \"TPTHtmlWindow Instance\", \"demo\" );\n" +
                "\n" +
                "            setWidth ( \"1024px\" );\n" +
                "            setHeight ( \"600px\" );\n" +
                "            setStyleName ( \"html\" );\n" +
                "\n" +
                "            addComponent ( search, \"search.field\" );\n" +
                "\n" +
                "            search.focus ();\n" +
                "        }\n" +
                "\n" +
                "        @Override\n" +
                "        public void enterKeyPressed ()\n" +
                "        {\n" +
                "            TPTApplication.getCurrentApplication ().getMainWindow ().showNotification ( \"You searched for: \" + search.getValue () );\n" +
                "        }\n" +
                "\n" +
                "        @Override\n" +
                "        public void escapeKeyPressed ()\n" +
                "        {\n" +
                "            close ();\n" +
                "        }\n" +
                "    }";


        String layout = "<div id=\"logo\">\n" +
                "            <h1><a href=\"#\">Solutions  </a></h1>\n" +
                "            <p><em> template design by <a href=\"http://www.freecsstemplates.org/\">Free CSS Templates</a></em></p>\n" +
                "        </div>\n" +
                "        <hr />\n" +
                "        <!-- end #logo -->\n" +
                "        <div id=\"header\">\n" +
                "            <div id=\"menu\">\n" +
                "                <ul>\n" +
                "                    <li><a href=\"#\" class=\"first\">Home</a></li>\n" +
                "                    <li class=\"current_page_item\"><a href=\"#\">Blog</a></li>\n" +
                "                    <li><a href=\"#\">About</a></li>\n" +
                "                    <li><a href=\"#\">Contact</a></li>\n" +
                "                </ul>\n" +
                "            </div>\n" +
                "            <!-- end #menu -->\n" +
                "            <div id=\"search\">\n" +
                "                Type text to search and press Enter\n" +
                "                <div location=\"search.field\" />\n" +
                "            </div>\n" +
                "            <!-- end #search -->\n" +
                "        </div>\n" +
                "        <!-- end #header -->\n" +
                "        <!-- end #header-wrapper -->\n" +
                "        <div id=\"page\">\n" +
                "            <div id=\"content\">\n" +
                "                <div class=\"post\">\n" +
                "                    <h2 class=\"title\"><a href=\"#\">Welcome to Solutions </a></h2>\n" +
                "                    <p class=\"meta\"><em>Sunday, April 26, 2009 7:27 AM Posted by <a href=\"#\">Someone</a></em></p>\n" +
                "                    <div class=\"entry\">\n" +
                "                        <p>This is <strong>Solutions </strong>, a free, fully standards-compliant CSS template designed by <a href=\"http://www.freecsstemplates.org/\">Free CSS Templates</a>, released for free under the <a href=\"http://creativecommons.org/licenses/by/2.5/\">Creative Commons Attribution 2.5</a> license.  You're free to use this template for anything as long as you link back to <a href=\"http://www.freecsstemplates.org/\">my site</a>. Enjoy :)</p>\n" +
                "                        <p>Sed lacus. Donec lectus. Nullam pretium nibh ut turpis. Nam bibendum. In nulla tortor, elementum ipsum. Proin imperdiet est. Phasellus dapibus semper urna. Pellentesque ornare, orci in felis. </p>\n" +
                "                        <div><a href=\"#\" class=\"links\">Full Story</a></div>\n" +
                "                    </div>\n" +
                "                </div>\n" +
                "            </div><!-- end #content -->\n" +
                "            <div id=\"sidebar\">\n" +
                "                <ul>\n" +
                "                    <li>\n" +
                "                        <h2>Aliquam tempus</h2>\n" +
                "                        <p>Mauris vitae nisl nec metus placerat perdiet est. Phasellus dapibus semper urna. Pellentesque ornare, orci in consectetuer hendrerit, volutpat.</p>\n" +
                "                    </li>\n" +
                "                </ul>\n" +
                "            </div>\n" +
                "            <!-- end #sidebar -->\n" +
                "            <div style=\"clear: both;\">&nbsp;</div>\n" +
                "        </div>\n" +
                "        <!-- end #page -->\n" +
                "        <div id=\"footer\">\n" +
                "            <p>Copyright (c) 2008 Sitename.com. All rights reserved. Design by <a href=\"http://www.freecsstemplates.org/\">Free CSS Templates</a>.</p>\n" +
                "        </div>\n" +
                "        <!-- end #footer -->";


        return "// Java code: \n\n" + java + "\n\n\n//Custom layout file demo.html\n\n" + layout;
    }

    public void performShowCase ()
    {
        TPTHtmlWindow w = new MyHtmlWindow ();
        TPTApplication.getCurrentApplication ().getMainWindow ().addWindow ( w );
    }

    public Resource getIcon ()
    {
        return new ThemeResource ( "icons/tpthtmlwindow.png" );
    }

    public class MyHtmlWindow extends TPTHtmlWindow
    {

        private TextField search = new TextField ();

        public MyHtmlWindow ()
        {
            super ( TM.get ( "sections.windows.tpthtmlwindow.caption"), "demo" );

            setWidth ( "1024px" );
            setHeight ( "600px" );
            setStyleName ( "html" );

            addComponent ( search, "search.field" );

            search.focus ();
        }

        @Override
        public void enterKeyPressed ()
        {
            TPTApplication.getCurrentApplication ().getMainWindow ().showNotification ( TM.get ( "sections.windows.tpthtmlwindow.search") + search.getValue () );
        }

        @Override
        public void escapeKeyPressed ()
        {
            close ();
        }
    }
}
