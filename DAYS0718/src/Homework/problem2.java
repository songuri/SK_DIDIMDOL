package Homework;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class problem2 {
	public static void main(String[] args){
		String printString[] = {"ÀÔ·Â°ª¿¡ 3,6,9°¡ ¾ø½À´Ï´Ù." , "¹Ú¼ö Â¦" , "¹Ú¼ö Â¦Â¦" , "¹Ú¼ö Â¦Â¦Â¦" , "¹Ú¼ö Â¦Â¦Â¦Â¦"};
		Scanner in = new Scanner(System.in);
		int cnt = 0;
		
		System.out.print("1~9999¼ýÀÚ¸¦ ÀÔ·ÂÇÏ¼¼¿ä.>>");
		int input = in.nextInt();
		
		
		Pattern p = Pattern.compile("[3]|[6]|[9]");
		Matcher m = p.matcher(String.valueOf(input));
		
		while(m.find())  cnt++;
		
		if(cnt < 5) System.out.println(printString[cnt]);
		else System.out.println("¹üÀ§¸¦ ÃÊ°úÇÏ¿´½À´Ï´Ù..");
		
	}

}
