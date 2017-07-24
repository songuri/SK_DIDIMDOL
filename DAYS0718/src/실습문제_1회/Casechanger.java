package 실습문제_1회;

import java.util.Scanner;

public class Casechanger {
	public static void main(String[] args){
		char alpha;
		
		System.out.println("문자 한 자를 입력하세요.");
		Scanner in = new Scanner(System.in);
		
		alpha = in.next().charAt(0);  //첫자만 받아들이기 위해서 이런식으로 한것 ㅇ.ㅇ
		// in.next()의 반환형이 String이므로 charAt을 사용할 수 있다.
		
		
		if(alpha >= 'a' && alpha <='z')
			alpha = (char) (alpha-32);
		else if(alpha >= 'A' && alpha <='Z')
			alpha = (char) (alpha+32);
		else{
			System.out.println("영문자가 아닙니다.");
			 System.exit(0);
		}
		
		System.out.println(alpha);
		System.exit(0);
		
	}

}
