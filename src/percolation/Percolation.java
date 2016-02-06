package percolation;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

import java.io.FileDescriptor;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.stream.Stream;

/**
 * Created by st on 04/02/16.
 */
public class Percolation {

    int[][]site;

    public Percolation(int N){

        // N is a horrible variable name
        int siteDimension = N;
        site = new int[siteDimension][siteDimension];

    }

    public void open(int i, int j){

    }

    public boolean isOpen(int i, int j){
        boolean isOpen = false;

        return isOpen;
    }

    public boolean isFull(int i, int j){
        boolean isFull = false;

        return isFull;
    }

    public boolean percolates(){

        boolean doesPercolate = false;

        return doesPercolate;

    }

    public static void main(String[] args){

        String file = "/home/st/code/coursera/algosAndData/testData/percolation/input20.txt";

        List<String> values = new ArrayList<String>();

        try (Stream<String> stream = Files.lines(Paths.get(file))){
            for (Object value : stream.toArray()){
                values.add(value.toString());
            }

        } catch (IOException e){
            e.printStackTrace();
        }

        int siteSize = Integer.valueOf(values.get(0));
        values.remove(0);

        Percolation percolation = new Percolation(siteSize);

        printSite(percolation);

        for(int i = 0; i < values.size(); i++){
            System.out.println("values = " + values.get(i));
        }

    }

    static void printSite(Percolation percolation){
        for (int i = 0; i < percolation.site.length; i++){
            for (int j = 0; j < percolation.site.length; j++){
                System.out.print(percolation.site[i][j] + " ");
            }
            System.out.println();
        }
    }
}
