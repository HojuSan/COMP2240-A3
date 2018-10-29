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

    //constructor
    public Process(String id)
    {
        this.ma = new MemoryAllocation();
        this.pages = new LinkedList<Page>();
        this.id = id;
        this.turnaroundTime = 0;
        this.faultCount = 0;
        this.faultTimes = new ArrayList<Integer>();
    }

    //setters
//    public void addToMemory(String id)
//    {
//        this.ma.addMa(id);
//    }
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
    public String getFault()    //prints the faults
    {
        String token1 = "{";

        for(int i =0;i < faultCount; i++)
        {
            if(i==0)
            {
                token1 += faultTimes.get(i);
            }
            token1 += ", " + faultTimes.get(i);
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