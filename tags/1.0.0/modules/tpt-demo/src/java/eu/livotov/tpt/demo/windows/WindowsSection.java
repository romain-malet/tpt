/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.livotov.tpt.demo.windows;

import com.vaadin.terminal.Resource;
import com.vaadin.terminal.ThemeResource;
import eu.livotov.tpt.demo.api.DemoSection;
import eu.livotov.tpt.i18n.TM;

/**
 *
 * @author dll
 */
public class WindowsSection extends DemoSection
{

    public WindowsSection ()
    {
        super ();
        addDemoItem ( new TPTWindowItem () );
        addDemoItem ( new TPTHtmlWindowItem () );
    }

    @Override
    public String getSectionTitle ()
    {
        return TM.get ( "sections.windows.title");
    }

    @Override
    public String getSectionInformation ()
    {
        return TM.get("sections.windows.info");
    }

    @Override
    public Resource getSectionIcon ()
    {
        return new ThemeResource ( "icons/windows.png" );
    }
}
