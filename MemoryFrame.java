//* File:                       Main.java
// * Course:                    COMP2240
//  * Assignment:               Assignment3
//   * Name:                    Juyong Kim  
//    * Student Number:         c3244203
//     * Purpose:               Primary file and then blah blah more explanation of shiet
//      * 
public class MemoryFrame
{
    private String id;
    private int usage;
    public MemoryFrame(String id)
    {
        this.id = id;
        this.usage = 0;
    }

    //getters
    public String getMFId()
    {
        return id;
    }
    public int getUsage()
    {
        return usage;
    }

    //setters
    public void upUsage()
    {
        this.usage++;
    }
}