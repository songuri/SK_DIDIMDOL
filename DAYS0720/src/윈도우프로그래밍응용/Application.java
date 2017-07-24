package 윈도우프로그래밍응용;

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
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.StringTokenizer;
import java.util.Vector;
import java.util.regex.Pattern;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
//메인 클래스 (외부 클래스)
public class Application extends JFrame{
	//내부 클래스 객체 생성
	MenuMain menuMain = new MenuMain();
	West west = new West();
	Buttons buttons = new Buttons();
	ShowTable showtable = new ShowTable();
	//전역 변수 선언
	int updateRow;
	
	//생성자 호출
	public Application(){
		setTitle("고객관리 시스템");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//각 항목을 원하는 위치에 배치
		add(menuMain.bar,BorderLayout.NORTH);
		add(west,BorderLayout.WEST);
		add(buttons,BorderLayout.SOUTH);
		add(showtable.scrollPane,BorderLayout.CENTER);
		
		setSize(600,300);
		setLocation(400,150);
		setVisible(true);
	}
	//내부 클래스 구현
	class MenuMain extends JPanel implements ActionListener, ItemListener{
		JMenuBar bar = new JMenuBar();
		JMenu file,sort,help;
		JMenuItem fopen,fsave,fexit,proinfo;
		JCheckBoxMenuItem	sname,sjob;				//이름정렬
		ButtonGroup gr = new ButtonGroup();
		
		FileDialog readOpen,saveOpen;
		String fileDir,fileName,saveFileName,readFileName;
		
		//내부 클래스 안에 생성자 구현
		public MenuMain(){
			bar = new JMenuBar();
			
			file = new JMenu("파일");
			sort = new JMenu("정렬");
			help = new JMenu("도움말");
			
			fopen = new JMenuItem("열기");
			fsave = new JMenuItem("저장");
			fexit = new JMenuItem("닫기");
			
			sname = new JCheckBoxMenuItem("이름");
			sjob = new JCheckBoxMenuItem("직업");
			proinfo = new JMenuItem("프로그램정보");
			
			//객체 붙이기
			file.add(fopen); file.add(fsave); file.addSeparator();//구분선넣기
			file.add(fexit); 
			
			gr.add(sname);	gr.add(sjob);//그룹화
			sort.add(sname); sort.add(sjob);
			help.add(proinfo);
			
			bar.add(file);
			bar.add(sort);
			bar.add(help);
			
			//이벤트 연결
			//[파일] 메뉴 이벤트 연결
			fopen.addActionListener(this);
			fsave.addActionListener(this);
			fexit.addActionListener(this);
			
			//[정렬] 메뉴 이벤트 연결
			sname.addItemListener(this);
			
		}

		@Override
		public void itemStateChanged(ItemEvent e) {
			//정렬 이벤트 처리
			if(e.getSource().equals(sname)){
				nameSort();
			/*else if(e.getSource().equals(sjob)){
				jobsort();
			}*/
			}
		}
		public void nameSort(){
			int row = showtable.table.getRowCount();
			int col = showtable.table.getColumnCount();
			
			String temp;
			String arr[][] = new String[row][col];
			//데이터 가져오기
			for(int i=0; i<row; i++){
				for(int j=0; j<col; j++){
						arr[i][j] = (String) showtable.table.getValueAt(i, j);
				}
			}
			//2차원 배열 선택정렬 알고리즘
			for(int i=0; i<row-1; i++){
				for(int j=i+1; j<row; j++){
					if(arr[i][0].compareTo(arr[j][0])>0){
						for(int k=0; k<col; k++){
							temp = arr[i][k];
							arr[i][k]=arr[j][k];
							arr[j][k]=temp;
						}
					}
				}
			}
			//데이터 넣기
			for(int i=0; i<row; i++){
				for(int j=0; j<col; j++){
					showtable.table.setValueAt(arr[i][j], i, j);
				}
			}
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getActionCommand().equals("열기")) open();
			if(e.getActionCommand().equals("저장")) save();
			if(e.getActionCommand().equals("닫기")) exit();
		}
		public void open(){
			StringTokenizer st;
			Vector vec;
			
			readOpen = new FileDialog(Application.this, "문서열기", FileDialog.LOAD);
			readOpen.setVisible(true);
			
			fileDir = readOpen.getDirectory();
			fileName = readOpen.getFile();
			readFileName = fileDir + "\\" + fileName;
			
			try{
				BufferedReader read = new BufferedReader(new FileReader(readFileName));
				String line = null;
				while((line = read.readLine()) != null);{
					st = new StringTokenizer(line, ", ");
					vec = new Vector();
					//1사람의 정보를 넣기
					while(st.hasMoreTokens()){
						vec.add(st.nextToken());
					}
					//벡터안에 벡터를 집어넣는 것은 add가 아니라 addElement
					showtable.data.addElement(vec);
				}
				//데이터 뿌려주기
				showtable.datamodel.fireTableDataChanged();
				read.close();
				
			}catch(Exception ex){
				System.out.println(ex);
				}
		}
		public void save(){
			//저장창을 띄우는 객체 생성
			saveOpen = new FileDialog(Application.this, "문서저장", FileDialog.SAVE);
			saveOpen.setVisible(true);
			
			fileDir = saveOpen.getDirectory();
			fileName = saveOpen.getFile();
			saveFileName = fileDir + "\\" + fileName;
			
			String str = "";
			String temp = "";
			
			try{//보조 스트림의 역할 : 입출력 성능 향상
				BufferedWriter save = new BufferedWriter(new FileWriter(saveFileName));
				for(int i=0; i<showtable.table.getRowCount(); i++){
					temp = showtable.data.elementAt(i).toString();
					str += temp.substring(1, temp.length() - 1) + "\n";	
				}
				save.write(str);
				save.close();
			//예외처리	
			}catch(Exception ex){
				System.out.println(ex);
			}
		}
		public void exit(){
			System.exit(0);
		}
	}
	class West extends JPanel{
		JLabel la;
		JTextField tf[];
		
		public West(){
			//라인보더 만들기
			setBorder(new TitledBorder(new LineBorder(Color.BLUE, 2),"입력"));
			
			String text[]={"이  름","핸드폰번호","주민번호"};
			tf = new JTextField[3];
			setLayout(new GridLayout(3, 2, 5, 10));			//3행 2열 행의간격5 열의간격10
			
			for(int i=0; i<text.length; i++){
				la = new JLabel(text[i]);
				la.setHorizontalAlignment(JLabel.CENTER);
				tf[i] = new JTextField(20);
				add(la); add(tf[i]);
			}
			setPreferredSize(new Dimension(200,300));
		}
	}
	class Buttons extends JPanel implements ActionListener{
		Vector<String> vector;
		JButton addBtn,updateBtn,delBtn;
		String juminNo;
		
		public Buttons(){
			setLayout(new GridLayout(1,3));
			addBtn = new JButton("추가");
			updateBtn = new JButton("수정");
			delBtn = new JButton("삭제");
			
			addBtn.setBackground(Color.LIGHT_GRAY);
			updateBtn.setBackground(Color.YELLOW);
			delBtn.setBackground(Color.CYAN);
			
			add(addBtn); add(updateBtn); add(delBtn);
			//이벤트연결
			addBtn.addActionListener(this);
			updateBtn.addActionListener(this);
			delBtn.addActionListener(this);
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getActionCommand().equals("추가")) addData();
			else if(e.getActionCommand().equals("수정")) updateData();
			else if(e.getActionCommand().equals("삭제")) delData();
		}
		//사용자 정의 메소드 addData 구현
		public void addData(){
			Vector<String> vector = new Vector<String>();
			
			vector.add(west.tf[0].getText());
			vector.add(west.tf[1].getText());
			//주민번호를 Vector객체에 저장하기 전에 먼저 주민번호 정상유무 체크
			juminNo = west.tf[2].getText();
			//[중요] 정규표현식 적용, 주민번호 자리수 확인
			String regex = "[0-9]{6}-[0-9]{7}";
			boolean check = Pattern.matches(regex, juminNo);
			
			if(check == false){
				JOptionPane.showMessageDialog(null, "주민번호가 정규표현식에 맞지 않는다", "경고", JOptionPane.ERROR_MESSAGE);
				west.tf[2].setText(null);	//주민번호 초기화
				west.tf[2].requestFocus();	//키보드 커서를 그 자리에 둬라
				return;						//그상태 그래도 둬라
			}
			//주민번호 유효성 확인
			int sum = 0;
			int weigth[] = {2,3,4,5,6,7,0,8,9,2,3,4,5};
			for(int i=0; i<13; i++){
				if(juminNo.charAt(i)=='-') continue;
					sum = sum + (juminNo.charAt(i)-48) * weigth[i];
			}
			int temp = 11 - (sum % 11);
			int result = temp % 10;
			if(result ==juminNo.charAt(13)-48){
				//shoeMessageDiaLog 인자값이 2개면 긍정적 메세지창, 4개면 부정적 메세지창
				JOptionPane.showMessageDialog(null, "주민번호 정상!");
			}
			else{
				JOptionPane.showMessageDialog(null, "주민번호 비정상!", "경고", JOptionPane.ERROR_MESSAGE);
				return;
			}
			vector.add(west.tf[2].getText());
			
			west.tf[0].setText(null);
			west.tf[1].setText(null);
			west.tf[2].setText(null);
			west.tf[0].requestFocus();
			
			showtable.data.addElement(vector);
			//data 벡터에 저장된 데이터들을 datamodel의 Jtable에 보여주기
			showtable.datamodel.fireTableDataChanged();
		}
		public void updateData(){
			for(int i=0; i<west.tf.length; i++){
				showtable.table.setValueAt(west.tf[i].getText(), updateRow, i);
				west.tf[i].setText(null);
			}
			west.tf[0].requestFocus();
		}
		public void delData(){
			//삭제확인창
			int yes_no_select = JOptionPane.showConfirmDialog(null, "정말 삭제 하시겠습니까?", "고객정보삭제", JOptionPane.YES_NO_OPTION);
			
			if(yes_no_select == JOptionPane.YES_OPTION){
				JTable tb = showtable.table;
				int deleteRow = tb.getSelectedRow();
				
				//행을 선택하지 않았을때
				if(deleteRow == -1){
					return;
				}
				else{
					DefaultTableModel model = (DefaultTableModel) tb.getModel();
					model.removeRow(deleteRow);
					
					west.tf[0].setText(null);
					west.tf[1].setText(null);
					west.tf[2].setText(null);
					west.tf[0].requestFocus();
				}
			}
			else{
				return;
			}
		}
	}
	/*class Showtable implements MouseListener{
		메소드가 5개이기 때문에 오버라이딩도 5개를 다 불러온다
		@Override
		public void mouseClicked(MouseEvent e) {}
		@Override
		public void mouseEntered(MouseEvent e) {}
		@Override
		public void mouseExited(MouseEvent e) {}
		@Override
		public void mousePressed(MouseEvent e) {}
		@Override
		public void mouseReleased(MouseEvent e) {}*/
		//불필요한것을 불러오지 않기위해서는 다음과 같이 입력하면된다
	class ShowTable extends MouseAdapter{
		
		DefaultTableModel	datamodel;
		JTable				table;
		JScrollPane			scrollPane;
		
		String colName[] = {"이름","핸드폰번호","주민번호"};
		//중요
		Vector<Vector<String>>	data;
		Vector<String> column_name;
		
		public ShowTable(){
			//Arrlist를 쓰지않고 Vector 2개를 쓰는 이유는 안정성,동기화 때문이다.
			data = new Vector<Vector<String>>();	//기본[10,10]사이즈이고 추가 될 시 10개씩
			column_name = new Vector<String>();
			
			for(int i=0; i<colName.length; i++){
				column_name.add(colName[i]);
			}
			datamodel = new DefaultTableModel(data, column_name);
			table = new JTable(datamodel);
			scrollPane = new JScrollPane(table);
			
			//이벤트연결
			table.addMouseListener(this);
		}
		
		//수동으로 오버라이드
		@Override
		public void mouseClicked(MouseEvent e) {
			//마우스 클릭시 행의 번호를 가져온다
			updateRow = table.getSelectedRow();
			//테이블에서 데이터 클릭시 west로 데이터를 가져온다
			for(int i=0; i<3; i++){
			west.tf[i].setText((String) showtable.table.getValueAt(updateRow, i));
			}
		}
	}
	public static void main(String[] args) {
		new Application();
		
	}

}


//함수적 프로그래밍:	y = f(x) 형태의 함수로 구성된 프로그래밍 기법