package othello;

import java.util.ArrayList;
import java.util.List;

public class AgentSelector extends ChoiceSelector {
	ArrayList<OthelloPlayer> collection = new ArrayList<OthelloPlayer>();
	
	public AgentSelector(ArrayList<OthelloPlayer> c){
		setType(c);
	}
}
