package days0717;

import java.util.Calendar;

public class AgeAndSex {
	public static void main(String[] args){
		String number = "920426-1031123";
	
	}
	public void getAgeAndSexAndBirth(String number){
		int Age;
		char gender;
		
		
		
		number.replace("-" , "");
		Age = Integer.parseInt(number.substring(0, 2));
		Calendar cal = Calendar.getInstance();
		
		if((number.charAt(7)-48) <3) Age = Age+1900;
		else Age = Age+2000;
		
		Age = cal.get(Calendar.YEAR) - Age;
		System.out.println(Age);
		
		if((number.charAt(7)-48)%2 ==1)
			gender = 'M';
		else
			gender = 'W';9ff
			
			
		
		
		
	}
}
