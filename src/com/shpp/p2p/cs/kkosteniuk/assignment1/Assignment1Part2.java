package com.shpp.p2p.cs.kkosteniuk.assignment1;

import com.shpp.karel.KarelTheRobot;

public class Assignment1Part2 extends KarelTheRobot {

    /*
   State before the method: starting position, Karel starts in the southwest corner and looks east,
   and he has plenty of beepers in his backpack;
   State after the method: Karol put the beeper where it was not on the axis of the column, all columns have in the
   cells of 1 beeper
   decomposition:
    * build a column
    * in the loop: find the base position of the next column and build the column
       */
    @Override
    public void run() throws Exception {
        turnToStart();
        buildColumn();
        findPlaseForNextColumn();
    }

    /*
    Karel go to place where the first column must stay (south-east)
     */
    private void turnToStart() throws Exception {
        while (frontIsClear()) {
            move();
        }
    }


    /*
     State before the method: Karel is at the base of the column. Karel's direction can be loved.
     Karel himself turns to the north.
     State after the method: Karel put out one beeper each to the box before the block. Karel stays in that
     position.
     When Karel puts beeper, he pre-checks whether the existing beeper to exclude doubles.
     Decomposition:
        * make a turn to the north
        * till there is no restriction at the top, he performs the column section device
        * as soon as Karel is in a position where there is a restriction from above, but exits the loop and make
        the capitel (the top of the column) and that's the end of his work with this method.
     */
    private void buildColumn() throws Exception {
        while (notFacingNorth()) {
            turnLeft();
        }
        while (frontIsClear()) {
            makeSection();
        }
        makeTopSection();
    }

    /*
     State before the method: Karol is in position where he has to put one beeper.
     State after the method: At the position where Karel stood at the beginning of this method lies one beeper
     and Karel stands on the new position where it should put one beeper.
      Decomposition:
        * the first thing Karel checks if the beeper is already in place
        * if not, Karel puts down one beeper and moves on to the next position
        * if yes, Karel does not put the beeper but goes to the next position at once
     */
    private void makeSection() throws Exception {
        if (noBeepersPresent()) {
            putBeeper();
            move();
        } else {
            move();
        }
    }

    /*
     State before the method: Karol built all sections of the column and moved to the position of the top of the column,
     above which there is a barrier.
     State after the method: At the position of the top of the column, above which there is a barrier put one beeper.
     Karel stayed at this position.
     Decomposition:
        * the first thing Karel does is check if there is already a beeper in position
        * if not, Karel puts down one beeper
        * If yes, Karel does nothing.
     */
    private void makeTopSection() throws Exception {
        if (noBeepersPresent()) {
            putBeeper();
        }
    }

    /*
     State before the method: The first column is fully prepared. Karel is in the top location of the first column.
     State after the method: Karel built all the columns in the current world
     Decomposition:
        * Karel climbs down from the first column
        * Karel moves to the base of the next column.
        * If the world ended before the bases of the of the next column, the Karel has built all columns in this world
     */
    private void findPlaseForNextColumn() throws Exception {
        goDown();
        while (frontIsClear()) {
            move();
            if (frontIsClear()) {
                move();
                if (frontIsClear()) {
                    move();
                    if (frontIsClear()) {
                        move();
                        buildColumn();
                        goDown();
                    }
                }
            }
        }
    }

    /*
         State before the method: Karel is in the position of the top cell of the column.
         State after the method: Karel is on the first cell of this column looking east.
     */
    private void goDown() throws Exception {
        turnAround();
        while (frontIsClear()) {
            move();
        }
        turnRight();
    }

    /*
    Karol makes a 180-degree turn
     */
    private void turnAround() throws Exception {
        turnLeft();
        turnLeft();
    }


    /*
   Karol makes a turn to the right
    */
    private void turnRight() throws Exception {
        for (int i = 0; i < 3; i++) {
            turnLeft();
        }
    }
}

