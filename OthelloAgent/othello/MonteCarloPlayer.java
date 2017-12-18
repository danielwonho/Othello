package othello;

import java.util.*;

public class MonteCarloPlayer extends OthelloPlayer {
	private int iterations;
	private Random r = new Random();
	private int playernum;

	/*
	 * Default constructor for the montecarlo player agent.
	 */
	public MonteCarloPlayer(int i, int p) {
		iterations = i;
		playernum = p;
	}

	/*
	 * The main search function for the monte carlo tree search.
	 */
	public OthelloMove MonteCarloTreeSearch(OthelloState state) {
		MCNode root = new MCNode(state);
		if (root.movesLeft())
			return null;

		for (int i = 0; i < iterations; i++) {
			MCNode node = treePolicy(root);
			if (node != null) {
				OthelloState node2 = defaultPolicy(node);
				int Node2Score = 0;
				if (playernum == 1) {
					Node2Score = node2.score4();
				} else {
					Node2Score = node2.score3();
				}
				node.backup(node, Node2Score);
			}
		}
		return root.bestChild(playernum).getMove();
	}

	/*
	 * Monte carlo tree policy function. if there are children, return the last
	 * child if the node is terminal, just return the node otherwise just choose
	 * the best child or a random child (sometimes)
	 */
	private MCNode treePolicy(MCNode node) {
		if (node == null)
			return null;// System.out.println("HI");
		if (node.childrenLeft()) {
			return node.lastChild();
		}
		if (node.isTerminal()) {
			return node;
		}
		MCNode nodetmp = null;
		if (r.nextInt(10) < 9) {
			nodetmp = node.bestChild(playernum);
		} else {
			nodetmp = node.randomChild(r);
		}
		return treePolicy(nodetmp);

	}

	/*
	 * default policy where we will just generate random moves
	 */
	private OthelloState defaultPolicy(MCNode node) {
		OthelloMove moveToMake = null;
		OthelloState state = node.cloneState();
		do {
			// generate the list of moves:
			List<OthelloMove> moves = state.generateMoves();
			// return one at random:
			if (!moves.isEmpty())
				moveToMake = moves.get(r.nextInt(moves.size()));
			state = state.applyMoveCloning(moveToMake);
		} while (!state.gameOver());
		return state;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cs380.othello.OthelloPlayer#getMove(cs380.othello.OthelloState)
	 */
	@Override
	public OthelloMove getMove(OthelloState state) {
		return MonteCarloTreeSearch(state);
	}
	
	@Override
	public String toString() {
		return "Montecarlo Tree Search";
	}
}
