package code.classes;

import processing.core.PVector;
import code.classes.TrainingData;

public class Brain {

    Perceptron[] perceptrons;  
    
    TrainingData training_data;

    public Brain(int data_points) {
        perceptrons = new Perceptron[2];
        for (int i = 0; i < perceptrons.length; i++) {
            perceptrons[i] = new Perceptron(4);
        }
 

        training_data = new TrainingData(data_points);

        //feed training data to the perceptrons to train them 
        //index 4 is the answer for the x direction
        //index 5 is the answer for the y direction
        for(int i = 0; i < training_data.training_data.length; i++) {
            float[] inputs = {training_data.training_data[i][0], training_data.training_data[i][1], training_data.training_data[i][2], training_data.training_data[i][3]};
            
            perceptrons[0].train(inputs, (int)training_data.training_data[i][4]);
            perceptrons[1].train(inputs, (int)training_data.training_data[i][5]);
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
