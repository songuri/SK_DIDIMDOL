package days0717;

import java.util.Scanner;

public class Sequential_String {
	public static void main(String[] args){
		
		String str[] = {
				"����ö" , "���ö" , "����ȣ" , "����ȣ","��浿", "����ȣ"
		};
		System.out.println("ã�����ϴ� �̸��� ?");
		
		String find = new Scanner(System.in).nextLine();
		boolean flag = true;
		for(int i =  0 ; i < str.length ; i++)
			if(find.equals(str[i])){
				System.out.println("ã�Ҵ� ");
				flag = false;
				break;
			}
		
		if(flag) System.out.println("��ã�Ҵ�.");
	}
}
