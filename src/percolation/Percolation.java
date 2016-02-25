package percolation;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

/**
 * Created by st on 04/02/16.
 */
public class Percolation extends WeightedQuickUnionUF{

    int numberOfSites;
    int systemDimension;
    int[][] system;

    public Percolation(int N) {
        super(N*N);
        systemDimension = N;
        numberOfSites = N*N;
        system = new int[N][N];
        initialiseSystem(N);
    }

    private void initialiseSystem(int N){
        int value = 1;
        for (int i = 0; i < N; i++){
            for (int j = 0; j < N; j++){
                system[i][j]=0;
                value++;
            }
        }
    }

    public void open(int i, int j){
        // Doing [i|j]-- to address off by 1.
        i--;
        j--;

        checkBoundary(i,j);
        int p = (systemDimension * i) + j;

        if(!(i-1 < 0)){
            if(isOpen(i-1,j)){
                union( p, ((systemDimension * (i-1)) +j ));
            }
        }

        if(!(i+1 >= systemDimension)){
            if(isOpen(i+1, j)){
                union( p, (systemDimension * (i+1)) + j);
            }
        }

        if(!(j-1 < 0)){
            if(isOpen(i, j-1)){
                union(p, (systemDimension * i) + (j-1));
            }
        }

        if ( !(j+1 >= systemDimension)){
            if(isOpen(i, j+1)){
                union(p, (systemDimension * i) + (j+1));
            }
        }

        system[i][j] = 1;

    }

    public boolean isOpen(int i, int j){
        if(system[i][j] == 1){
            return true;
        }
        else {
            return false;
        }
    }

    public boolean isFull(int i, int j){
        boolean isFull = false;

        return isFull;
    }

    // i and j are the coordinates of the system as a grid (2d array), not the system as a
    // 1d array - we need to translate \ convert 2d to 1d...
    // i and j need to become p and q at the top and bottom of the system

    public boolean percolates(){
        for (int i = 0 ; i < systemDimension; i++){
            for (int j = (numberOfSites - systemDimension)-1; j < numberOfSites ; j++){
                    if (connected(i, j)) {
                        return true;
                }
            }
        }
        return false;
    }

    private void checkBoundary(int i, int j){
        if(i < 0 || j < 0){
            throw new IndexOutOfBoundsException("i or j can't be less than 0");
        }
        if(i > systemDimension || j > systemDimension){
            throw new IndexOutOfBoundsException("i or j can't be greater than " + systemDimension);
        }
    }

    //    @Override
    public boolean connected(int i, int j, int k, int l){
        i--;
        j--;
        k--;
        l--;
        int p = i * j;
        int q = k * l;
        return connected(p , q);
    }

    private void printSystem() {

        for (int i = 0; i < system.length; i++) {
            for (int j = 0; j < system.length; j++) {
                System.out.print(system[i][j] + "\t");
            }
            System.out.println();
        }
        System.out.println();

    }

    private void printWquf(){
        for(int p = 0; p < numberOfSites; p++){
            System.out.print(p + "\t");
        }
        System.out.println();
        for(int p = 0; p < numberOfSites; p++){
            System.out.print(find(p) + "\t");
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

        int siteSize = 20;
        values.remove(0);

        Percolation percolation = new Percolation(siteSize);

        while(!percolation.percolates()){
            Random pRand = new Random();
            Random qRand = new Random();

            int p = pRand.nextInt(siteSize+1);
            int q = qRand.nextInt(siteSize+1);

            if(p == 0){
                p++;
            }
            if (q == 0){
                q++;
            }
            percolation.open(p, q);
            System.out.println("p = " + p + " q = " + q);
        }

        percolation.printSystem();
        percolation.printWquf();
        System.out.println("\npercolation.percolates = " + percolation.percolates());


    }

}
