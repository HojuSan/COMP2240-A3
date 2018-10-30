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
    private List<Process> ready,running,finished,blocked,faulted,organiseBlocked;
    private int processCount;
    private final int memPro = 6;
    private int timeQuantum;
    private int frames;
    private int time;
    private int dummy;
    private String policy;

    // constructor
    public CPU(int timeQuantum, int frames, String policy) 
    {
        //contains a list of all the processes
        faulted = new LinkedList<Process>();
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
        this.policy = policy;
    }

    //functions
    public void events()
    {

//        System.out.println("frames is "+ ready.get(0).getMa().getFrames());
        //organise queue before beginning
        Collections.sort(ready);

        //adds all processes checks if they are ready to access the main memory
        for(int i = 0; i < ready.size(); i++)
        {
            //checks if the process page isn't in the memory
            //NOTE first get is process, second get is the page
            //it gets the i process and checks the 0 page id to see if its true or false
            if(!ready.get(i).getMa().check(ready.get(i).getPages().get(0).getPageId(),policy))
            {
//                System.out.println(ready.get(i).getProcessId()+" has page faulted at time "+time);
                ready.get(i).addFault(time);                            //adds the page fault at the current time
//                System.out.println(ready.get(i).getProcessId()+" fault count is at "+ready.get(i).getFaultCount());
                faulted.add(ready.get(i));                              //adds the process to blocked queue
                ready.remove(ready.get(i));                             //removes it from ready queue
                i--;
            }
        }

        //finished queue is not full
        while(finished.size()!=processCount)
        {
//            System.out.println("TIMER::::::::::::::beginning of while loop, time is "+time);

            if(!faulted.isEmpty())
            {
                while(!faulted.isEmpty())
                {
                    blocked.add(faulted.get(0));
                    faulted.remove(faulted.get(0));
                }
            }

            //just checking ready queue
            checkReady();

            //if ready list is not empty
            if(!ready.isEmpty())
            {
                doready();
            }

//running starts here            //executes the pages in the process
            if(!running.isEmpty())
            {
//                System.out.println("program is running "+running.get(0).getProcessId()+" at time: "+time);
                //running queue, page runs, executes for timeQuantum amount
                for(int j = 0; j < timeQuantum; j++)
                {
                    //if page is in memory, process it and delete it
                    if(running.get(0).getMa().check(running.get(0).getPages().get(0).getPageId(),policy))
                    {

//                        System.out.println(running.get(0).getProcessId()+" is running at time: "+time+ " counterj is at: "+j);

                        //get the first page in the process, and remove it, since its processed
                        running.get(0).getPages().remove(running.get(0).getPages().get(0));

                        //however if all the pages have finished in that process
                        //remove the process
                        if(running.get(0).getPages().isEmpty())
                        {
                            time++;
//                            System.out.println("@@@@@@@@@@@@@"+running.get(0).getProcessId()+" at " +time+ " has left the queue");
                            running.get(0).setTat(time);
                            finished.add(running.get(0));
                            running.remove(running.get(0));

                            doBlocked();
                            doorganiseBlocked();
                            checkReady();
                            //System.out.println("TIMER::::::::::::::after a process finishes, time is "+time);
                            break;
                        }
                        if(j==2)
                        {
//                           System.out.println("%%%%%%%% putting "+running.get(0).getProcessId()+" from running into ready here at time: "+time);
                            ready.add(running.get(0));
                            running.remove(running.get(0));
                            
                        }
                        time++;
//                        System.out.println("TIMER::::::::::::::after a process runs once, time is "+time);
                        doBlocked();
                        doorganiseBlocked();
                        checkReady();

                    }
                    //page blocks cause its not in memory
                    else if(!running.get(0).getMa().check(running.get(0).getPages().get(0).getPageId(),policy))
                    {
//                        System.out.println(running.get(0).getProcessId()+" has page faulted at time "+time);

                        running.get(0).addFault(time);                            //adds the page fault at the current time
//                        System.out.println(running.get(0).getProcessId()+" fault count is at "+running.get(0).getFaultCount());
                        faulted.add(running.get(0));                              //adds the process to blocked queue          
                        running.remove(running.get(0));                           //removes it from ready queue
                        checkReady();

                        //process no longer exists in running, break the loop
                        break;
                    }

                    //once the process finished its slice, but still wants to go, but has to wait its turn
                    //boot it back into the ready queue
                    
                }

            }
            else
            {
                //nothing ran, just increment time instead
                time++;
//                System.out.println("TIMER::::::::::::::end of while loop, time is "+time);

                //if blocked queue is not empty run
                if(!blocked.isEmpty())
                {
                    doBlocked();
                }

                //sort the ready queue so the right process begins doing stuff
                if(!organiseBlocked.isEmpty())
                {
                    doorganiseBlocked();
                }
            }


            
        }//end of while loop
        //this should be 38 or 39
        //currently 52
//        System.out.println("4.end of while loop, time is "+time);
    }//end of cpurunning
    public void doBlocked()
    {
        //blocked queue
        for(int i = 0; i < blocked.size(); i++)
        {
            //adds a counter to the memeoryprocessing
            blocked.get(i).getPages().get(0).upPt();
//            System.out.println("-------------"+blocked.get(i).getProcessId()+" is at "+blocked.get(i).getPages().get(0).getPt()+" currently at time: "+time+" in doBlocked");

            //if the page fully loaded for 6 frames
            if(blocked.get(i).getPages().get(0).checkPt())
            {
//                System.out.println("$$$$$$$$ "+blocked.get(i).getProcessId()+" is leaving the blocked into ready at time: "+time);
                blocked.get(i).getMa().addMa(blocked.get(i).getPages().get(0).getPageId(),policy);
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
    public void doorganiseBlocked()
    {
        //int counter = organiseBlocked.size();
        Collections.sort(organiseBlocked);
        while(!organiseBlocked.isEmpty())
        {
            ready.add(organiseBlocked.get(0));
            organiseBlocked.remove(organiseBlocked.get(0));
        }
    }
    public void doready()
    {
            //adds the process to running queue
            running.add(ready.get(0));
            //removes it from ready queue
            ready.remove(ready.get(0));
    }
    public void checkReady()
    {
        for(int r = 0; r < ready.size();r++)
        {
//            System.out.println("$----->"+ready.get(r).getProcessId()+" is in ready queue at time: "+time);
        }
    }

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
        Collections.sort(finished);

        String token1="LRU - Fixed:\nPID  Turnaround Time  # Faults  Fault Times\n";
        for(int i = 0; i < processCount;i++)
        {
            token1 += i+1 + "    "+finished.get(i).getTat()+"               "+finished.get(i).getFaultCount()+"         "+finished.get(i).getFaults()+"\n";
        }
        return token1;
    }
    public String printClock()  
    {
        String token1="Clock - Fixed:\nPID  Turnaround Time  # Faults  Fault Times\n";
        for(int i = 0; i < processCount;i++)
        {
            token1 += i+1 + "    "+finished.get(i).getTat()+"               "+finished.get(i).getFaultCount()+"         "+finished.get(i).getFaults()+"\n";
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