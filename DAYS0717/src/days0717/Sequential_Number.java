package days0717;

import java.util.Scanner;

public class Sequential_Number {
	public static void main(String[] args){
	
		 int data[] = {30,20,10,50,40};
		 int find;
		 boolean flag = true;
		 
		 System.out.println("찾고자 하는 값을 입력하세요.");
		 find = new Scanner(System.in).nextInt();
		 
		 /* 	순차 검색 알고리즘 		*/ 
		 for(int i = 0 ; i < data.length ; i ++){
			 if(find == data[i]){
				 System.out.println("찾았습니다." + data[i]);
				 flag = false;
				 break;
			 }
		}
		 if(flag)
			 System.out.println("데이터가 존재하지 않습니다.");
		
		
		
		
	}
}
