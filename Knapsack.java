/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GA;


/**
 *
 * @author xchen_000
 */
public class Knapsack {
    protected static int capacity;        //Knapsack capacity
    protected static int genelength;      //gene length
    protected static double[] weight;     //weight in all
    protected static double[] value;      //value in all
    
    // weight in knapsack
    public static double getWeight(int[] gene) {
        double kweight = 0;

        for (int i = 0; i < gene.length; i++) {
            if (gene[i] == 1) {
                kweight += weight[i];
            }
        }

        return kweight;
    }
    
    // value in knapsack
    public static double getValue(int[] gene) {
        double kvalue = 0;

        for (int i = 0; i < gene.length; i++) {
            if (gene[i] == 1) {
                kvalue += value[i];
            }
        }

        return kvalue;
    }
    
    // fitness = value in knapsack
    public static double calFitness(int[] gene){
        return getValue(gene);
    }
    
    // check if gene value overweight
    public static boolean isOverWeight(int[] gene){
        boolean isOverWeight = false;
        double weightForAll = getWeight(gene);
        if(weightForAll>capacity){
            isOverWeight = true;
        }
        return isOverWeight;
    }
}
