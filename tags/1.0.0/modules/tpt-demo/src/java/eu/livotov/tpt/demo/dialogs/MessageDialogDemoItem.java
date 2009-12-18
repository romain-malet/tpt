/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.livotov.tpt.demo.dialogs;

import com.vaadin.terminal.Resource;
import com.vaadin.terminal.ThemeResource;
import eu.livotov.tpt.TPTApplication;
import eu.livotov.tpt.demo.api.DemoItem;
import eu.livotov.tpt.gui.dialogs.OptionDialog;
import eu.livotov.tpt.gui.dialogs.OptionKind;
import eu.livotov.tpt.i18n.TM;
import java.io.Serializable;

/**
 *
 * @author dll
 */
public class MessageDialogDemoItem implements DemoItem, Serializable
{

    public MessageDialogDemoItem ()
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
        return TM.get("md.title");
    }

    public String getItemDescription ()
    {
        return TM.get ( "md.info");
    }

    public Resource getIcon ()
    {
        return new ThemeResource ( "icons/messagedialog.png" );
    }

    public String getItemSourceCode ()
    {
        return "" +
                "final OptionDialog dlg = new OptionDialog ( TPTApplication.getCurrentApplication () );\n" +
                "dlg.showMessageDialog ( \"Hello\", \"That's a deal. Period.\", new OptionDialog.OptionDialogResultListener () {\n" +
                "\tpublic void dialogClosed ( OptionKind closeEvent )\n" +
                "\t{\n" +
                "\t\tTPTApplication.getCurrentApplication ().getMainWindow ().showNotification ( \"That's a deal !\");\n" +
                "\t}\n" +
                "});";

    }

    public void performShowCase ()
    {
        final OptionDialog dlg = new OptionDialog ( TPTApplication.getCurrentApplication () );

        dlg.showMessageDialog ( TM.get("md.title"), TM.get("md.message"), new OptionDialog.OptionDialogResultListener ()
        {

            public void dialogClosed ( OptionKind closeEvent )
            {
                TPTApplication.getCurrentApplication ().getMainWindow ().showNotification ( TM.get("md.reply") );
            }
        } );
    }
}
