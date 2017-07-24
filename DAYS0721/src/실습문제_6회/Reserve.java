package 실습문제_6회;

import java.util.Scanner;

public class Reserve {
		
	char[] typeArr = new char[5];
	
	
	
	public static void Banner(){
		int selectNumber;
		System.out.println("-----------------------------");
		System.out.println("다음 중 하나의 메뉴를 선택하세요.");
		System.out.println("-----------------------------");
		System.out.print("예약(1) , 조회(2) , 취소(3) , 끝내기(4) 선택하세요 :  ");
		selectNumber = new Scanner(System.in).nextInt();
	}
	public static void selctTypeBanner(){
		int selectNumber;
		System.out.println("좌석 구분 : R석(1) , S석(2) , A석(3) , B석(4)");
		
	}
	
	
	public static void main(String[] args){
		int selectNumber;
		do{
			Banner();
			
			
			
			
			
		}while(true);
		
	}


}
