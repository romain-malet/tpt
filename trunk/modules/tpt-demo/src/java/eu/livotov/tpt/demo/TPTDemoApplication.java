/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.livotov.tpt.demo;

import eu.livotov.tpt.TPTApplication;
import eu.livotov.tpt.demo.dialogs.DialogsDemoSection;
import eu.livotov.tpt.demo.i18n.I18NSection;
import eu.livotov.tpt.demo.widgets.WidgetsSection;
import eu.livotov.tpt.demo.windows.WindowsSection;

/**
 *
 * @author dll
 */
public class TPTDemoApplication extends TPTApplication
{

    @Override
    public void applicationInit ()
    {
        setTheme ( "tptdemo" );

        MainWindow mw = new MainWindow ();

        mw.addDemoSection ( new DialogsDemoSection () );
        mw.addDemoSection ( new WidgetsSection () );
        mw.addDemoSection ( new WindowsSection () );
        mw.addDemoSection ( new I18NSection () );


        addWindow ( mw );
    }

    @Override
    public void firstApplicationStartup ()
    {
    }
}
