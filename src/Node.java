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


    /**
     * This method creates an arrayList of successor Nodes. The nodes contain an int[] which
     * is the puzzleState. This method loops through the initial puzzleState (root and then parent).
     * It uses a copy puzzle method to create the initial puzzleState. It then swaps the indexes of
     * the array and uses childNodeCreator to an ArrayList<Node> of children with the new states.
     * It also adds its parent.
     * @return
     */

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
         * This condition moves 0 right creating a successor node as long as
         * it is valid. If the indexOfZero is 2, 5, or 8 condition will fail.
         */
        if (indexOfZero != 2 && indexOfZero != 5 && indexOfZero != 8) {
            copyPuzzle(childPuzzleRight, puzzleState);
            int temp = childPuzzleRight[indexOfZero + 1];
            childPuzzleRight[indexOfZero + 1] = childPuzzleRight[indexOfZero];
            childPuzzleRight[indexOfZero] = temp;
            childNodeCreator(childPuzzleRight);
        }

        /**
         * This condition moves 0 Left creating a successor node as long as
         * it is valid. If the indexOfZero is 0, 3, or 6 condition will fail.
         */
        if (indexOfZero != 0 && indexOfZero != 3 && indexOfZero != 6) {
            copyPuzzle(childPuzzleLeft, puzzleState);
            int temp = childPuzzleLeft[indexOfZero - 1];
            childPuzzleLeft[indexOfZero - 1] = childPuzzleLeft[indexOfZero];
            childPuzzleLeft[indexOfZero] = temp;
            childNodeCreator(childPuzzleLeft);
        }

        /**
         * This condition moves 0 Up creating a successor node as long as
         * it is valid. If the indexOfZero is 0, 1, or 2 condition will fail.
         */
        if (indexOfZero != 0 && indexOfZero != 1 && indexOfZero  != 2) {
            copyPuzzle(childPuzzleUp,puzzleState);
            int temp = childPuzzleUp[indexOfZero - 3];
            childPuzzleUp[indexOfZero - 3] = childPuzzleUp[indexOfZero];
            childPuzzleUp[indexOfZero] = temp;
            childNodeCreator(childPuzzleUp);
        }

        /**
         * This condition moves 0 Down creating a successor node as long as
         * it is valid. If the indexOfZero is 6, 7, or 8 condition will fail.
         */
        if (indexOfZero != 6 && indexOfZero != 7 && indexOfZero != 8) {
            copyPuzzle(childPuzzleDown,puzzleState);
            int temp = childPuzzleDown[indexOfZero + 3];
            childPuzzleDown[indexOfZero + 3] = childPuzzleDown[indexOfZero];
            childPuzzleDown[indexOfZero] = temp;
            childNodeCreator(childPuzzleDown);
        }
        return children;
    }

    /**
     * Create Successor helper method takes in the new childPuzzle and creates
     * a new Node of the updated move.
     * @param childPuzzle
     */
    public void childNodeCreator(int[] childPuzzle) {
        Node child = new Node(childPuzzle);
        children.add(child);
        child.parent = this;
    }


    /**
     * Used in createSuccessor(). This method copies the parentNode's state into
     * a new childPuzzle array. After the swap the new child puzzle is used in childNodeCreator().
     * @param childPuzzle
     * @param parentPuzzle
     */
    public void copyPuzzle(int[] childPuzzle, int[] parentPuzzle) {
        for (int i = 0; i < parentPuzzle.length; i++) {
            childPuzzle[i] = parentPuzzle[i];
        }
    }

    /**
     * Checks if the puzzleState is the Goal or not. Used in search methods to determine
     * if Node isGoal or not.
     */

    public boolean isGoal(){
        if(Arrays.equals(puzzleState, GOAL)){
            return true;
        }
        return false;
    }

    /**
     * Formats the puzzleState to output the N-Array as a visual puzzle.
     */
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