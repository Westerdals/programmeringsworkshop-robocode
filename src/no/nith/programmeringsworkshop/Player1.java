package no.nith.programmeringsworkshop;

import robocode.HitByBulletEvent;
import robocode.HitWallEvent;
import robocode.Robot;
import robocode.ScannedRobotEvent;

public class Player1 extends Robot {
    public void run() {
        while (true) {
            ahead(getLength(150, 250));
            turnLeft(getLength(30, 90));

            if ((int) Math.random() * 10 > 7) {
                turnGunRight(90);
                continue;
            }
            turnGunLeft(90);
        }
    }

    public void onScannedRobot(ScannedRobotEvent e) {
        if (e.getDistance() < 200) {
            if (getEnergy() > 50) {
                fire(3);
            } else
                fire(1);

        } else if (e.getDistance() < 300) {
            if (getEnergy() > 50) {
                fire(2);
            } else
                fire(1);

        }

        turnRight(e.getBearing());
    }

    public int getLength(int min, int max) {
        return (int) (Math.random() * max - min) + min;
    }

    public void onHitByBullet(HitByBulletEvent e) {
        turnGunRight(e.getBearing());
        if (e.getVelocity() < 10) {
            fire(3);
        }
    }

    public void onHitWall(HitWallEvent e) {
        turnLeft(90);
    }
}
