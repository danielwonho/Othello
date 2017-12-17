package othello;

import java.util.List;

public class ASlightlyBetterOthelloPlayer extends OthelloPlayer{
	
	private int max_depth;
	private int playerNum;

	public ASlightlyBetterOthelloPlayer(int max_depth, int player){
		this.max_depth = max_depth;
		this.playerNum = player;
	}
	
	@Override
	public OthelloMove getMove(OthelloState state) {
		return minimax(state, 0);
	}

	public OthelloMove minimax(OthelloState state, int d){
		int max = 0;
		int currMax = 0;
		List<OthelloMove> moves = state.generateMoves();
		OthelloMove ret = null;
		if(moves.size()>0)
		ret = moves.get(0);
		for(OthelloMove move:state.generateMoves()){
			currMax = max(state.applyMoveCloning(move),d+1);
			if(currMax>=max){
				max=currMax;
				ret = move;
			}
		}
		return ret;
	}
	
	public int max(OthelloState state, int d){
		if(state.gameOver()||state.generateMoves().isEmpty()||d>max_depth){
			if(playerNum==2)return state.score2();
			return state.score();
		}
		int currMax = 0;
		int max = Integer.MIN_VALUE;
		for(OthelloMove move:state.generateMoves()){
			currMax = min(state.applyMoveCloning(move), d+1);
			if(currMax>=max){
				max=currMax;
			}
		}
		return max;
	}
	
	public int min(OthelloState state, int d){
		if(state.gameOver()||state.generateMoves().isEmpty()||d>max_depth){
			if(playerNum==2)return state.score2();
			return state.score();
		}
		int currMin = 0;
		int min = Integer.MAX_VALUE;
		for(OthelloMove move:state.generateMoves()){
			currMin = max(state.applyMoveCloning(move), d+1);
			if(currMin<=min){
				min=currMin;
			}
		}
		return min;
	}
}
