package days0717;

import java.util.Scanner;

public class problem2 {
	public static void main(String[] args){
		System.out.println("input >>>");
		int input = new Scanner(System.in).nextInt();
		System.out.println(convert(input).toString());
		
	}
	public static StringBuffer convert(int input){
		
		int cash[]={50000,10000,5000,1000,500,100,50,10};
		String cashchar[][] = { {"������ ��" , "��"},{"���� ��" , "��"},{"��õ�� �� " , "��"},
								{"õ�� ��" , "��"} , {"����� ��" , "��"},{"��� ��" , "��"}
		}
		int cash50000 = input / 50000;
		input %= 50000;
		int cash10000 = input / 10000 ;
		input %= 10000;
		int cash5000 = input / 5000;
		input %= 5000;
		int cash1000 = input / 1000;
		input %= 1000;
		int cash500 = input /500;
		input %= 500;
		int cash100 = input /100;
		input %= 100;
		int cash50 = input /50;
		input %= 50;
		int cash10 = input /10;
		StringBuffer result = new StringBuffer();
		
		result.append("����� \n");
		result.append("50000���� : " + cash50000 + "�� \n");
		result.append("10000���� : " + cash10000 + "�� \n");
		result.append("5000���� : " + cash5000 + "�� \n");
		result.append("1000���� : " + cash1000 + "�� \n");
		result.append("500���� : " + cash500 + "��\n");
		result.append("100���� : " + cash100 + "�� \n");
		result.append("50���� : " + cash50 + "��\n");
		result.append("10���� : " + cash10 + "�� \n");
		return result;
		
		
	}

}
