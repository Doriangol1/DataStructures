package search;

import java.util.*;

/**
 * An implementation of a Searcher that performs an iterative search,
 * storing the list of next states in a Stack. This results in a
 * depth-first search.
 * 
 */
public class StackBasedDepthFirstSearcher<T> extends Searcher<T> {
	
	public StackBasedDepthFirstSearcher(SearchProblem<T> searchProblem) {
		super(searchProblem);
		
	}


	@Override
	public List<T> solve() {
		// TODO
		
		Stack<T> stack = new Stack<T>(); // create stack
		visitedStates.add(searchProblem.getInitialState()); // set intial state to visited
		stack.push(searchProblem.getInitialState()); // push intial state to stack
		T neighbor = null;

		while (!stack.isEmpty()){

			neighbor = getNextUnivistedNeighbor(stack.peek()); //get next neighbor of first item in stack

			if (searchProblem.isGoalState(neighbor)){ // if goal state reached
				stack.push(neighbor); // add last position (neighbor) to the stack and break;
				break;
			}

			if (neighbor == null){ // if no neighbor
				stack.pop(); // pop from stack
			}
			else { // otherwise, visit and add to stack
				visitedStates.add(neighbor);
				stack.push(neighbor);
			}

		}

		List<T> path = new LinkedList<>();
		stackToList(stack, path); // convert stack to list

		return path; // return path list
	}



	public List<T> stackToList(Stack<T> stack, List<T> list){ // method to convert stack to list

		if (stack.isEmpty()){ // if empty stack 
			return list;
		}

		T add = stack.pop(); // pop from stack
		stackToList(stack, list); // recurse with new stack
		list.add(add); // add stack to list bottom up

		return list; // return the list
	}

	public T getNextUnivistedNeighbor(T v){ // method to get next unvisited neighbor

		for (int i =0; i<searchProblem.getSuccessors(v).size(); i++){ // go through successors list
			if (!visitedStates.contains(searchProblem.getSuccessors(v).get(i))){ // if it has not been visited
				return searchProblem.getSuccessors(v).get(i); // return it
			}
		}

		return null; // null if no neighbors
	}
}
