import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    /*
    This class runs the Game and User Interface
    User may choose the difficulty of the Puzzle and
    the type of search algorithm they will use to solve it
    this also outputs all the related statistics for
    the algorithms
     */

    /*
       Initializes puzzles at various difficulties to be chosen
       by the user in main method. Puzzle represented as a one dimensional
       array to keep to simplicity.
    */
    //easy difficulty puzzle
    final static int[] easyPuzzle =
            {
                    1,3,4,
                    8,6,2,
                    7,0,5
            };
    //medium difficulty puzzle
    final static int[] mediumPuzzle =
            {
                    2,8,1,
                    0,4,3,
                    7,6,5
            };
    //hard difficulty puzzle
    final static int[] hardPuzzle =
            {
                    5,6,7,
                    4,0,8,
                    3,2,1
            };

    public static void main(String args[]) {

        //Builds all board difficulties
        Node nodeEasy = new Node(easyPuzzle);
        Node nodeMedium = new Node(mediumPuzzle);
        Node nodeHard = new Node(hardPuzzle);

        //by default
        Node finalPuzzle = nodeEasy;

            Scanner sc = new Scanner(System.in);

            System.out.println("This is an 8-Puzzle Solver");
            System.out.println("Please choose a Difficulty:");
            System.out.println("1: Easy");
            System.out.println("2: Medium");
            System.out.println("3: Hard");

            int userInput = sc.nextInt();
            if (userInput < 1 || userInput > 3) {
                System.out.println("Please enter a number between 1 and 3");
            }else{
                if (userInput == 1) {
                    System.out.println("You chose easy\n");
                    nodeEasy.printPuzzle();
                    finalPuzzle = nodeEasy;
                } else if (userInput == 2) {
                    System.out.println("You chose medium\n");
                    nodeMedium.printPuzzle();
                    finalPuzzle = nodeMedium;
                } else{
                    System.out.println("You chose hard\n");
                    nodeHard.printPuzzle();
                    finalPuzzle = nodeHard;
                }
            }

            System.out.println("Please choose a search algorithm:");
            System.out.println("1: Breadth First Search");
            System.out.println("2: Depth First Search");
            System.out.println("3: Uniform Cost");
            System.out.println("4: Greedy Best First");
            System.out.println("5: A* search");
            System.out.println("6: A* with Manhattan Search");

            int userInput2 = sc.nextInt();
            Search search = new Search(finalPuzzle);
            if(userInput2 < 1 || userInput2 > 7){
                System.out.println("Please enter a number between 1 and 7");
            }else {
                if (userInput2 == 1) {
                    System.out.println("You chose BreadthFirstSearch");
                    System.out.println("Running Search.....");
                    search.breadthFirstSearch();
                }
                if(userInput2 == 2){
                    System.out.println("You chose DepthFirstSearch");
                    System.out.println("Running search....");
                    search.depthFirstSearch();
                }
                if(userInput2 == 3){
                    System.out.println("You chose Uniform Cost ");
                    System.out.println("Running search....");
                }
                if(userInput2 == 4){
                    System.out.println("You chose Greedy Best First");
                    System.out.println("Running search....");
                }

                if(userInput2 == 5){
                    System.out.println("You chose A* search");
                    System.out.println("Running search....");
                }
                if(userInput2 == 6){
                    System.out.println("You chose A* with Manhattan Search");
                    System.out.println("Running search....");
                }

            }
    }
}
