//* File:                       Main.java
// * Course:                    COMP2240
//  * Assignment:               Assignment3
//   * Name:                    Juyong Kim  
//    * Student Number:         c3244203
//     * Purpose:               Saves the data for pages within a process
//      * 
public class Page
{
    //variables
    private String id;
    private int processingTime;
    private final int memPro = 6;

    //constructor
    public Page(String id)
    {
        this.id = id;
        processingTime = 0;
    }

    //functionsS
    public void upPt()                      //ups prosessing time to 6
    {                                       //used for memory allocation
        processingTime++;
    }
    public boolean checkPt()                //boolean check of processing time to show
    {                                       //it has fully loaded into memory
        if(memPro == processingTime)        
        {
            return true;
        }
        return false;
    }

    //getters
    public String getPageId()
    {
        return id;
    }
    public int getPt()
    {
        return processingTime;
    }
}