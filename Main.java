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
import java.util.ArrayList;

public class Main
{
    public static void main(String[] args) throws IOException
    {
        //variables
        int timeQuantum;
        int frames;
        boolean success = false;    

        //files
        String f1 = "Process1.txt";
        String f2 = "Process1.txt";
        String f3 = "Process1.txt";
        String f4 = "Process1.txt";

        //data from the file
        List<String> input = new LinkedList<String>();
        List<String> id = new LinkedList<String>();
        List<Integer> duration  = new ArrayList<Integer>();

        //loading files into stuff
        try 
        {
            Scanner file1 = new Scanner(new File(f1));
            Scanner file2 = new Scanner(new File(f2));
            Scanner file3 = new Scanner(new File(f3));
            Scanner file4 = new Scanner(new File(f4));
        } 
        catch (FileNotFoundException e) 
        { 
            System.out.println("File not found! Please give a valid file!");
        }

                //try catch for getting correct contents of the file
                try 
                {
                    //split by  into string array outside while loop
                    //split by  into string array outside while loop
                    while(file1.hasNext())
                    {
                        token1 = file1.next();
                        input.add(token1);
                        //System.out.println(token1);
                    }                  

                    //saves the amount of customers
                    cNum = Integer.parseInt(input.get(0));

                    //saves all the values into lists
                    for(int i = 1; i < input.size(); i+=2)
                    {

                        id.add(input.get(i));
                        duration.add(Integer.parseInt(input.get(i+1)));

                    }

                    //finishes the file reading
                    success = true;

                } 
                catch (NoSuchElementException e) 
                {
                    System.out.println("Empty file, or invalid contents!");
                }
        //close all the files
        file1.close();
        file2.close();
        file3.close();
        file4.close();

    }//args
}//main