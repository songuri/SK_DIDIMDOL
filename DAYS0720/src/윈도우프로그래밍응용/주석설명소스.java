/*package 윈도우프로그래밍응용;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FileDialog;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import org.w3c.dom.events.MouseEvent;


 * 내부 클래스는 외부에서 단독으로 사용 할 수 없다
 * 내부 클래스는 외부클래스의 메소드를 사용 할 수 있다.
 * 사용하기 좋다 ㅇ.ㅇ 읽기도 좋아 지고 
 * 실제 안드로이드 어플리케이션에서 많이 사용 된다.
 

//메인 클래스
public class Application extends JFrame{
	//inner Constructor 전역변수처럼 사용할려고 맨 위에 선언을 한 것이다.
	MenuMain menuMain = new MenuMain();
	West west = new West();
	Buttons buttons = new Buttons();
	ShowTable showtable = new ShowTable();
	
	//Constructor
	public Application(){
		setTitle("고객 관리 시스템");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		
		 * 이런식으로 객체를 넣어 주면 더 뭔가 좋긴 좋음 ㅇ.ㅇ 가독성도 증가하고 
		 
		add(menuMain.bar, BorderLayout.NORTH);  //menuMain에서 bar가 가장 최상위 객체 이다.
		add(west,BorderLayout.WEST);
		add(buttons , BorderLayout.SOUTH);
		add(showtable.scrollPane , BorderLayout.CENTER);
		setSize(600, 500);
		setLocation(400,150);
		setVisible(true);
	}
	
	class MenuMain extends JPanel implements ActionListener , ItemListener{
		JMenuBar bar;
		JMenu	file,sort,help;
		JMenuItem	fopen,fsave,fexit,proinfo;
		JCheckBoxMenuItem	sname ,sjob;  //이름 정렬.
		ButtonGroup gr = new ButtonGroup();
		
		
		FileDialog	readOpen , saveOpen;
		String fileDir, filename, savefileName ,readfileName;
		
		public MenuMain(){
			bar = new JMenuBar();
			
			file = new JMenu("파일");
			sort = new JMenu("정렬");
			help = new JMenu("도움말");
			
			fopen = new JMenuItem("열기");
			fsave = new JMenuItem("저장");
			fexit = new JMenuItem("닫기");
			
			sname = new JCheckBoxMenuItem("이름");
			sjob  = new JCheckBoxMenuItem("직업");
			
			proinfo = new JMenuItem("프로그램 정보");
			
			//초기화 ㅇ.ㅇ 
			
			//파일 메뉴의 아이템들을 추가 해주는것
			file.add(fopen); file.add(fsave);
			file.addSeparator(); //구분선을 넣어 주는 것이다.
			file.add(fexit);
			
			//정렬 메뉴의 아이템들을 그룹화 시켜서 추가 시켜주는것 
			gr.add(sname); gr.add(sjob); // 그룹화 => 이름 정렬.
			sort.add(sname);sort.add(sjob);
			
			//도움말 메뉴에 아이템들을 추가 시켜 주는것.
			help.add(proinfo);
			
			bar.add(file);	bar.add(sort); bar.add(help);
			
			//이벤트 연결
			
			//[파일 저장,열기,종료]
			fopen.addActionListener(this);
			fsave.addActionListener(this);
			fexit.addActionListener(this);
			
			//[정렬]
			sname.addItemListener(this);
			
			
		}
		@Override
		public void itemStateChanged(ItemEvent e) {
			// TODO Auto-generated method stub
		
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(e.getActionCommand().equals("열기"))	open();
			else if(e.getActionCommand().equals("저장"))	save();
			else if(e.getActionCommand().equals("닫기"))	exit();
			
		}
		public void open(){
			
		}
		public void save(){
			
		}
		public void exit(){
			
		}
		
	}
	
	class West extends JPanel{
		JLabel la;
		JTextField tf[];
		
		//Constructor
		public West(){
			//보더 만들기(west 판넬을 감싸버리는 거 ㅇ.ㅇ)
			setBorder(new TitledBorder(new LineBorder(Color.blue, 2),"입력")); //라인보더!!! 그래서 색하고 두깨를 지정해주는 것이다.
			String text[] = {"이름" , "핸드폰 번호" , "주민번호"};
			tf = new JTextField[3];
			setLayout(new GridLayout(3,2,5,10));
			
			for(int i = 0 ; i < text.length ; i ++){
				la = new JLabel(text[i]);
				tf[i] = new JTextField(20); // 영문자 20개만큼 받을 수 있도록 변경 된다.
				la.setHorizontalAlignment(JLabel.CENTER);
				add(la); add(tf[i]);		
			}
			setPreferredSize(new Dimension(230, 300));
		}
	}
	
	class Buttons extends JPanel implements ActionListener{

		Vector<String> vector;
		JButton		addBtn, updateBtn , delBtn;
		String JuminNo;
		
		
		public Buttons(){
			setLayout(new GridLayout(1, 3));
			addBtn = new JButton("추가");
			updateBtn = new JButton("수정");
			delBtn = new JButton("삭제");
			
			addBtn.setBackground(Color.GREEN);
			updateBtn.setBackground(Color.YELLOW);
			delBtn.setBackground(Color.CYAN);
			
			add(addBtn); add(updateBtn); add(delBtn);
			
			addBtn.addActionListener(this);
			updateBtn.addActionListener(this);
			delBtn.addActionListener(this);
			
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			
		}
		
		
	}
	
	//JPanel은 상속을 받을 필요가 없다. MouseListener할때의 예제.
	class ShowTable  implements MouseListener{
		
		 * (non-Javadoc)
		 * @see java.awt.event.MouseListener#mouseClicked(java.awt.event.MouseEvent)
		 * 좋든 싫든 간에  MouseListener를 Implements했으면 아래 5가지의 모든 함수 들을 구현 해줘야 한다.
		 * 하지만 내가 필요로 하지 않는 모든 기능들 까지 구현해야한다.
		@Override
		public void mouseClicked(java.awt.event.MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(java.awt.event.MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(java.awt.event.MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseEntered(java.awt.event.MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(java.awt.event.MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	
	class ShowTable extends MouseAdapter{
		 이것은 아까처럼 interface를 상속 받는 것이 아니라 나는 마우스 클릭 하나의 이벤트만 처리할것이기 
		 * 떄문에 위처럼 MouseLisener interface를 상속받아서 불필요한 모든 이벤트를 구현할 필요 없이
		 * MouseAdapter 클래스를 직접 상속 받아서 내가 필요한 기능한 오버라이드 해서 사용 하도록 한다.
		 
		
		DefaultTableModel	datamodel;
		JTable				table;
		JScrollPane			scrollPane;
		
		String colName[] = {"이름" , "핸드폰 번호" , "주민 번호"};
		//[중요]
		Vector<Vector<String>>	data; 
		Vector<String>			column_name;
		
		public ShowTable(){
			data = new Vector<Vector<String>>(); // 기본 10개  부족하면 10개씩 생성.
			column_name = new Vector<String>();
			
			for(int i = 0 ; i < colName.length ; i ++){
				column_name.add(colName[i]);
			}
			//1단계
			datamodel = new DefaultTableModel(data, column_name);		
			//2단계
			table = new JTable(datamodel);
			//3단계
			scrollPane = new JScrollPane(table);
			
			table.addMouseListener(this);
		}
		
		@Override
		public void mouseClicked(java.awt.event.MouseEvent e) {
		
			
		}
	}
	
	
	public static void main(String[] args){
		new Application();
	}
}
*/