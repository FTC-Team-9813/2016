package org.firstinspires.ftc.Cobalt;

/**
 * Created by Kilroy on 1/19/2017.
 */

public class DelayClass {
    public boolean delayClass(int delay){
//creates delay wow!!!!!!!
        try{
            Thread.sleep(delay);
        }catch(InterruptedException ex){
            Thread.currentThread().interrupt();
        }


      return true;
    }
}
