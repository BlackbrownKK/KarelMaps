package com.shpp.p2p.cs.kkosteniuk.assignment1;

import com.shpp.karel.KarelTheRobot;

public class Assignment1Part4 extends KarelTheRobot {

    /*
     State before the method: starting position, Karel starts in the southwest corner and looks east,
     and he has plenty of beepers in his backpack;
     State after the method: Karol put one beeper in each cell of the field in staggered order
      Decomposition:
        * if the world of the form of one vertical row - start the method to make a vertical row
        * if it's a 1:1 world, just put a beeper and that's
        * it if neither - start the method to do the checkerboard
     */
    @Override
    public void run() throws Exception {

        if (frontIsClear()) {
            makeChessField();
        } else {
            turnLeft();
            if (frontIsClear()) {
                makeVerticalRow();
            } else {
                putBeeper();
            }
        }
    }

    /*
     State before the method: starting position, Karel starts in the southwest corner and looks east,
     and he has plenty of beepers in his backpack;
     State after the method: Karol put one beeper in each cell of the field in staggered order
      Decomposition:
        * Karel looks to the left and enters the cycle to check that the row he is on is not the last row
         If the row is at the end, this cycle ends.
        * in the loop of this method Karel makes a row of beepers in staggered order makeRow() and goes to the next row
         and gets to the starting position looking east.
        * Karel checks the second cell of the previous row checkChess() for a beeper, if there is no beeper,
         then Karel for providing the chess order moves to the second cell of the new row looks to the east
        * Karel rotates to the left to check in the loop that the row he is on is not the last.
        * If the Karel is out of the cycle because it is on the last row, then in this case the Karel can be on
        the first or second place of the top row, or in the case of the world 2:2 he can stand on the edge and leave
        all cycles without putting the beeper down. If the world is 2:2, then Karel lays the beeper on the current
        cell and the program ends. If the world not 2:2 Karel in this case makes the last row makeRow() without cycle
     */

    private void makeChessField() throws Exception {
        turnLeft();
        while (frontIsClear()) {
            turnRight();
            makeRow();
            goToNextRow();
            checkChess();
            turnLeft();
        }
        turnRight();
        if (frontIsBlocked()) {
            putBeeper();
        } else {
            makeRow();
        }
    }


/*
    State before the method: Karel on the first or second cell looks east.
    State after the method: Karel on the first cell of the current row looks west
    Decomposition:
        * the length of the row can be endless and Karel enters a cycle from which he can exit if there is an
         hurdle in front of him
        * In this cycle, Karel puts the beeper and goes on a 4-step inspection, if none of the four steps shows an
        blocks, Karel returnsToDoWork() and the loop ends and a new loop begins.
        * An inspection of each step's barriers does the corresponding beeper placement action in staggered order and
         always returns Karel back to the first cell facing the obstacle for Karel to be dropped from the loop
 */

    private void makeRow() throws Exception {
        while (frontIsClear()) {
            putBeeper();
            move();
            firstStepInspection();
            secontStepInspection();
            thirdStepInspection();
            fourthStepInspection();
        }
    }

    /*
    the first step of the inspection, if the barrier is missing Karel moves on to the second step of the inspection
    if the cell is at the last one and Karel looks to the East, he stops the inspection and goes to the West cell
    of that row
     */
    private void firstStepInspection() throws Exception {
        if (frontIsClear()) {
            move();
        } else {
            if (facingEast()) {
                turnAround();
                goToWall();
            }
        }
    }

    /*
    the second step of the inspection, if the barrier is missing Karel moves on to the third step of the inspection
    if the cell is at the last one and Karel looks to the East, he stops the inspection, put one beeper and goes
    to the West cell of that row.
     */

    private void secontStepInspection() throws Exception {
        if (frontIsClear()) {
            move();
        } else {
            if (facingEast()) {
                putBeeper();
                turnAround();
                goToWall();
            }
        }
    }

    /*
   the third step of the inspection, if the barrier is absent Karel moves on to the fourth step of the inspection
   If the cell is at the extreme end and Karel looks to the East, he stops the inspection and moves one cell back
   and there peck one beeper and goes to the west cell of that row
   */
    private void thirdStepInspection() throws Exception {
        if (frontIsClear()) {
            move();
        } else {
            if (facingEast()) {
                turnAround();
                move();
                putBeeper();
                goToWall();
            }
        }
    }

    /*
    the fourth step of the inspection, if the barrier is absent Karel does the work of putting the beepers in a
    staggered row and begins a new inspection loop.
    If the cell is the last one and Karel looks to the East, he does the work of stacking beepers in staggered
    rows and stacks one beeper on the cell where it happens to be one beeper on the cell where it appears after
    returnToDoWork() and goes to the west cell of the row
    */
    private void fourthStepInspection() throws Exception {
        if (frontIsClear()) {
            returnToDoWork();
        } else {
            if (facingEast()) {
                returnToDoWork();
                putBeeper();
                turnAround();
                goToWall();
            }
        }
    }

    /*
    State before the method: Karel on the first or second cell looks north.
   State after the method: Karel on the first cell of the current row looks south
    Decomposition:
     * the length of the row can be endless and Karel enters a cycle from which he can exit if there is an
         hurdle in front of him
        * In this cycle, Karel puts the beeper and goes on a 4-step inspection, if none of the four steps shows an
        blocks, Karel returnsToDoWork() and the loop ends and a new loop begins.
        * An inspection of each step's barriers does the corresponding beeper placement action in staggered order and
         always returns Karel back to the first cell facing the obstacle for Karel to be dropped from the loop
        * the difference between makeVerticalRow and makeRow: if (facingNorth()) and if (facingEast()) respectively

 */
    private void makeVerticalRow() throws Exception {
        while (frontIsClear()) {
            putBeeper();
            move();
            firstStepInspectionForVerticalRow();
            secontStepInspectionForVerticalRow();
            thirdStepInspectionForVerticalRow();
            fourthStepInspectionForVerticalRow();
        }
    }

    /*
    the first step of the inspection, if the barrier is missing Karel moves on to the second step of the inspection
    if the cell is at the last one and Karel looks to the North, he stops the inspection and goes to the West cell
    of that row
     */
    private void firstStepInspectionForVerticalRow() throws Exception {
        if (frontIsClear()) {
            move();
        } else {
            if (facingNorth()) {
                turnAround();
                goToWall();
            }
        }
    }

    /*
   the second step of the inspection, if the barrier is missing Karel moves on to the third step of the inspection
    if the cell is at the last one and Karel looks to the East, he stops the inspection, put one beeper and goes
    to the North cell of that row.
 */
    private void secontStepInspectionForVerticalRow() throws Exception {
        if (frontIsClear()) {
            move();
        } else {
            if (facingNorth()) {
                putBeeper();
                turnAround();
                goToWall();
            }
        }
    }

    /*
    the third step of the inspection, if the barrier is absent Karel moves on to the fourth step of the inspection
    If the cell is at the extreme end and Karel looks to the North, he stops the inspection and moves one cell back
    and there peck one beeper and goes to the west cell of that row
*/
    private void thirdStepInspectionForVerticalRow() throws Exception {
        if (frontIsClear()) {
            move();
        } else {
            if (facingNorth()) {
                turnAround();
                move();
                putBeeper();
                goToWall();
            }
        }
    }

    /*
     the fourth step of the inspection, if the barrier is absent Karel does the work of putting the beepers in a
    staggered row and begins a new inspection loop.
    If the cell is the last one and Karel looks to the North, he does the work of stacking beepers in staggered
    rows and stacks one beeper on the cell where it happens to be one beeper on the cell where it appears after
    returnToDoWork() and goes to the west cell of the row
*/
    private void fourthStepInspectionForVerticalRow() throws Exception {
        if (frontIsClear()) {
            returnToDoWork();
        } else {
            if (facingNorth()) {
                returnToDoWork();
                putBeeper();
                turnAround();
                goToWall();
            }
        }
    }

    /*
    Karel moves forward until there is an obstacle in front of him
     */
    private void goToWall() throws Exception {
        while (frontIsClear()) {
            move();
        }
    }

    /*
    State before the method: Karel on the west cell of the current row looks west
   State after the method: Karel at the last cell of the next row looks east
 */
    private void goToNextRow() throws Exception {
        turnRight();
        move();
        turnRight();
    }

    /*
    State before the method: Karel on the west cell of the current row looks west
    State after the method:
    Karel on the first cell of the current row looks west if there is a beeper on the previous row on the second cell
    OR
    Karel on the second cell of the current row looks west if there is no beeper on the previous row on the second cell
     */
    private void checkChess() throws Exception {
        move();
        turnRight();
        move();
        if (noBeepersPresent()) {
            turnAround();
            move();
            turnRight();
        } else {
            turnRight();
            move();
            turnRight();
            move();
            turnRight();
        }

    }

    /*
    State before the method: Karel is in the fourth step of the makeRow() exploration method on the fourth step
    further there is a barrier or there is no barrier. Karel turns 180 degrees. In the case of the makeRow() method,
    Karel looks east, and in the case of the makeVerticalRow method, Karel looks north.
    State after the method: Karel is in the position where he was before this method was called, put one beeper two
    cells to the south (if makeVerticalRow) or west (if makeRow) of Karel's position.


     */
    private void returnToDoWork() throws Exception {
        turnAround();
        move();
        move();
        turnAround();
        putBeeper();
        move();
        move();
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
