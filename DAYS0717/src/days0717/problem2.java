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
		String cashchar[][] = { {"오만원 권" , "매"},{"만원 권" , "매"},{"오천원 권 " , "매"},
								{"천원 권" , "매"} , {"오백원 권" , "개"},{"백원 권" , "개"}
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
		
		result.append("결과는 \n");
		result.append("50000원권 : " + cash50000 + "장 \n");
		result.append("10000원권 : " + cash10000 + "장 \n");
		result.append("5000원권 : " + cash5000 + "장 \n");
		result.append("1000원권 : " + cash1000 + "장 \n");
		result.append("500원권 : " + cash500 + "개\n");
		result.append("100원권 : " + cash100 + "개 \n");
		result.append("50원권 : " + cash50 + "개\n");
		result.append("10원권 : " + cash10 + "개 \n");
		return result;
		
		
	}

}
