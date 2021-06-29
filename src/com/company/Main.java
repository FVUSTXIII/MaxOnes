package com.company;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        int fitnessIndicator = 0;
        Population population = new Population(GeneticAlgorithm.POPULATION_SIZE).initializePopulation();
        GeneticAlgorithm geneticAlgorithm = new GeneticAlgorithm();
        int generationNumber = 0;
        System.out.println("---------------------------------------------");
        System.out.println("Generation # 0" + " | Fittest chromosome fitness: " +
                population.getChromosomes()[0].getFitness());
        fitnessIndicator = printPopulation(population, "Target Chromosomes: " + Arrays.toString(GeneticAlgorithm.TARGET_CHROMOSOME));
        System.out.println("Population's Total Fitness: " + fitnessIndicator);
        while (population.getChromosomes()[0].getFitness() < GeneticAlgorithm.TARGET_CHROMOSOME.length) {
            generationNumber++;
            System.out.println("---------------------------------------------------------");
            population = geneticAlgorithm.evolve(population);
            population.sortChromosomesByFitness();
            System.out.println("Generation #" + generationNumber + " | Fittest chromosome fittest: " +
                    population.getChromosomes()[0].getFitness());
            fitnessIndicator = printPopulation(population, "Target Chromosome: " + Arrays.toString(GeneticAlgorithm.TARGET_CHROMOSOME));
            System.out.println("Population's Total Fitness: " + fitnessIndicator);
        }
    }
    public static int printPopulation(Population population, String heading) {
        int total_fitness = 0;
        int total_function = 0;
        double avg = 0.00;
        System.out.println(heading);
        System.out.println("---------------------------------------------------------");
        for (int x = 0; x < population.getChromosomes().length; x++) {
            System.out.println("Chromosome # " + x + " : " + Arrays.toString(population.getChromosomes()[x].getGenes())
                    + "\t| X Value:     " + ConvertBinToDec(population.getChromosomes()[x].getGenes())
                    + "\t|f(x) = x^2:   " + function(ConvertBinToDec(population.getChromosomes()[x].getGenes()))
                    + "\t| Fitness:     " + population.getChromosomes()[x].getFitness());
            total_fitness += population.getChromosomes()[x].getFitness();
            total_function += function(ConvertBinToDec(population.getChromosomes()[x].getGenes()));
        }
        avg = total_function / 4;
        System.out.println("Total f(x): " + total_function);
        System.out.println("Average:    " + Math.round(avg));
        return total_fitness;
    }
    public static int ConvertBinToDec(int[] arr) {
        char[] decimal_convertion = new char[arr.length];
        int i = 0;
        for(int a: arr) {
            decimal_convertion[i] = Integer.toString(a).charAt(0);
            i++;
        }
        String decimal_convertion_s = new String(decimal_convertion);
        return Integer.parseInt(decimal_convertion_s, 2);
    }
    public static int function(int x) {
        return x*x;
    }
}
