//* File:                       Main.java
// * Course:                    COMP2240
//  * Assignment:               Assignment3
//   * Name:                    Juyong Kim  
//    * Student Number:         c3244203
//     * Purpose:               Primary file and then blah blah more explanation of shiet
//      * 
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
import java.lang.String;
import java.nio.file.*;
import java.io.IOException;

import java.util.List;
import java.util.LinkedList;

public class Assign3
{
    public static void main(String[] args) throws IOException
    {
        //args is all string
        //convert into intger
//        frames = args[0];
//        timeQuantum = args[1];
//        f1 = args[2];
//        f2 = args[3];
//        f3 = args[4];
//        f4 = args[5];

        //its just clean
        System.out.println("\n");
        
        //variables
        //these values hardcoded for testing purposes
        int pCount=4;
        int timeQuantum = 3;
        int frames = 30;

        //files
        String f1 = "Process1.txt";
        String f2 = "Process2.txt";
        String f3 = "Process3.txt";
//        String f4 = "Process4.txt";

        //data from the file
        Process process1 = new Process(f1,frames/pCount);
        Process process2 = new Process(f2,frames/pCount);
        Process process3 = new Process(f3,frames/pCount);
//        Process process4 = new Process(f4,frames/pCount);

        //load data from files into processes
        readFile(f1,process1);
        readFile(f2,process2);
        readFile(f3,process3);
//        readFile(f4,process4);

        //adds the process into the memory allocation

//        CPU cpu1 = new CPU(timeQuantum, frames,"lru");
        CPU cpu2 = new CPU(timeQuantum, frames,"clock");

//        cpu1.addProcess(process1);
//        cpu1.addProcess(process2);
//        cpu1.addProcess(process3);
//        cpu1.addProcess(process4);

        cpu2.addProcess(process1);
        cpu2.addProcess(process2);
        cpu2.addProcess(process3);
//        cpu2.addProcess(process4);

        //testing sections
//        cpu1.events();
        cpu2.events();
//        System.out.println(cpu.print()); //prints process values from, Memory Allocation
//        System.out.println(cpu1.printLRU());
        System.out.println(cpu2.printClock());
//        for(int i = 0; i < process1.getSize(); i++)
//        {
//           System.out.println(process1.getPage().get(i));
//        }

    }//end of main

    //recieves the file name and saves the variables into string lists
    public static void readFile(String fileName, Process process)
    {
        //variables
        List<String> input = new LinkedList<String>();
        String token1;          //dummy variable used for text manipulation

        //loading files into stuff
        try 
        {
            Scanner file1 = new Scanner(new File(fileName));

            //try catch for getting correct contents of the file
            try 
            {
                //split by into string list outside while loop
                while(file1.hasNext())
                {
                    token1 = file1.next();
                    input.add(token1);
                }                  

                //saves the pages into a process
                if(input.get(0).equals("begin"))
                {
                    //ignore the first part of file
                    //since its begin and input.size()-1
                    //to ignore the end string
                    for(int i = 1; i < input.size()-1; i++)
                    {
                        process.addPage(input.get(i));
                    }

                }

            } 
            catch (NoSuchElementException e) 
            {
                System.out.println("Empty file, or invalid contents!");
            }

            //close all the files
            file1.close();
        } 
        catch (FileNotFoundException e) 
        { 
            System.out.println("File not found! Please give a valid file!");
        }

    }//end of readFile function

}//main

