import java.lang.reflect.Array;
import java.util.*;

public class Search {

    private Node root; // Initial State

    private Queue<Node> queue = new LinkedList<>(); // Used in Breadth first search

    private Stack<Node> stack = new Stack<>(); // Used in Depth first search

    private List<Node> childPath = new LinkedList<>(); // Used to hold the path from found node to root

 //   private Set<Node> visited = new HashSet<>();
    private ArrayList<int[]> visited = new ArrayList<>();// holds all Visited Node states

    private int nodesVisited = 0;

    // Takes in the root node i.e. The initial State
    public Search(Node root) {
        this.root = root;
    }
/*\\\\\\\\\\\\\\/////////////////////////\\\\\\\\\\\\\\\\\\//////////////////////\\\\\\\\\\\\\\\\\\//////////////////////////\*/
    /**
     * Breadth First Search
     */
    public void breadthFirstSearch() {
        queue.add(root); //adds root Node to queue
        boolean goalFound = false;
        while (!queue.isEmpty() && !goalFound) { // checks if queue is not empty run loop
            nodesVisited += 1;
            Node currentNode = queue.poll(); // removes front node from queue adds to current
            visited.add(currentNode.getPuzzleState()); // add the current Node to visited
            ArrayList<Node> nextSuccessors = currentNode.createSuccessors(); //Creates Nodes in successor function
            for (Node childNode : nextSuccessors) {
                if(!isList(visited, childNode.getPuzzleState())){ // checks to see if childNodeState is in visited
                    if (childNode.isGoal()) { //goal checker
                        childPathTracer(childPath, childNode);  //creates a path from found goal to rootNode
                        pathPrinter(childPath);//prints path from found goal to rootNode
                        GoalPrinter(nodesVisited); //Prints statistics

                        goalFound = true; //exits loop
                    }
                    if (!queue.contains(childNode)) { //make sure queue doesn't contain child
                        queue.add(childNode); //add child to front of the queue
                    }
                }
            }
        }
    }
/*\\\\\\\\\\\\\\/////////////////////////\\\\\\\\\\\\\\\\\\//////////////////////\\\\\\\\\\\\\\\\\\//////////////////////////\*/
    /**
     * Depth First Search
     */
    public void depthFirstSearch() {
        stack.push(root); // pushes root Node onto Stack
        boolean goalFound = false;
        while (!stack.empty() && !goalFound) { //checks if stack is not empty
            nodesVisited += 1;
            Node currentNode = stack.pop(); // removes top of stack
            ArrayList<Node> nextSuccessors = currentNode.createSuccessors();
            for (Node childNode : nextSuccessors) {
                if(!isList(visited, childNode.getPuzzleState())) {
                    if (childNode.isGoal()) {
                        childPathTracer(childPath, childNode);
                        pathPrinter(childPath);
                        GoalPrinter(nodesVisited);

                        goalFound = true;
                    }
                    if (!stack.contains(childNode) && !visited.contains(childNode.getPuzzleState())) {
                        visited.add(currentNode.getPuzzleState());
                        stack.push(childNode);
                    }
                }
            }
        }
    }
/*\\\\\\\\\\\\\\/////////////////////////\\\\\\\\\\\\\\\\\\//////////////////////\\\\\\\\\\\\\\\\\\//////////////////////////\*/



    /**
     * Printer function to help clean the code
     * @param nodesVisited
     */
    public void GoalPrinter(int nodesVisited)
    {
    System.out.println("========================================");
    System.out.println("Goal Node Found!");
    System.out.println("Nodes Visited:" + nodesVisited);
    System.out.println("Moves to Goal: ");
    System.out.println("Movement Cost: ");
    System.out.println("Total Cost: ");
    System.out.println("=====================================");
    }

    /**
     * Lambda Expression to determine if a nodeState is in the
     * visited arrayList or not. This was used because I did not want to
     * rewrite equals and hashcode for hashset
     */

    public boolean isList(List<int[]> visit, int[] nodeState){
        return visit.stream().anyMatch(a-> Arrays.equals(a, nodeState));
    }


    /**
     * Traces the path from final goal node up to the root adding the parents to a list path
     * @param path takes in the empty child path
     * @param n takes in the found childNode == goal
     */
    public void childPathTracer(List<Node> path, Node n){
        Node current = n;
        path.add(current);
        while(current.parent != null){
            current = current.parent;
            path.add(current);
        }
    }

    /**
     *  takes in the list filled by the childPathTracer and prints out that path
     *  If there is no path prints no path
     * @param childPath takes in list childpath
     */
    public void pathPrinter(List<Node> childPath) {
            if(childPath.size() > 0){
                for(int i = 0; i < childPath.size(); i++){
                    childPath.get(i).printPuzzle();
                }
            }else{
                System.out.println("error");
            }
    }


}
