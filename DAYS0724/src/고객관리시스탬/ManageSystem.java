package 고객관리시스탬;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.*;
import java.util.*;
import java.util.List;

public class ManageSystem extends JFrame {

	public static final String String = null;	
	
	//내부 클래스들 객체 생성
	MenuMain menuMain = new MenuMain();    //메뉴 만들기 내부클래스
	West west = new West(); 		       //입력,신상정보,정보검색 보더 만들기 내부클래스
	Buttons buttons = new Buttons();       //버튼들 만들기 내부클래스
	ShowTable showTable = new ShowTable();  //JTable 만들기 내부클래스
	
	public ManageSystem(){
		//레이블 선언 부분
		//레이블 선언 후 반드시 반복문이 와야 한다.
		LABEL: //레이블명
		while(true){ //레이블 영역 시작
			String password = JOptionPane.showInputDialog("고객관리 시스템"+"\n"+"패스워드 입력");
			String passwd = "king1234";		
			
			if(password.equals(passwd)){
				setTitle("고객정보 관리시스템");
				setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				
				add(menuMain.bar,BorderLayout.NORTH);
				add(west,BorderLayout.WEST);
				add(buttons,BorderLayout.SOUTH);
				add(showTable.scrollPane,BorderLayout.CENTER);		
				
				setSize(900,650);
				setLocation(80,40);
				setVisible(true);
				
				break LABEL;
			}else{
				JOptionPane.showMessageDialog(null,
	                    "패스워드가 맞지 않습니다."+"\n"+
				        "'확인' 버튼 누르세요.",
	                    "에러 메세지",
	                    JOptionPane.ERROR_MESSAGE);	
				continue LABEL;				
			}
		} //레이블 영역 끝
	}
	//메뉴 만들기 내부 클래스
	class MenuMain extends JPanel implements ActionListener, ItemListener{		
		JMenuBar bar;
		JMenu file,sort,help,cor;
		JMenuItem fnew,fopen,fsave,fexit,proinfo;
		JCheckBoxMenuItem sno,sname,schul,sjob;
		
		FileDialog readOpen,saveOpen;
		String fileDir,fileName,savefileName,readfileName;
		ButtonGroup gr = new ButtonGroup();
		
		public MenuMain(){
			bar= new JMenuBar();
			
			file = new JMenu("파일");
			sort = new JMenu("정렬");			
			help = new JMenu("도움말");
			
			fopen = new JMenuItem("열기");
			fsave = new JMenuItem("저장");
			fexit = new JMenuItem("닫기");
			
			sno = new JCheckBoxMenuItem("번호");
			sname = new JCheckBoxMenuItem("이름");
			schul = new JCheckBoxMenuItem("출신도");
			sjob = new JCheckBoxMenuItem("직업");
			
			proinfo =  new JMenuItem("프로그램 정보");
			
			//붙이기
			file.add(fopen);
			file.add(fsave);
			file.addSeparator(); //구분선 넣기
			file.add(fexit);
			
			gr.add(sno);
			gr.add(sname);
			gr.add(schul);
			gr.add(sjob);
			
			sort.add(sno);
			sort.add(sname);
			sort.add(schul);
			sort.add(sjob);			
			
			help.add(proinfo);
			
			bar.add(file);
			bar.add(sort);			
			bar.add(help);
			
			//파일 메뉴 이벤트 연결
			fopen.addActionListener(this);
			fsave.addActionListener(this);
			fexit.addActionListener(this);
			
			//정렬 메뉴 이벤트 연결
			sno.addItemListener(this);
			sname.addItemListener(this);
			schul.addItemListener(this);
			sjob.addItemListener(this);			
		}
		//이벤트처리
		@Override
		public void actionPerformed(ActionEvent e) {			
			if(e.getActionCommand().equals("열기"))      Open();
			else if(e.getActionCommand().equals("저장"))	Save();
			else if(e.getActionCommand().equals("닫기"))	Exit();			
		}
		//-------
		//파일 열기
		//-------
		public void Open(){
			StringTokenizer st;
			Vector v;
			
			readOpen = 
				new FileDialog(ManageSystem.this,"문서열기",FileDialog.LOAD);
			readOpen.setVisible(true);
			
			fileDir = readOpen.getDirectory();
			fileName = readOpen.getFile();
			             
			readfileName =fileDir + "//" + fileName;		    
		    
		    try{
			    BufferedReader read = new BufferedReader(new FileReader(readfileName));
			    String line=null;
				
				while((line=read.readLine()) != null){					
					st= new StringTokenizer(line,", ");
					v = new Vector();  //중요  
					
					while(st.hasMoreTokens()){						
						v.add(st.nextToken());						
					}
					//-----------------------------------------------------
					//추가 부분=> 주민번호 뒤 6자리를 "******" 으로 변환하기
					//-----------------------------------------------------
					String juminNo_encryption =  (String) v.get(4);
					juminNo_encryption = juminNo_encryption.substring(0, 8)+"******";					
					
					v.set(4, juminNo_encryption);					
					
					//------------
				    //Vector값 출력
				    //------------
				    //System.out.println(v);
				    //------------------------
					showTable.data.addElement(v);					
				}				
				showTable.datamodel.fireTableDataChanged();
				read.close();
			}catch(Exception e){
			    System.out.println(e);
			}
		}
		//-------
		//파일 저장
		//-------
		public void Save(){						
			saveOpen = new FileDialog(ManageSystem.this,"문서저장",FileDialog.SAVE);
			saveOpen.setVisible(true);
			
			fileDir = saveOpen.getDirectory();
			fileName = saveOpen.getFile();			
			savefileName =fileDir + "//" + fileName;
		  
			String str="";
			String temp="";		    
		    try{
			    BufferedWriter save = new BufferedWriter(new FileWriter(savefileName));
				
			    for(int i=0; i<showTable.table.getRowCount(); i++){			    	
			    	temp = showTable.data.elementAt(i).toString();			    	
			    	str += temp.substring(1,temp.length()-1)+"\n";			    	
			    }
			    save.write(str);			    
			    save.close();  //자원해제		    
		    }catch(Exception ex){
		    	System.out.println(ex);
		    }
		}
		public void Exit(){			
			System.exit(0);	
		}
		//정렬 이벤트처리
		@Override
		public void itemStateChanged(ItemEvent e) {			
			if(e.getSource().equals(sno))        noSort();      //'번호' 정렬
			else if(e.getSource().equals(sname)) stringSort(1); //'이름' 정렬
			else if(e.getSource().equals(schul)) stringSort(7); //'출신도' 정렬
			else if(e.getSource().equals(sjob))	 stringSort(9); //'직업' 정렬			
		}
		//'번호' 정렬
		public void noSort(){   		  
		    int row = showTable.table.getRowCount();
		    int col = showTable.table.getColumnCount();
		  
		    String temp;
		    String[][] arr = new String[row][col];
		  
		    for(int i=0; i<row; i++){
			    for(int j=0; j<col ;j++){
				    arr[i][j]=(String) showTable.table.getValueAt(i,j);				    
			    }
		    }		  
		    for(int i=0; i<row-1 ;i++){
			    for(int j=i+1; j<row; j++){
				   if(Integer.parseInt(arr[i][0]) > Integer.parseInt(arr[j][0])){
					   for(int k=0; k<col; k++){
						   temp=arr[i][k];
						   arr[i][k]=arr[j][k];
						   arr[j][k]=temp;
					   }
				   }
			    }
		    }			  
		    for(int i=0; i<row; i++){
			    for(int j=0; j<col;j++){
				    showTable.table.setValueAt(arr[i][j], i, j);
			    }
		    }	 
		}
		//이름,출신도,직업 정렬
		public void stringSort(int sortType){    
			  int row = showTable.table.getRowCount();
			  int col = showTable.table.getColumnCount();
			  
			  String temp;
			  String[][] arr = new String[row][col];
			  
			  for(int i=0; i<row; i++){
				  for(int j=0; j<col ;j++){
					  arr[i][j]=(String) showTable.table.getValueAt(i, j);
				  }
			  }		 
			  for(int i=0; i<row-1 ;i++){
				  for(int j=i+1; j<row; j++){
					  if(arr[i][sortType].compareTo(arr[j][sortType]) > 0){
						  for(int k=0; k<col; k++){
							  temp=arr[i][k];
							  arr[i][k]=arr[j][k];
							  arr[j][k]=temp;
						  }
					  }
				  }
			  }		
			  for(int i=0; i<row; i++){
				  for(int j=0; j<col; j++){
					  showTable.table.setValueAt(arr[i][j], i, j);
				  }
			  }
		}		
	}
	//입력,신상정보,정보검색 보더 만들기 내부 클래스
	class West extends JPanel{		
		//내부클래스 객체 생성
		Input input = new Input();
		Output output= new Output();
		
		public West(){		
		    setLayout(new BorderLayout());	
		    add(input,BorderLayout.CENTER);
		    add(output,BorderLayout.SOUTH);
		}
		class Input extends JPanel{	    	
			JTextField[] tf = new JTextField[5];
	    	String[] text = {"번호","이름","핸드폰 번호","이메일","주민등록번호"};
    		String[] jobText = {"선택","회사원","공무원","의사","변호사","연예인","학생","기타"};
    		JLabel la,job;
    		JComboBox box;
	    	
	    	public Input(){
	    		LineBorder line_input = new LineBorder(Color.blue, 1);
				setBorder(new TitledBorder(line_input,
						                   "입력",
						                   TitledBorder.LEFT,
						                   TitledBorder.TOP,
						                   null,
						                   Color.RED
				 		                   ));
	    		//setBorder(new TitledBorder(new LineBorder(Color.BLUE,2),"입력"));
	    		
				setLayout(new GridLayout(6,2,5,30));
	    		
	    		for(int i =0; i < text.length; i++){
	    			la = new JLabel(text[i]);
	    			tf[i] = new JTextField(10);
	                la.setHorizontalAlignment(JLabel.CENTER);
	    			add(la);
	    			add(tf[i]);	    			
	    		}	    		
	    		box = new JComboBox(jobText);
	    		job = new JLabel("직업");
		    	job.setHorizontalAlignment(JLabel.CENTER);
		    	add(job);
	    		add(box);
	    		setPreferredSize(new Dimension(280,300));	
	    	}	   	
	   }
	    class Output extends JPanel implements ActionListener{
	    	
	    	JPanel info = new JPanel();    //신상정보 보더 만들기 JPanel
	    	JPanel search = new JPanel();  //정보검색 보더 만들기 JPanel
	    	
	    	CardLayout card = new CardLayout();
	    	String[] text ={"    나  이 :","    성  별 :","    출신도 :","    생  일 :"};
    		JLabel[] label=new JLabel[4] ;
    		ButtonGroup group = new ButtonGroup();
    		JRadioButton[] searchRadio = new JRadioButton[3];
    		JButton searchButton;
    		JButton exitButton ;
    		JTextField nameText;
    		String[] search_name={"이름","직업","출신도"};
    		int type;    		
    		
	    	public Output(){	    		
	    		//'신상정보' Border 만들기
	    		LineBorder line_one = new LineBorder(Color.blue, 1);
				info.setBorder(new TitledBorder(line_one,
						                   "신상정보",
						                   TitledBorder.LEFT,
						                   TitledBorder.TOP,
						                   null,
						                   Color.RED
				 		                   ));
	    		//info.setBorder(new TitledBorder(new LineBorder(Color.BLUE,2),"신상정보"));
				
				info.setLayout(new GridLayout(4,1));
				
				for(int i=0; i < text.length; i++){
					
					label[i]= new JLabel(text[i],JLabel.LEFT);
					info.add(label[i]);
				}
				//'정보검색' Border 만들기
				LineBorder line_two = new LineBorder(Color.blue, 1);
				search.setBorder(new TitledBorder(line_two,
						                   "정보검색",
						                   TitledBorder.LEFT,
						                   TitledBorder.TOP,
						                   null,
						                   Color.RED
				 		                   ));
				//search.setBorder(new TitledBorder(new LineBorder(Color.BLUE,2),"정보검색"));

				nameText = new JTextField(10);
				searchButton = new JButton("찾기");
				exitButton = new JButton("나가기");
				
				searchButton.setBackground(Color.CYAN);
				exitButton.setBackground(Color.LIGHT_GRAY);
				
				//이벤트연결
				searchButton.addActionListener(this);
				exitButton.addActionListener(this);
				
				int x =-70;				
				search.setLayout(null); //사용자 임의 배치 방법
				search.setPreferredSize(new Dimension(280,250));
				
				for(int i=0; i<searchRadio.length; i++){
					searchRadio[i]= new JRadioButton(search_name[i]);
					searchRadio[i].setBounds(x+=80,30,65,30);
					
					group.add(searchRadio[i]);
					search.add(searchRadio[i]);
					
					//이벤트 연결
					searchRadio[i].addActionListener(this);
				}
				nameText.setBounds(25,80,200,30);
				searchButton.setBounds(25,130,70,30);
				exitButton.setBounds(115,130,110,30);
				search.add(nameText);
				search.add(searchButton);
				search.add(exitButton);
				
				card = new CardLayout();
				setLayout(card);
				
				add(info,"신상정보 카드");
				add(search,"정보검색 카드");				
	    	}
			@Override
			public void actionPerformed(ActionEvent e) {				
				if(e.getActionCommand().equals("이름"))		  type = 1;
				else if(e.getActionCommand().equals("직업"))	  type = 9;
				else if(e.getActionCommand().equals("출신도")) type = 7;
				else if(e.getActionCommand().equals("찾기"))	  Search();
				else if(e.getActionCommand().equals("나가기")) ExitData();
			}
			//찾기
			public void Search(){				 
				Vector v = new Vector();
				
				//순차 검색 알고리즘 적용
				for(int i=0; i< showTable.data.size();i++){					
					if(nameText.getText().equals(showTable.data.elementAt(i).get(type))){						
						v.addElement(showTable.data.elementAt(i));
					}
				}
				/* 중요: setDataVector() 메소드 => 컬럼 벡터와 데이터 벡터 합치기
				   void setDataVector(Vector  dataVector,Vector  columnIdentifiers)
				    기능: 현재의 dataVector 인스턴스 변수를, 새로운 행의 Vector 인 dataVector 에 
				          옮겨놓는다.
				    파라미터: dataVector - 신규의 데이터 벡터(the new data vector)
				           columnIdentifiers - 열의 이름(the names of the columns) 
				    아래 문장 없으면 데이터 '검색' 이 안됨 
				*/   
				showTable.datamodel.setDataVector(v, showTable.column_name);
				showTable.TableSize();
				
				nameText.setText(null);				
			}
			//나가기
			public void ExitData(){				
				//card.show(west.output,"신상정보 카드");
				card.next(west.output);
				buttons.addBtn.setEnabled(true);    //'추가' 버튼 활성화
				buttons.nextBtn.setEnabled(true);   //'다음' 버튼 활성화
				
				west.input.tf[0].setText(null);
				west.input.tf[1].setText(null);
				west.input.tf[2].setText(null);
				west.input.tf[3].setText(null);
				west.input.tf[4].setText(null);
				west.input.box.setSelectedIndex(0);
				west.input.tf[0].requestFocus();
				
				west.output.label[0].setText("    나  이 :");
				west.output.label[1].setText("    성  별 :");
				west.output.label[2].setText("    출신도 :");
				west.output.label[3].setText("    생  일 :");					
				
				showTable.datamodel.setDataVector(showTable.data, showTable.column_name);
				showTable.TableSize();				
			}
	    }	
	}
	//버튼들 만들기 내부 클래스
	class Buttons extends JPanel implements ActionListener{
		
		Vector<String> vector;
		JButton addBtn,delBtn,preBtn,nextBtn,updateBtn,searchBtn;
		
		int age;
		String juminNo,sung,chul;
		
		public Buttons(){			
			setLayout(new GridLayout(1,7));
			
			addBtn = new JButton("추가");
			delBtn = new JButton("삭제");
			preBtn = new JButton("이전");
			nextBtn = new JButton("다음");			
			updateBtn = new JButton("수정");
			searchBtn = new JButton("검색");
			
			addBtn.setBackground(Color.YELLOW);
			delBtn.setBackground(Color.LIGHT_GRAY);
			updateBtn.setBackground(Color.PINK);
			searchBtn.setBackground(Color.GREEN);
			
			add(addBtn);add(delBtn);add(preBtn);add(nextBtn);
			add(updateBtn);add(searchBtn);
			
			//이벤트 연결
			addBtn.addActionListener(this);
			delBtn.addActionListener(this);
			preBtn.addActionListener(this);
			nextBtn.addActionListener(this);			
			updateBtn.addActionListener(this);
			searchBtn.addActionListener(this);			
		}
		@Override
		public void actionPerformed(ActionEvent e) {
		
			if(e.getActionCommand().equals("추가"))		AddData();
			else if(e.getActionCommand().equals("삭제"))	DelData();
			else if(e.getActionCommand().equals("이전")) PreData();
			else if(e.getActionCommand().equals("다음")) NextData();			
			else if(e.getActionCommand().equals("수정")) UpdateData();
			else if(e.getActionCommand().equals("검색")) SearchData();
		}
		//'추가' 버튼 이벤트 처리 
		public void AddData(){
			
			nextBtn.setEnabled(true);  //'다음' 버튼 활성화
			
			vector = new Vector<String>();
			
			vector.add(west.input.tf[0].getText());
			vector.add(west.input.tf[1].getText());
			vector.add(west.input.tf[2].getText());
			vector.add(west.input.tf[3].getText());
			vector.add(west.input.tf[4].getText());
			
			juminNo = west.input.tf[4].getText();			
			
			int[] weight ={2,3,4,5,6,7,0,8,9,2,3,4,5};
			int sum=0;
			int temp,result;
			
			if(juminNo.length()== 0){
				JOptionPane.showMessageDialog(null,
						                      "주민번호가 입력되지 않았음",
						                      "에러메세지",
						                      JOptionPane.ERROR_MESSAGE);				
				west.input.tf[4].requestFocus();
				return;				
			}
			else if(juminNo.length()< 14 || juminNo.length()>14 ){				
				if(juminNo.length()< 14){
					JOptionPane.showMessageDialog(null,
						                          "주민번호 14자리가 안됨",
						                          "에러메세지",
						                          JOptionPane.ERROR_MESSAGE);
					west.input.tf[4].setText(null);
					west.input.tf[4].requestFocus();
					return;
				}
				else{
					JOptionPane.showMessageDialog(null,
						                          "주민번호 14자리가 넘음",
						                          "에러메세지",
						                          JOptionPane.ERROR_MESSAGE);
					west.input.tf[4].setText(null);
					west.input.tf[4].requestFocus();
					return;
				}				
			}
			else if(juminNo.length() == 14){				
				for(int i=0; i<juminNo.length()-1; i++){
					if(juminNo.charAt(i)=='-') continue;
					sum +=weight[i]*(juminNo.charAt(i)-48);
				}		
			    temp= 11-(sum%11);
			    result = temp%10;
			   
			    if(result == juminNo.charAt(13)-48){				   
					JOptionPane.showMessageDialog(null,"주민번호 check결과 정상입니다");				   
					//나이추출
					Calendar cal= Calendar.getInstance(Locale.KOREA);
					int year = cal.get(Calendar.YEAR); //현재 시스템 년도를 얻어옴
	
					int yy=Integer.parseInt(juminNo.substring(0,2));
					if(juminNo.charAt(7)-48 <3) 
						yy = yy+1900;
					else 
						yy = yy+2000;
	
					age = year - yy + 1; //본나이 
					
					//성별추출
					if((juminNo.charAt(7)-48)% 2 == 0)
						sung="여자";					
					else
						sung="남자";				
					//"출신도" 추출			
					String localeStr = juminNo.substring(8,10); 
					int locale = Integer.parseInt(localeStr); 
					
					int localeCode[]={0,9,13,16,26,35,40,48,55,67,77,81,91,95};  	
					String birthArea[]={"서울","부산","인천","경기도","강원도","충북","충남",
								        "전북","전남","경북","대구","경남","제주도"};
					
					String birthPlace = null; 
					for(int j=0; j<=12; j++){
						if(locale >= localeCode[j] && locale < localeCode[j+1]){
							birthPlace = birthArea[j];						
						  	break; 
						} else 
							continue;
					}				
					vector.add(String.valueOf(age)); 
					vector.add(sung);
					vector.add(birthPlace);
					vector.add(juminNo.substring(2,4)+"/"+juminNo.substring(4,6));
					
					west.output.label[0].setText("    나  이 :"+"    " + String.valueOf(age));
					west.output.label[1].setText("    성  별 :"+"    " + sung);
					west.output.label[2].setText("    출신도 :"+"    " + birthPlace);
					west.output.label[3].setText("    생  일 :"+"  " + juminNo.substring(2,4)+"/"+juminNo.substring(4,6));				
				 }
				 else{
					   JOptionPane.showMessageDialog(null,
							                         "주민번호 틀림",
							                         "에러메세지",
								                     JOptionPane.ERROR_MESSAGE);
					   west.input.tf[4].setText(null);
					   west.input.tf[4].requestFocus();
					   return;
				 }			     
		   }		   
		   vector.add(west.input.box.getSelectedItem().toString());		   
		  
		   west.input.tf[0].setText(null);
		   west.input.tf[1].setText(null);
		   west.input.tf[2].setText(null);
		   west.input.tf[3].setText(null);
		   west.input.tf[4].setText(null);
		   west.input.box.setSelectedIndex(0);
		   west.input.tf[0].requestFocus();
		   
		   west.output.label[0].setText("    나  이 :");
		   west.output.label[1].setText("    성  별 :");
		   west.output.label[2].setText("    출신도 :");
		   west.output.label[3].setText("    생  일 :");
			
		   showTable.data.addElement(vector);		   
		   
		   //중요: 아래 문장이 없으면 JTable에 데이터 '추가' 안됨
		   showTable.datamodel.fireTableDataChanged();				
		}
		//'삭제' 버튼 이벤트 처리
		public void DelData(){
			int yes_no_select = JOptionPane.showConfirmDialog(null, 
					                      "정말 삭제하겠습니까?", 
					                      "삭제 확인 창", 
					                      JOptionPane.YES_NO_OPTION);
			
			if(yes_no_select == JOptionPane.YES_OPTION){					
				addBtn.setEnabled(true);    //'추가' 버튼 활성화
				
				JTable tb = showTable.table;
				int deletedRow = tb.getSelectedRow();
				
				if(deletedRow == -1)
					return;
				else{				
					DefaultTableModel model = (DefaultTableModel) tb.getModel();				
					model.removeRow(deletedRow);
					
					west.input.tf[0].setText(null);
					west.input.tf[1].setText(null);
					west.input.tf[2].setText(null);
					west.input.tf[3].setText(null);
					west.input.tf[4].setText(null);
					west.input.tf[4].setEnabled(true);
					
					west.input.box.setSelectedIndex(0);
					
					west.output.label[0].setText("    나  이 :");
					west.output.label[1].setText("    성  별 :");
					west.output.label[2].setText("    출신도 :");
					west.output.label[3].setText("    생  일 :");
					
					west.input.tf[0].requestFocus();
				}
			}else{
				return;
			}
		}
		//'이전' 버튼 이벤트 처리
		public void PreData(){	
			if(showTable.row > 0){				
				showTable.row--;
				addBtn.setEnabled(false);  //'추가' 버튼 비활성화
				nextBtn.setEnabled(true);  //'다음' 버튼 활성화
			}
			else{  
				return;
			}
			showTable.Info();			
		}
		//'다음' 버튼 이벤트 처리
		public void NextData(){			
			if(showTable.row < showTable.datamodel.getRowCount()-1){
				showTable.row++;
				preBtn.setEnabled(true);   //'이전' 버튼 활성화
				addBtn.setEnabled(false);  //'추가' 버튼 비활성화
			}
			else{
				nextBtn.setEnabled(false);  //'다음' 버튼 비활성화
				addBtn.setEnabled(true);    //'추가' 버튼 활성화
				
				west.input.tf[0].setText(null);
				west.input.tf[1].setText(null);
				west.input.tf[2].setText(null);
				west.input.tf[3].setText(null);
				
				//중요: '주민번호'를 무효인 컴포넌트로 설정=> 즉 수정 못하게
				west.input.tf[4].setEnabled(true);
	    		//--------------------------------
				west.input.tf[4].setText(null);			
				west.input.box.setSelectedIndex(0);				
				west.input.tf[0].requestFocus();
				
				west.output.label[0].setText("    나  이 :");
				west.output.label[1].setText("    성  별 :");
				west.output.label[2].setText("    출신도 :");
				west.output.label[3].setText("    생  일 :");								
				return;
			}
			showTable.Info();			
		}		
		//'수정' 버튼 이벤트 처리
		public void UpdateData(){
			
			int updateRow = showTable.table.getSelectedRow();
			
			//'핸드폰번호' 수정
			showTable.table.setValueAt(west.input.tf[2].getText(), updateRow, 2);
			//'이메일' 수정
			showTable.table.setValueAt(west.input.tf[3].getText(), updateRow, 3);
			//'직업' 수정
			showTable.table.setValueAt(west.input.box.getSelectedItem().toString(), updateRow, 9);			
		}
		//'검색' 버튼 이벤트 처리
		public void SearchData(){			
			//west.output.card.show(west.output,"정보검색 카드");
			west.output.card.next(west.output);			
		}	
	}
	//JTable 만들기 내부 클래스
	class ShowTable extends MouseAdapter {
		DefaultTableModel datamodel;
		JTable table;
		JScrollPane scrollPane;
		
		String[] colName={"번호","이름","핸드폰번호","E-Mail","주민등록번호",
				"나이","성별","출신도","생일","직업"};
		
		Vector<Vector<String>> data;
		Vector<String> column_name;
		int row = -1;
		
		public ShowTable(){			
			data = new Vector<Vector<String>>();
			column_name = new Vector<String>();
			
			for(int i =0 ; i< colName.length; i++){
				column_name.add(colName[i]);
			}
			/* 중요: JTable 데이터 '수정' 못하게=> West클래스의 Input에서 직접 수정하겠금
			    수동으로 메소드 추가=> ctrl + spacebar 눌러 추가시킴				
			   row,column에 위치하는 셀이 편집 가능한지 아닌지를 리턴
			    편집이 가능한 경우에 true, 안되면 false
			------------------------------------------- */
			/* 생성자 => DefaultTableModel를 구축해 data와 columnNames를  
			           setDataVector() 메소드에 건네주는 것으로 테이블을 초기화 한다
			*/
			datamodel=new DefaultTableModel(data,column_name){				
				public boolean isCellEditable(int row, int column) {
					return false;  //return true; 하면 셀 편집 가능
				};
			};
			table= new JTable(datamodel);
			scrollPane= new JScrollPane(table);
			
			//이벤트 연결
			table.addMouseListener(this);
			
			//-----------------------------------------------------------
			//JTable의 컬럼명을 클릭하면 데이터를 오름차순, 내림차순시키기
			//-----------------------------------------------------------
			table.setAutoCreateRowSorter(true);
			
			TableRowSorter<TableModel> tablesorter = 
				new TableRowSorter<TableModel>(table.getModel());
			
			table.setRowSorter(tablesorter);
			
			TableSize();			
		}
		@Override
		public void mouseClicked(MouseEvent e) {
			row =table.getSelectedRow();
			Info();			
		}		
		public void Info(){
			int row = this.row;
			
			west.input.tf[0].setText((String) showTable.table.getValueAt(row,0));
			west.input.tf[1].setText((String) showTable.table.getValueAt(row,1));
			west.input.tf[2].setText((String) showTable.table.getValueAt(row,2));
			west.input.tf[3].setText((String) showTable.table.getValueAt(row,3));
			west.input.tf[4].setText((String) showTable.table.getValueAt(row,4));
			
			//tf[1].setEnabled(false); //'이름'을 무효인 컴포넌트로 설정=> 즉 수정 못하게
    		//중요: '주민번호'를 무효인 컴포넌트로 설정=> 즉 수정 못하게
			west.input.tf[4].setEnabled(false);
    		//---------------------------------
			west.input.box.setSelectedItem(showTable.table.getValueAt(row,9));
					
			west.output.label[0].setText("    나이   :"+"    " +showTable.table.getValueAt(row,5));
			west.output.label[1].setText("    성별   :"+"    " + showTable.table.getValueAt(row,6));
			west.output.label[2].setText("    출신도   :"+"    " +showTable.table.getValueAt(row,7));						
			west.output.label[3].setText("    생일   :"+"  " + showTable.table.getValueAt(row,8));
			
			/* void changeSelection(int rowIndex,int columnIndex,
                    			    boolean toggle,boolean extend) 
                  기능: toggle 와 extend 의 2 개의 플래그 상태에 의해, 테이블의 선택 모델을 갱신 한다
                  중요: 아래 문장이 없으면 '이전', '다음' 버튼 클릭시 JTable의 행이 이동 안됨    
            */
			showTable.table.changeSelection(row, 0, false, false);			
		}
		public void TableSize(){
			//데이터들 셀의 중앙 정렬 시키기
			DefaultTableCellRenderer tableCell = new DefaultTableCellRenderer();			
			tableCell.setHorizontalAlignment(SwingConstants.CENTER);
			TableColumnModel CellModel = table.getColumnModel();

			for(int i=0; i<CellModel.getColumnCount();i++)
				CellModel.getColumn(i).setCellRenderer(tableCell);
			//---------------------------------------------------------
			table.getColumnModel().getColumn(0).setPreferredWidth(50);
			table.getColumnModel().getColumn(1).setPreferredWidth(50);
			table.getColumnModel().getColumn(2).setPreferredWidth(120);
			table.getColumnModel().getColumn(3).setPreferredWidth(150);
			table.getColumnModel().getColumn(4).setPreferredWidth(130);
			table.getColumnModel().getColumn(5).setPreferredWidth(50);
			table.getColumnModel().getColumn(6).setPreferredWidth(50);
			table.getColumnModel().getColumn(7).setPreferredWidth(50);
			table.getColumnModel().getColumn(8).setPreferredWidth(50);
			table.getColumnModel().getColumn(9).setPreferredWidth(50);
		}		
	}	
	public static void main(String[] args){	
		new ManageSystem();
	}
}

