package com.sk.jsp;

import java.util.ArrayList;

public class BoardManager {
	
	
	public static ArrayList<Board> al = new ArrayList<Board>();
	
	static {
		al.add(new Board("111111", "내용1", "이순신", "sk", ""));
		al.add(new Board("222222", "내용2", "임꺽정", "sk", ""));		
	}
	
	public BoardManager() {
		
	}
	
	public void insert(Board b) {
		al.add(b);
	}
	
	
	public ArrayList<Board> select() {		
		return al;
	}
	
	public ArrayList<Board> selectPage(int start, int end) {	
		/*
		SELECT mem_id FROM  ( SELECT ROWNUM AS RNUM, Z.* FROM
                ( SELECT * FROM member ORDER BY mem_id asc ) Z WHERE ROWNUM <= 30 
         ) WHERE RNUM >=  19;
         */
		
		ArrayList<Board> temp = new ArrayList<Board>();		
		for(int i = start ; i <= end; i++) {
			if ( i > al.size() ) break;
			temp.add( al.get(i-1)   );			
		}
		return temp;
	}
	
	
	public Board select(int idx) {
		
		for(int i = 0; i < al.size(); i++)
			if ( al.get(i).idx == idx ) return  al.get(i);
		return null;
	}
	
	public int updateHit(int idx) {
		for(int i = 0; i < al.size(); i++)
			if ( al.get(i).idx == idx ) 
				return ++al.get(i).hit;
		return 0;
	}
	
	public boolean checkPassword(int idx, String password) {
		for(int i = 0; i < al.size(); i++)
			if ( al.get(i).idx == idx &&  password.equals(al.get(i).password ) )
				return true;
		return false;
	}
	
	public int delete(int idx) {
		for(int i = 0; i < al.size(); i++)
			if ( al.get(i).idx == idx ) {
				al.remove(i);
				return 1;
			}
		return 0;
	}
	
	public int update(int idx, String title, String memo) {
		for(int i = 0; i < al.size(); i++)
			if ( al.get(i).idx == idx ) {
				al.get(i).title = title;
				al.get(i).memo =  memo;
			}
		return 1;
	}
	
}
