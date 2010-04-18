/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package eu.livotov.tpt.gui.vdv.api;

/**
 *
 * @author dlivotov
 */
public class RasterizedPageProperties
{

    private int width = 0;

    private int height = 0;


    public RasterizedPageProperties(int w, int h)
    {
        width = w;
        height = h;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    

}
