package com.cryptoia.neuron;

import java.util.ArrayList;
import java.util.List;
public class NeuralNetwork {
    List<List<Neuron>> neuralNetwork;
    List<List<Double>> resultActivation;
    
    public NeuralNetwork(List<List<Neuron>> neuralNetwork){
        this.neuralNetwork = neuralNetwork;
        resultActivation = new ArrayList<>();
    }
    public void neuralNetworkCreation(int numberOfInputs){
        if(numberOfInputs>= 1){
            List<Neuron> listTemp = new ArrayList<>();
            for(int i = 0; i < numberOfInputs; i++){
                listTemp.add(new Neuron());
            }
            neuralNetwork.add(listTemp);
            neuralNetworkCreation(numberOfInputs/2);
        }

    }
    public int getIndex(Neuron neuron){
        for(int i = 0; i < neuralNetwork.size()-1; i++){
            if(neuron == neuralNetwork.get(i)){
                return i;
            }
        }
        return -1;
    }
    public Double predictionY(List<Pair<Double, Double>> inputs){
        neuralNetworkCreation(inputs.size());
        boolean firstLayer = true;
        for(List<Neuron> neuralLayer : neuralNetwork){
            List<Double> actualLayer = new ArrayList<>();
            resultActivation.add(actualLayer);
            for(Neuron neuron : neuralLayer){
                if(firstLayer){
                    neuron.setInputs(new ArrayList<>().add(inputs.get(getIndex(neuron))));
                } else{
                    neuron.setInputs(new ArrayList<>().add(inputs.get((inputs.get(getIndex(neuron)*2)-1), getIndex(neuron)*2)));
                }
                actualLayer.add(neuron.activationFunction());
            }
            if(firstLayer){firstLayer = false;}
        }
        return resultActivation.getLast().getLast();
    }
}
