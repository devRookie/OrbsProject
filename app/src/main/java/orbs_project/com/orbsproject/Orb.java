package orbs_project.com.orbsproject;


/**
 * Created by Omie on 12/11/2014.
 */

//Orb Class holds all the information for all the balls
public class Orb {


    private int orbColor;
    private float orbRadius;
    private float xLocation;
    private float yLocation;
    private float xVelocity;
    private float yVelocity;


    public Orb(float x, float y, float radius, float speed, float angle, int color) {

        this.xLocation = x; //x_location
        this.yLocation = y; //y_location
        //== Getting speed + angle and calculating the velocity of x and y.
        this.xVelocity = (float)(speed * Math.cos(Math.toRadians(angle))); //x_speed
        this.yVelocity = (float)(-speed * Math.sin(Math.toRadians(angle))); //y_speed
        this.orbRadius = radius; //radius
        setOrbRadius(radius);
        this.orbColor = color; //color
    }


    //==Get Methods==
    public int getOrbColor() { return orbColor; }

    public float getxLocation() {
        return xLocation;
    }

    public float getyLocation() {
        return yLocation;
    }

    public float getxVelocity() { return xVelocity; }

    public float getyVelocity() { return yVelocity ;}

    public float getOrbRadius() { return orbRadius; }


    //==Set Methods==
    public void setyVelocity(float yVelocity) { this.yVelocity = yVelocity; }

    public void setxVelocity(float xVelocity) { this.xVelocity = xVelocity; }

    public void setyLocation(float yLocation) {this.yLocation = yLocation;  }

    public void setxLocation(float xLocation) { this.xLocation = xLocation; }

    public void setOrbColor(int orbColor) {
        this.orbColor = orbColor;
    }

    public void setOrbRadius(float orbRadius) {
        this.orbRadius = orbRadius;
    }

}
