package days0717;

import java.util.Scanner;

public class problem3 {
	public static void main(String[] args){
	
		System.out.println("년도를 입력하세요");
		int year = new Scanner(System.in).nextInt();
		
		if(isLeapYear(year))System.out.println("윤년 입니다.");
		else System.out.println("윤년이 아닙니다.");
		
		
	}
	public static boolean isLeapYear(int year){
		if(year % 4 == 0 && year%100 != 0 || year%400 ==0) return true;
		else return false;
	}

}
