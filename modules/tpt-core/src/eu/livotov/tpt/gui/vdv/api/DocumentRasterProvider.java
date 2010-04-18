/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package eu.livotov.tpt.gui.vdv.api;

import java.io.InputStream;

/**
 *
 * @author dlivotov
 */
public interface DocumentRasterProvider
{

    String getDocumentId();

    int getPagesCount();

    InputStream getPageData( int pageNumber, float zoomLevel, int rotationLevel );

    RasterizedPageProperties getPageProperties ( int pageNumber, float zoomLevel, int rotationLevel );

    void closeDocument();
}
