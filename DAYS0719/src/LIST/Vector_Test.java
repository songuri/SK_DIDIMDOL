package LIST;

import java.util.Vector;

public class Vector_Test {
	public static void main(String[] args){
		
		String arr[] = {"김영호" , "오세명", "김은상", "김민제", "최유리", "김근표"};
		
		Vector<String> vec = new Vector<String>(4,3);
		//초기 사이즈는 4개이고 4개 이상이 들어오면 공간을 3개씩 늘리라는 것임 ㅇ.ㅇ
		//즉 Vector는 가변 배열이라는 것을 확인할려고 이런걸 한거임
		
		
		System.out.println("vector의 크기 : " + vec.size());
		System.out.println("vector의 용량 : " + vec.capacity());
		

		for(String tmp : arr)
			vec.add(tmp);
		

		for(String item : vec) System.out.print(item + " ");
		
		System.out.println(arr.toString());
		System.out.println("vector의 크기 : " + vec.size());
		System.out.println("vector의 용량 : " + vec.capacity());
		
		
	}
}
