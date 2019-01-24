import java.lang.reflect.Array;
import java.util.*;

public class Search {

    private int[] goalState;
    private Node root;

 //   public int[] getGoalState() {
  //      return goalState;
   // }

    Queue<Node> queue = new LinkedList<>();
    ArrayList<int[]> childPath = new ArrayList<>();
    Set<Node> visited = new HashSet<>();

    private int length = 0;
    public Search(Node root, int[] goalState) {
        this.root = root;
        this.goalState = goalState;
    }

    public void breadthFirstSearch() {

        queue.add(root); //adds root Node to queue
        boolean goalFound = false;
      //  visited.add(root.getPuzzleState());
        while (!queue.isEmpty() && !goalFound) { //checks if queue is not empty run loop
            length += 1;
            Node currentNode = queue.poll(); // removes front node from queue adds to current
            visited.add(currentNode); // add the current Node to visited

            //test print
         //   System.out.println("CURRENT:" + Arrays.toString(currentNode.getPuzzleState()));

            ArrayList<Node> nextSuccessors = currentNode.createSuccessors();
                //successor Tester
           //    for (Node n : nextSuccessors) {
        //            System.out.println("SUCCESSOR:" + Arrays.toString(n.getPuzzleState()));
          //      }
                for (Node childIndex : nextSuccessors) {
                   if(childIndex.isGoal()){
                      System.out.println("Final Node" + Arrays.toString(childIndex.getPuzzleState()));
                        System.out.println("Goal found");
                        System.out.println("Length:" + length);
                        System.out.println("Moves to Goal:" );
                        System.out.println("Movement Cost: ");
                        goalFound = true;
                    }
                    if (!queue.contains(childIndex) && !visited.contains(childIndex)){
                        childIndex.setParent(currentNode);
                 //       System.out.println("parent" + Arrays.toString(childIndex.getParent().getPuzzleState()));
                        if (!childIndex.getParent().isSamePuzzle(childIndex.getPuzzleState())) {
                            queue.add(childIndex);
                        }


                     //   System.out.println("child" + Arrays.toString(childIndex.getPuzzleState()));

                }
            }
        }
    }

}

