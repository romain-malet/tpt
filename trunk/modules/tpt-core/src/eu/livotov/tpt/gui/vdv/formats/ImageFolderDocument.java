/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package eu.livotov.tpt.gui.vdv.formats;

import eu.livotov.tpt.gui.vdv.api.DocumentRasterProvider;
import eu.livotov.tpt.gui.vdv.api.RasterizedPageProperties;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import javax.imageio.ImageIO;

/**
 *
 * @author dlivotov
 */
public class ImageFolderDocument implements DocumentRasterProvider
{
    private File imagesFolder = null;
    private int pagesCount = 0;
    private boolean hasFacingPage = false;
    private String documentId = UUID.randomUUID().toString();
    private List<File> pages = new ArrayList<File>();
    private Map<String,RasterizedPageProperties> pageProperties = new ConcurrentHashMap<String, RasterizedPageProperties>();
    
    public ImageFolderDocument( File folder )
    {
        this.imagesFolder = folder;
        loadData();
    }

    public String getDocumentId()
    {
        return documentId;
    }

    public int getPagesCount()
    {
        return pagesCount;
    }

    public InputStream getPageData(int pageNumber, float zoomLevel, int rotationLevel)
    {
        try
        {
            return new FileInputStream(pages.get(pageNumber));
        } catch (Throwable err)
        {
            err.printStackTrace();
            return null;
        }
    }

    private void loadData()
    {
        File[] files = imagesFolder.listFiles();

        if ( files!=null)
        {
            for ( File file : files)
            {
                if ( file!=null && file.getName().endsWith(".jpg"))
                {
                    pages.add(file);
                    pagesCount++;
                }
            }
        }

        pagesCount--;
    }

    public RasterizedPageProperties getPageProperties(int pageNumber, float zoomLevel, int rotationLevel)
    {
        if (!pageProperties.containsKey("" + pageNumber))
        {
            try
            {
                BufferedImage image = ImageIO.read( pages.get(pageNumber<=0?1:pageNumber) );
                pageProperties.put("" + pageNumber, new RasterizedPageProperties(image.getWidth(), image.getHeight()));
            } catch( Throwable err)
            {
                err.printStackTrace();
                pageProperties.put("" + pageNumber, new RasterizedPageProperties(0,0));
            }
        }

        return pageProperties.get("" + pageNumber);
    }

    public boolean hasFacingPage()
    {
        return false;
    }

    public void closeDocument()
    {
    }

}
