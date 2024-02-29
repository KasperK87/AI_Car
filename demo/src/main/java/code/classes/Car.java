package code.classes;

import processing.core.*;

public class Car {
    
    PVector position;
    PVector velocity;

    PVector target;

    public Car(PVector position) {
        this.position = position;
        this.velocity = new PVector(1, 1);

        this.target = new PVector(0, 0);
    }

    public void update() {
        velocity = new PVector(target.x, target.y).sub(position).normalize();
        position.x += velocity.x;
        position.y += velocity.y;
    }

    public void render(PApplet p) {
        p.rectMode(p.CENTER);
        
        p.pushMatrix();
            p.translate(position.x, position.y);
            p.rotate(get_direction());
            p.fill(255, 0, 0);
            p.rect(0, 0, 20, 10);
        p.popMatrix();
    }

    public void set_target(PVector target) {
        this.target = target;
    }

    public float get_direction() {
        return velocity.heading();
    }

    public PVector get_position() {
        return position.copy();
    }
}
