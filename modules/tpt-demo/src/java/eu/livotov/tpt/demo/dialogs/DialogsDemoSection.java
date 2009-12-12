/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.livotov.tpt.demo.dialogs;

import com.vaadin.terminal.Resource;
import com.vaadin.terminal.ThemeResource;
import eu.livotov.tpt.demo.api.DemoSection;
import eu.livotov.tpt.i18n.TM;

/**
 *
 * @author dll
 */
public class DialogsDemoSection extends DemoSection
{

    public DialogsDemoSection ()
    {
        super ();
        addDemoItem ( new MessageDialogDemoItem () );
        addDemoItem ( new ConfirmationDialogDemoItem () );
        addDemoItem ( new QuestionDialogDemoItem () );
        addDemoItem ( new CancellableQuestionDialogDemoItem () );
        addDemoItem ( new DownloadDialogDemoItem () );
        addDemoItem ( new CustomDialogDemoItem () );
    }

    @Override
    public String getSectionTitle ()
    {
        return TM.get("sections.dialogs.title");
    }

    @Override
    public String getSectionInformation ()
    {
        return TM.get("sections.dialogs.info");
    }

    @Override
    public Resource getSectionIcon ()
    {
        return new ThemeResource ( "icons/dialogs.png" );
    }
}
