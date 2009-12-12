/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.livotov.tpt.demo.dialogs;

import com.vaadin.terminal.ExternalResource;
import com.vaadin.terminal.Resource;
import com.vaadin.terminal.ThemeResource;
import eu.livotov.tpt.TPTApplication;
import eu.livotov.tpt.demo.api.DemoItem;
import eu.livotov.tpt.gui.dialogs.DownloadDialog;
import eu.livotov.tpt.i18n.TM;
import java.io.Serializable;

/**
 *
 * @author dll
 */
public class DownloadDialogDemoItem implements DemoItem, Serializable
{

    public DownloadDialogDemoItem ()
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
        return TM.get ( "dd.title" );
    }

    public String getItemDescription ()
    {
        return TM.get ( "dd.info" );
    }

    public Resource getIcon ()
    {
        return new ThemeResource ( "icons/downloaddialog.png" );
    }

    public String getItemSourceCode ()
    {
        return "DownloadDialog dlg = new DownloadDialog ( TPTApplication.getCurrentApplication () );\n" +
                "dlg.showDownloadDialog ( \"Title\", \"Download this !\", new ExternalResource ( \"http://www.sharehost.com/xxx.jpg\" ), \"A gigapixel photo sample.jpg\", new DownloadDialog.DownloadDialogResultListener ()\n" +
                "{\n" +
                "\tpublic void dialogClosed ()\n" +
                "\t{\n" +
                "\t\t// dialog closed.\n" +
                "\t}\n" +
                "} );\n";
    }

    public void performShowCase ()
    {
        DownloadDialog dlg = new DownloadDialog ( TPTApplication.getCurrentApplication () );

        dlg.showDownloadDialog ( TM.get ( "dd.title" ), TM.get("dd.message"), new ThemeResource ( "files/handbook.pdf" ), "handbook.pdf", new DownloadDialog.DownloadDialogResultListener ()
        {

            public void dialogClosed ()
            {
                // dialog closed.
            }
        } );
    }
}
