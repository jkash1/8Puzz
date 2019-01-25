import javax.swing.plaf.basic.BasicInternalFrameTitlePane;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;


public class Node {

    //ArrayList to hold all child nodes
    private ArrayList<Node> children;
    ArrayList<String> movement  = new ArrayList<>();
    public Node parent;

    public final static int columnLength = 3;
    private final int SIZE = 9;

    //heuristics
    private int cost; // g(n)
    private int costToGoal; // h(n)
    private int totalCost; // f(n)
    private final int[] GOAL = new int[]{ 1,2,3,8,0,4,7,6,5 };
    private int[] puzzleState;
    private int depth;

    //Node Constructor
    public Node(int[] puzzleState) {
        this.puzzleState = puzzleState;
        children = new ArrayList<>();
    }
    public int[] getPuzzleState() {
        return puzzleState;
    }

    public ArrayList<Node> getChildren() {
        return children;
    }

    public void setChildren(ArrayList<Node> children) {
        this.children = children;
    }

    public void addChild(Node child) {
        children.add(child);
    }

    public int getDepth() {
        return depth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getCostToGoal() {
        return costToGoal;
    }

    public void setCostToGoal(int costToGoal) {
        this.costToGoal = costToGoal;
    }

    public int getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(int totalCost) {
        this.totalCost = totalCost;
    }

    public ArrayList<Node> createSuccessors() {

        int indexOfZero = 0;
        int[] childPuzzleRight = new int[9];
        int[] childPuzzleLeft = new int[9];
        int[] childPuzzleUp = new int[9];
        int[] childPuzzleDown = new int[9];

        for (int i = 0; i <  SIZE; i++) {
            if (puzzleState[i] == 0)
                indexOfZero = i;
        }

        /**
         * moveRightcreates successor node with move to the right as long as
         * it is valid. If the indexOfZero is 2, 5, or 8 condition will fail
         * and the moveRight successor node will not be created.
         */
        if (indexOfZero != 2 && indexOfZero != 5 && indexOfZero != 8) {
            movement.add("RIGHT");
            //keeping parent for output
            copyPuzzle(childPuzzleRight, puzzleState);
            // Moves 0 to the right
            // In future will need to get cost of swapped item
            int temp = childPuzzleRight[indexOfZero + 1];
            childPuzzleRight[indexOfZero + 1] = childPuzzleRight[indexOfZero];
            childPuzzleRight[indexOfZero] = temp;
            childNodeCreator(childPuzzleRight);
        }

        /**
         * moveLeft() method creates successor node with move to the left as long as
         * it is valid. If the indexOfZero is 0, 3, or 6 condition will be false
         * and moveLeft succesor node will not be created.
         */
        if (indexOfZero != 0 && indexOfZero != 3 && indexOfZero != 6) {
            movement.add("LEFT");
            copyPuzzle(childPuzzleLeft, puzzleState);
            //moves zero left
            int temp = childPuzzleLeft[indexOfZero - 1];
            childPuzzleLeft[indexOfZero - 1] = childPuzzleLeft[indexOfZero];
            childPuzzleLeft[indexOfZero] = temp;
            childNodeCreator(childPuzzleLeft);
        }

        /**
         * moveUp creates successor node with move Up as long as
         * it is valid. If the indexOfZero is 0, 1, or 2 condition will be false
         * and moveUp succesor node will not be created.

         */
        if (indexOfZero != 0 && indexOfZero != 1 && indexOfZero  != 2) {
            movement.add("UP");
            copyPuzzle(childPuzzleUp,puzzleState);
            // moves 0 up
            int temp = childPuzzleUp[indexOfZero - 3];
            childPuzzleUp[indexOfZero - 3] = childPuzzleUp[indexOfZero];
            childPuzzleUp[indexOfZero] = temp;

            childNodeCreator(childPuzzleUp);
     //       successors.add(new Node(childPuzzleUp));
        }
        /**
         * moveDowncreates successor node with move Up as long as
         * it is valid. If the indexOfZero is 6, 7, or 8 condition will be false
         * and moveDown successor node will not be created.
         *
         */
        if (indexOfZero != 6 && indexOfZero != 7 && indexOfZero != 8) {
            movement.add("DOWN");
            copyPuzzle(childPuzzleDown,puzzleState);
            //move 0 down
            int temp = childPuzzleDown[indexOfZero + 3];
            childPuzzleDown[indexOfZero + 3] = childPuzzleDown[indexOfZero];
            childPuzzleDown[indexOfZero] = temp;
            childNodeCreator(childPuzzleDown);
        }
        return children;
    }

    // successor helper
    public void childNodeCreator(int[] childPuzzle) {
        Node child = new Node(childPuzzle);
        children.add(child);
        child.parent = this;
    }

    //used to create child arrays
    public void copyPuzzle(int[] childPuzzle, int[] parentPuzzle) {
        for (int i = 0; i < parentPuzzle.length; i++) {
            childPuzzle[i] = parentPuzzle[i];
        }
    }

    public boolean isGoal(){
        if(Arrays.equals(puzzleState, GOAL)){
            return true;
        }
        return false;
    }

    public void printPuzzle() {
        int tile = 0;
        for (int i = 0; i < columnLength; i++) {
            for (int j = 0; j < columnLength; j++) {
                System.out.print(puzzleState[tile] + " ");
                tile++;
            }
            System.out.println();
        }
        System.out.println();
    }
}