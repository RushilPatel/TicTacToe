/*
 * Author: Rushil Patel, rushil2011@my.fit.edu Spring 2013 Project: TicTacToe
 */
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JOptionPane;

public class TicTacToe {
    // Stores player moves
    private static ArrayList<String> playerPoints = new ArrayList<String> ();
    // Stores computer moves
    private static ArrayList<String> myPoint = new ArrayList<String> ();
    private static int box11;
    private static int box12;
    private static int box13;
    private static int box21;
    private static int box22;
    private static int box23;
    private static int box31;
    private static int box32;
    private static int box33;
    private static int myWins = 0;
    private static int playerWins = 0;
    private static int ties = 0;
    private static int myTurnCounter; // Track number of moves made by the
                                      // computer

    public static void main (final String[] args) {

        // Set all boxes to empty
        box11 = 0;
        box12 = 0;
        box13 = 0;
        box21 = 0;
        box22 = 0;
        box23 = 0;
        box31 = 0;
        box32 = 0;
        box33 = 0;
        myTurnCounter = 0;
        // Clear all the stored values
        playerPoints.clear ();
        myPoint.clear ();

        // Creates a new Canvas and creates game environment

        MyStdDraw.clear (MyStdDraw.GRAY);
        // StdDraw.setCanvasSize (600, 600);
        MyStdDraw.clear (MyStdDraw.GRAY);
        MyStdDraw.setXscale (-300, 300);
        MyStdDraw.setYscale (-300, 300);
        MyStdDraw.setPenColor (MyStdDraw.BOOK_RED);
        MyStdDraw.rectangle (0, 0, 300, 300);
        MyStdDraw.line (-100, 300, -100, -300);
        MyStdDraw.line (100, 300, 100, -300);
        MyStdDraw.line (-300, 100, 300, 100);
        MyStdDraw.line (-300, -100, 300, -100);
        MyStdDraw.text (-200, 200, "11");
        MyStdDraw.text (-200, 0, "21");
        MyStdDraw.text (-200, -200, "31");
        MyStdDraw.text (0, 200, "12");
        MyStdDraw.text (0, 0, "22");
        MyStdDraw.text (0, -200, "32");
        MyStdDraw.text (200, 200, "13");
        MyStdDraw.text (200, 0, "23");
        MyStdDraw.text (200, -200, "33");
        MyStdDraw.setPenColor (MyStdDraw.WHITE);
        MyStdDraw.text (170, 315, "Game Developed By: Rushil Patel");
        MyStdDraw.text (-190, 315, "Player: " + playerWins + "  Computer: "
                + myWins + "  Ties: " + ties);
        startGame ();
    }

    // start playing
    private static void startGame () {
        // Let the player go first
        boolean myTurn = false;

        while (true) {
            // Record player's move and check if the player has won
            while (!myTurn) {
                String box = "";
                boolean execLoop = true;
                while (execLoop) {
                    if (MyStdDraw.mousePressed ()) {
                        box = convert (MyStdDraw.mouseX (), MyStdDraw.mouseY ());
                        if (!playerPoints.contains (box)
                                && !myPoint.contains (box)) {
                            execLoop = false;
                        }
                        MyStdDraw.changeMouseState ();
                    }
                }

                MyStdDraw.setPenColor (MyStdDraw.GREEN);
                boxMarker (box);
                playerPoints.add (box); // store player's move
                // Check if the player won
                if (win (box, false)) {
                    JOptionPane.showMessageDialog (null,
                            "Game Over\nResult: You Win!!!");
                    playerWins++;
                    main (null);
                } else {
                    // if the player didn't win. then its computer's turn
                    myTurn = true;

                }
            }

            // Check for a tie game
            if ( ( box11 != 0) && ( box12 != 0) && ( box13 != 0)
                    && ( box21 != 0) && ( box22 != 0) && ( box23 != 0)
                    && ( box31 != 0) && ( box32 != 0) && ( box33 != 0)) {
                JOptionPane.showMessageDialog (null,
                        "Game Over\nResult: Tie Game!!!");
                ties++;
                main (null); // Restart the game, if there was a tie game

            } else {
                // if the center box is not filled, then fill the center box
                // first
                String mybox;
                if (box22 == 0) {
                    box22 = 1;
                    myPoint.add ("22");
                    MyStdDraw.setPenColor (MyStdDraw.RED);
                    MyStdDraw.filledCircle (0, 0, 50);
                    mybox = "22";
                } else {
                    // if the center box is filled then fill some other box
                    final String box = myBox ();
                    MyStdDraw.setPenColor (MyStdDraw.RED);
                    boxMarker (box);
                    mybox = box;
                    myPoint.add (box);// Store computer's move
                }
                // check if the computer won
                if (win (mybox, true)) {
                    JOptionPane.showMessageDialog (null,
                            "Game Over\nResult: Computer Wins!!!");
                    myWins++;
                    main (null);// Replay the game, if it is over

                } else {
                    myTurnCounter++;
                    myTurn = false; // Player's turn if the computer did not win
                }
            }
        }
    }

    // Mark computer's move
    private static void boxMarker (final String string) {

        if (string.equals ("11")) {
            if (box11 == 0) {
                box11 = 1;
                MyStdDraw.filledCircle (-200, 200, 50);
            }
        } else if (string.equals ("12")) {
            if (box12 == 0) {
                box12 = 1;
                MyStdDraw.filledCircle (0, 200, 50);
            }
        } else if (string.equals ("13")) {
            if (box13 == 0) {
                box13 = 1;
                MyStdDraw.filledCircle (200, 200, 50);
            }
        } else if (string.equals ("21")) {
            if (box21 == 0) {
                box21 = 1;
                MyStdDraw.filledCircle (-200, 0, 50);
            }
        } else if (string.equals ("22")) {
            if (box22 == 0) {
                box22 = 1;
                MyStdDraw.filledCircle (0, 0, 50);
            }
        } else if (string.equals ("23")) {
            if (box23 == 0) {
                box23 = 1;
                MyStdDraw.filledCircle (200, 0, 50);
            }
        } else if (string.equals ("31")) {
            if (box31 == 0) {
                box31 = 1;
                MyStdDraw.filledCircle (-200, -200, 50);
            }
        } else if (string.equals ("32")) {
            if (box32 == 0) {
                box32 = 1;
                MyStdDraw.filledCircle (0, -200, 50);
            }
        } else if (string.equals ("33")) {
            if (box33 == 0) {
                box33 = 1;
                MyStdDraw.filledCircle (200, -200, 50);
            }
        }
    }

    // Check and see if the computer can block a box to avoid the player from
    // winning
    // if so, the returns the box name to be blocked
    private static String block () {
        if ( ( box11 == 0) && win ("11", false)) {
            return ( "11");
        } else if ( ( box12 == 0) && win ("12", false)) {
            return ( "12");
        } else if ( ( box13 == 0) && win ("13", false)) {
            return ( "13");
        } else if ( ( box21 == 0) && win ("21", false)) {
            return ( "21");
        } else if ( ( box22 == 0) && win ("22", false)) {
            return ( "22");
        } else if ( ( box23 == 0) && win ("23", false)) {
            return ( "23");
        } else if ( ( box31 == 0) && win ("31", false)) {
            return ( "31");
        } else if ( ( box32 == 0) && win ("32", false)) {
            return ( "32");
        } else if ( ( box33 == 0) && win ("33", false)) {
            return ( "33");
        } else {
            return randomBox ();
        }
    }

    // First check if the computer can win. If it can, then return the box name.
    // If the computer can't win, then check if the player can be avoided from
    // winning.
    // If not, then make a random move, or return the box name to block
    private static String myBox () {
        if ( ( box11 == 0) && win ("11", true)) {
            return ( "11");
        } else if ( ( box12 == 0) && win ("12", true)) {
            return ( "12");
        } else if ( ( box13 == 0) && win ("13", true)) {
            return ( "13");
        } else if ( ( box21 == 0) && win ("21", true)) {
            return ( "21");
        } else if ( ( box22 == 0) && win ("22", true)) {
            return ( "22");
        } else if ( ( box23 == 0) && win ("23", true)) {
            return ( "23");
        } else if ( ( box31 == 0) && win ("31", true)) {
            return ( "31");
        } else if ( ( box32 == 0) && win ("32", true)) {
            return ( "32");
        } else if ( ( box33 == 0) && win ("33", true)) {
            return ( "33");
        } else {
            return block ();
        }
    }

    private static Random rnd = new Random ();
    // array of the corner boxes
    private static int[] cornerBoxes = { 1, 3, 7, 9 };

    private static int[] middleBoxes = { 2, 4, 6, 8 };

    // Selects a random box for the computer
    private static String randomBox () {
        int box;
        // If it's computer's first turn and if the
        // center box is taken up, then pick one of the
        // corner boxes.
        if (myTurnCounter == 0) {
            box = cornerBoxes[rnd.nextInt (4)];
        } else if (myTurnCounter == 1) {
            // Pick one of the middle boxes on second turn;
            box = middleBoxes[rnd.nextInt (4)];
        } else {
            box = rnd.nextInt (9) + 1;
        }

        if (box == 1) {
            if (box11 == 0) {
                return "11";
            } else {
                return randomBox ();
            }
        } else if (box == 2) {
            if (box12 == 0) {
                return "12";
            } else {
                return randomBox ();
            }
        } else if (box == 3) {
            if (box13 == 0) {
                return "13";
            } else {
                return randomBox ();
            }
        } else if (box == 4) {
            if (box21 == 0) {
                return "21";
            } else {
                return randomBox ();
            }
        } else if (box == 5) {
            // box 22 will always be filled
            // before this step is executed
            return randomBox ();
        } else if (box == 6) {
            if (box23 == 0) {
                return "23";
            } else {
                return randomBox ();
            }
        } else if (box == 7) {
            if (box31 == 0) {
                return "31";
            } else {
                return randomBox ();
            }
        } else if (box == 8) {
            if (box32 == 0) {
                return "32";
            } else {
                return randomBox ();
            }
        } else {
            if (box33 == 0) {
                return "33";
            } else {
                return randomBox ();
            }
        }

    }

    // Checks for the winner.
    // Compares the previously stored moves to the current move and checks for
    // win
    private static boolean win (final String box, final boolean computer) {
        if (computer) {
            if ( ( myPoint.size () < 2) || ( playerPoints.size () < 2)) {
                return false;
            } else {
                if (box.equals ("11")) {
                    return ( ( myPoint.contains ("12") && myPoint
                            .contains ("13"))
                            || ( myPoint.contains ("21") && myPoint
                                    .contains ("31")) || ( myPoint
                            .contains ("22") && myPoint.contains ("33")));

                } else if (box.equals ("12")) {
                    return ( ( myPoint.contains ("11") && myPoint
                            .contains ("13")) || ( myPoint.contains ("22") && myPoint
                            .contains ("32")));

                } else if (box.equals ("13")) {
                    return ( ( myPoint.contains ("12") && myPoint
                            .contains ("11"))
                            || ( myPoint.contains ("22") && myPoint
                                    .contains ("31")) || ( myPoint
                            .contains ("23") && myPoint.contains ("33")));

                } else if (box.equals ("21")) {
                    return ( ( myPoint.contains ("11") && myPoint
                            .contains ("31")) || ( myPoint.contains ("22") && myPoint
                            .contains ("23")));

                } else if (box.equals ("22")) {
                    return ( ( myPoint.contains ("11") && myPoint
                            .contains ("33"))
                            || ( myPoint.contains ("21") && myPoint
                                    .contains ("23"))
                            || ( myPoint.contains ("12") && myPoint
                                    .contains ("32")) || ( myPoint
                            .contains ("13") && myPoint.contains ("31")));

                } else if (box.equals ("23")) {
                    return ( ( myPoint.contains ("21") && myPoint
                            .contains ("22")) || ( myPoint.contains ("13") && myPoint
                            .contains ("33")));

                } else if (box.equals ("31")) {
                    return ( ( myPoint.contains ("32") && myPoint
                            .contains ("33"))
                            || ( myPoint.contains ("21") && myPoint
                                    .contains ("11")) || ( myPoint
                            .contains ("22") && myPoint.contains ("13")));

                } else if (box.equals ("32")) {
                    return ( ( myPoint.contains ("31") && myPoint
                            .contains ("33")) || ( myPoint.contains ("22") && myPoint
                            .contains ("12")));

                } else if (box.equals ("33")) {
                    return ( ( myPoint.contains ("32") && myPoint
                            .contains ("31"))
                            || ( myPoint.contains ("22") && myPoint
                                    .contains ("11")) || ( myPoint
                            .contains ("23") && myPoint.contains ("13")));
                } else {
                    return false;
                }
            }
        } else {
            if (playerPoints.size () < 2) {
                return false;
            } else {
                if (box.equals ("11")) {
                    return ( ( playerPoints.contains ("12") && playerPoints
                            .contains ("13"))
                            || ( playerPoints.contains ("21") && playerPoints
                                    .contains ("31")) || ( playerPoints
                            .contains ("22") && playerPoints.contains ("33")));

                } else if (box.equals ("12")) {
                    return ( ( playerPoints.contains ("11") && playerPoints
                            .contains ("13")) || ( playerPoints.contains ("22") && playerPoints
                            .contains ("32")));

                } else if (box.equals ("13")) {
                    return ( ( playerPoints.contains ("12") && playerPoints
                            .contains ("11"))
                            || ( playerPoints.contains ("22") && playerPoints
                                    .contains ("31")) || ( playerPoints
                            .contains ("23") && playerPoints.contains ("33")));

                } else if (box.equals ("21")) {
                    return ( ( playerPoints.contains ("11") && playerPoints
                            .contains ("31")) || ( playerPoints.contains ("22") && playerPoints
                            .contains ("23")));

                } else if (box.equals ("22")) {
                    return ( ( playerPoints.contains ("11") && playerPoints
                            .contains ("33"))
                            || ( playerPoints.contains ("21") && playerPoints
                                    .contains ("23"))
                            || ( playerPoints.contains ("12") && playerPoints
                                    .contains ("32")) || ( playerPoints
                            .contains ("13") && playerPoints.contains ("31")));

                } else if (box.equals ("23")) {
                    return ( ( playerPoints.contains ("21") && playerPoints
                            .contains ("22")) || ( playerPoints.contains ("13") && playerPoints
                            .contains ("33")));

                } else if (box.equals ("31")) {
                    return ( ( playerPoints.contains ("32") && playerPoints
                            .contains ("33"))
                            || ( playerPoints.contains ("21") && playerPoints
                                    .contains ("11")) || ( playerPoints
                            .contains ("22") && playerPoints.contains ("13")));

                } else if (box.equals ("32")) {
                    return ( ( playerPoints.contains ("31") && playerPoints
                            .contains ("33")) || ( playerPoints.contains ("22") && playerPoints
                            .contains ("12")));

                } else {
                    return ( ( playerPoints.contains ("32") && playerPoints
                            .contains ("31"))
                            || ( playerPoints.contains ("22") && playerPoints
                                    .contains ("11")) || ( playerPoints
                            .contains ("23") && playerPoints.contains ("13")));
                }
            }
        }
    }

    // Converts the mouse coordinates to the box number
    private static String convert (final double x, final double y) {
        if ( ( x <= -100) && ( y >= 100) && ( y <= 300) && ( x >= -300)) {
            return "11";
        } else if ( ( x <= -100) && ( x >= -300)
                && ( ( y >= -100) && ( y <= 100))) {
            return "21";
        } else if ( ( x <= -100) && ( x >= -300) && ( y <= -100)
                && ( y >= -300)) {
            return "31";
        } else if ( ( x >= 100) && ( x <= 300) && ( y >= 100) && ( y <= 300)) {
            return "13";
        } else if ( ( x > 100) && ( x < 300) && ( ( y >= -100) && ( y <= 100))) {
            return "23";
        } else if ( ( x >= 100) && ( x <= 300) && ( y >= -300) && ( y <= -100)) {
            return "33";
        } else if ( ( ( x > -100) && ( x < 100)) && ( y > 100) && ( y < 300)) {
            return "12";
        } else if ( ( ( x > -100) && ( x < 100)) && ( y <= -100)
                && ( y >= -300)) {
            return "32";
        } else {
            return "22";
        }
    }
}
