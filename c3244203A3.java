//* File:                       Main.java
// * Course:                    COMP2240
//  * Assignment:               Assignment3
//   * Name:                    Juyong Kim  
//    * Student Number:         c3244203
//     * Purpose:               Main file
//      * Note:                 Presumes all file name put in exists, or it will index out of bounds
//       *                       Also please don't mark me down for my secret function, didn't have time to finish it, please let me keep for the memories
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
import java.lang.String;
import java.nio.file.*;
import java.io.IOException;

import java.util.List;
import java.util.LinkedList;

public class c3244203A3
{
    public static void main(String[] args) throws IOException
    {
        //variables
        //args is all string
        int frames = Integer.parseInt(args[0]);
        int timeQuantum = Integer.parseInt(args[1]);
        int pCount = args.length-2;
        int perFrames = frames/pCount;
        CPU cpu1 = new CPU(timeQuantum, frames,"lru");
        CPU cpu2 = new CPU(timeQuantum, frames,"clock");
        
        //skip first 2 values
        //and input values into the CPU
        for(int i =2; i<args.length;i++)
        {
            String token1 = args[i];
            Process process1 = new Process(token1,frames/(args.length-2));//3);//
            Process process2 = new Process(token1,frames/(args.length-2));//args.length-2);
            readFile(token1,process1);
            readFile(token1,process2);
            cpu1.addProcess(process1);
            cpu2.addProcess(process2);
        }

        //testing sections
        cpu1.events();
        cpu2.events();
        System.out.println(cpu1.printLRU());
        System.out.println(cpu2.printClock());
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

