package days0717;

import java.util.Scanner;

public class BinarySearch_Number {
	public static void main(String[] args){
		
		 int data[] = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15};
		 int low = 0;
		 int high = data.length;
		 int find;
		 int mid = (low + high)/2;
		 
		 
		 System.out.println("찾고자 하는 데이터의 값을 입력하세요.");
		 find = new Scanner(System.in).nextInt();
		 if(binarySearch(data , 0 , data.length , data.length/2 , find)){
			 System.out.println("찾았다");
		 }
		 else{
			 System.out.println("못찾았다.");
		 }
		 /* 재귀적 구현 */
		 
		 while(low <= high){
			 mid = (low + high) /2;
			 if(find == data[mid]){
				 System.out.println("찾았다.");
				 return;
			 }
			 if(find > data[mid])
				 low = mid +1;
			 else
				 high = mid -1;
		 }
		 /* 반복문 구현 */
		 
		 
		 
		 
	}
	public static boolean binarySearch( int dataset[],int low , int high , int mid ,int find ){
	
		if(low >= high)
			return false;
		if(find < dataset[mid]){
			return binarySearch(dataset,low , mid -1 , (low + mid) /2 , find);
		}
		else if ( find >dataset[mid]){
			return binarySearch(dataset,mid+1 , high , (mid + high)/2 , find);
		}
		if(find == dataset[mid] )
			return true;
		
		return false;
		
	}
	
}
