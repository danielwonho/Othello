/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package othello;

public abstract class OthelloPlayer {
	public abstract OthelloMove getMove(OthelloState state);
	public abstract String toString();
}
