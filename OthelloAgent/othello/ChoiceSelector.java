package othello;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;



public abstract class ChoiceSelector {
	
	Scanner s = new Scanner(System.in);     
	ArrayList<?> collection;
	
	public void setType(ArrayList<?> t){
		collection = t;
	}

	
	public  Object getChoice(String type){
		System.out.println("Please select "+type+" from the choices below.");
		display();
		int choice = getInput(type);
		return collection.get(choice);
	}
	
	public void display(){
		if(collection.size()>0){
			for(int i=0; i<collection.size(); i++){
				System.out.println(i+1+") "+collection.get(i));
			}
		}
		else{
			System.out.println("There are no choices to choose from.");
		}
	}
	
	public int getInput(String type){
		int n = -1;
		while(n<1||n>collection.size()){
			try{
				System.out.println("Please enter the choice number of "+type);
				n = Integer.parseInt(s.nextLine());
				return n-1;
			}
			catch(NumberFormatException nfe){
				System.out.println("Please try again with a valid integer choice.");
			}
			System.out.println("Please enter a choice from 1 to "+collection.size());
		}
		return n-1;
	}
}
