package days0717;

import java.util.Scanner;

public class problem3 {
	public static void main(String[] args){
	
		System.out.println("�⵵�� �Է��ϼ���");
		int year = new Scanner(System.in).nextInt();
		
		if(isLeapYear(year))System.out.println("���� �Դϴ�.");
		else System.out.println("������ �ƴմϴ�.");
		
		
	}
	public static boolean isLeapYear(int year){
		if(year % 4 == 0 && year%100 != 0 || year%400 ==0) return true;
		else return false;
	}

}
