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


public class CPU 
{
    //List for ready, running and finished queues
    private List<Process> ready,running,finished,blocked;
    private int processCount;
    private final int memPro = 6;
    private int timeQuantum;
    private int frames;

    // constructor
    public CPU(int timeQuantum, int frames) 
    {
        //contains a list of all the processes
        running = new LinkedList<Process>();
        ready = new LinkedList<Process>();
        finished = new LinkedList<Process>();
        blocked = new LinkedList<Process>();
        this.timeQuantum = timeQuantum;
        this.frames = frames;
        processCount = 0;
    }

    //functions
    public void events()
    {
//TODO work on this, currently working on memoryAllocation

        //program runs while running or ready queue is still doing stuff
        while(!running.isEmpty()&&!ready.isEmpty())
        {
            for(int i = 0; i < ready.size(); i++)
            {
                ready.get(i);
            }
        }//end of while loop

    }//end of cpurunning

    public void addProcess(Process process) //Setting the process into a list
    {
        //adds all the process to the ready queue
        ready.add(process);
        processCount++;
    }
    public String print()   //testing purposes, prints all the values in all the processes
    {
        String token1="";

        for(int i = 0; i < processCount;i++)
        {
            token1 += "This is " + ready.get(i).getId()+"\n";

            for(int j = 0; j < ready.get(i).getSize();j++)
            {
                token1 += ready.get(i).getPage().get(j)+"\n";
            }
        }
        return token1;
    }
    //reults section
    //TODO returns values
    public String printLRU()  
    {
        String token1="LRU - Fixed:\nPID  Turnaround Time  # Faults  Fault Times\n";
        for(int i = 0; i < processCount;i++)
        {
            token1 += i + " woot\n";
        }
        return token1;
    }
    public String printClock()  
    {
        String token1="Clock - Fixed:\nPID  Turnaround Time  # Faults  Fault Times\n";
        for(int i = 0; i < processCount;i++)
        {
            token1 += i + " woot\n";
        }
        return token1;
    }

    //setters
    //getters
    
}//end of class