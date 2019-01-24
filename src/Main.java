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

    //Goal state for all puzzles and searches
   final static int[] goalState =
            {
                    1,2,3,
                    8,0,4,
                    7,6,5
            };

/*
    public void solutionFinder(ArrayList<Node> solution){
        if(solution.size() > 0) {
            for(int i =0; i < solution.size();i++) {
                solution.get(i).printPuzzle();
            }
        }else{
            System.out.println("No path found");
        }
    }*/



    public static void main(String args[]) {

        Main myMain = new Main();


        //Builds all board difficulties
        Node nodeEasy = new Node(easyPuzzle);
        Node nodeMedium = new Node(mediumPuzzle);
        Node nodeHard = new Node(hardPuzzle);
    //1    Node nodeGoal = new Node(goalState);

        //by default
        Node finalPuzzle = nodeEasy;

     //   BreadthFirstSearch bs = new BreadthFirstSearch();

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
                } else if (userInput == 3) {
                    System.out.println("You chose hard\n");
                    nodeHard.printPuzzle();
                    finalPuzzle = nodeHard;
                }
            }

            System.out.println("Please choose a search algorithm:");
            System.out.println("1: Breadth First Search");

            int userInput2 = sc.nextInt();
            if(userInput2 < 1 || userInput2 > 7){
                System.out.println("Please enter a number between 1 and 7");
            }else {
                if (userInput2 == 1) {
                    System.out.println("You chose BreadthFirstSearch");
                    Search search = new Search(finalPuzzle, goalState);
                    search.breadthFirstSearch();
                  //  myMain.solutionFinder(solution);
                }
                if(userInput2 == 2){
                    System.out.println("You chose DepthFirstSearch");
                }
                if(userInput2 == 3){
                    System.out.println("You chose Uniform Cost ");
                }
                if(userInput2 == 4){
                    System.out.println("You chose Greedy Best First");
                }
                if(userInput2 == 5){
                    System.out.println("You chose A* search");
                }
                if(userInput2 == 6){
                    System.out.println("You chose A* with Manhattan Search");
                }

            }

    }
}
