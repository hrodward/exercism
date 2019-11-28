import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Dominoes {

	private static final String NO_DOMINO_CHAIN_FOUND = "No domino chain found.";

	public List<Domino> formChain(final List<Domino> dominoesList) throws ChainNotFoundException {

		if (dominoesList.isEmpty()) {
			return new ArrayList<>();
		} else if (dominoesList.size() == 1) {
			Domino stone = dominoesList.get(0);
			if(stone.getLeft() == stone.getRight()) {
				return new ArrayList<>(Arrays.asList(stone));
			}
			throw new ChainNotFoundException(NO_DOMINO_CHAIN_FOUND);
		}

//		checkEvenNumberOfLeftAndRightValuesInDifferentStones(dominoesList);

		Domino startingStone = dominoesList.get(0);

		List<Domino> remainingStones = new ArrayList<>(dominoesList);
		remainingStones.remove(startingStone);

		Deque<Domino> deque = new ArrayDeque<>();
		deque.add(startingStone);

		Domino previousStone = null;
		Map<Domino, List<Domino>> triedCombinations = new HashMap<>();
		while (remainingStones.size() > 0) {

			System.out.println("RemainingStones = " + remainingStones);
			System.out.println("triedCombinations = " + triedCombinations);
			System.out.println("startingStone = " + startingStone);
			System.out.println("previousStone = " + previousStone);
			System.out.println("deque = " + deque);
			System.out.println();

			Domino nextStone = getAdjacentStoneAvoidingPrevious(startingStone, remainingStones, triedCombinations.get(startingStone));

			if (nextStone != null) {
				System.out.println("nextStone = " + nextStone + "\n");
				if(triedCombinations.get(startingStone) == null) {
					triedCombinations.put(startingStone, new ArrayList<>(Arrays.asList(nextStone)));
				} else {
					List<Domino> more = triedCombinations.get(startingStone);
					more.add(nextStone);
					triedCombinations.put(startingStone, more);
				}
				deque.push(nextStone);
				remainingStones.remove(nextStone);
				startingStone = nextStone;
				previousStone = null;
			} else {
				System.out.println("No nextStone -> popping deque\n");
				if (deque.size() == 1) {
					throw new ChainNotFoundException(NO_DOMINO_CHAIN_FOUND);
				}
				previousStone = deque.pop();
				remainingStones.add(previousStone);
				startingStone = deque.getFirst();
				List<Domino> more = triedCombinations.get(previousStone);
				if(more != null) {
					more.clear();
				} else {
					more = new ArrayList<>();
				}
				triedCombinations.put(previousStone, more);
			}
//			freshDominoesList.clear();
//			break;
		}

		List<Domino> chain = Arrays.asList(deque.toArray(new Domino[0]));
		Collections.reverse(chain);
		return chain;
	}

	private Domino getAdjacentStoneAvoidingPrevious(final Domino startingStone, final List<Domino> remainingStones,
	    final List<Domino> triedStones) {
		for (Domino candidate : remainingStones) {
			if (triedStones != null && triedStones.contains(candidate)) {
				continue;
			}
			int matchingNumber = startingStone.getRight();
			if (matchingNumber == candidate.getLeft()) {
				return candidate;
			}
			if (matchingNumber == candidate.getRight()) {
				return new Domino(candidate.getRight(), candidate.getLeft());
			}
		}
		return null;
	}

	private void checkEvenNumberOfLeftAndRightValuesInDifferentStones(final List<Domino> dominoesList)
	    throws ChainNotFoundException {
		Map<Integer, Integer> pairCount = new HashMap<>();
		for (Domino domino : dominoesList) {
			increaseCounter(pairCount, domino.getLeft());
			if(domino.getLeft() != domino.getRight()) {
				increaseCounter(pairCount, domino.getRight());
			}
		}
		for (Integer counter : pairCount.values()) {
			if(counter % 2 != 0) {
				throw new ChainNotFoundException(NO_DOMINO_CHAIN_FOUND);
			}
		}
	}

	private void increaseCounter(final Map<Integer, Integer> pairCount, final int value) {
		Integer counter = pairCount.get(value);
		pairCount.put(value, counter == null ? 1 : counter + 1);
	}

}