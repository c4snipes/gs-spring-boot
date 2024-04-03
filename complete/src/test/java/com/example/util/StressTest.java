package com.example.util;

import java.util.stream.Stream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import com.example.springboot.Stress;

public class StressTest {

    private static Stream<Arguments> generateTestData() {
        // Example of generating boundary values for each parameter
        int[] xs = {8, 9, 10, 35, 36, 37}; // x's boundary values and near-boundary values
        int[] ys = {-301, -300, -299, 299, 300, 301}; // y's boundary values and near-boundary values
        int[] zs = {0, 1, 2, 19, 20, 21}; // z's boundary values and near-boundary values, including z = 0 for special case

        Stream.Builder<Arguments> builder = Stream.builder();
        
        // Generating combinations of boundary values for x, y, z
        for (int x : xs) {
            for (int y : ys) {
                for (int z : zs) {
                    // Expected results need to be calculated based on the Stress class's logic
                    int expectedResult = calculateExpectedResult(x, y, z);
                    builder.add(Arguments.of(x, y, z, expectedResult));
                }
            }
        }

        return builder.build();
    }

    @ParameterizedTest
    @MethodSource("generateTestData")
    void testCalculate(int x, int y, int z, int expectedResult) {
        Stress stress = new Stress();
        int actualResult = stress.calculate(x, y, z);
        
        Assertions.assertEquals(expectedResult, actualResult, 
                "The calculated result does not match the expected result for inputs x=" + x + ", y=" + y + ", z=" + z);
    }
    
    private static int calculateExpectedResult(int x, int y, int z) {
        // Handling the special case where z is 0
        if (z == 0) {
            return Integer.MAX_VALUE;
        }
        
        // Find the smallest integer greater than or equal to the square root of x
        int rx = (int) Math.ceil(Math.sqrt(x));
    
        // Calculate the square of z to use in the division
        int sqz = z * z;
    
        // Perform the calculation with rounding up to the nearest integer
        int result;
        if (y >= 0) {
            // If y is non-negative, add (z*z - 1) to ensure the result is rounded up
            result = (rx * y + sqz - 1) / sqz;
        } else {
            // If y is negative, the usual integer division will round towards zero, which is correct
            result = (rx * y) / sqz;
        }
        
        return result;
    }
}