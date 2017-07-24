package Homework;

import java.util.Scanner;

public class problem1 {
	public static void main(String[] args){
		String rnn = "920426-1031123";
		Scanner in = new Scanner(System.in);
		
		
		rnn = in.nextLine();
		rnn = rnn.replace("-", "");
		
		System.out.println(rnn.substring(0, 6));
	}
	
}
