package no.nith.programmeringsworkshop;

import java.awt.Color;

import robocode.AdvancedRobot;
import robocode.ScannedRobotEvent;

public class KillingTank extends AdvancedRobot {

    public void run() {
        setColors(Color.green, Color.black, Color.pink, Color.red, Color.cyan);

        while (true) {
            setAhead(Math.sin(getTime() / 5) * 200);
            setTurnRadarRight(360);

            execute();
        }
    }

    public void onScannedRobot(ScannedRobotEvent event) {
        double vinkel = event.getBearing();
        double absoluttvinkel = getHeading() + vinkel - getGunHeading();

        setTurnRight(event.getBearing() - getHeading());
        setTurnGunRight(absoluttvinkel);
        setFire(3);
    }
}
