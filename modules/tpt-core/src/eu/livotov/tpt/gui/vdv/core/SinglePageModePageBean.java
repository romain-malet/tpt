/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package eu.livotov.tpt.gui.vdv.core;

/**
 *
 * @author dlivotov
 */
public class SinglePageModePageBean
{

    private int pageNumber = 0;

    private String page = "";

    private String notes = "";

    private String dummyLeft = "";

    private String dummyRight = "";


    public SinglePageModePageBean()
    {

    }

    public SinglePageModePageBean( int page )
    {
        this();
        this.pageNumber = page;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getDummyLeft() {
        return dummyLeft;
    }

    public void setDummyLeft(String dummyLeft) {
        this.dummyLeft = dummyLeft;
    }

    public String getDummyRight() {
        return dummyRight;
    }

    public void setDummyRight(String dummyRight) {
        this.dummyRight = dummyRight;
    }

    



}
