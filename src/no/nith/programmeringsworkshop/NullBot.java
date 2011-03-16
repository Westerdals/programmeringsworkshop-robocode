package no.nith.programmeringsworkshop;

import java.awt.Color;

import robocode.AdvancedRobot;
import robocode.HitRobotEvent;
import robocode.HitWallEvent;
import robocode.ScannedRobotEvent;
import robocode.TurnCompleteCondition;

public class NullBot extends AdvancedRobot {
    int numOthersLeft;
    int turnDirection = 1;

    public void run() {
        setBodyColor(Color.pink);
        setGunColor(Color.red);
        setRadarColor(Color.yellow);
        setBulletColor(Color.green);
        setScanColor(Color.green);
        while (true) {
            this.numOthersLeft = getOthers();

            setAhead(40000);
            setTurnRight(180);
            waitFor(new TurnCompleteCondition(this));
            setTurnLeft(60);
            waitFor(new TurnCompleteCondition(this));
            setTurnRight(180);
            waitFor(new TurnCompleteCondition(this));

            turnRadarLeft(360);
        }
    }

    public void onHitWall(HitWallEvent e) {
        turnRight(120);
    }

    public void onHitRobot(HitRobotEvent e) {
        if (e.getBearing() >= 0)
            this.turnDirection = 1;
        else {
            this.turnDirection = -1;
        }

        turnRight(e.getBearing());

        fireByDistance(e.getEnergy());

        ahead(40);
    }

    public void onScannedRobot(ScannedRobotEvent e) {
        if ((e.getDistance() < 300) || (this.numOthersLeft <= 2)) {
            turnGunLeft(e.getBearing());

            if (e.getBearing() >= 0)
                this.turnDirection = 1;
            else {
                this.turnDirection = -1;
            }

            turnRight(e.getBearing());

            if (e.getVelocity() == 0)
                fire(3);
            else if (e.getDistance() < 150) {
                fire(2);
            }
            ahead(e.getDistance() + 20);
            scan();
        } else {
            turnGunLeft(e.getBearing());

            if (e.getVelocity() == 0)
                fire(3);
            else {
                fire(0.5D);
            }
            scan();
        }
    }

    public void fireByDistance(double distance) {
        if ((distance > 200) || (getEnergy() < 15))
            fire(1);
        else if (distance > 50)
            fire(2);
        else
            fire(3);
    }
}
