package code;

import processing.core.*;

import code.classes.Car;

/**
 * Hello world!
 */
public final class App extends PApplet{

    Car car, car2, car3;

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
        car = new Car(new PVector(100, 100), 100);
        car2 = new Car(new PVector(100, 100), 100);
        car3 = new Car(new PVector(100, 100), 100);
    }

    public void draw() {
        car.set_target(new PVector(mouseX, mouseY));
        car.update();
        car.render(this);

        car2.set_target(new PVector(mouseX, mouseY));
        car2.update();
        car2.render(this);

        car3.set_target(new PVector(mouseX, mouseY));
        car3.update();
        car3.render(this);
    }
}
