package com.cryptoia.neuron;

import java.util.ArrayList;
import java.util.List;

import com.cryptoia.utilities.LossFunction;
import com.cryptoia.utilities.Pair;
import com.cryptoia.utilities.lossfunctions.LossFunctionMSE;
import com.cryptoia.utilities.lossfunctions.LossFunctionMSENoSquare;

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
    // public int getIndex(Neuron neuron){
    //     for(int i = 0; i < neuralNetwork.size()-1; i++){
    //         if(neuron == neuralNetwork.get(i)){
    //             return i;
    //         }
    //     }
    //     return -1;
    // } old usefull function because i was idiot (i forgot the existance of indexOf)
    public Double predictionY(List<Pair<Double, Double>> inputs){
        Double weight = Double.valueOf(1);
        neuralNetworkCreation(inputs.size());
        boolean firstLayer = true;
        for(List<Neuron> neuralLayer : neuralNetwork){
            List<Double> actualLayer = new ArrayList<>();
            resultActivation.add(actualLayer);
            for(Neuron neuron : neuralLayer){
                if(firstLayer){
                    List<Pair<Double, Double>> temp = new ArrayList<>();
                    temp.add(new Pair<>(inputs.get(neuralNetwork.indexOf(neuron)).getFirst(), weight));
                    neuron.setInputs(temp);
                } else{
                    List<Pair<Double, Double>> temp = new ArrayList<>();
                    temp.add(new Pair<>(inputs.get((neuralNetwork.indexOf(neuron)*2)-1).getFirst(), weight));
                    temp.add(new Pair<>(inputs.get((neuralNetwork.indexOf(neuron)*2)).getFirst(), weight));
                    neuron.setInputs(temp);
                }
                actualLayer.add(neuron.activationFunction());
            }
            if(firstLayer){firstLayer = false;}
        }
        return resultActivation.getLast().getLast();
    }
    public List<Double> getLossTab(List<List<Pair<Double, Double>>> allInputs, List<Double> answer, LossFunction l){
        List<Double> sampleTab = new ArrayList<>();
        for(List<Pair<Double, Double>> inputs : allInputs){
            neuralNetwork = new ArrayList<>();
            sampleTab.add(predictionY(inputs));
        }
        List<Double> loss = new ArrayList<>();
        for (int i = 0; i < sampleTab.size(); i++) {
            loss.add(Neuron.lossFunction(sampleTab.get(i), answer.get(i), l));
        }
        return loss;
    }
    public Double lossFunctionNetwork(List<List<Pair<Double, Double>>> allInputs, List<Double> answer){
        LossFunction l = new LossFunctionMSE();
        List<Double> loss = getLossTab(allInputs, answer, l);
        Double lossFunctionResult = Double.valueOf(0);
        for(Double d : loss){
            lossFunctionResult += d;
        }
        lossFunctionResult = (1/loss.size())*lossFunctionResult;
        return lossFunctionResult;
    }
    public Double derivateLossFunctionNetwork(List<List<Pair<Double, Double>>> allInputs, List<Double> answer){
        LossFunction l = new LossFunctionMSENoSquare();
        List<Double> loss = getLossTab(allInputs, answer, l);
        Double lossFunctionResult = Double.valueOf(0);
        for(Double d : loss){
            lossFunctionResult += d;
        }
        lossFunctionResult = (2/loss.size())*lossFunctionResult;
        return lossFunctionResult;
    }


    public void updateBiases(Double learningRate, List<List<Pair<Double, Double>>> allInputs, List<Double> answers) {
        // Obtenez la dérivée de la fonction de perte
        Double lossDerivative = derivateLossFunctionNetwork(allInputs, answers);
        
        for (List<Neuron> layer : neuralNetwork) {
            for (Neuron neuron : layer) {
                // Calculez la dérivée de l'activation avec respect au biais
                Double activationDerivative = neuron.derivativeActivationFunctionWithRespectToBias();
                
                // Mise à jour du biais avec la descente de gradient
                neuron.setBiases(neuron.biases - learningRate * lossDerivative * activationDerivative);
            }
        }
    }
    

}
