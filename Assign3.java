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
        //variables
        //these values hardcoded for testing purposes
        int timeQuantum = 3;
        int frames = 30;

        //files
        String f1 = "Process1.txt";
        String f2 = "Process2.txt";
        String f3 = "Process3.txt";
        String f4 = "Process4.txt";

        //data from the file
        List<String> process1 = new LinkedList<String>();
        List<String> process2 = new LinkedList<String>();
        List<String> process3 = new LinkedList<String>();
        List<String> process4 = new LinkedList<String>();

        //load data from files into processes
        readFile(f1,process1);
        readFile(f2,process2);
        readFile(f3,process3);
        readFile(f4,process4);

    }//end of main

    public static void readFile(String fileName, List<String> process)
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
                        process.add(input.get(i));
                    }

                }

            } 
            catch (NoSuchElementException e) 
            {
                System.out.println("Empty file, or invalid contents!");
            }
        } 
        catch (FileNotFoundException e) 
        { 
            System.out.println("File not found! Please give a valid file!");
        }

        //close all the files
        file1.close();
    }//end of readFile function

}//main

