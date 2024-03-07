import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import code.classes.DeepBrain;
import processing.core.PVector;

public class DeepBrainTest {
    
    @Test
    public void feedforward(){
        //following test is based mattmazur.com

        float[] inputs = {0.05f, 0.1f, 1.0f};

        DeepBrain brain = new DeepBrain();

        PVector output = brain.get_direction(inputs);

        assertEquals(0.75f, output.x, 0.01);
        assertEquals(0.77f, output.y, 0.01);
    }
    
}
