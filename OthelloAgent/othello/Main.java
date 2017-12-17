package othello;

/**
 *
 * @author Daniel Jeong
 */
public class Main {
    
    
    public static void main(String args[]) {
        // Create the game state with the initial position for an 8x8 board:
    	int n = 0;
    	int d = 0;
    	try{
			n = Integer.parseInt(args[0]);
			d = Integer.parseInt(args[1]);
		}
		catch(Exception NumberFormatException){
			System.out.println("Wrong input. Please enter whether you want the non-random player to be player 1 or 2 followed by the number of iterations.");
			System.exit(0);
		}
        OthelloState state = new OthelloState(8);
        OthelloPlayer players[] = {new OthelloRandomPlayer(),
        		//new ASlightlyBetterOthelloPlayer2(d,n)
        		new MonteCarloPlayer(d,n)
        	                       };
        if(n==1){
        	OthelloPlayer temp = players[0];
        	players[0] = players[1];
        	players[1] = temp;
        }
        do{
            // Display the current state in the console:
            System.out.println("\nCurrent state, " + OthelloState.PLAYER_NAMES[state.nextPlayerToMove] + " to move:");
            System.out.print(state);
            
            // Get the move from the player:
            OthelloMove move = players[state.nextPlayerToMove].getMove(state);
            if(move==null)System.out.println("No move available for: "+OthelloState.PLAYER_NAMES[state.nextPlayerToMove]);
            else{
            	System.out.println(move);
            }
            state = state.applyMoveCloning(move);            
        }while(!state.gameOver());

        // Show the result of the game:
        System.out.println("\nFinal state with score: " + state.score());
        System.out.println(state);
        System.out.println(state.getWinner(state.score())); 
    }   
    
}
