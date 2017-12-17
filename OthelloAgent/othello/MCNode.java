package othello;
import java.util.*;

public class MCNode {
	private OthelloState state;
	private List<OthelloMove> moves;
	private OthelloMove move = null;
	private MCNode parent = null;
	private List<MCNode> children = new ArrayList<MCNode>();
	private int nodeScore = 0;
	private int visited = 0;

	//constructor
	public MCNode(OthelloState s){
		state = s;
		moves = s.generateMoves();
	}
	
	//createNode, requires a move as parameter
	public MCNode(MCNode parent, OthelloMove move){
		this(parent.state.applyMoveCloning(move));
		this.parent = parent;
		this.move = move;
	}
	
	/*
	 * bestChild
	 *  if the next player to move in node is PLAYER1, it returns the child with the maximum average score, if the next player to move in the node is PLAYER2, then it returns the child with the minimum average score.
	 */	
	public MCNode bestChild(int player){
		if(state.nextPlayerToMove==player){
			double max = Double.MIN_VALUE;
			MCNode ret = null;
			for(MCNode curr:children){
				double score = (curr.nodeScore/(double)curr.visited);
				if(score>max){
					max = score;
					ret = curr;
				}
			}
			return ret;
		}
		else{
			double min = Double.MAX_VALUE;;
			MCNode ret = null;
			for(MCNode curr:children){
				double score = (curr.nodeScore/(double)curr.visited);
				if(score<min){
					min = score;
					ret = curr;
				}
			}
			return ret;
		}
	}
	
	/* backup
	 * increments in 1 the number of times "node" has been visited, and updates the average score in "node" with the value "score". If "node" has a parent, then it calls "backup(node.parent,score)".
	 */
	 void backup(MCNode node, int score){
		 node.visited++;
		 node.nodeScore+=score;
		 if(node.parent!=null){
			 backup(node.parent, score);
		 }
	 }
	 
	 //returns score of the state depending on what player the agent is
	 int score(int player){
		 if(player==1){
			 return state.score3();
		 }
		 return state.score4();
	 }
	 
	 //returns action
	 OthelloMove getMove(){
		 return move;
	 }
	 
	 //Checks if any children are not in the tree yet
	 public boolean childrenLeft(){
		 if(children.size()<moves.size()){
			 //System.out.println(children.size()+" "+moves.size());
			 children.add(new MCNode(this,moves.get(children.size())));
			 return true;
		 }
		 return false;
	 }
	 
	 //returns the last child or "new node"
	 public MCNode lastChild(){
		 return children.get(children.size()-1);
	 }
	 
	 //returns whether a node is terminal (game over)
	 public boolean isTerminal(){
		 return state.gameOver();
	 }
	 
	 //returns a random child
	 public MCNode randomChild(Random r){
		 if(children.isEmpty())return null;
			return children.get(r.nextInt(children.size()));
	 }
	 
	 //returns cloned state
	 public OthelloState cloneState(){
		 return state.clone();
	 }
	 
	 //returns whether there are any moves left
	 public boolean movesLeft(){
		 return moves.isEmpty();
	 }
}
	 

