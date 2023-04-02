package com.shpp.p2p.cs.kkosteniuk.assignment1;

import com.shpp.karel.KarelTheRobot;

public class Assignment1Part1 extends KarelTheRobot {


    /*
    State before the method: Karel at the starting position
    State after the method: the paper is placed on the starting position
    decomposition into 3 blocks: go for the newspaper; take the newspaper and turn around; return to the position.
     */
    @Override
    public void run() throws Exception {
        goForNewspapaer();
        takeNewspaper();
        returnBackToMyPlece();
    }

    /*
    State before the method: starting position
    State after the method: Karel at the position where the newspaper lies. Karol does not pick up the newspaper
     */
    private void goForNewspapaer() throws Exception {
        turnRight();
        move();
        turnLeft();
        goFourSteps();
    }

    /*
    State before the method: Karel at the position where the newspaper lies
    State after the method: Karel picked up the paper and turned around 180 degrees
     */
    private void takeNewspaper() throws Exception {
        pickBeeper();
        turnAround();
    }

    /*
    State before the method: Karel at the position where the newspaper lay
    State after the method: Karel moved to the starting position and left the paper at the starting position
     */
    private void returnBackToMyPlece() throws Exception {
        goFourSteps();
        turnRight();
        move();
        putBeeper();
    }

    /*
    Karol makes a turn to the right
     */
    private void turnRight() throws Exception {
        for (int i = 0; i < 3; i++) {
            turnLeft();
        }
    }

    /*
    Karol makes four moves
     */
    private void goFourSteps() throws Exception {
        for (int i = 0; i < 4; i++) {
            move();
        }
    }

    /*
    Karol makes a 180-degree turn
     */
    private void turnAround() throws Exception {
        turnLeft();
        turnLeft();
    }
}
