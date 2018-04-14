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
public class Population {
    private double crossRate;     // crossover rate
    private double mutationRate;   // mutation rate

    private Individual[] individuals;   // array of individuals in the population
    private int individualNum;          // individual count
    private int geneLength;             // individual gene lenth
    
    // Constructor
    public Population(int individualNum, int geneLength, double crossRate, double mutationRate) {
        this.crossRate = crossRate;
        this.mutationRate = mutationRate;
        this.individualNum = individualNum;
        this.geneLength = geneLength;
        individuals = new Individual[individualNum];
        for(int i=0; i<individualNum;i++){
            Individual single = new Individual(geneLength);
            individuals[i] = single;
        }
    }

    public double getCrossRate() {
        return crossRate;
    }

    public void setCrossRate(double crossRate) {
        this.crossRate = crossRate;
    }

    public double getMutationRate() {
        return mutationRate;
    }

    public void setMutationRate(double mutationRate) {
        this.mutationRate = mutationRate;
    }

    public int getIndividualNum() {
        return individualNum;
    }

    public int getGeneLength() {
        return geneLength;
    }
    
    public Individual getIndividual(int i) {
        return individuals[i];
    }

    public void setIndividual(int index, Individual i) {
        individuals[index] = i;
    }
    
    // get max fitness individual
    public Individual getFittest() {
        Individual fittest = individuals[0];
        for (int i = 0; i < getIndividualNum(); i++) {
            if (fittest.getFitness() < getIndividual(i).getFitness()) {
                fittest = getIndividual(i);
            }
        }
        return fittest;
    }
    
    // get max fitness in double
     public double getMaxFitness() {
        double maxFit = individuals[0].getFitness();

        for (Individual i : individuals) {
            if (i.getFitness() > maxFit) {
                maxFit = i.getFitness();
            }
        }

        return maxFit;
    } 
}
