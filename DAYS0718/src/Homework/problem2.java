package Homework;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class problem2 {
	public static void main(String[] args){
		String printString[] = {"�Է°��� 3,6,9�� �����ϴ�." , "�ڼ� ¦" , "�ڼ� ¦¦" , "�ڼ� ¦¦¦" , "�ڼ� ¦¦¦¦"};
		Scanner in = new Scanner(System.in);
		int cnt = 0;
		
		System.out.print("1~9999���ڸ� �Է��ϼ���.>>");
		int input = in.nextInt();
		
		
		Pattern p = Pattern.compile("[3]|[6]|[9]");
		Matcher m = p.matcher(String.valueOf(input));
		
		while(m.find())  cnt++;
		
		if(cnt < 5) System.out.println(printString[cnt]);
		else System.out.println("������ �ʰ��Ͽ����ϴ�..");
		
	}

}
