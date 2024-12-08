package com.cryptoia.neuron;

import java.util.ArrayList;
import java.util.List;

import com.cryptoia.utilities.Pair;

public class Neuron{
    Double biases;
    
    List<Pair<Double, Double>> inputs;

    public Neuron(){
        biases = Double.valueOf(0);
        this.inputs = new ArrayList<>();
    }
    public Neuron(List inputs){
        biases = Double.valueOf(0);
        this.inputs = inputs;
    }

    public void setBiases(Double biases){
        this.biases = biases;
    }
    public float weightedSum(){
        float output = 0;
        for (Pair<Double, Double> entry : inputs) {
            output += (entry.getFirst() * entry.getSecond()) + biases;
        }
        return output;
    }
    public Double activationFunction(){
        return 1/(1+ Math.exp(-weightedSum()));
    }
}