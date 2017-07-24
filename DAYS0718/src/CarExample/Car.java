package CarExample;

public class Car {
	private int speed;
	private int wheelnum;
	private String carName;

	public Car(){
		
	}
	
	public Car(String name){
		carName = name;
	}
	public Car(int velocity){
		speed = velocity;
	}
	public Car(String name , int velocity){
		carName = name;
		speed = velocity;
	}

	public void speedUp(){
		speed += 10;
	}
	public void speedDown(){
		speed-= 10;
	}
	public void stop(){
		
	}
	
	/*public static void main(String[] args){
		
		Car myCar , yourCar , hisCar;
		myCar = new Car("제네시스");
		System.out.println(myCar.carName);
		
	}*/
}
