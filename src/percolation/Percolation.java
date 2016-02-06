package percolation;
//
//import edu.princeton.cs.algs4.StdRandom;
//import edu.princeton.cs.algs4.StdStats;
//import edu.princeton.cs.algs4.WeightedQuickUnionUF;

import java.io.FileDescriptor;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
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

        if (siteDimension < 1){
            throw new IllegalArgumentException("site size cannot be less than 1, you entered " + siteDimension);
        }

        site = new int[siteDimension][siteDimension];

    }

    public void open(int i, int j){

        validateInput(i, j);

        i -=1;
        j -=1;

        if(!isOpen(i,j)){
            site[i][j] = 1;
        }
    }

    public boolean isOpen(int i, int j){

        if(site[i][j] == 1){
            return true;
        } else{
            return false;
        }
    }

    public boolean isFull(int i, int j){

        validateInput(i, j);

        boolean isFull = false;

        return isFull;
    }

    public boolean percolates(){

        boolean doesPercolate = false;

        return doesPercolate;

    }

    public void validateInput(int i, int j){

        if(i < 1 || j < 1){
            throw new IndexOutOfBoundsException("can't be less than 1");
        }
        if (i > site.length || j > site.length){
            throw new IndexOutOfBoundsException("can't be more than " + site.length);
        }
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

//        int siteSize = Integer.valueOf(values.get(0));
        int siteSize = 3;
        values.remove(0);

        Percolation percolation = new Percolation(siteSize);

        printSite(percolation);

        percolation.open(1,1);
        System.out.println("is 1,1 open " + percolation.isOpen(1,1));
        percolation.open(2,1);
        percolation.open(3,1);

        printSite(percolation);

    }

    static HashMap<Integer, Integer> parseInputFile(List<String> values){

        HashMap<Integer, Integer> unionOperations = new HashMap<Integer, Integer>();


        return unionOperations;

    }

    static void printSite(Percolation percolation){
        for (int i = 0; i < percolation.site.length; i++){
            for (int j = 0; j < percolation.site.length; j++){
                System.out.print(percolation.site[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
