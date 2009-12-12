/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.livotov.tpt.demo.i18n;

import com.vaadin.terminal.Resource;
import com.vaadin.terminal.ThemeResource;
import eu.livotov.tpt.demo.api.DemoSection;
import eu.livotov.tpt.i18n.TM;

/**
 *
 * @author dll
 */
public class I18NSection extends DemoSection
{

    public I18NSection ()
    {
        super ();
    }

    @Override
    public String getSectionTitle ()
    {
        return TM.get ( "i18n.title" );
    }

    @Override
    public String getSectionInformation ()
    {
        return TM.get ( "i18n.info" );
    }

    @Override
    public Resource getSectionIcon ()
    {
        return new ThemeResource ( "icons/i18n.png" );
    }
}
