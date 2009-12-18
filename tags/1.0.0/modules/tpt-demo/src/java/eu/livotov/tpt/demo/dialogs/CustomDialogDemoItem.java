/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.livotov.tpt.demo.dialogs;

import com.vaadin.terminal.Resource;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.InlineDateField;
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
public class CustomDialogDemoItem implements DemoItem, Serializable
{

    public CustomDialogDemoItem ()
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
        return TM.get("cd.title");
    }

    public String getItemDescription ()
    {
        return TM.get("cd.info");
    }

    public String getItemSourceCode ()
    {
        return "final OptionDialog dlg = new OptionDialog ( TPTApplication.getCurrentApplication () );\n" +
                "        dlg.setHeight ( \"300px\");\n" +
                "\n" +
                "        dlg.showCustomDialog ( \"Please choose a day\", new InlineDateField (), new OptionDialog.OptionDialogResultListener ()\n" +
                "        {\n" +
                "\n" +
                "            public void dialogClosed ( OptionKind closeEvent )\n" +
                "            {\n" +
                "                TPTApplication.getCurrentApplication ().getMainWindow ().showNotification ( closeEvent.name () + \" pressed\" );\n" +
                "            }\n" +
                "        }, OptionKind.OK, OptionKind.CANCEL );";

    }

    public void performShowCase ()
    {
        final OptionDialog dlg = new OptionDialog ( TPTApplication.getCurrentApplication () );
        dlg.setHeight ( "300px");

        dlg.showCustomDialog ( TM.get("cd.title"), new InlineDateField (), new OptionDialog.OptionDialogResultListener ()
        {

            public void dialogClosed ( OptionKind closeEvent )
            {
                TPTApplication.getCurrentApplication ().getMainWindow ().showNotification ( closeEvent.name () + TM.get("cd.reply") );
            }
        }, OptionKind.OK, OptionKind.CANCEL );
    }

    public Resource getIcon ()
    {
        return new ThemeResource ( "icons/cquestiondialog.png" );
    }
}
