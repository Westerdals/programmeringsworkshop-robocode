package no.nith.programmeringsworkshop;

import java.awt.Color;

import robocode.HitWallEvent;
import robocode.Robot;
import robocode.ScannedRobotEvent;

public class CharlieSheen extends Robot {
    @Override
    public void run() {
        setBodyColor(Color.YELLOW);
        while (true) {
            turnRadarRight(360);
            ahead(200);
        }

    }

    @Override
    public void onScannedRobot(ScannedRobotEvent event) {

        turnGunRight(getHeading() - getGunHeading() + event.getBearing());
        if (event.getDistance() <= 40) {
            fire(3);
        } else if (event.getDistance() <= 100) {
            fire(2);
        } else if (event.getDistance() <= 400) {
            fire(1);
        }
        ahead(100);
    }

    @Override
    public void onHitWall(HitWallEvent event) {
        turnRight(60);
    }
}
