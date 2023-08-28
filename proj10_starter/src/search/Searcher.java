package search;

import java.util.ArrayList;
import java.util.List;

/**
 * An abstraction over the idea of a search.
 *
 * @author liberato
 *
 * @param <T>
 */
public abstract class Searcher<T> {
	protected final SearchProblem<T> searchProblem;
	protected final List<T> visitedStates;
	protected List<T> solution;

	/**
	 * Instantiates a searcher.
	 * 
	 * @param searchProblem
	 *            the search problem for which this searcher will find and
	 *            validate solutions
	 */
	public Searcher(SearchProblem<T> searchProblem) {
		this.searchProblem = searchProblem;
		this.visitedStates = new ArrayList<T>();
	}

	/**
	 * Finds and return a solution to the problem, consisting of a list of
	 * states.
	 * 
	 * The list should start with the initial state of the underlying problem.
	 * Then, it should have one or more additional states. Each state should be
	 * a successor of its predecessor. The last state should be a goal state of
	 * the underlying problem.
	 * 
	 * If there is no solution, then this method should return an empty list.
	 * 
	 * @return a solution to the problem (or an empty list)
	 */
	public abstract List<T> solve();

	/**
	 * Checks that a solution is valid.
	 * 
	 * A valid solution consists of a list of states. The list should start with
	 * the initial state of the underlying problem. Then, it should have one or
	 * more additional states. Each state should be a successor of its
	 * predecessor. The last state should be a goal state of the underlying
	 * problem.
	 * 
	 * @param solution
	 * @return true iff this solution is a valid solution
	 * @throws NullPointerException
	 *             if solution is null
	 */
	public final boolean isValid(List<T> solution) {
        // TODO

		if (solution == null){ // if null solution
			throw new NullPointerException();
		}

		if (solution.isEmpty()) // if empty solution
			return false;

		if (!searchProblem.getInitialState().equals(solution.get(0))) // if initial state doesn't match
			return false;

		for (int i=1; i<solution.size();i++){ // check that each path state is a successor of it's previous state
			if (!searchProblem.getSuccessors(solution.get(i-1)).contains(solution.get(i)))
				return false;
		}
		
		if (!searchProblem.isGoalState(solution.get(solution.size() - 1))) // check that goal state matches
			return false;
		
        return true; // if all passes, return true;
	}
}
