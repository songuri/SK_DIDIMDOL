package days0713;

public class test2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String str[][]={
				{"10" , "���ö" , "�λ�" , "������"},
				{"1" , "�ڼ���" , "����" , "�л�"},
				{"2" , "������" , "�뱸" , "�ǻ�"}
				
		};
		String temp;
		for(int i = 0 ; i < str.length-1; i ++){
			for(int j = 0 ; j < str.length ; i ++){
				if(Integer.parseInt(str[i][0]) > Integer.parseInt(str[j][0])){
					for(int k = 0 ; k < str[0].length ; k++){
						temp = str[i][k];
						str[i][k] = str[j][k];
						str[j][k] = temp;
					}
					
				}
			}
		}
		/*
		 * �����˻��� �̺� �˻�
		 * ���� �˻�
		 * ���� : 
		 * 		1 > �˰��� ������ �����ϴ�.
		 * 		2 > DATA�� ���Ľ�Ű�� �ʾƵ� �˻��� �����ϴ�.
		 * ���� :
		 * 		1 > ��� �˻� Ƚ�� : ( N+1) /2
		 * 
		 *  �̺� �˻�
		 *  	1 > ��հ˻�Ƚ�� LOG(N)
		 *  	2 > DATA�� ���ĵǾ� �־�� �Ѵ�.
		 * 
		 */
		
	}

}

