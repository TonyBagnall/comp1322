package week7_examples.functors.distances;

/**
 * Demo example for Distance based
 */
public class NearestNeighbour {

    public static int nearestNeighbour(double[][] training, double[] test, Distance dist){
        int closest=0;
        double closestDistance=Double.MAX_VALUE;
        for(int i=0;i<training.length;i++){
            double d = dist.distance(training[i], test);
            if(d<closestDistance){
                closest=i;
                closestDistance=d;
            }
        }
        return closest;


    }

    public static void main(String[] args) {
        double[] a1 = {1,2,3,4};
        double[] a2 = {0,0,0,0};
        MaxDistance max = new MaxDistance();
        EuclideanDistance ed = new EuclideanDistance();
        System.out.println(" MAX DIST  ="+max.distance(a1,a2));
        System.out.println(" Euclidean DIST  ="+ed.distance(a1,a2));
        double[][] trainData= {{2,2,2,2},{10,20,30,40},{0,0,0,3},{5,5,5,5} };
        int p1 = nearestNeighbour(trainData,a2,max);
        int p2 = nearestNeighbour(trainData,a2,ed);
        System.out.println(" Max NN = "+p1);
        System.out.println(" ED NN = "+p2);


// Define an interface with a static variable, a static method, and a default method


    }


}
