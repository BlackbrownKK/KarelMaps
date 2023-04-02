package com.shpp.p2p.cs.kkosteniuk.assignment1;

import com.shpp.karel.KarelTheRobot;

public class Assignment1Part3 extends KarelTheRobot {

  /*
    State before the method: starting position, Karel starts in the southwest corner and looks east,
    and he has plenty of beepers in his backpack;
    State after the method: Kaleel put one beeper in the middle of the bottom horizontal
    Decomposition:
    * If the world has the length of the first row 1, then Karel just puts one beeper on his position and that's it
    * if the world has a length of the first row > 1, the startWorkInRow() method is run
   */

    @Override
    public void run() throws Exception {
        if (frontIsClear()) {
            startWorkInRow();
        } else {
            putBeeper();
        }
    }

    /*
    State before the method: starting position, Karel starts in the southwest corner and looks east,
    and he has plenty of beepers in his backpack;
    State after the method: Karol cleared all unnecessary beepers except for one beeper in the middle
    of the bottom horizontal  If the bottom (first south) horizontal line has an even number of cells,
    the beeper will in the cell that is closer to the southeast corner.
    Decomposition:
    * Karel puts one beeper in each cell on the south horizontal  - makeBasicLine();
    * Karel collects all the beepers that he put, in the first cell of the first row let's call it stock - makeSrock();
    * Karel takes one beeper at a time and takes turns moving one beeper on the south line starting from the southeast
    cell, the second beeper he takes for himself. He does this until he runs out of beepers in the stock -
    makeLineToCenter();
    * Karel moves to his very first position on the southwest cell of the south line and from there begins moving
    eastward, picking up all but the first beeper that comes his way - cleanAllOthersBeppers();
    */
    private void startWorkInRow() throws Exception {
        makeBasicLine();
        makeSrock();
        makeLineToCenter();
        cleanAllOthersBeppers();
    }

    /*
     State before the method: starting position, Karel starts in the southwest corner and looks east,
     and he has plenty of beepers in his backpack;
     State after the method: beepers in each cell of the south line. Karel is at the easternmost point
     of the south line and looking east
     */
    private void makeBasicLine() throws Exception {
        while (notFacingEast()) {
            turnLeft();
        }
        while (frontIsClear()) {
            putBeeper();
            move();
        }
        putBeeper();
    }

    /*
    State before the method: beepers in each cell of the south line. Karel is at the easternmost point
     of the south line and looking east
    State after the method: Karel is at the east end point and is looking west  All beepers that were in the south
    line cells have been moved to Stock
    Decomposition:
    * pick up all beepers from the south line while they are here
    * take each beeper to Stock

    */

    private void makeSrock() throws Exception {
        turnAround();
        while (frontIsClear()) {
            carryToStock();
            while (noBeepersPresent()) {
                move();
            }
        }
        carryToStock();
    }

    /*
    In this method, Karel's task is to take the taken beeper from the south line cells to the Stock cell
    State before the method: Karel is in any cell of the south line is facing west.
    State after the method: Karel is at the last west point and looking east All beepers that were in the south
    line cells have been moved to Stock
*/

    private void carryToStock() throws Exception {
        pickBeeper();
        goToEndAndTurnAround();
        putBeeper();
        goToLastBeperAndTurnAround();
    }


    /*
    State before the method: Karel is at the extreme west point of the south line and looks east.
    State after the method: Karel is in the last east cell of the first row, looking west.
    Decomposition:
    * move to cell Stock
    * until there are beepers in Stock the first beeper he moves to the cell of the south line starting from
    the most eastern cell, the next beeper takes in his pocket
     */
    private void makeLineToCenter() throws Exception {
        goToEndAndTurnAround();
        while (beepersPresent()) {
            goToPutBeperOnLineToCenter();
            toKarelsPocket();
        }
    }

    /*
    State before the method: Karel is on the Stock cell facing west. There are beepers in the Stock cell.
    State after the method: Karel is on the Stock cell facing west. There are no beepers in the Stock cell.
 */
    private void toKarelsPocket() throws Exception {
        if (beepersPresent()) {
            pickBeeper();
        } else {
        }
    }

    /*
    State before the method: Karel is in the easternmost cell of the second south line facing west.
    State after the method: Karel is on the Stock cell facing east. There are no beepers in the Stock cell.
    Decomposition:
    * Karel pick one beper
    * Karol goes straight ahead to the barrier then 180-degree turn.
    * put one beper starting from the most eastern cell
    * Karol goes straight ahead to the barrier then 180-degree turn.
 */
    private void goToPutBeperOnLineToCenter() throws Exception {
        pickBeeper();
        goToEndAndTurnAround();
        putOneBeperInEveryNextPlace();
        goToEndAndTurnAround();
    }

    /*
    Karel puts one beeper under him checking that there are no beepers in the cell. If the beeper is present,
    Karel moves on to the next cell.
     */
    private void putOneBeperInEveryNextPlace() throws Exception {
        if (beepersPresent()) {
            while (beepersPresent()) {
                move();
            }
            putBeeper();
        } else {
            putBeeper();
        }
    }

    /*
    State before the method: Karel is on the Stock box facing west. There are no beepers in the Stock cell.
    State after the method: Karel is on the east cell of the south line. There is only one
    beeper which first lies to the west direction
    Decomposition:
    * Karel moves eastward until he finds the first beeper
    * as it finds the first beeper, does not pick it up, but moves on to the next cell (if the world has the length
    of the first row 2 Karel simply leaves the outermost beeper untouched
    * if the world has the length of the first row > 2, then starting from the next cell it raises all beepers to the
    end of the southern row
 */
    private void cleanAllOthersBeppers() throws Exception {
        while (noBeepersPresent()) {
            move();
        }
        if (frontIsClear()) {
            move();
            while (frontIsClear()) {
                pickBeeper();
                move();
            }
            pickBeeper();
        }
    }

    /*
    Karol goes while beepers Present then 180-degree turn.
    */
    private void goToLastBeperAndTurnAround() throws Exception {
        while (beepersPresent()) {
            move();
        }
        turnAround();
    }

    /*
    Karol goes straight ahead to the barrier then 180-degree turn.
    */
    private void goToEndAndTurnAround() throws Exception {
        while (frontIsClear()) {
            move();
        }
        turnAround();
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



