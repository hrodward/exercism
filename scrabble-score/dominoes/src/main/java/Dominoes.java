import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class Dominoes {

	private static final String NO_DOMINO_CHAIN_FOUND = "No domino chain found.";

	public List<Domino> formChain(final List<Domino> dominoesList) throws ChainNotFoundException {

		if (dominoesList.isEmpty()) {
			return new ArrayList<>();

		} else if (dominoesList.size() == 1) {
			Domino stone = dominoesList.get(0);
			if(stone.isDouble()) {
				return new ArrayList<>(Arrays.asList(stone));
			}
			throw new ChainNotFoundException(NO_DOMINO_CHAIN_FOUND);
		}

		Domino startingStone = dominoesList.get(0); // any stone will do

		List<Domino> remainingStones = new ArrayList<>(dominoesList);
		remainingStones.remove(startingStone);

		Deque<Domino> solutionStack = new ArrayDeque<>();
		solutionStack.add(startingStone);

		Map<Domino, List<Domino>> triedPaths = new HashMap<>();

		while (remainingStones.size() > 0) {
			Domino nextStone = getAdjacentStone(startingStone, remainingStones, triedPaths.get(startingStone));
			if (nextStone != null) {
				pushNextStoneToSolution(startingStone, remainingStones, solutionStack, triedPaths, nextStone);
				startingStone = nextStone;
			} else {
				if (solutionStack.size() == 1) {
					throw new ChainNotFoundException(NO_DOMINO_CHAIN_FOUND);
				}
				popPreviousStoneFromSolution(solutionStack, remainingStones, triedPaths);
				startingStone = solutionStack.getFirst();
			}
		}

		return adaptSolutionToExpectedType(solutionStack);
	}

	private Domino getAdjacentStone(
			final Domino startingStone,
			final List<Domino> remainingStones,
	    final List<Domino> triedStones)
	{

		Optional<Domino> nextIsLeft = remainingStones.parallelStream()
			.filter(candidate -> triedStones == null || !triedStones.contains(candidate))
			.filter(candidate -> startingStone.getRight() == candidate.getLeft())
			.findAny();
		if(nextIsLeft.isPresent()) {
			return nextIsLeft.get();
		}

		Optional<Domino> nextIsRight = remainingStones.parallelStream()
				.filter(candidate -> triedStones == null || !triedStones.contains(candidate))
				.filter(candidate -> startingStone.getRight() == candidate.getRight())
				.findAny();
		if(nextIsRight.isPresent()) {
			return nextIsRight.get().reverse();
		}

		return null;
	}

	private void pushNextStoneToSolution(
			final Domino startingStone,
			final List<Domino> remainingStones,
			final Deque<Domino> solutionStack,
	    final Map<Domino, List<Domino>> triedPaths,
	    final Domino nextStone)
	{
		solutionStack.push(nextStone);
		remainingStones.remove(nextStone);
		addNextStoneToTriedPaths(startingStone, triedPaths, nextStone);
	}

	private void addNextStoneToTriedPaths(
			final Domino startingStone,
			final Map<Domino, List<Domino>> triedPaths,
			final Domino nextStone)
	{
		List<Domino> triedPathsForStartingStone = triedPaths.get(startingStone);
		if (triedPathsForStartingStone == null) {
			triedPathsForStartingStone = new ArrayList<>();
		}
		triedPathsForStartingStone.add(nextStone);
		triedPaths.put(startingStone, triedPathsForStartingStone);
	}

	private void popPreviousStoneFromSolution(
			final Deque<Domino> solutionStack,
			final List<Domino> remainingStones,
	    final Map<Domino, List<Domino>> triedPaths)
	{
		Domino previousStone = solutionStack.pop();
		remainingStones.add(previousStone);
		triedPaths.remove(previousStone);
	}

	private List<Domino> adaptSolutionToExpectedType(final Deque<Domino> solutionStack) {
		List<Domino> chain = Arrays.asList(solutionStack.toArray(new Domino[0]));
		Collections.reverse(chain);
		return chain;
	}

}