package ���������α׷���;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
/*
 * ���� ����ϴ� Ŭ�������� �����ε� �Ǿ��ִ� ���� �޼ҵ� �߿��� 
 * ���� ��� �޼ҵ带 ��������� ������ �� �˾ƾ��Ѵ�.
 * �̰��� ���� ������ �غ��ƾ� �Ѵ�.
 */
public class JTableCreate extends JFrame{
	
	String colName[] = {"�̸�" , "����" , "����"};
	
	DefaultTableModel	 model;
	JTable 				table;
	JScrollPane			scroll;
	
	public JTableCreate(){
		setTitle("JTable");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//1�ܰ� -> ���� ������ ����� �߰����� ����� �����ϴ�.
		model = new DefaultTableModel(colName, 0);
		
		//2�ܰ�
		table = new JTable(model);
		//3�ܰ�
		scroll = new JScrollPane(table);
		
		
		/*
		 * LayOut ����
		 * 1. Boarder Layout
		 * 2. GridLayout : ��� ���� ���� �Ѵ�.
		 * 3. FlowLayout : ���ʿ��� ���������� .
		 * 4. CardLayout : �������� ī�带 ����� ���� ī�尡 ���� �����ϸ鼭 �Ǵ°� ��.�� 
		 * 5. UserLayout : ����ڰ� �����Ӱ� ��ġ�� ��ġ�ϴ°� ��.��
		 * 
		 */
		add(scroll,BorderLayout.CENTER);
		
		//���ο� �ǳ��� �����ؼ� �߰��� ���̴�.
		JPanel panel = new JPanel();
		JTextField text1 = new JTextField(6);  // 6�ڸ��� �Է��� ���� ��
		JTextField text2 = new JTextField(3);  // ���̸� �Է¹��� ��
		JTextField text3 = new JTextField(2);  // ������ �Է¹��� ��
		panel.add(new JLabel("�̸�"));
		panel.add(text1);
		panel.add(new JLabel("����"));
		panel.add(text2);
		panel.add(new JLabel("����"));
		panel.add(text3);
		
		JButton addbtn = new JButton("�߰�");
		JButton delbtn = new JButton("����");
		addbtn.setBackground(Color.BLACK);
		delbtn.setBackground(Color.RED);
		
		panel.add(addbtn);
		panel.add(delbtn);
		
		add(panel, BorderLayout.SOUTH);
		
		//�̺�Ʈ ����
		// ���̺�Ʈ���� Ŭ������ ���� �̺�Ʈ ó���� ���ȭ ���״�.
		addbtn.addActionListener(new AddAction(table , text1 , text2, text3));
		delbtn.addActionListener(new RemoveAction(table));
		
		setSize(500,300);
		setLocation(300,150);
		setVisible(true);
	}
	
	public static void main(String[] args){
		new JTableCreate(); //��ü ������ ���ÿ� ������ ȣ��
	}
}
