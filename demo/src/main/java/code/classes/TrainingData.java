package code.classes;

public class TrainingData {
    public float[][] training_data;

    public TrainingData(int data_points) {
        training_data = new float[data_points][5];

        for (int i = 0; i < data_points; i++) {
            float x1 = (float) Math.random()*600;
            float y1 = (float) Math.random()*400;
            float x2 = (float) Math.random()*600;
            float y2 = (float) Math.random()*400;
            float answer;
            if (Math.abs(x1-x2) > Math.abs(y1-y2)) {
                answer = 1;
            } else {
                answer = -1;
            }
            training_data[i][0] = x1;
            training_data[i][1] = y2;
            training_data[i][2] = x1;
            training_data[i][3] = y2;
            training_data[i][4] = answer;
        }
    }


}
