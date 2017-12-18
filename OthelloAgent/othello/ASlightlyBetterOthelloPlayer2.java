package othello;

import java.util.List;

/*
 * Pretty much the same as previous minimax but with better scoring heuristics.
 */

public class ASlightlyBetterOthelloPlayer2 extends OthelloPlayer {

	private int max_depth;
	private int playerNum;

	public ASlightlyBetterOthelloPlayer2(int max_depth, int player) {
		this.max_depth = max_depth;
		this.playerNum = player;
	}

	@Override
	public OthelloMove getMove(OthelloState state) {
		return minimax(state, 0);
	}

	public OthelloMove minimax(OthelloState state, int d) {
		int max = 0;
		int currMax = 0;
		List<OthelloMove> moves = state.generateMoves();
		OthelloMove ret = null;
		if (moves.size() > 0)
			ret = moves.get(0);
		for (OthelloMove move : state.generateMoves()) {
			currMax = max(state.applyMoveCloning(move), d + 1);
			if (currMax >= max) {
				max = currMax;
				ret = move;
			}
		}
		return ret;
	}

	public int max(OthelloState state, int d) {
		if (state.gameOver() || state.generateMoves().isEmpty() || d > max_depth) {
			if (playerNum == 2)
				return state.score4();
			return state.score3();
		}
		int currMax = 0;
		int max = Integer.MIN_VALUE;
		for (OthelloMove move : state.generateMoves()) {
			currMax = min(state.applyMoveCloning(move), d + 1);
			if (currMax >= max) {
				max = currMax;
			}
		}
		return max;
	}

	public int min(OthelloState state, int d) {
		if (state.gameOver() || state.generateMoves().isEmpty() || d > max_depth) {
			if (playerNum == 2)
				return state.score4();
			return state.score3();
		}
		int currMin = 0;
		int min = Integer.MAX_VALUE;
		for (OthelloMove move : state.generateMoves()) {
			currMin = max(state.applyMoveCloning(move), d + 1);
			if (currMin <= min) {
				min = currMin;
			}
		}
		return min;
	}
	
	@Override
	public String toString() {
		return "Minimax";
	}
}
