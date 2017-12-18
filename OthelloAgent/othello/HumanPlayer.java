package othello;

public class HumanPlayer extends OthelloPlayer{

	@Override
	public OthelloMove getMove(OthelloState state) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public OthelloMove selectMove(OthelloState state){
		ChoiceSelector c = new ChoiceSelector(state.generateMoves());
		
	}

}
