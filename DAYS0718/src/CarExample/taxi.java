package CarExample;
/*
 * 부모클래스의 생성자를 잘 맞춰줘야 한다.
 * 
 */
public class taxi extends Car {

	public taxi(String name){
		super(name);
	}
	public taxi(int velocity){
		super(velocity);
	}
	public taxi(String name , int velocity){
		super(name, velocity);
	}
}
