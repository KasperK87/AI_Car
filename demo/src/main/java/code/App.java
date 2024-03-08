package code;

import processing.core.*;

import code.classes.Car;
import code.classes.DeepBrain;

/**
 * Hello world!
 */
public final class App extends PApplet{

    Car car;
    Car deepCar;
    int version;

    private App() {
    }

    /**
     * Says hello to the world.
     * @param args The arguments of the program.
     */
    public static void main(String[] args) {
        String[] processingArgs = {"MySketch"};
		App mySketch = new App();
		PApplet.runSketch(processingArgs, mySketch);
    }

    public void settings() {
        size(600, 400, P2D);
    }

    public void setup() {
        version = 0;
        car = new Car(new PVector(300, 200), 1000);
        deepCar = new Car(new PVector(300, 300), new DeepBrain(1000));
    }

    public void draw() {
        //make light trail
        noStroke();
        fill(255, 10);
        rect(300,200, 600,400);

        car.set_target(new PVector(mouseX, mouseY));
        car.update();
        car.render(this);

        deepCar.set_target(new PVector(mouseX, mouseY));
        deepCar.update();
        deepCar.render(this);

        textSize(32);
        fill(0);
        text("press any key to train: " + version + "v", 10, 32);
    }

    public void keyReleased(){
        car.train();
        deepCar.train();
        version++;

        if (key == 'r') {
            car.set_position(300, 200);
            deepCar.set_position(300, 300);
        }
    }
}
