//* File:                       Main.java
// * Course:                    COMP2240
//  * Assignment:               Assignment3
//   * Name:                    Juyong Kim  
//    * Student Number:         c3244203
//     * Purpose:               Primary file and then blah blah more explanation of shiet
//      * 
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
    private int time;

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
        time = 0;
    }

    //functions
    public void events()
    {
//TODO work on this, currently working on memoryAllocation
//this thing will get complicated fast
//work on this carefully and thoughtfully

        //program runs while running or ready queue is still doing stuff
 //       while(!running.isEmpty() && !ready.isEmpty())
 //       {
 //           //adds all processes checks if they are ready to access the main memory
 //           for(int i = 0; i < ready.size(); i++)
 //           {
 //               //checks if the process page isn't in the memory
 //               //NOTE first get is process, second get is the page
 //               //it gets the i process and checks the 0 page id to see if its true or false
 //               if(ready.get(i).check(ready.get(i).getPageId())==false)
 //               {
 //                   //adds the page fault at the current time
 //                   ready.get(i).addFault(time);
 //                   //adds the page to memory
 //                   ready.get(i).addToMemory(ready.get(i).getPageId());
 //                   //adds the process to blocked queue
 //                   blocked.add(ready.get(i));
 //                   //removes it from ready queue
 //                   ready.remove(ready.get(i));
 //               }
 //               //if the page is in memory run
 //               if(ready.get(i).check(ready.get(i).getPageId())==true)
 //               {
 //                   //adds the process to running queue
 //                   running.add(ready.get(i));
 //                   //removes it from ready queue
 //                   ready.remove(ready.get(i));
 //               }
 //           }
//
 //           //blocked queue
 //           for(int i = 0; i < blocked.size(); i++)
 //           {
 //               //adds a counter to the memeoryprocessing
 //               blocked.get(i).addMP();
 //           }
 //           //running queue
 //           for(int i = 0; i < running.size(); i++)
 //           {
//
 //           }
 //           time++; //increment time
//
 //       }//end of while loop

    }//end of cpurunning

    public void addProcess(Process process) //Setting the process into a list
    {
        //adds all the process to the ready queue
        ready.add(process);
        processCount++;
    }
//    public String print()   //testing purposes, prints all the values in all the processes
//    {
//        String token1="";
//
//        for(int i = 0; i < processCount;i++)
//        {
//            token1 += "This is " + ready.get(i).getId()+"\n";
//
//            for(int j = 0; j < ready.get(i).getSize();j++)
//            {
//                token1 += ready.get(i).getPage().get(j)+"\n";
//            }
//        }
//        return token1;
//    }
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
    public int getTime()
    {
        return time;
    }
    
}//end of class