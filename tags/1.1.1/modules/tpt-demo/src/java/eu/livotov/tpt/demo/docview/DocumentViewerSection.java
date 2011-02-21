/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.livotov.tpt.demo.docview;

import com.vaadin.ui.Upload.FinishedEvent;
import com.vaadin.ui.Upload.StartedEvent;
import com.vaadin.ui.Upload.SucceededEvent;
import com.vaadin.terminal.Resource;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.terminal.gwt.server.WebApplicationContext;
import com.vaadin.ui.ProgressIndicator;
import com.vaadin.ui.Upload;
import eu.livotov.tpt.TPTApplication;
import eu.livotov.tpt.demo.api.DemoSection;
import eu.livotov.tpt.gui.vdv.DocumentViewer;
import eu.livotov.tpt.gui.vdv.formats.PdfDocument;
import eu.livotov.tpt.i18n.TM;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author dll
 */
public class DocumentViewerSection extends DemoSection implements Upload.Receiver
{
    Upload uploader = new Upload(TM.get("sections.dv.upload.caption"), this);
    DocumentViewer viewer = new DocumentViewer();
    ProgressIndicator pi = new ProgressIndicator();
    File fileToView = null;


    public DocumentViewerSection ()
    {
        super ();
        initUI();
        initActions();
        displayEmbeddedDocument();
    }

    @Override
    public String getSectionTitle ()
    {
        return TM.get ( "sections.dv.title");
    }

    @Override
    public String getSectionInformation ()
    {
        return TM.get("sections.dv.info");
    }

    @Override
    public Resource getSectionIcon ()
    {
        return new ThemeResource ( "icons/pdf.png" );
    }

    private void initUI()
    {
        removeComponent( demoItemsContent );

        pi.setIndeterminate(true);
        pi.setVisible(false);
        pi.setPollingInterval(1000);

        uploader.setCaption("");
        uploader.setButtonCaption(TM.get("sections.dv.upload.caption"));
        uploader.setStyleName("small");
        uploader.setImmediate(true);

        setSpacing(true);
        addComponent(uploader);
        addComponent(pi);
        addComponent(viewer);

        viewer.setSizeFull();
        setExpandRatio(viewer, 1.0f);
    }

    private void initActions()
    {
        uploader.addListener( new Upload.SucceededListener()
        {
            public void uploadSucceeded(SucceededEvent event)
            {
                setDocument(fileToView);
            }
        });

        uploader.addListener( new Upload.StartedListener()
        {
            public void uploadStarted(StartedEvent event)
            {
                uploader.setVisible(false);
                pi.setVisible(true);
            }
        });

        uploader.addListener(new Upload.FinishedListener()
        {
            public void uploadFinished(FinishedEvent event)
            {
                uploader.setVisible(true);
                pi.setVisible(false);
            }
        });
    }

    public OutputStream receiveUpload(String filename, String MIMEType)
    {
        try 
        {
            fileToView = new File(System.getProperty("java.io.tmpdir") + File.separator + filename);
            return new FileOutputStream(fileToView);
        } catch (FileNotFoundException ex)
        {
            Logger.getLogger(DocumentViewerSection.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public void setDocument(File pdf)
    {
        if ( pdf != null)
        {
            viewer.closeDocument();
            viewer.loadDocument( new PdfDocument(pdf));
        }
    }

    private void displayEmbeddedDocument()
    {
        final String doc = ((WebApplicationContext)TPTApplication.getCurrentApplication().getContext()).getHttpSession().getServletContext().getRealPath("/VAADIN/themes/tptdemo/files/handbook.pdf");
        setDocument( new File(doc));
    }
}
