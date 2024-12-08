package com.cryptoia.neuron;

import java.util.ArrayList;
import java.util.List;

import com.cryptoia.utilities.LossFunction;
import com.cryptoia.utilities.Pair;

public class Neuron{
    Double biases;
    
    List<Pair<Double, Double>> inputs;

    public Neuron(){
        biases = Double.valueOf(0);
        this.inputs = new ArrayList<>();
    }
    public Neuron(List<Pair<Double, Double>> inputs){
        biases = Double.valueOf(0);
        this.inputs = inputs;
    }

    public void setBiases(Double biases){
        this.biases = biases;
    }
    public void setInputs(List<Pair<Double, Double>> inputs){
        this.inputs = inputs;
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
    //Return for one neuron the loss function 
    public static Double lossFunction(Double yPredict, Double yReal, LossFunction l){
        return l.lossFunction(yPredict, yReal);
    }

    // Retourne la dérivée de la sortie par rapport au biais
    public Double derivativeActivationFunctionWithRespectToBias() {
        // Calcul de la sortie activée
        Double activation = activationFunction();
        // Calcul de la dérivée de la fonction sigmoïde
        return activation * (1 - activation); // σ'(z) = σ(z) * (1 - σ(z))
    }

}
