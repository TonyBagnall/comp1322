package week7_examples.functors.distances;

public class MaxDistance implements Distance{
    @Override
    public double distance(double[] ar1, double[] ar2) {
        double max = Math.abs(ar1[0]-ar2[0]);
        for(int i=1;i<ar1.length;i++){
            double temp = Math.abs(ar1[i]-ar2[i]);
            if(temp>max)
                max=temp;
        }
        return max;
    }
}
