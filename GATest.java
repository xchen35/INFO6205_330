

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import GAKnapsack.Individual;
import GAKnapsack.Knapsack;
import GAKnapsack.Population;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author xchen_000
 */
public class GATest {
    
    @Test
    public void testInitialPopulation() {
        int individualNum = 1000;
        int geneLength = 100;
        double crossRate = 0.9;
        double mutRate = 0.05;
        Population p = new Population(individualNum, geneLength, crossRate, mutRate);
        assert(p.getIndividualNum() == individualNum);
        assert(p.getCrossRate() == crossRate);
        assert(p.getGeneLength() == geneLength);
        assert(p.getMutationRate() == mutRate);
    }
    
    @Test
    public void testKnapsackGetWeight() {
        Knapsack ks = new Knapsack();
        int[] testGene = {1, 0, 0};
        double[] testWeight = {1, 2, 3};
        assert(ks.getW(testGene, testWeight) == 1);
    }
    
    @Test
    public void testKnapsackGetValue() {
        Knapsack ks = new Knapsack();
        int[] testGene = {1, 0, 0};
        double[] testValue = {1, 2, 3};
        assert(ks.getV(testGene, testValue) == 1);
    }
    
    @Test
    public void testKnapsackIsOverW() {
        Knapsack ks = new Knapsack();
        int[] testGene = {1, 1, 0};
        int cap = 10;
        double[] testWeight = {10, 5, 3};
        assert(ks.isOverW(testGene, cap, testWeight) == true);
    }
    
}
