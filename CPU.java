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
import java.util.*;

public class CPU 
{
    //List for ready, running and finished queues
    private List<Process> ready,running,finished,blocked,organiseBlocked;
    private int processCount;
    private final int memPro = 6;
    private int timeQuantum;
    private int frames;
    private int time;
    private int dummy;

    // constructor
    public CPU(int timeQuantum, int frames) 
    {
        //contains a list of all the processes
        organiseBlocked = new LinkedList<Process>();
        running = new LinkedList<Process>();
        ready = new LinkedList<Process>();
        finished = new LinkedList<Process>();
        blocked = new LinkedList<Process>();
        this.timeQuantum = timeQuantum;
        this.frames = frames;
        processCount = 0;
        time = 0;
        //delete after testing
        dummy = 0;
    }

    //functions
    public void events()
    {
//TODO work on this, currently working on memoryAllocation
//this thing will get complicated fast
//work on this carefully and thoughtfully

        //finished queue is not full
        while(finished.size()!=processCount)
        {
            System.out.println("1.beginning of while loop, time is "+time);
            //currently order is
            //check blocked-->then ready---> then go to running


            //if blocked queue is not empty run
            if(!blocked.isEmpty())
            {
                //int counter = blocked.size();
                //blocked queue
                for(int i = 0; i < blocked.size(); i++)
                {
                    //adds a counter to the memeoryprocessing
                    blocked.get(i).getPages().get(0).upPt();

                    //if the page fully loaded for 6 frames
                    if(blocked.get(i).getPages().get(0).checkPt())
                    {
//WOOOT Important, it does the memory allocation here
                        blocked.get(i).addToMemory(blocked.get(i).getPages().get(0).getPageId());
                    //System.out.println("first blocked, adds");
                        //put process into ready queue
                        organiseBlocked.add(blocked.get(i));
                        //add to organisation blocked list then remove it from blocked queue
                        blocked.remove(blocked.get(i));
                        //since an element has been removed, have to reduce counter by one so it stays
                        //in same position or it will skip an element
                        i--;
                    }
                }
            }

//TODO this might be in the wrong location figure out where this should go
            //sort the ready queue so the right process begins doing stuff
            if(!organiseBlocked.isEmpty())
            {
                //int counter = organiseBlocked.size();
                Collections.sort(organiseBlocked);
                while(!organiseBlocked.isEmpty())
                {
                    ready.add(organiseBlocked.get(0));
                    organiseBlocked.remove(organiseBlocked.get(0));
                }
            }

            //if ready list is not empty
            if(!ready.isEmpty())
            {
                //int counter = ready.size();

                //adds all processes checks if they are ready to access the main memory
                for(int i = 0; i < ready.size(); i++)
                {
                    //checks if the process page isn't in the memory
                    //NOTE first get is process, second get is the page
                    //it gets the i process and checks the 0 page id to see if its true or false
                    if(!ready.get(i).getMa().check(ready.get(i).getPages().get(0).getPageId()))
                    {
                        ready.get(i).addFault(time);                            //adds the page fault at the current time
                        ready.get(i).addToMemory(ready.get(i).getPages().get(0).getPageId());     //adds the page to memory to start processing
                        blocked.add(ready.get(i));                              //adds the process to blocked queue
                        ready.remove(ready.get(i));                             //removes it from ready queue
                        i--;
                    }
                    //if the page is in memory run
                    else if(ready.get(i).getMa().check(ready.get(i).getPages().get(0).getPageId()))
                    {
                        //adds the process to running queue
                        running.add(ready.get(i));
                        //removes it from ready queue
                        ready.remove(ready.get(i));
                        i--;
                    }
                }
            }
//TODO needs working on, something with time might be wrong
//update turnaround time aka find a way to do it

            //executes the pages in the process
            if(!running.isEmpty())
            {
                //running queue, executes for timeQuantum amount
                for(int i = 0; i < running.size(); i++)
                {
                    //running queue, page runs, executes for timeQuantum amount
                    for(int j = 0; j < timeQuantum; j++)
                    {
                        //if page is in memory, process it and delete it
                        if(running.get(i).getMa().check(running.get(i).getPages().get(0).getPageId()))
                        {
                    System.out.println(running.get(i).getProcessId()+" Page "+running.get(i).getPages().get(0).getPageId());

                            //get the first page in the process, and remove it, since its processed
                            running.get(i).getPages().remove(running.get(i).getPages().get(0));

                            //however if all the pages have finished in that process
                            //remove the process
                            if(running.get(i).getPages().isEmpty())
                            {
                                finished.add(running.get(i));
                                running.remove(running.get(i));
                                i--;
                            }
                            time++;
                    System.out.println("2.after page processed time is "+time);
                            //process no longer exists in running, break the loop
                            break;
                        }
                        //page blocks cause its not in memory
                        else if(!running.get(i).getMa().check(running.get(i).getPages().get(0).getPageId()))
                        {
                    System.out.println(running.get(i).getProcessId());

                            running.get(i).addFault(time);                            //adds the page fault at the current time
                            running.get(i).addToMemory(running.get(i).getPages().get(0).getPageId());     //adds the page to memory to start processing
                            blocked.add(running.get(i));                              //adds the process to blocked queue
                            running.remove(running.get(i));                           //removes it from ready queue
                            time++;
                    System.out.println("3.after page block time is "+time);
                            i--;
                            //process no longer exists in running, break the loop
                            break;
                        }
                    }
                }
            }
            else
            {
                //nothing ran, just increment time instead
                time++;
            }
            
        }//end of while loop
        //this should be 38 or 39
        //currently 52
        System.out.println("4.end of while loop, time is "+time);
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

        for(int i = 0; i < ready.size();i++)
        {
            token1 += "This is " + ready.get(i).getProcessId()+"\n";

            //size of pages in a process
            for(int j = 0; j < ready.get(i).getPages().size(); j++)
            {
                token1 += ready.get(i).getPages().get(j).getPageId() +"\n";
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
    public int getTime()
    {
        return time;
    }
    
}//end of class