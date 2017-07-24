package 실습문제_6회;

public class SeatType {
	private char type;
	private boolean isReserve;
	private int seatNumber;
	private String userName;
	
	
	public SeatType(char type , boolean isReserve , int seatNumber){
		this.type = type ; this.isReserve = isReserve; this.seatNumber = seatNumber;
		userName = null;
	}
	
	public void setType(char type){
		this.type =type;
	}
	public void setisReserve(boolean isReserve){
		this.isReserve = isReserve;
	}
	public void setseatNumber(int seatNumber){
		this.seatNumber = seatNumber;
	}
	
	public char getType(){
		return type;
	}
	public boolean getisReserve(){
		return isReserve;
	}
	public int getSeatNumber(){
		return seatNumber;
	}
	public void setName(String name){
		userName = name;
	}
	public String getName(){
		return userName;
	}
	
	
	

}
