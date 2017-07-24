package days0717;

public class humanNumberChecker {
	public static void main(String[] args){
		String Number = "920426-1031123";
		
		if(isAvailableHumanNumber(Number))
			System.out.println("정답");
		else{
			System.out.println("틀렸어");
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
		 * 아스키 코드값을 뺀것으로 해결 하였다.
		 * 오랜 만에 쓰는 건데 잊지 말자.
		 */
		
	}
}
