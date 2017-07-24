package 윈도우프로그래밍;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
/*
 * 내가 사용하는 클래스에서 오버로딩 되어있는 많은 메소드 중에서 
 * 내가 어떠한 메소드를 사용할지를 결정할 줄 알아야한다.
 * 이것은 많이 경험을 해보아야 한다.
 */
public class JTableCreate extends JFrame{
	
	String colName[] = {"이름" , "나이" , "성별"};
	
	DefaultTableModel	 model;
	JTable 				table;
	JScrollPane			scroll;
	
	public JTableCreate(){
		setTitle("JTable");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//1단계 -> 모델을 설정을 해줘야 추가적인 기능이 가능하다.
		model = new DefaultTableModel(colName, 0);
		
		//2단계
		table = new JTable(model);
		//3단계
		scroll = new JScrollPane(table);
		
		
		/*
		 * LayOut 정리
		 * 1. Boarder Layout
		 * 2. GridLayout : 행과 열로 구분 한다.
		 * 3. FlowLayout : 왼쪽에서 오른쪽으로 .
		 * 4. CardLayout : 여러장의 카드를 만들어 놓고 카드가 서로 스왑하면서 되는거 ㅇ.ㅇ 
		 * 5. UserLayout : 사용자가 자유롭게 위치를 배치하는것 ㅇ.ㅇ
		 * 
		 */
		add(scroll,BorderLayout.CENTER);
		
		//새로운 판넬을 생성해서 추가할 것이다.
		JPanel panel = new JPanel();
		JTextField text1 = new JTextField(6);  // 6자리의 입력을 받을 것
		JTextField text2 = new JTextField(3);  // 나이를 입력받을 것
		JTextField text3 = new JTextField(2);  // 성별을 입력받을 것
		panel.add(new JLabel("이름"));
		panel.add(text1);
		panel.add(new JLabel("나이"));
		panel.add(text2);
		panel.add(new JLabel("성별"));
		panel.add(text3);
		
		JButton addbtn = new JButton("추가");
		JButton delbtn = new JButton("삭제");
		addbtn.setBackground(Color.BLACK);
		delbtn.setBackground(Color.RED);
		
		panel.add(addbtn);
		panel.add(delbtn);
		
		add(panel, BorderLayout.SOUTH);
		
		//이벤트 연결
		// 각이벤트별로 클래스를 만들어서 이벤트 처리를 모듈화 시켰다.
		addbtn.addActionListener(new AddAction(table , text1 , text2, text3));
		delbtn.addActionListener(new RemoveAction(table));
		
		setSize(500,300);
		setLocation(300,150);
		setVisible(true);
	}
	
	public static void main(String[] args){
		new JTableCreate(); //객체 생성과 동시에 생성자 호출
	}
}
