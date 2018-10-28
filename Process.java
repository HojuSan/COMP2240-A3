import java.util.List;
import java.util.LinkedList;
import java.util.ArrayList;

public class Process
{
    //variables
    private String id;
    private int size;
    private List<String> page;
    private int turnaroundTime;
    private int faultCount;
    private List<Integer> faultTimes;

    //constructor
    public Process(String id)
    {
        this.page = new LinkedList<String>();
        this.id = id;
        this.size = 0;
        this.turnaroundTime = 0;
        this.faultCount = 0;
        this.faultTimes = new ArrayList<Integer>();
    }

    //setters
    public void addPage(String pageId)
    {
        this.page.add(pageId);
        this.size++;
    }
    public void setTAT(int tat)    //turnaroundtime
    {
        this.turnaroundTime = tat;
    }
    public void addFault(int faultNum)
    {
        this.faultCount++;
        this.faultTimes.add(faultNum);
    }

    //getters
    public String getId()
    {
        return id;
    }
    public List getPage()
    {
        return page;
    }
    public int getSize()
    {
        return size;
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

}