package days0717;

import java.util.Scanner;

public class Sequential_Number {
	public static void main(String[] args){
	
		 int data[] = {30,20,10,50,40};
		 int find;
		 boolean flag = true;
		 
		 System.out.println("ã���� �ϴ� ���� �Է��ϼ���.");
		 find = new Scanner(System.in).nextInt();
		 
		 /* 	���� �˻� �˰��� 		*/ 
		 for(int i = 0 ; i < data.length ; i ++){
			 if(find == data[i]){
				 System.out.println("ã�ҽ��ϴ�." + data[i]);
				 flag = false;
				 break;
			 }
		}
		 if(flag)
			 System.out.println("�����Ͱ� �������� �ʽ��ϴ�.");
		
		
		
		
	}
}
