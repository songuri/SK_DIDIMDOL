package 실습문제_6회;

import java.util.ArrayList;

public class Seat {
	ArrayList<SeatType> seat;
	int numOfSeat;
	int reservedSeat;
	
	public Seat(){
		
		
		for(int i = 1 ; i <= 5 ; i ++){
			SeatType tmp = new SeatType('R' , true , i);
			seat.add(tmp);
		}
		for(int i = 6 ;i <= 12 ; i ++){
			SeatType tmp = new SeatType('S' , true , i);
			seat.add(tmp);
		}
		for(int i = 13 ; i <=22 ; i++){
			SeatType tmp = new SeatType('A' , true , i);
			seat.add(tmp);
		}
		for(int i = 23 ; i <=47 ; i++){
			SeatType tmp = new SeatType('A' , true , i);
			seat.add(tmp);
		}
	}
	public void getSeatInfo(){
		System.out.print("R석 예약 ");
		for(int i = 1 ; i <= 5 ; i ++){
			if(seat.get(i).getisReserve())
				System.out.print("--");
			else
				System.out.print(seat.get(i).getName());
		}
		for(int i = 6 ;i <= 12 ; i ++){
			if(seat.get(i).getisReserve())
				System.out.println("--");
			else
				System.out.print(seat.get(i).getName());
		}
		for(int i = 13 ; i <=22 ; i++){
			if(seat.get(i).getisReserve())
				System.out.print("--");
			else
				System.out.print(seat.get(i).getName());
		}
		for(int i = 23 ; i <=47 ; i++){
			if(seat.get(i).getisReserve())
				System.out.print("--");
			else
				System.out.print(seat.get(i).getName());
		}
	
	}
	public void reserve(char type , String name){
		for(SeatType tmp : seat){
			if(tmp.getType() == type && tmp.getisReserve()){
				tmp.setisReserve(false);tmp.setName(name);
				return;
			}
		}
	}
	public void cancel(String name){
		for(SeatType tmp : seat){
			if(tmp.getName().equals(name)){
				tmp.setName(null); tmp.setisReserve(true);
				return;
			}
		}
	}
	
}
