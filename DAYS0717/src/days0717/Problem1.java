package days0717;

import java.util.Scanner;

public class Problem1 {
	public static void main(String[] args){
		System.out.println("�Է� : ");
		String input = new Scanner(System.in).nextLine();
		converter(input);
		
		
	
	}
	public static void converter(String input){
		if(input.charAt(0) >= 97 && input.charAt(0) <= 122)	
			System.out.println("��ȯ : " + input.toUpperCase());
		else if(input.charAt(0) >= 65 && input.charAt(0) <= 90)
			System.out.println("��ȯ : " + input.toLowerCase());
		else
			System.out.println("���ھƴ�");
	}

}
