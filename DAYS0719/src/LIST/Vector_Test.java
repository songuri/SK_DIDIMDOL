package LIST;

import java.util.Vector;

public class Vector_Test {
	public static void main(String[] args){
		
		String arr[] = {"�迵ȣ" , "������", "������", "�����", "������", "���ǥ"};
		
		Vector<String> vec = new Vector<String>(4,3);
		//�ʱ� ������� 4���̰� 4�� �̻��� ������ ������ 3���� �ø���� ���� ��.��
		//�� Vector�� ���� �迭�̶�� ���� Ȯ���ҷ��� �̷��� �Ѱ���
		
		
		System.out.println("vector�� ũ�� : " + vec.size());
		System.out.println("vector�� �뷮 : " + vec.capacity());
		

		for(String tmp : arr)
			vec.add(tmp);
		

		for(String item : vec) System.out.print(item + " ");
		
		System.out.println(arr.toString());
		System.out.println("vector�� ũ�� : " + vec.size());
		System.out.println("vector�� �뷮 : " + vec.capacity());
		
		
	}
}
