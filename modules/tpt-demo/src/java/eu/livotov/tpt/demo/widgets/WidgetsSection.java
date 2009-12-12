/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.livotov.tpt.demo.widgets;

import com.vaadin.terminal.Resource;
import com.vaadin.terminal.ThemeResource;
import eu.livotov.tpt.demo.api.DemoSection;
import eu.livotov.tpt.i18n.TM;

/**
 *
 * @author dll
 */
public class WidgetsSection extends DemoSection
{

    public WidgetsSection ()
    {
        super ();
        addDemoItem ( new CaptchaDemoItem () );
        addDemoItem ( new SizerDemoItem () );
        addDemoItem ( new LazyLoaderDemoItem () );
        addDemoItem ( new MultiViewDemoItem () );
    }

    @Override
    public String getSectionTitle ()
    {
        return TM.get ( "sections.widgets.title" );
    }

    @Override
    public String getSectionInformation ()
    {
        return TM.get ( "sections.widgets.info");
    }

    @Override
    public Resource getSectionIcon ()
    {
        return new ThemeResource ( "icons/widgets.png" );
    }
}
