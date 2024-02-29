package code.classes;

import processing.core.PVector;
import code.classes.TrainingData;

public class Brain {

    Perceptron[] perceptrons;  
    
    TrainingData training_data;

    public Brain() {
        perceptrons = new Perceptron[2];
        for (int i = 0; i < perceptrons.length; i++) {
            perceptrons[i] = new Perceptron(4);
        }

        training_data = new TrainingData(1000);

        for(int i = 0; i < 100; i++) {
            float[] inputs = {training_data.training_data[i][0], training_data.training_data[i][1], training_data.training_data[i][2], training_data.training_data[i][3]};
            
            if (training_data.training_data[i][4] == 1) {
                perceptrons[0].train(inputs, 1);
            } else {
                perceptrons[0].train(inputs, 0);
            }
        }

        for(int i = 0; i < 100; i++) {
            float[] inputs = {training_data.training_data[i][0], training_data.training_data[i][1], training_data.training_data[i][2], training_data.training_data[i][3]};
            
            if (training_data.training_data[i][4] == -1) {
                perceptrons[1].train(inputs, 1);
            } else {
                perceptrons[1].train(inputs, 0);
            }
        }
    }
       

    public PVector get_direction(float[] inputs) {
        float[] outputs = new float[perceptrons.length];
        for (int i = 0; i < perceptrons.length; i++) {
            outputs[i] = perceptrons[i].feedforward(inputs);
        }
        return new PVector(outputs[0], outputs[1]);
    }
    
}
