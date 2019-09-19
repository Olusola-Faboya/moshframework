package moshproject.common;

public class NormalWeigthValues {
	
	public static double[] scaleValues(double[] vals) {
        double[] result = new double[vals.length];       
        double sum = sumArray(vals);      
        for (int x = 0; x < vals.length; x++) {     
        result[x] = ((vals[x] / sum));       
        }
        return result;
    }
 
    // The standard collection classes don't have array min and max.	
	 public static double sumArray(double[] vals) {
	    	double sum =0.0;
	    	for(int i = 0;i<vals.length;i++){
	    	 sum=sum +vals[i];       
	        }
	        return sum;
	    }
	 
	 
//    public static double minArray(double[] vals) {
//    	double min =vals[0];
//    	for(int i = 0;i<vals.length;i++){
//    	 min=Math.min(min,vals[i]);       
//        }
//        return min;
//    }
// 
//    public static double maxArray(double[] vals) {
//        double max = vals[0];
//        for (int x = 0; x < vals.length; x++) {
//        	max=Math.max(max,vals[x]);  
//            
//        }
//        return max;
//    }


}
