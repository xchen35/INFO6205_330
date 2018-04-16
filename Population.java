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
// phenotype
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
            Individual person = new Individual(geneLength);
            individuals[i] = person;
        }
    }

    public Individual[] getIndividuals() {
        return individuals;
    }

    public void setIndividuals(Individual[] individuals) {
        this.individuals = individuals;
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
     
    // using individual fitness divided by all fitness to find the greater total value of a gene which is more likely to be true
    public double[] getSelectRates() {
        double[] selection = new double[individualNum];
        double sumFitness = 0;
        
        for (Individual i : individuals) {
            sumFitness += i.getFitness();
        }

        for (int i = 0; i < getIndividualNum(); i++) {
            selection[i] = individuals[i].getFitness() / sumFitness;
        }

        return selection;
    }
    
    // Insertion sort for Individuals
    public Individual[] sort(){
        Individual[] tempList = new Individual[individualNum];
        for(int i = 0; i< individualNum; i++){
            tempList[i] = individuals[i];
        }
        Individual temp;
        for(int i = 1; i< individualNum; i++){
            for(int j = i; j>0; j--){
                if(tempList[j].getFitness() < tempList[j-1].getFitness()){
                    temp = tempList[j];
                    tempList[j] = tempList[j-1];
                    tempList[j-1] = temp;                   
                }
            }
        }
//        for(int i=0; i<tempList.length; i++){
//            System.out.println(tempList[i].getFitness());
//        }
        return tempList;
    }
    
    // get total fitness in double
     public double getTotalFitness() {
        double totalFit = 0;

        for (Individual i : individuals) {
            totalFit+= i.getFitness();
        }

        return totalFit;
    }
}
