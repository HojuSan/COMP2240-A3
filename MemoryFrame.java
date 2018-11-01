//* File:                       MemoryFrame.java
// * Course:                    COMP2240
//  * Assignment:               Assignment3
//   * Name:                    Juyong Kim  
//    * Student Number:         c3244203
//     * Purpose:               Saves the page id and star data for memory allocation
//      *                       and clock policy usage
public class MemoryFrame
{
    //variables
    private String id;
    private boolean star;

    //constructor
    public MemoryFrame(String id)
    {
        this.id = id;
        this.star = true;
    }

    //setters
    public void starTrue()
    {
        star = true;
    }
    public void starFalse()
    {
        star = false;
    }
    
    //getters
    public String getMFId()
    {
        return id;
    }
    public Boolean getStar()
    {
        return star;
    }
}