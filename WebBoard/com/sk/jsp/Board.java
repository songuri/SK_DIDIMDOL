package com.sk.jsp;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Board {
	public int  idx;
	public String title;
	public String memo;
	public String uname;
	public String password;
	public String file;
	public String time;
	public int hit;
	
	public Board(String title, String memo, String uname, String password, String file) {
		
		idx = (int)Math.floor(Math.random() * 1000000000);
		this.title = title;
		this.memo = memo;
		this.uname = uname;
		this.password = password;
		this.file = file;
		this.time = (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(new Date());
		hit = 0;
		
	}
	
	

}
