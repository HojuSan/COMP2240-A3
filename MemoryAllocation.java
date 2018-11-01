//* File:                       MemoryAllocation.java
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
    private LinkedList<MemoryFrame> exists;
    private int frames;
    private int pointer;
    // constructor
    public MemoryAllocation(int frames) 
    {
        this.pointer = 0;
        this.frames = frames;
        exists = new LinkedList<MemoryFrame>();
    }

    public int getFrames()
    {
        return frames;
    }
    public void addMa(String id, String policy) //adds to memory Allocation
    {
        String value = policy;

        //if less than or equal to allocated frame amount, just add to memory
        if(exists.size()<=frames)
        {
            exists.add(new MemoryFrame(id));
        }
        //if value is lru run lru policy
        else if(value.equals("lru"))
        {
            lru(id);
        }
        //if value is clock run clock policy
        else if(value.equals("clock"))
        {
            clock(id);
        }
    }
    public boolean check(String id,String policy)
    {
        //if empty then nothing is in memory
        if(exists.size()==0)
        return false;

        //least recently used policy update
        if(policy.equals("lru"))
        {
            for(int i = 0; i < exists.size(); i++)
            {
                //checks if the id exists within memory
                if(exists.get(i).getMFId().equals(id))
                {
                    //made in a way so the top of the list(0) is always the 
                    //least recently used

                    //move it to the back of the list
                    exists.add(exists.get(i));
                    //delete it from its current position
                    exists.remove(exists.get(i));    
                    return true;
                }
            }
            return false;
        }
        //clock policy update
        else if(policy.equals("clock"))
        {
            //simply checks if it exists
            for(int i = 0; i < exists.size(); i++)
            {
                //checks if the id exists within memory
                if(exists.get(i).getMFId().equals(id))
                {
                    return true;
                }
            }
            return false;
        }
        else
        {
            return false;
        }


    }
    //makes the pointer point to the next value
    public void pointerNext()
    {
        //up pointer location
        if(pointer<exists.size()-1)
        {
            pointer++;
        }
        else    //set it to zero
        {
            pointer=0;
        }
    }
    //uses least recently used policy
    public void lru(String id)
    {
        //remove the least used
        exists.remove(exists.get(0));      
        //add the new page id
        exists.add(new MemoryFrame(id));
    }
    //uses clock policy
    public void clock(String id)
    {
        //while pointer location is true
        while(exists.get(pointer).getStar()==true)
        {
            exists.get(pointer).starFalse();
            pointerNext();
        }
        exists.remove(pointer);
        exists.add(pointer, new MemoryFrame(id));
        pointerNext();
    }

}//end of class