package week7_examples.functors.distances;

public class EuclideanDistance implements Distance{

    @Override
    public double distance(double[] ar1, double[] ar2) {
        double sum=0;
        for(int i=0;i<ar1.length;i++){
            sum+=(ar1[i]-ar2[i])*(ar1[i]-ar2[i]);
        }
        return sum;
    }
}
