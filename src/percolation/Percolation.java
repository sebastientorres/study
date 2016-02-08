package percolation;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
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
        if(!(i-1 < 0)){
            if(isOpen(i-1,j)){
                union(i*j, (i-1)*j);
            }
        }

        if(!(i+1 >= systemDimension)){
            if(isOpen(i+1, j)){
                union(i*j, (i+1)*j);
            }
        }

        if(!(j-1 < 0)){
            if(isOpen(i, j-1)){
                union(i*j, i*(j-1));
            }
        }

        if ( !(j+1 >= systemDimension)){
            if(isOpen(i, j+1)){
                union(i*j, i*(j+1));
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

    public boolean percolates(){
        for (int i = 0 ; i < systemDimension; i++){
            for (int j = system.length - systemDimension; j < system.length ; j++){
                if(isOpen(i, j)) {
                    if (!connected(i, j)) {
                        return false;
                    }
                }
            }
        }
        return true;
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

        System.out.println(find(20));
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
        int siteSize = 10;
        values.remove(0);

        Percolation percolation = new Percolation(siteSize);

        percolation.open(2,2);
        percolation.open(2,3);
        percolation.open(1,1);
        percolation.open(1,2);
        percolation.open(10,1);
        percolation.open(3,10);
        percolation.open(10,10);

        System.out.println(percolation.connected(2,2,2,3));


        percolation.printSystem();

    }

}
