package days0717;

public class humanNumberChecker {
	public static void main(String[] args){
		String Number = "920426-1031123";
		
		if(isAvailableHumanNumber(Number))
			System.out.println("����");
		else{
			System.out.println("Ʋ�Ⱦ�");
		}
		System.out.println(Number);
		
		
	}
	public static boolean isAvailableHumanNumber(String Number){
		int sum = 0  , tmp , result;
		int weight[] = {2,3,4,5,6,7,8,9,2,3,4,5};
	
		
		Number = Number.replace("-","");
		System.out.println(Number);
		
		for(int i = 0 ; i <= 11 ; i ++)
			sum = (int)(Number.charAt(i)) * weight[i];	
		System.out.println(sum);
		tmp = 11 - (sum%11);
		result = tmp%10;
		System.out.println(sum + tmp);
		if(result -(Number.charAt(12)-48) == 0)
			return true;
		else 
			return false;
		/*
		 * �ƽ�Ű �ڵ尪�� �������� �ذ� �Ͽ���.
		 * ���� ���� ���� �ǵ� ���� ����.
		 */
		
	}
}
