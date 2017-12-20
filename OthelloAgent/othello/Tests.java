package othello;

import static org.junit.Assert.*;

import java.util.*;
import org.junit.Test;

public class Tests {

	@Test
	public void OthelloState() {
		OthelloState o = new OthelloState(5);
		//check dimensions
		assertEquals(o.boardSize,5);
		assertEquals(o.board.length,5);
		assertEquals(o.board[0].length,5);
		//check initial pieces
		assertEquals(o.board[o.boardSize / 2 - 1][o.boardSize / 2 - 1],0);
		assertEquals(o.board[o.boardSize / 2][o.boardSize / 2],0);
		assertEquals(o.board[o.boardSize / 2 - 1][o.boardSize / 2],1);
		assertEquals(o.board[o.boardSize / 2][o.boardSize / 2 - 1],1);
		//check boardsize if input is <2
		OthelloState o2 = new OthelloState(1);
		assertEquals(o2.boardSize,2);
		
		//OthelloStateClone
		OthelloState c = o.clone();
		//check dimensions
		assertEquals(c.boardSize,5);
		assertEquals(c.board.length,5);
		assertEquals(c.board[0].length,5);
		//check initial pieces
		assertEquals(c.board[o.boardSize / 2 - 1][c.boardSize / 2 - 1],0);
		assertEquals(c.board[o.boardSize / 2][c.boardSize / 2],0);
		assertEquals(c.board[o.boardSize / 2 - 1][c.boardSize / 2],1);
		assertEquals(c.board[o.boardSize / 2][c.boardSize / 2 - 1],1);
		
		//check that moves are generated
		List<OthelloMove> moves = c.generateMoves();
		assertEquals(moves.size(),4);
		
		//check scores
		assertEquals(c.score(),0);
		OthelloState c2 = c.applyMoveCloning(moves.get(0));
		c.applyMove(moves.get(0));
		assertEquals(c.score(),3);
		assertEquals(c2.score(),3);
		assertEquals(c.score2(),-3);
		assertEquals(c2.score2(),-3);
		assertEquals(c.score3(),3);
		assertEquals(c2.score3(),3);
		assertEquals(c.score4(),-3);
		assertEquals(c2.score4(),-3);
		assertEquals(c.otherPlayer(1),0);
		assertEquals(c.otherPlayer(0),1);
		//check Winners
		assertEquals(c.getWinner(1),"Player 1 wins!");
		assertEquals(c.getWinner(-1),"Player 2 wins!");
		assertEquals(c.getWinner(0),"It's a tie!");
		//check game over
		assertEquals(c.gameOver(),false);
		
	}

}
