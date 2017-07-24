package days0713;

public class test2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String str[][]={
				{"10" , "김수철" , "부산" , "연예인"},
				{"1" , "박수인" , "서울" , "학생"},
				{"2" , "최인혜" , "대구" , "의사"}
				
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
		 * 순차검색과 이분 검색
		 * 순차 검색
		 * 장점 : 
		 * 		1 > 알고리즘 구현이 용의하다.
		 * 		2 > DATA를 정렬시키지 않아도 검색이 가능하다.
		 * 단점 :
		 * 		1 > 평균 검색 횟수 : ( N+1) /2
		 * 
		 *  이분 검색
		 *  	1 > 평균검색횟수 LOG(N)
		 *  	2 > DATA가 정렬되어 있어야 한다.
		 * 
		 */
		
	}

}

