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
public class GA {
    
    private final static Logger LOGGER = Logger.getLogger(GA.class.getName());
    
    // function initialization
    public Population initialPopulation(){
        int individualNum = 1000;
        int geneLength = 100;
        double crossRate = 0.9;
        double mutRate = 0.05;
        Population p = new Population(individualNum, geneLength, crossRate, mutRate);

        for (int i = 0; i < individualNum; i++) {
            int[] individualGene = new int[geneLength];
            for (int j = 0; j < geneLength; j++) {
                individualGene[j] = (int) Math.round(Math.random());
            }
            
            // make all element legal
            if (Knapsack.isOverWeight(individualGene)) {                
                while (Knapsack.isOverWeight(individualGene)) {
                    int shiftPoint = (int) (Math.random()*individualGene.length);
                    if (individualGene[shiftPoint] == 1) {
                        individualGene[shiftPoint] = 0;
                    }
                }
            }
//            System.out.println("initial");
            p.getIndividual(i).setGene(individualGene);

        }
        p.setIndividuals(p.sort());
        return p;
    }
    
    //  evolution mechanism
    public Population evolve(Population pop){

        Population greater = new Population(pop.getIndividualNum(), pop.getGeneLength(), pop.getCrossRate(), pop.getMutationRate());
            
            // selection
            Individual i1 = selection(greater);
            Individual i2 = selection(greater);
            while(i1==i2){
                i2 = selection(greater);
            }
//            System.out.println("1" + (Math.random()<greater.getCrossRate()));
            //crossover
            if(Math.random()<greater.getCrossRate()){
                crossover(i1,i2);
            }
//            System.out.println("2");
            //Mutation
            if(Math.random()<greater.getMutationRate()){
                mutation(i1);
            }
//            System.out.println("3");
        //
        greater.setIndividuals(greater.sort());
        return greater;
    }
    
    // function selection
    public Individual selection(Population pop){
        // greater fitness means greater selection rate;
        // more close to individuals[0] should be more fit to the model, so we increase selectRate using reverse cumulate
        double[] cumulative = pop.getSelectRates();
        for (int i = cumulative.length -1; i >0 ; i--) {
            cumulative[i] += cumulative[i - 1];
        }

        // find hit time when we check every cumulative
        int[] hitNum = new int[cumulative.length];
        for (int i = 0; i < hitNum.length; i++) {
            hitNum[i] = 0;
        }
        for (int i = 0; i < cumulative.length; i++) {
            double random = Math.random();
            for (int j = 0; j < cumulative.length - 1; j++) {
                // if shift point <= cumulative[0]
                if (j == 0 && random <= cumulative[0]) {
                    hitNum[0]++;
                    break;
                } else if (random > cumulative[j] && random <= cumulative[j + 1]) {
                    hitNum[j + 1]++;
                    break;
                }
            }
        }

        // find the most hit point, if there are multiple, choose the first one
        int reNum = 0;
        for (int i = 1; i < hitNum.length; i++) {
            if (hitNum[i] > hitNum[reNum]) {
                reNum = i;
            }
        }
        return pop.getIndividual(reNum);
        
        
        
        
        
        // random chosen
//        int index=0;
//        index = (int) Math.round(Math.random()*pop.getIndividualNum());
//        return pop.getIndividual(index);
    }
    
    // function crossover
    public void crossover(Individual i1, Individual i2) {
        // we must crossover or we are not done
        while (true) {
            // clone gene from individual
            int[] crossGeno1 = i1.clone();
            int[] crossGeno2 = i2.clone();
//            System.out.println("1");
            int[] cross = new int[i1.getGeneLength()];
            
            // sexual crossover location select
            int crossLoc1 = (int) (Math.random()*cross.length);
            int crossLoc2 = (int) (Math.random()*cross.length);
            // make location1 and location2 different
            while(crossLoc1 == crossLoc2){
                crossLoc2 = (int) (Math.random() * cross.length);
            }
            // choose gene location >location1 and <location2 to crossover
            for (int i=0; i<cross.length; i++) {
                if (i>crossLoc1 && i<crossLoc2) {
//                if (i==crossLoc1 || i==crossLoc2) {
                    cross[i] = 1;
                }else {
                    cross[i] = 0;
                }
            }

            // crossover every selected location
            for (int i=0; i<cross.length; i++) {
                if (cross[i] == 1) {
                    int mom = i1.getGene()[i];
                    int dad = i2.getGene()[i];
                    crossGeno1[i] = dad;
                    crossGeno2[i] = mom;
                }
            }
//            System.out.println(Knapsack.getWeight(crossGeno1));
            // then change the clone gene to individual if not overweight or we start again
            if (!Knapsack.isOverWeight(crossGeno1) && !Knapsack.isOverWeight(crossGeno2)) {
                if(Knapsack.getValue(crossGeno1)>i1.getFitness()||Knapsack.getValue(crossGeno1)>i2.getFitness()||Knapsack.getValue(crossGeno2)>i1.getFitness()||Knapsack.getValue(crossGeno2)>i2.getFitness()){
                    i1.setGene(crossGeno1);
                    i2.setGene(crossGeno2);
                    if(i1.getWeight()>4000||i2.getWeight()>4000){
//                        System.out.println("OverWeight: " +i1.getWeight() + " " +i2.getWeight());
                    }
                    break;
                }
            }
        }
    }
    
    // Mutation
    public void mutation(Individual i){
        while (true) {
            // clone gene from individual
            int[] mutatGeno = i.clone();

            // choose mutate location single point
            int index = (int)(Math.random()*i.getGeneLength());
            int mom = i.getGene()[index];
            if (mom == 1) {
                mutatGeno[index] = 0;
            } else {
                mutatGeno[index] = 1;
            }
            
            //　then change the clone gene to individual if not overweight or we start again
            if (!Knapsack.isOverWeight(mutatGeno)) {
//                System.out.println("mutateWith weight: "+ Knapsack.getWeight(mutatGeno));
                LOGGER.info("mutateWith weight: "+ Knapsack.getWeight(mutatGeno));
                i.setGene(mutatGeno);
                break;
            }
        }
    }
    
}
