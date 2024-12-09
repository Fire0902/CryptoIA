package neuralnetworktest;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.cryptoia.neuron.NeuralNetwork;
import com.cryptoia.neuron.Neuron;
import com.cryptoia.utilities.Pair;

public class NeuralNetworkTest {

    @Test
    public void testLearning() {
        // Initialisation des neurones et du réseau
        List<List<Neuron>> neurons = new ArrayList<>();
        NeuralNetwork reseau = new NeuralNetwork(neurons);

        // Initialisation des valeurs
        List<List<Pair<Double, Double>>> values = new ArrayList<>();
        List<Pair<Double, Double>> innerList = new ArrayList<>();
        innerList.add(new Pair<>(1.0, 1.0));
        innerList.add(new Pair<>(2.0, 1.0));
        innerList.add(new Pair<>(3.0, 1.0));
        values.add(innerList);

        Double real = 4.0;
        List<Double> answer = new ArrayList<>();

        // Création du réseau
        reseau.neuralNetworkCreation(values.size());

        // Assertion pour vérifier la mise à jour des biais
        assertEquals(Double.valueOf(6), reseau.updateBiases(0.01, values, answer));
    }
}
