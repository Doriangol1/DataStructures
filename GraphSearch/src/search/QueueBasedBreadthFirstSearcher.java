package search;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.*;
/**
 * An implementation of a Searcher that performs an iterative search,
 * storing the list of next states in a Queue. This results in a
 * breadth-first search.
 * 
 */
public class QueueBasedBreadthFirstSearcher<T> extends Searcher<T> {

	public QueueBasedBreadthFirstSearcher(SearchProblem<T> searchProblem) {
		super(searchProblem);
	}

	@Override
	public List<T> solve() {
        		// TODO

		Queue<List<T>> queue = new LinkedList<List<T>>(); // intialize queue that stores paths, with the last item of each path being the current position
		visitedStates.add(searchProblem.getInitialState()); // "visit" initial state
		
		List<T> Ipath = new LinkedList<T>();
		Ipath.add(searchProblem.getInitialState()); // add initial state to first path
		queue.add(Ipath); // add original path to queue
		
		
		while (!queue.isEmpty()){ // go through queue

			List<T> path = queue.remove(); // dequeue next path
			T current = path.get(path.size()-1); // store current state, being the path's last state
			
			if (searchProblem.isGoalState(current)){ // if current state is the goal state
				return path; // return the path
			}
		
			for (T neighbor : searchProblem.getSuccessors(current)){ // for each neighbor

				if (!visitedStates.contains(neighbor)){ // if it hasn't been visited

					List<T> updatedPath = new LinkedList<T>(path); // create new updated path with previous path in it
					updatedPath.add(neighbor); // add neighor to path
					visitedStates.add(neighbor); // "visit" neighbor 
					queue.add(updatedPath); // add updated path to queue
					
				}	

			}
				
		}		
		
		return new LinkedList<T>(); // if goal state is not reached, return empty list
	
	}

}
