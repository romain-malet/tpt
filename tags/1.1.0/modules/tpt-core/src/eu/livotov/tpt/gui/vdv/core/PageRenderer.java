/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package eu.livotov.tpt.gui.vdv.core;

import com.vaadin.ui.VerticalLayout;
import eu.livotov.tpt.gui.widgets.TPTMultiView.TPTView;
import eu.livotov.tpt.gui.vdv.api.DocumentRasterProvider;

/**
 *
 * @author dlivotov
 */
public abstract class PageRenderer extends VerticalLayout implements TPTView
{
    
    public abstract void goPage( int number );
    
    public abstract void setZoom ( float zoom );
    
    public abstract void setRotation ( int rotation );
    
}
