//* File:                       Main.java
// * Course:                    COMP2240
//  * Assignment:               Assignment3
//   * Name:                    Juyong Kim  
//    * Student Number:         c3244203
//     * Purpose:               Primary file and then blah blah more explanation of shiet
//      * 
public class Page
{
    private String id;
    private int processingTime;
    private final int memPro = 6;
//    private int usedCount;

    public Page(String id)
    {
        this.id = id;
        processingTime = 0;
        this.usedCount = 0;
    }

    public String getPageId()
    {
        return id;
    }
//    public int getUsedCount()
//    {
//        return usedCount;
//    }
    public void upPt()
    {
        processingTime++;
    }
    public int getPt()
    {
        return processingTime;
    }
    public boolean checkPt()
    {
        if(memPro == processingTime)
        {
            return true;
        }
        return false;
    }
}