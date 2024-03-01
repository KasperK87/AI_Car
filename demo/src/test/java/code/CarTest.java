package code;

import processing.core.*;

import java.math.*;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import code.classes.*;

public class CarTest {

    @Test
    public void headingTest() {
        Car car = new Car(new PVector(100, 100), 30000);
        car.set_target(new PVector(200, 200));
        car.update();
        assertEquals(Math.toRadians(45), car.get_direction(), 5);
    }

    @Test
    public void positionTest() {
        Car car = new Car(new PVector(100, 100), 60000);
        car.set_target(new PVector(200, 100));
        
        //uses to frames to move
        car.update();
        car.update();
        assertEquals(101, car.get_position().x, 0.3);
    }

    @Test
    public void improvesWithTraining(){
        Car car = new Car(new PVector(100, 100), 1000);
        PVector target = new PVector(500, 300);
        car.set_target(target);

        for (int i = 0; i < 1000; i++){
            car.update();
        }

        PVector loc = car.get_position().copy();

        for (int i = 0; i < 50; i++){
            car.train();
        }

        for (int i = 0; i < 1000; i++){
            car.update();
        }

        assertTrue(PVector.dist(target, loc) > PVector.dist(target, car.get_position()));
    }
    
}
