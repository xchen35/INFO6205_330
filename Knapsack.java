/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GAKnapsack;

import java.util.logging.Logger;



/**
 *
 * @author xchen_000
 */
public class Knapsack {
    
//        int individualNum = 100 ;
//        int geneLength = 100;
//        double crossRate = 0.9;
//        double mutRate = 0.05;
    protected static int capacity = 4000;       // Knapsack capacity
    protected static int genelength = 100;      // gene length
    protected static double[] weight;           // weight in all
    protected static double[] value;            // value in all
    private static double maxFitness;           // value of max fitness in double
    private static Individual ind;              // individual with max fitness   

    private final static Logger LOGGER = Logger.getLogger(Knapsack.class.getName());
    
    //Constructor
    public Knapsack(){
        weight = new double[genelength];
        value = new double[genelength];
        for(int i=0; i<genelength; i++){
            weight[i] = Math.random()*100;
            value[i] = Math.random()*100;
        }
//        System.out.println("1");
        maxFitness = 0;
        ind = new Individual(genelength);
    }
    
    // test function
    public static void test(){
        for(int i=0; i<genelength; i++){
//            System.out.println(weight[i] + " " + value[i]);
            LOGGER.info("Auto Generate individual weight and value"+i+": "+weight[i] + ", " + value[i]);
        }
    }
    
    // run 400 times evolve
    public static void run(){
        GA ga = new GA();
        
        Population pop = ga.initialPopulation();
        
        int count = 0;
        
        // get maxFit with elolve
        while(true){
            count++;
//            System.out.println("Generation: "+count);
            LOGGER.info("Start Evolve Generation = "+count);
            pop = ga.evolve(pop);
            
            double tempFitness = pop.getMaxFitness();
//            System.out.println("1");
            if(count<400){
                if(maxFitness<tempFitness){
                    maxFitness = tempFitness;
                    ind = pop.getFittest();
//                    System.out.println("maxFit until "+count+ ": "+maxFitness);
//                    System.out.println("maxFit weight until "+count+ ": "+ ind.getWeight());
                    LOGGER.info("maxFit until "+count+ ": "+maxFitness);
                    LOGGER.info("maxFit weight until "+count+ ": "+ ind.getWeight());
                }
            }

            if(count == 400){
                break;
            }
            
        }
        
        //Individual result = pop.getFittest();
        String report = "Best Gene Set with value: \n";
        report += maxFitness + "\n";
        report += "Best value with ind: " + ind.getFitness() +"\n";
        for (int i = 0; i < ind.getGene().length; i++) {
            report += (i + 1) + " " + ind.getGene()[i] + "\n";
        }
        
//        System.out.println(report);
        LOGGER.info(report);
        
        for(int i=0; i<pop.getIndividuals().length; i++){
//            System.out.println(pop.getIndividuals()[i].getFitness());
            double temp = pop.getIndividuals()[i].getFitness();
            LOGGER.info("Individual: "+temp);
        }
//        System.out.println(ind.getFitness());       
    }
    
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
    // for unit test
    public double getW(int[] gene, double[] weight) {
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
    
    // for unit test
    public double getV(int[] gene, double[] value) {
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
    
    // for unit test
    public boolean isOverW(int[] gene, int cap, double weight[]){
        boolean isOverWeight = false;
        double weightForAll = getW(gene, weight);
        if(weightForAll>cap){
            isOverWeight = true;
        }
        return isOverWeight;
    }
    
     public static void main(String[] args){
         Knapsack ks = new Knapsack();
         ks.test();
         ks.run();
     }
}
