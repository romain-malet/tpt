/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package eu.livotov.tpt.gui.vdv.formats;

import eu.livotov.tpt.gui.vdv.api.DocumentRasterProvider;
import eu.livotov.tpt.gui.vdv.api.PageNumber;
import eu.livotov.tpt.gui.vdv.api.RasterizedPageProperties;
import eu.livotov.tpt.gui.vdv.core.Utils;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import javax.imageio.ImageIO;
import org.icepdf.core.exceptions.PDFException;
import org.icepdf.core.exceptions.PDFSecurityException;
import org.icepdf.core.pobjects.Document;
import org.icepdf.core.pobjects.PDimension;
import org.icepdf.core.pobjects.Page;
import org.icepdf.core.util.GraphicsRenderingHints;

/**
 *
 * @author dlivotov
 */
public class PdfDocument implements DocumentRasterProvider
{
    private File documentFile = null;
    private String documentId = "";
    private Map<String,RasterizedPageProperties> pagePropertiesMap = new ConcurrentHashMap<String,RasterizedPageProperties>();

    public final static float ROTATION_STEP = 90.0f;

    private Document pdfDocument;
    private float zoom = 1.0f;
    private int pagesCount = 0;
    private PDimension pageSize;
    private File fileCacheFolder;

    private boolean icePdfPresent = false;


    public PdfDocument( File pdf)
    {
        icePdfPresent = false;

        try
        {
            Class.forName("org.icepdf.core.pobjects.Document");
            Package.getPackage("org.icepdf.core");
            icePdfPresent = true;
        } catch ( Throwable err)
        {
            icePdfPresent = false;
            System.err.println("Warning: no IcePDF files are present on a classpath, you'll be unable to display PDF documents in DocumentViewer. Please download and put the IcePDF core .jar files to your application classpath. You can download latest version from http://www.icepdf.org or just take the bundled files from tpt-demo.war/WEB-INF/lib folder.");
        }

        if ( pdf == null || !pdf.getName().toLowerCase().endsWith(".pdf"))
        {
            //throw new IllegalArgumentException("Document must be a valid PDF file");
        }

        documentFile = pdf;
        documentId = pdf.getAbsolutePath();

        try
        {
            openDocument();
        } catch ( Throwable err)
        {
            throw new RuntimeException(err.getMessage(), err);
        }
    }

    public String getDocumentId()
    {
        return documentId;
    }

    public int getPagesCount()
    {
        return pagesCount;
    }

    public boolean hasFacingPage()
    {
        return false;
    }

    public InputStream getPageData(int pageNumber, float zoomLevel, int rotationLevel)
    {
        final int actualPageNumber = pageNumber == PageNumber.LAST_PAGE ? getPagesCount() : pageNumber;
        return getPageStream(actualPageNumber, zoomLevel > 0.1 ? zoomLevel:0.1f);
    }

    public RasterizedPageProperties getPageProperties(int pageNumber, float zoomLevel, int rotationLevel)
    {
        final int actualPageNumber = pageNumber == PageNumber.LAST_PAGE ? getPagesCount() : pageNumber;
        final float actualZoom = (zoomLevel > 0.1 ? zoomLevel:0.1f);
        final String key = "" + actualZoom;

        if ( !pagePropertiesMap.containsKey( key ))
        {
           final Dimension dm = getPageSize( pageNumber, actualZoom);
           RasterizedPageProperties pp = new RasterizedPageProperties((int)dm.getWidth(), (int)dm.getHeight());
           pagePropertiesMap.put(key, pp);
        }

        return pagePropertiesMap.get(key);
    }

    public void closeDocument()
    {
        if ( icePdfPresent && pdfDocument != null )
        {
            pdfDocument.dispose ();
        }

        documentFile = null;
    }

    protected Dimension getPageSize( int page, float zoom)
    {
        if ( icePdfPresent)
        {
            Image img = getPageImage ( page, zoom);
            return new Dimension ( img.getWidth ( null ), img.getHeight ( null ) );
        } else
        {
            return new Dimension( 480,640 );
        }
    }

    protected void openDocument () throws PDFException, IOException, PDFSecurityException
    {
        if ( icePdfPresent)
        {
            if ( pdfDocument != null )
            {
                closeDocument ();
            }

            fileCacheFolder = new File ( System.getProperty ( "java.io.tmpdir" ) + File.separator + "pdf" + Utils.md5 ( documentFile.getAbsolutePath () ) );
            fileCacheFolder.mkdirs ();

            pdfDocument = new Document ();
            pdfDocument.setFile ( documentFile.getAbsolutePath () );

            pagesCount = pdfDocument.getPageTree ().getNumberOfPages ();

            if ( pdfDocument.getCatalog () != null )
            {
                pageSize = pdfDocument.getPageDimension ( 0, 0.0f, 1.0f );
            }
            else
            {
                pageSize = new PDimension ( 1f, 1f );
            }

            updatePageImageCache (1, 1.0f);
        } else
        {
            fileCacheFolder = new File ( System.getProperty ( "java.io.tmpdir" ) + File.separator + "pdf-noicepdf-stub" );
            fileCacheFolder.mkdirs ();
            pagesCount = 1;
            pageSize = null;
            pdfDocument = null;
        }
    }


    private Image getPageImage ( int page, float zoom)
    {
        if ( pdfDocument != null )
        {
            int actualPage = page;

            // check page bounds just encase.
            if ( page < 1 )
            {
                actualPage = 1;
            }
            else if ( page > pagesCount )
            {
                actualPage = pagesCount;
            }

            return pdfDocument.getPageImage ( actualPage - 1, GraphicsRenderingHints.SCREEN, Page.BOUNDARY_CROPBOX, 0.0f, zoom );
        }
        return null;
    }

    public void invalidate ()
    {
            if ( icePdfPresent && pdfDocument != null )
            {
                Page page;

                for ( int i = 0, max = pagesCount; i < max; i++ )
                {
                    page = pdfDocument.getPageTree ().getPage ( i, this );
                    if ( page.isInitiated () )
                    {
                        page.getLibrary ().disposeFontResources ();
                        page.reduceMemory ();
                    }
                    pdfDocument.getPageTree ().releasePage ( page, this );
                }
            }
    }

    public InputStream getPageStream ( int page, float zoom )
    {
        try
        {
            if ( icePdfPresent)
            {
                File pps = updatePageImageCache(page, zoom);
                return new FileInputStream ( pps );
            } else
            {
                return PdfDocument.class.getResourceAsStream("icepdf-stub.jpg");
            }
        }
        catch ( Throwable ex )
        {
            return null;
        }
    }

    private File generatePageId ( int page, float zoom)
    {
        String pageId = "";

        if ( icePdfPresent )
        {
            pageId = String.format ( "page_%s_%s.jpg", page, zoom );
        } else
        {
            pageId = "icepdf-nojars-stub-page.jpg";

        }

        return new File ( fileCacheFolder + File.separator + pageId);
    }

    private File updatePageImageCache ( int page, float zoom)
    {
        File pps = generatePageId ( page, zoom);
        
        if ( icePdfPresent && !pps.exists () )
        {
            try
            {
                BufferedImage img = ( BufferedImage ) getPageImage ( page, zoom);
                FileOutputStream fos = new FileOutputStream ( pps );
                ImageIO.write ( img, "jpg", fos );
                img.flush ();
                fos.flush ();
                fos.close ();
            }
            catch ( Throwable err )
            {
                throw new RuntimeException ( err.getMessage (), err );
            }
        }

        return pps;
    }

    @Override
    protected void finalize() throws Throwable
    {
        closeDocument();
        super.finalize();
    }



}
