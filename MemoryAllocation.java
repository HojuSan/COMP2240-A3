/* 
Assignment 1
Course:     COMP2240
Author:     Juyong Kim
Student No: c3244203
Date:       09/08/18
Description: Round Robin
*/
//Libraries
import java.util.Comparator;
import java.util.Collections;
import java.util.List;
import java.util.LinkedList;
import java.util.Queue;


public class MemoryAllocation 
{
    //list of all the processes
    private List<Process> events;
    private int processCount;
    private final int memPro = 6;
    private int dis;

    // constructor
    public MemoryAllocation() 
    {
        //contains a list of all the processes
        events = new LinkedList<Process>();
        processCount = 0;
    }

    //Setting the process into a list
    public void addProcess(Process process)
    {
        events.add(process);
        processCount++;
    }

    //testing purposes, prints all the values in all the processes
    public String print()
    {
        String token1="";

        for(int i = 0; i < processCount;i++)
        {
            token1 += "This is " + events.get(i).getId()+"\n";

            for(int j = 0; j < events.get(i).getSize();j++)
            {
                token1 += events.get(i).getPage().get(j)+"\n";
            }
        }
        return token1;
    }

    
}