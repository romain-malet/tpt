/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.livotov.tpt.demo.api;

import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import de.java2html.converter.JavaSource2HTMLConverter;
import de.java2html.javasource.JavaSource;
import de.java2html.javasource.JavaSourceParser;
import de.java2html.options.JavaSourceConversionOptions;
import eu.livotov.tpt.TPTApplication;
import eu.livotov.tpt.gui.windows.TPTWindow;
import eu.livotov.tpt.i18n.TM;
import java.io.StringReader;
import java.io.StringWriter;

/**
 *
 * @author dll
 */
public class JavaSourcecodeViewer extends TPTWindow
{

    private Panel sourceViewer = new Panel ();
    private Label content = new Label ();

    public JavaSourcecodeViewer ( String src )
    {
        super ( TM.get("java.src.title") );
        initUI ();
        setJavaSnippet ( src );

        setModal ( true );
        setWidth ( "760px" );
        setHeight ( "500px" );
        TPTApplication.getCurrentApplication ().getMainWindow ().addWindow ( this );
    }

    private void initUI ()
    {
        setSizeFull ();
        content.setContentMode ( Label.CONTENT_XHTML );
        sourceViewer.setStyleName ( Panel.STYLE_LIGHT );
        sourceViewer.addComponent ( content );
        sourceViewer.setWidth ( null);
        sourceViewer.setHeight ( "100%");
        setContent ( sourceViewer );
    }

    public void setJavaSnippet ( String src )
    {
        try
        {
            StringReader reader = new StringReader ( src );
            JavaSource java = new JavaSourceParser ().parse ( reader );
            JavaSource2HTMLConverter converter = new JavaSource2HTMLConverter ();

            JavaSourceConversionOptions options = JavaSourceConversionOptions.getDefault ();
            options.setShowLineNumbers ( false );
            options.setShowTableBorder ( false );

            StringWriter writer = new StringWriter ();
            converter.convert ( java, options, writer );

            content.setValue ( writer.toString () );
            writer.close ();
        }
        catch ( Throwable err )
        {
            content.setCaption ( "Error: " + err.getMessage () );
            err.printStackTrace ();
        }
    }
}
