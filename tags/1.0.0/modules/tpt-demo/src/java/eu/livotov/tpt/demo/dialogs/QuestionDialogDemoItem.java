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
import eu.livotov.tpt.gui.dialogs.OptionDialog;
import eu.livotov.tpt.gui.dialogs.OptionKind;
import eu.livotov.tpt.i18n.TM;
import java.io.Serializable;

/**
 *
 * @author dll
 */
public class QuestionDialogDemoItem implements DemoItem, Serializable
{

    public QuestionDialogDemoItem ()
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
        return TM.get ( "qd.title" );
    }

    public String getItemDescription ()
    {
        return TM.get ( "qd.info" );
    }

    public Resource getIcon ()
    {
        return new ThemeResource ( "icons/questiondialog.png" );
    }

    public String getItemSourceCode ()
    {
        return "" +
                "final OptionDialog dlg = new OptionDialog ( TPTApplication.getCurrentApplication () );\n" +
                "dlg.showConfirmationDialog ( \"Attention please !\", \"Are you sure to exit ?\", new OptionDialog.OptionDialogResultListener () {\n" +
                "\tpublic void dialogClosed ( OptionKind closeEvent )\n" +
                "\t{\n" +
                "\t\tTPTApplication.getCurrentApplication ().getMainWindow ().showNotification ( \"TYou answered: \" + closeEvent.name ());\n" +
                "\t}\n" +
                "});";

    }

    public void performShowCase ()
    {
        final OptionDialog dlg = new OptionDialog ( TPTApplication.getCurrentApplication () );

        dlg.showQuestionDialog ( TM.get("qd.caption"), TM.get("qd.text"), new OptionDialog.OptionDialogResultListener ()
        {

            public void dialogClosed ( OptionKind closeEvent )
            {
                TPTApplication.getCurrentApplication ().getMainWindow ().showNotification ( TM.get ( "qd.reply") + closeEvent.name () );
            }
        } );
    }
}
