package velvet.smooth.motion.impl;

import velvet.smooth.motion.Motion;

public class OvershootMotion implements Motion {

    public double getDelta(double delta) {
        //return 1 - Math.pow(Math.exp(-delta), 5) * Math.cos(11 * delta);
        return 0;
    }
}
