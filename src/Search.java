import java.lang.reflect.Array;
import java.util.*;

public class Search {

    private int[] goalState;
    private Node root;
    private Queue<Node> queue = new LinkedList<>();
    private List<Node> childPath = new LinkedList<>();
    private Set<Node> visited = new HashSet<>();

    private int length = 0;

    public Search(Node root, int[] goalState) {
        this.root = root;
        this.goalState = goalState;
    }

    public void breadthFirstSearch() {
        queue.add(root); //adds root Node to queue
        boolean goalFound = false;
        while (!queue.isEmpty() && !goalFound) { //checks if queue is not empty run loop
            length += 1;
            Node currentNode = queue.poll(); // removes front node from queue adds to current
            visited.add(currentNode); // add the current Node to visited
            ArrayList<Node> nextSuccessors = currentNode.createSuccessors();

            for (Node childIndex : nextSuccessors) {

                if (childIndex.isGoal()) {

                    PathTrace(childPath, childIndex);
                    solutionFinder(childPath);

                    System.out.println("========================================");
                    System.out.println("Goal Node Found: ");
                    System.out.println("Nodes Visited:" + length);
                    System.out.println("Moves to Goal:");
                    System.out.println("Movement Cost: ");
                    System.out.println("=======================================");
                    goalFound = true;

                }
                if (!queue.contains(childIndex) && !visited.contains(childIndex)) {
                        queue.add(childIndex);
                }
            }
        }
    }

    public void PathTrace(List<Node> path, Node n){
        Node current = n;
        path.add(current);

        while((current.parent != null)){
            current = current.parent;
            path.add(current);
        }
    }

    public void solutionFinder(List<Node> childPath) {
            if(childPath.size() > 0){
                for(int i = 0; i < childPath.size(); i++){
                    childPath.get(i).printPuzzle();
                }
            }else{
                System.out.println("no path found");
            }
    }
}

