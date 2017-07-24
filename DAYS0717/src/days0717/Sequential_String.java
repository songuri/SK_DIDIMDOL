package days0717;

import java.util.Scanner;

public class Sequential_String {
	public static void main(String[] args){
		
		String str[] = {
				"오수철" , "김수철" , "박태호" , "최인호","허길동", "서민호"
		};
		System.out.println("찾고자하는 이름은 ?");
		
		String find = new Scanner(System.in).nextLine();
		boolean flag = true;
		for(int i =  0 ; i < str.length ; i++)
			if(find.equals(str[i])){
				System.out.println("찾았다 ");
				flag = false;
				break;
			}
		
		if(flag) System.out.println("못찾았다.");
	}
}
