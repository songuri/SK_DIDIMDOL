package 실습문제_3회;


/*
 * 객체지향을 위해서 GET , SET을 구현하여 사용하면 좋다 ㅇ.ㅇ
 */
public class Person {
	private String name;
	private int age;
	
	
	public Person(String name , int age){
		this.name = name;
		this.age = age;
		
	}
	public Person(){
		
	}
	public String getName(){
		return name;
	}
	public int getAge(){
		return age;
	}
	public void setName(String name){
		this.name = name;
	}
	public void setAge(int age){
		this.age = age;
	}

}
