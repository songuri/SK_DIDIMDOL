package �������ý���;

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
	
	//���� Ŭ������ ��ü ����
	MenuMain menuMain = new MenuMain();    //�޴� ����� ����Ŭ����
	West west = new West(); 		       //�Է�,�Ż�����,�����˻� ���� ����� ����Ŭ����
	Buttons buttons = new Buttons();       //��ư�� ����� ����Ŭ����
	ShowTable showTable = new ShowTable();  //JTable ����� ����Ŭ����
	
	public ManageSystem(){
		//���̺� ���� �κ�
		//���̺� ���� �� �ݵ�� �ݺ����� �;� �Ѵ�.
		LABEL: //���̺��
		while(true){ //���̺� ���� ����
			String password = JOptionPane.showInputDialog("������ �ý���"+"\n"+"�н����� �Է�");
			String passwd = "king1234";		
			
			if(password.equals(passwd)){
				setTitle("������ �����ý���");
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
	                    "�н����尡 ���� �ʽ��ϴ�."+"\n"+
				        "'Ȯ��' ��ư ��������.",
	                    "���� �޼���",
	                    JOptionPane.ERROR_MESSAGE);	
				continue LABEL;				
			}
		} //���̺� ���� ��
	}
	//�޴� ����� ���� Ŭ����
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
			
			file = new JMenu("����");
			sort = new JMenu("����");			
			help = new JMenu("����");
			
			fopen = new JMenuItem("����");
			fsave = new JMenuItem("����");
			fexit = new JMenuItem("�ݱ�");
			
			sno = new JCheckBoxMenuItem("��ȣ");
			sname = new JCheckBoxMenuItem("�̸�");
			schul = new JCheckBoxMenuItem("��ŵ�");
			sjob = new JCheckBoxMenuItem("����");
			
			proinfo =  new JMenuItem("���α׷� ����");
			
			//���̱�
			file.add(fopen);
			file.add(fsave);
			file.addSeparator(); //���м� �ֱ�
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
			
			//���� �޴� �̺�Ʈ ����
			fopen.addActionListener(this);
			fsave.addActionListener(this);
			fexit.addActionListener(this);
			
			//���� �޴� �̺�Ʈ ����
			sno.addItemListener(this);
			sname.addItemListener(this);
			schul.addItemListener(this);
			sjob.addItemListener(this);			
		}
		//�̺�Ʈó��
		@Override
		public void actionPerformed(ActionEvent e) {			
			if(e.getActionCommand().equals("����"))      Open();
			else if(e.getActionCommand().equals("����"))	Save();
			else if(e.getActionCommand().equals("�ݱ�"))	Exit();			
		}
		//-------
		//���� ����
		//-------
		public void Open(){
			StringTokenizer st;
			Vector v;
			
			readOpen = 
				new FileDialog(ManageSystem.this,"��������",FileDialog.LOAD);
			readOpen.setVisible(true);
			
			fileDir = readOpen.getDirectory();
			fileName = readOpen.getFile();
			             
			readfileName =fileDir + "//" + fileName;		    
		    
		    try{
			    BufferedReader read = new BufferedReader(new FileReader(readfileName));
			    String line=null;
				
				while((line=read.readLine()) != null){					
					st= new StringTokenizer(line,", ");
					v = new Vector();  //�߿�  
					
					while(st.hasMoreTokens()){						
						v.add(st.nextToken());						
					}
					//-----------------------------------------------------
					//�߰� �κ�=> �ֹι�ȣ �� 6�ڸ��� "******" ���� ��ȯ�ϱ�
					//-----------------------------------------------------
					String juminNo_encryption =  (String) v.get(4);
					juminNo_encryption = juminNo_encryption.substring(0, 8)+"******";					
					
					v.set(4, juminNo_encryption);					
					
					//------------
				    //Vector�� ���
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
		//���� ����
		//-------
		public void Save(){						
			saveOpen = new FileDialog(ManageSystem.this,"��������",FileDialog.SAVE);
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
			    save.close();  //�ڿ�����		    
		    }catch(Exception ex){
		    	System.out.println(ex);
		    }
		}
		public void Exit(){			
			System.exit(0);	
		}
		//���� �̺�Ʈó��
		@Override
		public void itemStateChanged(ItemEvent e) {			
			if(e.getSource().equals(sno))        noSort();      //'��ȣ' ����
			else if(e.getSource().equals(sname)) stringSort(1); //'�̸�' ����
			else if(e.getSource().equals(schul)) stringSort(7); //'��ŵ�' ����
			else if(e.getSource().equals(sjob))	 stringSort(9); //'����' ����			
		}
		//'��ȣ' ����
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
		//�̸�,��ŵ�,���� ����
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
	//�Է�,�Ż�����,�����˻� ���� ����� ���� Ŭ����
	class West extends JPanel{		
		//����Ŭ���� ��ü ����
		Input input = new Input();
		Output output= new Output();
		
		public West(){		
		    setLayout(new BorderLayout());	
		    add(input,BorderLayout.CENTER);
		    add(output,BorderLayout.SOUTH);
		}
		class Input extends JPanel{	    	
			JTextField[] tf = new JTextField[5];
	    	String[] text = {"��ȣ","�̸�","�ڵ��� ��ȣ","�̸���","�ֹε�Ϲ�ȣ"};
    		String[] jobText = {"����","ȸ���","������","�ǻ�","��ȣ��","������","�л�","��Ÿ"};
    		JLabel la,job;
    		JComboBox box;
	    	
	    	public Input(){
	    		LineBorder line_input = new LineBorder(Color.blue, 1);
				setBorder(new TitledBorder(line_input,
						                   "�Է�",
						                   TitledBorder.LEFT,
						                   TitledBorder.TOP,
						                   null,
						                   Color.RED
				 		                   ));
	    		//setBorder(new TitledBorder(new LineBorder(Color.BLUE,2),"�Է�"));
	    		
				setLayout(new GridLayout(6,2,5,30));
	    		
	    		for(int i =0; i < text.length; i++){
	    			la = new JLabel(text[i]);
	    			tf[i] = new JTextField(10);
	                la.setHorizontalAlignment(JLabel.CENTER);
	    			add(la);
	    			add(tf[i]);	    			
	    		}	    		
	    		box = new JComboBox(jobText);
	    		job = new JLabel("����");
		    	job.setHorizontalAlignment(JLabel.CENTER);
		    	add(job);
	    		add(box);
	    		setPreferredSize(new Dimension(280,300));	
	    	}	   	
	   }
	    class Output extends JPanel implements ActionListener{
	    	
	    	JPanel info = new JPanel();    //�Ż����� ���� ����� JPanel
	    	JPanel search = new JPanel();  //�����˻� ���� ����� JPanel
	    	
	    	CardLayout card = new CardLayout();
	    	String[] text ={"    ��  �� :","    ��  �� :","    ��ŵ� :","    ��  �� :"};
    		JLabel[] label=new JLabel[4] ;
    		ButtonGroup group = new ButtonGroup();
    		JRadioButton[] searchRadio = new JRadioButton[3];
    		JButton searchButton;
    		JButton exitButton ;
    		JTextField nameText;
    		String[] search_name={"�̸�","����","��ŵ�"};
    		int type;    		
    		
	    	public Output(){	    		
	    		//'�Ż�����' Border �����
	    		LineBorder line_one = new LineBorder(Color.blue, 1);
				info.setBorder(new TitledBorder(line_one,
						                   "�Ż�����",
						                   TitledBorder.LEFT,
						                   TitledBorder.TOP,
						                   null,
						                   Color.RED
				 		                   ));
	    		//info.setBorder(new TitledBorder(new LineBorder(Color.BLUE,2),"�Ż�����"));
				
				info.setLayout(new GridLayout(4,1));
				
				for(int i=0; i < text.length; i++){
					
					label[i]= new JLabel(text[i],JLabel.LEFT);
					info.add(label[i]);
				}
				//'�����˻�' Border �����
				LineBorder line_two = new LineBorder(Color.blue, 1);
				search.setBorder(new TitledBorder(line_two,
						                   "�����˻�",
						                   TitledBorder.LEFT,
						                   TitledBorder.TOP,
						                   null,
						                   Color.RED
				 		                   ));
				//search.setBorder(new TitledBorder(new LineBorder(Color.BLUE,2),"�����˻�"));

				nameText = new JTextField(10);
				searchButton = new JButton("ã��");
				exitButton = new JButton("������");
				
				searchButton.setBackground(Color.CYAN);
				exitButton.setBackground(Color.LIGHT_GRAY);
				
				//�̺�Ʈ����
				searchButton.addActionListener(this);
				exitButton.addActionListener(this);
				
				int x =-70;				
				search.setLayout(null); //����� ���� ��ġ ���
				search.setPreferredSize(new Dimension(280,250));
				
				for(int i=0; i<searchRadio.length; i++){
					searchRadio[i]= new JRadioButton(search_name[i]);
					searchRadio[i].setBounds(x+=80,30,65,30);
					
					group.add(searchRadio[i]);
					search.add(searchRadio[i]);
					
					//�̺�Ʈ ����
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
				
				add(info,"�Ż����� ī��");
				add(search,"�����˻� ī��");				
	    	}
			@Override
			public void actionPerformed(ActionEvent e) {				
				if(e.getActionCommand().equals("�̸�"))		  type = 1;
				else if(e.getActionCommand().equals("����"))	  type = 9;
				else if(e.getActionCommand().equals("��ŵ�")) type = 7;
				else if(e.getActionCommand().equals("ã��"))	  Search();
				else if(e.getActionCommand().equals("������")) ExitData();
			}
			//ã��
			public void Search(){				 
				Vector v = new Vector();
				
				//���� �˻� �˰��� ����
				for(int i=0; i< showTable.data.size();i++){					
					if(nameText.getText().equals(showTable.data.elementAt(i).get(type))){						
						v.addElement(showTable.data.elementAt(i));
					}
				}
				/* �߿�: setDataVector() �޼ҵ� => �÷� ���Ϳ� ������ ���� ��ġ��
				   void setDataVector(Vector  dataVector,Vector  columnIdentifiers)
				    ���: ������ dataVector �ν��Ͻ� ������, ���ο� ���� Vector �� dataVector �� 
				          �Űܳ��´�.
				    �Ķ����: dataVector - �ű��� ������ ����(the new data vector)
				           columnIdentifiers - ���� �̸�(the names of the columns) 
				    �Ʒ� ���� ������ ������ '�˻�' �� �ȵ� 
				*/   
				showTable.datamodel.setDataVector(v, showTable.column_name);
				showTable.TableSize();
				
				nameText.setText(null);				
			}
			//������
			public void ExitData(){				
				//card.show(west.output,"�Ż����� ī��");
				card.next(west.output);
				buttons.addBtn.setEnabled(true);    //'�߰�' ��ư Ȱ��ȭ
				buttons.nextBtn.setEnabled(true);   //'����' ��ư Ȱ��ȭ
				
				west.input.tf[0].setText(null);
				west.input.tf[1].setText(null);
				west.input.tf[2].setText(null);
				west.input.tf[3].setText(null);
				west.input.tf[4].setText(null);
				west.input.box.setSelectedIndex(0);
				west.input.tf[0].requestFocus();
				
				west.output.label[0].setText("    ��  �� :");
				west.output.label[1].setText("    ��  �� :");
				west.output.label[2].setText("    ��ŵ� :");
				west.output.label[3].setText("    ��  �� :");					
				
				showTable.datamodel.setDataVector(showTable.data, showTable.column_name);
				showTable.TableSize();				
			}
	    }	
	}
	//��ư�� ����� ���� Ŭ����
	class Buttons extends JPanel implements ActionListener{
		
		Vector<String> vector;
		JButton addBtn,delBtn,preBtn,nextBtn,updateBtn,searchBtn;
		
		int age;
		String juminNo,sung,chul;
		
		public Buttons(){			
			setLayout(new GridLayout(1,7));
			
			addBtn = new JButton("�߰�");
			delBtn = new JButton("����");
			preBtn = new JButton("����");
			nextBtn = new JButton("����");			
			updateBtn = new JButton("����");
			searchBtn = new JButton("�˻�");
			
			addBtn.setBackground(Color.YELLOW);
			delBtn.setBackground(Color.LIGHT_GRAY);
			updateBtn.setBackground(Color.PINK);
			searchBtn.setBackground(Color.GREEN);
			
			add(addBtn);add(delBtn);add(preBtn);add(nextBtn);
			add(updateBtn);add(searchBtn);
			
			//�̺�Ʈ ����
			addBtn.addActionListener(this);
			delBtn.addActionListener(this);
			preBtn.addActionListener(this);
			nextBtn.addActionListener(this);			
			updateBtn.addActionListener(this);
			searchBtn.addActionListener(this);			
		}
		@Override
		public void actionPerformed(ActionEvent e) {
		
			if(e.getActionCommand().equals("�߰�"))		AddData();
			else if(e.getActionCommand().equals("����"))	DelData();
			else if(e.getActionCommand().equals("����")) PreData();
			else if(e.getActionCommand().equals("����")) NextData();			
			else if(e.getActionCommand().equals("����")) UpdateData();
			else if(e.getActionCommand().equals("�˻�")) SearchData();
		}
		//'�߰�' ��ư �̺�Ʈ ó�� 
		public void AddData(){
			
			nextBtn.setEnabled(true);  //'����' ��ư Ȱ��ȭ
			
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
						                      "�ֹι�ȣ�� �Էµ��� �ʾ���",
						                      "�����޼���",
						                      JOptionPane.ERROR_MESSAGE);				
				west.input.tf[4].requestFocus();
				return;				
			}
			else if(juminNo.length()< 14 || juminNo.length()>14 ){				
				if(juminNo.length()< 14){
					JOptionPane.showMessageDialog(null,
						                          "�ֹι�ȣ 14�ڸ��� �ȵ�",
						                          "�����޼���",
						                          JOptionPane.ERROR_MESSAGE);
					west.input.tf[4].setText(null);
					west.input.tf[4].requestFocus();
					return;
				}
				else{
					JOptionPane.showMessageDialog(null,
						                          "�ֹι�ȣ 14�ڸ��� ����",
						                          "�����޼���",
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
					JOptionPane.showMessageDialog(null,"�ֹι�ȣ check��� �����Դϴ�");				   
					//��������
					Calendar cal= Calendar.getInstance(Locale.KOREA);
					int year = cal.get(Calendar.YEAR); //���� �ý��� �⵵�� ����
	
					int yy=Integer.parseInt(juminNo.substring(0,2));
					if(juminNo.charAt(7)-48 <3) 
						yy = yy+1900;
					else 
						yy = yy+2000;
	
					age = year - yy + 1; //������ 
					
					//��������
					if((juminNo.charAt(7)-48)% 2 == 0)
						sung="����";					
					else
						sung="����";				
					//"��ŵ�" ����			
					String localeStr = juminNo.substring(8,10); 
					int locale = Integer.parseInt(localeStr); 
					
					int localeCode[]={0,9,13,16,26,35,40,48,55,67,77,81,91,95};  	
					String birthArea[]={"����","�λ�","��õ","��⵵","������","���","�泲",
								        "����","����","���","�뱸","�泲","���ֵ�"};
					
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
					
					west.output.label[0].setText("    ��  �� :"+"    " + String.valueOf(age));
					west.output.label[1].setText("    ��  �� :"+"    " + sung);
					west.output.label[2].setText("    ��ŵ� :"+"    " + birthPlace);
					west.output.label[3].setText("    ��  �� :"+"  " + juminNo.substring(2,4)+"/"+juminNo.substring(4,6));				
				 }
				 else{
					   JOptionPane.showMessageDialog(null,
							                         "�ֹι�ȣ Ʋ��",
							                         "�����޼���",
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
		   
		   west.output.label[0].setText("    ��  �� :");
		   west.output.label[1].setText("    ��  �� :");
		   west.output.label[2].setText("    ��ŵ� :");
		   west.output.label[3].setText("    ��  �� :");
			
		   showTable.data.addElement(vector);		   
		   
		   //�߿�: �Ʒ� ������ ������ JTable�� ������ '�߰�' �ȵ�
		   showTable.datamodel.fireTableDataChanged();				
		}
		//'����' ��ư �̺�Ʈ ó��
		public void DelData(){
			int yes_no_select = JOptionPane.showConfirmDialog(null, 
					                      "���� �����ϰڽ��ϱ�?", 
					                      "���� Ȯ�� â", 
					                      JOptionPane.YES_NO_OPTION);
			
			if(yes_no_select == JOptionPane.YES_OPTION){					
				addBtn.setEnabled(true);    //'�߰�' ��ư Ȱ��ȭ
				
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
					
					west.output.label[0].setText("    ��  �� :");
					west.output.label[1].setText("    ��  �� :");
					west.output.label[2].setText("    ��ŵ� :");
					west.output.label[3].setText("    ��  �� :");
					
					west.input.tf[0].requestFocus();
				}
			}else{
				return;
			}
		}
		//'����' ��ư �̺�Ʈ ó��
		public void PreData(){	
			if(showTable.row > 0){				
				showTable.row--;
				addBtn.setEnabled(false);  //'�߰�' ��ư ��Ȱ��ȭ
				nextBtn.setEnabled(true);  //'����' ��ư Ȱ��ȭ
			}
			else{  
				return;
			}
			showTable.Info();			
		}
		//'����' ��ư �̺�Ʈ ó��
		public void NextData(){			
			if(showTable.row < showTable.datamodel.getRowCount()-1){
				showTable.row++;
				preBtn.setEnabled(true);   //'����' ��ư Ȱ��ȭ
				addBtn.setEnabled(false);  //'�߰�' ��ư ��Ȱ��ȭ
			}
			else{
				nextBtn.setEnabled(false);  //'����' ��ư ��Ȱ��ȭ
				addBtn.setEnabled(true);    //'�߰�' ��ư Ȱ��ȭ
				
				west.input.tf[0].setText(null);
				west.input.tf[1].setText(null);
				west.input.tf[2].setText(null);
				west.input.tf[3].setText(null);
				
				//�߿�: '�ֹι�ȣ'�� ��ȿ�� ������Ʈ�� ����=> �� ���� ���ϰ�
				west.input.tf[4].setEnabled(true);
	    		//--------------------------------
				west.input.tf[4].setText(null);			
				west.input.box.setSelectedIndex(0);				
				west.input.tf[0].requestFocus();
				
				west.output.label[0].setText("    ��  �� :");
				west.output.label[1].setText("    ��  �� :");
				west.output.label[2].setText("    ��ŵ� :");
				west.output.label[3].setText("    ��  �� :");								
				return;
			}
			showTable.Info();			
		}		
		//'����' ��ư �̺�Ʈ ó��
		public void UpdateData(){
			
			int updateRow = showTable.table.getSelectedRow();
			
			//'�ڵ�����ȣ' ����
			showTable.table.setValueAt(west.input.tf[2].getText(), updateRow, 2);
			//'�̸���' ����
			showTable.table.setValueAt(west.input.tf[3].getText(), updateRow, 3);
			//'����' ����
			showTable.table.setValueAt(west.input.box.getSelectedItem().toString(), updateRow, 9);			
		}
		//'�˻�' ��ư �̺�Ʈ ó��
		public void SearchData(){			
			//west.output.card.show(west.output,"�����˻� ī��");
			west.output.card.next(west.output);			
		}	
	}
	//JTable ����� ���� Ŭ����
	class ShowTable extends MouseAdapter {
		DefaultTableModel datamodel;
		JTable table;
		JScrollPane scrollPane;
		
		String[] colName={"��ȣ","�̸�","�ڵ�����ȣ","E-Mail","�ֹε�Ϲ�ȣ",
				"����","����","��ŵ�","����","����"};
		
		Vector<Vector<String>> data;
		Vector<String> column_name;
		int row = -1;
		
		public ShowTable(){			
			data = new Vector<Vector<String>>();
			column_name = new Vector<String>();
			
			for(int i =0 ; i< colName.length; i++){
				column_name.add(colName[i]);
			}
			/* �߿�: JTable ������ '����' ���ϰ�=> WestŬ������ Input���� ���� �����ϰڱ�
			    �������� �޼ҵ� �߰�=> ctrl + spacebar ���� �߰���Ŵ				
			   row,column�� ��ġ�ϴ� ���� ���� �������� �ƴ����� ����
			    ������ ������ ��쿡 true, �ȵǸ� false
			------------------------------------------- */
			/* ������ => DefaultTableModel�� ������ data�� columnNames��  
			           setDataVector() �޼ҵ忡 �ǳ��ִ� ������ ���̺��� �ʱ�ȭ �Ѵ�
			*/
			datamodel=new DefaultTableModel(data,column_name){				
				public boolean isCellEditable(int row, int column) {
					return false;  //return true; �ϸ� �� ���� ����
				};
			};
			table= new JTable(datamodel);
			scrollPane= new JScrollPane(table);
			
			//�̺�Ʈ ����
			table.addMouseListener(this);
			
			//-----------------------------------------------------------
			//JTable�� �÷����� Ŭ���ϸ� �����͸� ��������, ����������Ű��
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
			
			//tf[1].setEnabled(false); //'�̸�'�� ��ȿ�� ������Ʈ�� ����=> �� ���� ���ϰ�
    		//�߿�: '�ֹι�ȣ'�� ��ȿ�� ������Ʈ�� ����=> �� ���� ���ϰ�
			west.input.tf[4].setEnabled(false);
    		//---------------------------------
			west.input.box.setSelectedItem(showTable.table.getValueAt(row,9));
					
			west.output.label[0].setText("    ����   :"+"    " +showTable.table.getValueAt(row,5));
			west.output.label[1].setText("    ����   :"+"    " + showTable.table.getValueAt(row,6));
			west.output.label[2].setText("    ��ŵ�   :"+"    " +showTable.table.getValueAt(row,7));						
			west.output.label[3].setText("    ����   :"+"  " + showTable.table.getValueAt(row,8));
			
			/* void changeSelection(int rowIndex,int columnIndex,
                    			    boolean toggle,boolean extend) 
                  ���: toggle �� extend �� 2 ���� �÷��� ���¿� ����, ���̺��� ���� ���� ���� �Ѵ�
                  �߿�: �Ʒ� ������ ������ '����', '����' ��ư Ŭ���� JTable�� ���� �̵� �ȵ�    
            */
			showTable.table.changeSelection(row, 0, false, false);			
		}
		public void TableSize(){
			//�����͵� ���� �߾� ���� ��Ű��
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

