/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package eu.livotov.tpt.demo.api;

import com.vaadin.terminal.Resource;

/**
 *
 * @author dll
 */
public interface DemoItem
{

    boolean hasSourceCode ();

    boolean hasShowCase ();

    String getItemName();

    String getItemDescription();

    String getItemSourceCode();

    void performShowCase();

    Resource getIcon();

}
