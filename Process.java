//* File:                       Main.java
// * Course:                    COMP2240
//  * Assignment:               Assignment3
//   * Name:                    Juyong Kim  
//    * Student Number:         c3244203
//     * Purpose:               Primary file and then blah blah more explanation of shiet
//      * 
import java.util.List;
import java.util.LinkedList;
import java.util.ArrayList;

public class Process implements Comparable<Process>
{
    //variables
    private String id;
    private int turnaroundTime;
    private int faultCount;
    private ArrayList<Integer> faultTimes;
    private LinkedList<Page> pages;
    private Page newPage;
    private MemoryAllocation ma;
    private int frames;

    //constructor
    public Process(String id,int frames)
    {
        this.frames = frames;
        this.ma = new MemoryAllocation(frames);
        this.pages = new LinkedList<Page>();
        this.id = id;
        this.turnaroundTime = 0;
        this.faultCount = 0;
        this.faultTimes = new ArrayList<Integer>();
    }

    //setters
    public void setTat(int tat)
    {
        this.turnaroundTime = tat;
    }
    public void addPage(String pageId)
    {
        this.pages.add(new Page(pageId));
    }
    public void addTat(int tat)    //turnaroundtime
    {
        this.turnaroundTime =+ tat;
    }
    public void addFault(int faultNum)
    {
        this.faultCount++;
        this.faultTimes.add(faultNum);
    }

    //getters
    public int getTat()
    {
        return turnaroundTime;
    }
    public String getProcessId()
    {
        return id;
    }
    public int getFaultCount()
    {
        return faultCount;
    }
    public String getFaults()    //prints the faults
    {
        String token1 = "{";

        for(int i =0;i < faultCount; i++)
        {
            if(i==0)
            {
                token1 += faultTimes.get(i);
            }
            else
            {
                token1 += ", " + faultTimes.get(i);
            }
        }

        token1 += "}";

        return token1;
    }
    public LinkedList<Page> getPages()
    {
        return pages;
    }
    public MemoryAllocation getMa()
    {
        return ma;
    }
    @Override
    public int compareTo(Process process)
    {
        return id.compareTo(process.getProcessId());
    }

}