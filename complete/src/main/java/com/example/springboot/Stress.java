package com.example.springboot;

public class Stress {

    public int calculate(int x, int y, int z) {
        // Check for z being zero and return Integer.MAX_VALUE as a special case handling
        if (z == 0) {
            return Integer.MAX_VALUE;
        }
    
        int rx = (int) Math.ceil(Math.sqrt(x));
        int sqz = z * z;
    
        // For non-negative y, add (z*z - 1) before division to round up
        // For negative y, the usual division rounds towards zero
        return y >= 0 ? (rx * y + sqz - 1) / sqz : (rx * y) / sqz;
    }
    
    public static void main(String[] args) {
        Stress stress = new Stress();
        System.out.println("Calculated Stress: " + stress.calculate(25, 100, 10)); 
    }
}
