/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package eu.livotov.tpt.gui.vdv;

import com.vaadin.ui.VerticalLayout;
import eu.livotov.tpt.gui.widgets.TPTMultiView;
import eu.livotov.tpt.gui.widgets.TPTMultiView.TPTView;
import eu.livotov.tpt.gui.vdv.api.DocumentRasterProvider;
import eu.livotov.tpt.gui.vdv.api.PageMode;
import eu.livotov.tpt.gui.vdv.api.PageNumber;
import eu.livotov.tpt.gui.vdv.api.PageRotation;
import eu.livotov.tpt.gui.vdv.api.PageZoom;
import eu.livotov.tpt.gui.vdv.core.PageRenderer;
import eu.livotov.tpt.gui.vdv.core.SinglePageDocumentRenderer;
import eu.livotov.tpt.gui.vdv.formats.PdfDocument;
import java.io.File;

/**
 * The actual viewer component which should be used in applications.
 * @author dlivotov
 */
public class DocumentViewer extends VerticalLayout
{
    private DocumentRasterProvider document = null;

    private float zoomFactor = PageZoom.AS_IS;
    private int rotationFactor = PageRotation.NO_ROTATION;
    private PageMode viewMode = PageMode.SINGLE_PAGE_MODE;
    private int currentPage = PageNumber.FIRST_PAGE;

    private TPTMultiView viewController = new TPTMultiView(false);


    /**
     * Creates the new empty viewer component
     */
    public DocumentViewer()
    {
        setMargin(false);
        setSpacing(false);
        setSizeFull();

        viewController.setSizeFull();

        addComponent(viewController);
        setExpandRatio(viewController, 1.0f);
    }

    /**
     * Creates the new viewer component and loads the specified document.
     * @param document document file to display. Note,  that only PDF files are supported at the moment.
     */
    public DocumentViewer( File document )
    {
        this();
        loadDocument ( document );
    }

    /**
     * Creates the new viewer component and the sets a document to view
     * @param document document raster provider
     */
    public DocumentViewer( DocumentRasterProvider document)
    {
        this();
        loadDocument(document);
    }

    /**
     * Loads a new document to display. Do not forget to call closeDocument(); if you had previous document open.
     * @param document new document file to display. Note, that only PDF files are supported at the moment
     */
    public void loadDocument( File document )
    {
        if ( document != null && document.getName().toLowerCase().endsWith(".pdf"))
        {
            loadDocument( new PdfDocument(document));
        } else
        {
            throw new UnsupportedOperationException("This file format is supported in current version, sorry. We can display PDF only files at the moment.");
        }
    }

    /**
     * Loads a new document to display
     * @param document raster provider instance which represents the data of new document
     */
    public void loadDocument( DocumentRasterProvider document )
    {
        currentPage = 0;
        this.document = document;

        if ( viewController.isViewAvailable(PageMode.SINGLE_PAGE_MODE.name()))
        {
            viewController.replaceView(PageMode.SINGLE_PAGE_MODE.name(), new SinglePageDocumentRenderer(document, zoomFactor));
        } else
        {
            viewController.addView(PageMode.SINGLE_PAGE_MODE.name(), new SinglePageDocumentRenderer(document, zoomFactor));
        }
        
        viewController.switchView(PageMode.SINGLE_PAGE_MODE.name());
    }

    /**
     * Closes the current document
     */
    public void closeDocument()
    {
        if ( document != null)
        {
            try
            {
                document.closeDocument();
            } catch( Throwable err)
            {
                err.printStackTrace();
            }
        }
    }

    /**
     * Provides number of pages in currently opened document
     * @return number of pages
     */
    public int getPagesCount()
    {
        if ( document != null)
        {
            return document.getPagesCount();
        } else
        {
            throw new IllegalArgumentException( "Document is not open, cannot count number of pages in it.");
        }
    }

    /**
     * Navigates viewer to specified page
     * @param pageNumber new page number to navigate to
     */
    public void goPage( int pageNumber )
    {
        if ( pageNumber>=PageNumber.FIRST_PAGE && (pageNumber<=getPagesCount() || pageNumber == PageNumber.LAST_PAGE))
        {
            currentPage = (pageNumber == PageNumber.LAST_PAGE) ? getPagesCount() : pageNumber;
            ((PageRenderer)viewController.getCurrentView()).goPage(currentPage);
        } else
        {
            throw new IllegalArgumentException("Page number " + pageNumber + " is out of bounds ( 0 ... " + getPagesCount() + " )");
        }
    }

    /**
     * Provides number of current page we're viewing
     * @return current page
     */
    public int getCurrentPage()
    {
        return currentPage;
    }

    /**
     * Sets the new zoom factor for the document
     * @param zoomFactor new zoom factor in form of 0.1 .... 9.99 , where 1.0 is a 100% of the document
     */
    public void setZoom( float zoomFactor )
    {
        this.zoomFactor = zoomFactor;

        try
        {
            ((PageRenderer)viewController.getCurrentView()).setZoom(this.zoomFactor);
        } catch ( Throwable ignored) {}
    }

    /**
     * Provides the current zoom factor
     * @return current zoom factor value
     */
    public float getZoom()
    {
        return zoomFactor;
    }

    /**
     * Sets document rotation mode. NOT SUPPORTED now
     * @param rotationFactor
     */
    public void setRotation( int rotationFactor )
    {
        this.rotationFactor = rotationFactor;
        ((PageRenderer)viewController.getCurrentView()).setRotation(this.rotationFactor);
    }

    /**
     * Provides the current rotation mode. NOT SUPPORTED NOW
     * @return current rotation mode
     */
    public int getRotation()
    {
        return rotationFactor;
    }

    /**
     * Sets the document display mode. NOT SUPPORTED NOW
     * @param viewMode new display mode
     */
    public void setViewMode( PageMode viewMode )
    {
        this.viewMode = viewMode;
        viewController.switchView(this.viewMode.name());
    }

    /**
     * Provides the current display mode of the document. NOT SUPPORTED NOW
     * @return current display mode
     */
    public PageMode getViewMode()
    {
        return viewMode;
    }

}
