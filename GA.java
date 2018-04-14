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
public class GA {
    
    // function initialization
    public Population initialPopulation(){
        int individualNum = 100;
        int geneLength = 100;
        double crossRate = 0.9;
        double mutRate = 0.05;
        Population p = new Population(individualNum, geneLength, crossRate, mutRate);

        for (int i = 0; i < individualNum; i++) {
            int[] temArray = new int[geneLength];
            for (int j = 0; j < geneLength; j++) {
                temArray[j] = (int) Math.round(Math.random());
            }

            if (!Knapsack.isOverWeight(temArray)) {
                while (!Knapsack.isOverWeight(temArray)) {
                    int ram = (int) (Math.random()*temArray.length);
                    if (temArray[ram] == 1) {
                        temArray[ram] = 0;
                    }
                }
            }

            p.getIndividual(i).setGene(temArray);

        }
        return p;
    }
    
    // function evelotionLoop
    public Population evelotionLoop(Population pop){
        Population greater = new Population(pop.getIndividualNum(), pop.getGeneLength(), pop.getCrossRate(), pop.getMutationRate());
        while(true){
            
            break;
        }
        return greater;
    }
    
    // function selection
    public Individual selection(Population pop){
        // greater fitness means greater selection rate;
        
        
        
        
        
        
        //
        int index=0;
        index = (int) Math.round(Math.random()*pop.getIndividualNum());
        return pop.getIndividual(index);
    }
    
    // function crossover
    public void crossover(Individual i1, Individual i2) {
        // we must crossover or we are not done
        while (true) {
            // clone gene from individual
            int[] crossGeno1 = i1.clone();
            int[] crossGeno2 = i2.clone();

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

            // then change the clone gene to individual if not overweight or we start again
            if (!Knapsack.isOverWeight(crossGeno1) && !Knapsack.isOverWeight(crossGeno2)) {
                i1.setGene(crossGeno1);
                i2.setGene(crossGeno2);
                break;
            }
        }
    }
    
    // Mutation
    public void mutation(Individual i){
        while (true) {
            // clone gene from individual
            int[] mutatGeno = i.clone();

            // choose mutate location
            int index = (int)(Math.random()*i.getGeneLength());
            int mom = i.getGene()[index];
            if (mom == 1) {
                mutatGeno[index] = 0;
            } else {
                mutatGeno[index] = 1;
            }

            //　then change the clone gene to individual if not overweight or we start again
            if (!Knapsack.isOverWeight(mutatGeno)) {
                i.setGene(mutatGeno);
                break;
            }
        }
    }
    
}
