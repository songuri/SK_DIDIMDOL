package LIST;

import java.util.ArrayList;
import java.util.Random;

public class ArrayList_Test {
	public static void main(String[] args){
		ArrayList<Integer> list = new ArrayList<Integer>();
		list.add(new Integer(90));
		list.add(90); // �ڵ��ڽ�(boxing) => JDK 1.5�������� �����ϴ�.
		
		Random ran = new Random();
		for(int i = 0 ; i < 10 ; i ++) list.add(ran.nextInt(1000));
		
		for(Integer tmp : list) System.out.print(tmp + " ");
		
		list.add(2,90);
		System.out.println(list.get(0));
	}

}
