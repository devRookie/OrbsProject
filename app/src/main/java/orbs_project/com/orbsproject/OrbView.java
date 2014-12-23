package orbs_project.com.orbsproject;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Omie on 12/8/2014.
 */
public class OrbView extends View {

    private Paint paint = new Paint();
    private ArrayList<Orb> orbPool = new ArrayList(); //Array to hold all the balls that will be added
    private int canvasWidth, canvasHeight;
    private int orbCount = 30;
    private Random rnd = new Random();
    private Handler handler;
    private long FRAME_RATE = 16L;
    private int orbMinSize = 5;
    private int orbMaxSize = 10;
    private int orbMinSpeed = 5;
    private int orbMaxSpeed = 10;
    private int orbColor = 0;
    private int bgColor = 0;
    private boolean randomOrbColor = false;
    private boolean randomBG = false;

    public OrbView(Context context) {
        super(context);
        handler = new Handler();
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int bg = this.bgColor;
        if (randomBG == true){
            bg = this.bgColor;
        }
        paint.setColor(bg);
        canvas.drawRect(0, 0, getWidth(), getHeight(), paint);
        for (Orb orb : orbPool) {
            paint.setColor(orb.getOrbColor());
            canvas.drawCircle(orb.getxLocation(), orb.getyLocation(),
                    orb.getOrbRadius(), paint);
            this.orbMovement(orb);
        }
        handler.postDelayed(updateRun, FRAME_RATE);
    }

    public Runnable updateRun = new Runnable(){
        @Override
        public void run() {
            invalidate();
        }
    };

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        this.canvasWidth = w;
        this.canvasHeight = h;
        if (this.orbPool.size() >= 0) {
            this.orbPool.clear();
        }
        int i2 = w/2;
        int i3 = (h/2) - (h/4);
        //for loop to add orbs
        for (int i = 0; i < orbCount; i++){
            float j = (int) (1 + Math.random() * this.orbMaxSize +
                    this.orbMinSize); //Radius
            float k = (int) (1 + Math.random() * this.orbMaxSpeed +
                    this.orbMinSpeed);   //Speed
            float l = (int) (1 + 360 * Math.random());     //Angle
            int color = this.orbColor;
            if (this.randomOrbColor == true){
                color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256),
                        rnd.nextInt(256));
            }
            orbPool.add(new Orb(i2, i3, j, k, l, color));
        }
        super.onSizeChanged(w, h, oldw, oldh);
    }

    //Use this method that way you won't have to keep calling it everything 
    //in the orbMovment method.
    public float maxX(float radius){
        return this.canvasWidth - radius;
    }
    public float maxY(float radius){
        return this.canvasHeight - radius;
    }

    public void orbMovement(Orb orb) {
        // Calculate the orb's new position
        orb.setxLocation(orb.getxLocation() + orb.getxVelocity());  //Same as x = x + x_velocity
        orb.setyLocation(orb.getyLocation() + orb.getyVelocity());  //Same as y = y + y_velocity

        if (orb.getxLocation() < orb.getOrbRadius()) {
            orb.setxVelocity(orb.getxVelocity() * -1); //Times negative to reverse movement
        } else if (orb.getxLocation() > maxX(orb.getOrbRadius())) {
            orb.setxVelocity(orb.getxVelocity() * -1);
        }
        if (orb.getyLocation() < orb.getOrbRadius()) {
            orb.setyVelocity(orb.getyVelocity() * -1);
        } else if (orb.getyLocation() > maxY(orb.getOrbRadius())) {
            orb.setyVelocity(orb.getyVelocity() * -1);
        }
    }

    public void orbPreferences(String minSize, String maxSize, String
            minSpeed, String maxSpeed, String orbCount, String orbColor, String bgColor){

        this.orbMinSize = Integer.parseInt(minSize);
        this.orbMaxSize = Integer.parseInt(maxSize);

        this.orbMinSpeed = Integer.parseInt(minSpeed);
        this.orbMaxSpeed = Integer.parseInt(maxSpeed);

        this.orbCount = Integer.parseInt(orbCount);

        setOrbBgColor(bgColor);
        setOrbColor(orbColor);
    }

    public void setOrbColor(String orbC){
        if (orbC.equals("Random")){
            randomOrbColor = true;
            return;
        }else
            randomOrbColor = false;
        this.orbColor = Integer.parseInt(orbC);
    }

    public void setOrbBgColor(String bgColor){
        if (bgColor.equals("Random")){
            randomBG = true;
            this.bgColor = Color.argb(255, rnd.nextInt(256), rnd.nextInt
                    (256), rnd.nextInt(256));
            return;
        }else
            randomBG = false;
        this.bgColor = Integer.parseInt(bgColor);
    }

}