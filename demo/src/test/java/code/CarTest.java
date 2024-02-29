package code;

import processing.core.*;

import java.math.*;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import code.classes.*;

public class CarTest {

    @Test
    public void headingTest() {
        Car car = new Car(new PVector(100, 100), 1000);
        car.set_target(new PVector(200, 200));
        car.update();
        assertEquals(Math.toRadians(45), car.get_direction(), 0.0001);
    }

    @Test
    public void positionTest() {
        Car car = new Car(new PVector(100, 100), 1000);
        car.set_target(new PVector(200, 100));
        car.update();
        assertEquals(101, car.get_position().x, 0.0001);
    }
    
}
