/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GAKnapsack;

/**
 *
 * @author xchen_000
 */
// genotype
public class Individual{
    private int geneLength;     // gene length
    private int[] gene;         // gene in int array
    private double fitness;     // fitness in double count for value
    
    public Individual(int length){
        this.geneLength = length;
        this.gene = new int[length];
        this.fitness = 0;

        for (int i = 0; i < geneLength; i++){
            double random = Math.random();
            // random choose genotype
            if (random <= 0.5){
                gene[i] = 0;
            } 
            else{
                gene[i] = 1;
            }
        }
    }

    // Geetter and setter
    public int getGeneLength() {
        return geneLength;
    }

    public int[] getGene() {
        return gene;
    }

    public void setGene(int[] gene) {
        this.gene = gene;
    }
    
    // Get index Gene
    public int getIndexGene(int index) {
        return this.gene[index];
    }
    
    // clone individual
    public int[] clone(){
        int[] clone = new int[geneLength];
        for(int i = 0; i < getGeneLength(); i++){
            clone[i] = gene[i];
        }
        return clone;
    }
    
    // flipGene to mutate
    public void mutateGene(int index){
        this.gene[index] = Math.abs(gene[index]-1);
    }
    
    // get fitness for the individual
    public double getFitness() {
        double fitness = 0;
        fitness = Knapsack.calFitness(gene);
        return fitness;
    }
    
    // get total weight of fitness individual
    public double getWeight(){
        double kweight = 0;

        kweight = Knapsack.getWeight(gene);

        return kweight;
    }
}
