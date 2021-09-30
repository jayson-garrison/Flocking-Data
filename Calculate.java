 /*
 Author:      Jayson C. Garrison
 ID:          1555563
 Lab Section: N/A
 Date:        09/29/2021 
 Course:      N/A
 Section      01
 File Name:   Calculate.java
 Classes:     Calculate
 Description: To calculate mean and std from the Flocking dataset for DAI21 and other submissions
*/
import java.util.*;

import javax.print.attribute.standard.DateTimeAtCompleted;

import java.io.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;

public class Calculate <E>{
    public static void main(String[] args) { 
        
        final String convTS = "Convergence Timestep,.csv";
        // relative paths
        // home
        // final String path = "/home/jayso/Flocking-Data/";
        // tumaster
        final String path = "/home/tumaster/jayso/Flocking-Data/";

        // declare data structures
        
        int[][] dataIZ = new int[24][2];
        int[][] dataPZ = new int[24][2];
        int[][] data3Z = new int[24][2];

        File data = new File(path + "aligned/");
        File fdata = new File(path + "unaligned/");

        // loop thru data and fdata (36 each)
        // for each dir, print the name with a ":"
        // then call two methods that go into that dir and calculate the mean and std.
        // done
        //System.out.println(data.isDirectory());
        //System.out.println(fdata.getName());
        try {
            //FileWriter writeIt = new FileWriter( "MeanStd.txt" );
            //DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm:ss");
            //LocalDateTime current = LocalDateTime.now();
            //String fileName = dtf.format(current) + "MeanStd.txt";
            PrintWriter fileOut = new PrintWriter( new FileWriter( "MeanStd2.txt" ) );
            String[] dirsData = data.list();
            String[] dirsFData = fdata.list();
            String[] totalData = new String[72];
            for (int i = 0; i < totalData.length; i++) {
                if ( i < 36) {
                    totalData[i] = dirsData[i];
                } // end if
                else {
                    totalData[i] = dirsFData[i - 36];
                } // end else
            } // end for

            for (int i = 0; i < totalData.length; i++) {
                int j = 0;
                File temp;
                // print name for each into the out file
                fileOut.print(totalData[i] + ": ***************");

                // pretty filthy hardcode but we are running out of time
                // would like to automate this better and make it more general
                // read this file and call methods to calculate mean and stdv
                if ( i < 36) {
                    temp = new File(path + "aligned/" + totalData[i] + "/" + convTS);
                } // end if
                else {
                    temp = new File(path + "unaligned/" + totalData[i] + "/" + convTS);
                } // end else
                Scanner read = new Scanner(temp);
                double[] entries = new double[200];
                while (read.hasNextDouble()) {
                    entries[j] = read.nextDouble();
                    j++;
                } // end while

                // printing the mean and stdv
                fileOut.print("Mean> " + calculateMean(entries) + " ");
                fileOut.print("Stdv> " + calculateStdv(entries) + "***************");
                fileOut.println();
            } // end for
            fileOut.close();
        } catch (FileNotFoundException e) {
            System.out.println("FileNotFoundException error");
        } catch (IOException er) {
            System.out.println("IO Error");
        } // end catch
        

    } // end main 
    /**
     * calculates and stores the mean of a set of data and
     * stores it into the 10th index of DataSet
     * @param DataSet
     */
    public static double calculateMean(double[] dataSet ) {
        double sum = 0.0;
        for ( int i = 0; i < dataSet.length; i++) {
            sum = sum + dataSet[i];
        } // end for
        return sum / dataSet.length;
    } // end calculateMean

    /**
     * calculates and stores the standard deviation of a set of data
     * and stores it into the 11th index of DataSet
     * @param DataSet
     */
    public static double calculateStdv( double[] dataSet ) { 
        // declare variables
        double stdv;
        double sum = 0;
        double mean = calculateMean(dataSet);
        if (mean == -1){
            return -1;
        } // end if
        else {
            for ( int i = 0; i < dataSet.length; i++) {
                sum = sum + Math.abs(Math.pow(dataSet[i] - mean, 2));
            } // end for
            return Math.pow(sum / dataSet.length, .5);
        } // end else
    } // end calculateStdv

    public static <E> void print(ArrayList<E>[] data) {
        for (int i = 0; i < data.length; i++) {
            for (E elm : data[i]) {
                System.out.println(elm);
            } // end for
        } // end for
    } // end print

} // end calculate