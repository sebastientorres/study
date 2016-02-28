//package percolation;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;
import java.util.Random;

/**
 * Created by st on 04/02/16.
 */
public class Percolation {

    private int numberOfSites;
    private int systemDimension;
    private int[][] system;
    private int numberOfOpenSites;

    private WeightedQuickUnionUF wquf;

    public Percolation(int N) {

        if (N <= 0) {
            throw new IllegalArgumentException("Site dimension can't be 0 or less.");
        }

        wquf = new WeightedQuickUnionUF(N*N);

        systemDimension = N;
        numberOfSites = N*N;
        numberOfOpenSites = 0;
        system = new int[N][N];
        initialiseSystem(N);
    }

    private void initialiseSystem(int N) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                system[i][j] = 0;
            }
        }
    }
    //    @Override
    private boolean connected(int i , int j , int k , int l) {
        i--;
        j--;
        k--;
        l--;
        int p = (i * systemDimension) + j;
        int q = (k * systemDimension) + l;
        return wquf.connected(p , q);
    }
    public void open(int i , int j) {

        checkBoundary(i , j);

        // Doing [i|j]-- to address off by 1.
        i--;
        j--;

        int p = (systemDimension * i) + j;

        if (!(i-1 < 0)) {
            if (isOpen(i-1 , j)) {
                wquf.union(p , ((systemDimension * (i-1)) +j));
            }
        }

        if (!(i+1 >= systemDimension)) {
            if (isOpen(i+1, j)) {
                wquf.union(p , (systemDimension * (i+1)) + j);
            }
        }

        if (!(j-1 < 0)) {
            if (isOpen(i, j-1)) {
                wquf.union(p , (systemDimension * i) + (j-1));
            }
        }

        if (!(j+1 >= systemDimension)) {
            if (isOpen(i , j+1)) {
                wquf.union(p , (systemDimension * i) + (j+1));
            }
        }

        system[i][j] = 1;

    }

    public boolean isOpen(int i , int j) {
        checkBoundary(i , j);
        return system[i][j] == 1;
    }

    public boolean isFull(int i , int j) {
        checkBoundary(i , j);
        i--;
        j--;

        for (int counter = 0; counter < systemDimension; counter++) {
            if ( wquf.connected(( systemDimension * i) + j , counter)) {
                return true;
            }
        }
        return false;
    }

    // i and j are the coordinates of the system as a grid (2d array), not the system as a
    // 1d array - we need to translate \ convert 2d to 1d...
    // i and j need to become p and q at the top and bottom of the system

    public boolean percolates() {
        for (int i = 0; i < systemDimension; i ++) {
            for (int j = (numberOfSites - systemDimension) - 1; j < numberOfSites; j ++) {
                    if (wquf.connected(i , j)) {
                        return true;
                }
            }
        }
        return false;
    }

    private void checkBoundary(int i , int j) {
        if (i < 0 || j < 0) {
            throw new IndexOutOfBoundsException("i or j can't be less than 0");
        }
        if (i > systemDimension || j > systemDimension) {
            throw new IndexOutOfBoundsException("i or j can't be greater than " + systemDimension);
        }
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
        for (int p = 0; p < numberOfSites; p++) {
            System.out.print(p + "\t");
        }
        System.out.println();
        for (int p = 0; p < numberOfSites; p++) {
            System.out.print(wquf.find(p) + "\t");
        }
    }

    private double getNumberOfOpenSites() {
        if (numberOfOpenSites > 0) {
            return numberOfOpenSites;
        } else {
            for (int i = 0; i < systemDimension; i++) {
                for (int j = 0; j < systemDimension; j++) {
                    if (system[i][j] == 1) {
                        numberOfOpenSites++;
                    }
                }
            }
            return numberOfOpenSites;
        }
    }

    private double getOpenSitePercentage() {
        return Double.valueOf(getNumberOfOpenSites()) / Double.valueOf(numberOfSites);
    }

    public static void main(String[] args) {

        int siteSize = 6;

        Percolation percolation = new Percolation(siteSize);

        while (!percolation.percolates()) {
            Random pRand = new Random();
            Random qRand = new Random();

            int p = pRand.nextInt(siteSize + 1);
            int q = qRand.nextInt(siteSize + 1);

            if(p == 0) {
                p++;
            }
            if (q == 0) {
                q++;
            }
            percolation.open(p, q);
        }
    }

}
