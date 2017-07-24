package 실습문제_1회;

import java.util.Scanner;

public class ChangeMoney {
	public static void main(String[] args){
		int arrMoney[] = {50000,10000,5000,1000,500,100,50,10,1};
		String str[] = {"오만원 권" , "만원 권" , "오천원 권" , "천원 권" , "오백원 권 ", "백원 권 ", "오십원 권","십원 권" , "일원 권"};
		
		int result[] = new int[9];
		//이런식으로 공간을 고정적으로 확보하면 전체가 0으로 초기화가 되어 버린다.
		
		
		System.out.println("금액을 입력하세요.");
		Scanner in = new Scanner(System.in);
		int Money = in.nextInt();
		
		
		
		for(int i = 0 ; i < arrMoney.length;  i++){
			int num = Money/arrMoney[i];
			int remainder = Money%arrMoney[i];
			Money = remainder;
			
			if(num  > 0 && i <4){
				System.out.println(str[i] + " " + num + "매");
			}
			else if(num > 0 && i >= 4){
				System.out.println(str[i] + " " + num + "개");
			}
		}
		
	}

}
