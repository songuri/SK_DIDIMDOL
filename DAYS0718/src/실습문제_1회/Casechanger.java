package �ǽ�����_1ȸ;

import java.util.Scanner;

public class Casechanger {
	public static void main(String[] args){
		char alpha;
		
		System.out.println("���� �� �ڸ� �Է��ϼ���.");
		Scanner in = new Scanner(System.in);
		
		alpha = in.next().charAt(0);  //ù�ڸ� �޾Ƶ��̱� ���ؼ� �̷������� �Ѱ� ��.��
		// in.next()�� ��ȯ���� String�̹Ƿ� charAt�� ����� �� �ִ�.
		
		
		if(alpha >= 'a' && alpha <='z')
			alpha = (char) (alpha-32);
		else if(alpha >= 'A' && alpha <='Z')
			alpha = (char) (alpha+32);
		else{
			System.out.println("�����ڰ� �ƴմϴ�.");
			 System.exit(0);
		}
		
		System.out.println(alpha);
		System.exit(0);
		
	}

}
