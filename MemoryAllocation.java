//* File:                       Main.java
// * Course:                    COMP2240
//  * Assignment:               Assignment3
//   * Name:                    Juyong Kim  
//    * Student Number:         c3244203
//     * Purpose:               Primary file and then blah blah more explanation of shiet
//      * 
import java.util.List;
import java.util.LinkedList;
public class MemoryAllocation
{
    private List<String> exists;
    private int frames;
    // constructor
    public MemoryAllocation(int frames) 
    {
        this.frames = frames;
        exists = new LinkedList<String>();
    }

    public int getFrames()
    {
        return frames;
    }
    public void addMa(String id) //adds to memory Allocation
    {
//        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!In MEMORY ALLOCATION adds this: "+id);
        exists.add(id);
    }
    public boolean check(String id)
    {
//        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!In MEMORY ALLOCATION");
        //if empty then nothing is in memory
        if(exists.size()==0)
        return false;

        for(int i = 0; i < exists.size(); i++)
        {
            //checks if the id exists within memory
            if(exists.get(i).equals(id))
            {
                return true;
            }
        }
        return false;
    }

}//end of class