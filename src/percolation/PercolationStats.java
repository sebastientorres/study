package percolation;

/**
 * Created by st on 05/02/16.
 */
public class PercolationStats {

    public PercolationStats(int N, int T){

        if(N <= 0 || T <= 0){
            throw new IllegalArgumentException("N and \\ or T should be 1 or more.");
        }

        for (int i = 0; i < T; i++){
            Percolation percolation = new Percolation(N);
        }

    }

    public double mean() {

        double result = 0;


        return result;

    }

    public double stddev() {

        double result = 0;

        return result;
    }

    public double confidenceLo() {

        double result = 0;

        return result;
    }

    public double confidenceHi() {

        double result = 0;

        return result;
    }

    public static void main(String[] args){

        int dimension = Integer.valueOf(args[0]);
        int numberOfTimes = Integer.valueOf(args[1]);

        PercolationStats stats = new PercolationStats(dimension, numberOfTimes);

    }
}
