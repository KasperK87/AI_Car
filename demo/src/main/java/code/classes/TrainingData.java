package code.classes;

import processing.core.PVector;

public class TrainingData {
    public float[][] training_data;

    public TrainingData(int data_points) {
        training_data = new float[data_points][6];

        for (int i = 0; i < data_points; i++) {
            float x1 = (float) Math.random()*600;
            float y1 = (float) Math.random()*400;
            float x2 = (float) Math.random()*600;
            float y2 = (float) Math.random()*400;

            PVector normal = new PVector(x1-x2, y1-y2).normalize();

            float answer = -normal.x;

            float answer2 = -normal.y;
         
            training_data[i][0] = x1;
            training_data[i][1] = y1;
            training_data[i][2] = x2;
            training_data[i][3] = y2;
            training_data[i][4] = answer;
            training_data[i][5] = answer2;
        }
    }


}
