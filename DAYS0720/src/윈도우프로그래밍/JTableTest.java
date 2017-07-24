package ���������α׷���;

import javax.swing.*;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class JTableTest extends JFrame {
	JTable table;
	JScrollPane scroll;
	
	String title[] = {"��ȣ" , "�̸�" , "�ڵ��� ��ȣ" , "�̸���"};
	String data[][] = {{"1","����ȣ" , "010-2342-4567"  , "tngkr8121@naver.com"} , 
						{"2" , "�迬��" , "010-6545-1234" , "tsdfasdf@daum.net"},
						{"3" , "����ö" , "010-6784-5874" , "tnakw@hamail.com"}};
	
	//Constructor
	public JTableTest(){
		setTitle("Make JTable");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		table = new JTable(data, title);
		scroll = new JScrollPane(table);
		add(scroll);
		
		table.getColumnModel().getColumn(0).setPreferredWidth(50);
		table.getColumnModel().getColumn(1).setPreferredWidth(70);
		table.getColumnModel().getColumn(2).setPreferredWidth(120);
		table.getColumnModel().getColumn(3).setPreferredWidth(170);
		
		setSize(450,200);
		setLocation(500,300);
		setVisible(true);
	}
	
	public static void main(String[] args){
		new JTableTest();
		
	}
}
