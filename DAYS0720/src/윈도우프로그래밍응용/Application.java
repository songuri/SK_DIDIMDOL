package ���������α׷�������;

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
//���� Ŭ���� (�ܺ� Ŭ����)
public class Application extends JFrame{
	//���� Ŭ���� ��ü ����
	MenuMain menuMain = new MenuMain();
	West west = new West();
	Buttons buttons = new Buttons();
	ShowTable showtable = new ShowTable();
	//���� ���� ����
	int updateRow;
	
	//������ ȣ��
	public Application(){
		setTitle("������ �ý���");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//�� �׸��� ���ϴ� ��ġ�� ��ġ
		add(menuMain.bar,BorderLayout.NORTH);
		add(west,BorderLayout.WEST);
		add(buttons,BorderLayout.SOUTH);
		add(showtable.scrollPane,BorderLayout.CENTER);
		
		setSize(600,300);
		setLocation(400,150);
		setVisible(true);
	}
	//���� Ŭ���� ����
	class MenuMain extends JPanel implements ActionListener, ItemListener{
		JMenuBar bar = new JMenuBar();
		JMenu file,sort,help;
		JMenuItem fopen,fsave,fexit,proinfo;
		JCheckBoxMenuItem	sname,sjob;				//�̸�����
		ButtonGroup gr = new ButtonGroup();
		
		FileDialog readOpen,saveOpen;
		String fileDir,fileName,saveFileName,readFileName;
		
		//���� Ŭ���� �ȿ� ������ ����
		public MenuMain(){
			bar = new JMenuBar();
			
			file = new JMenu("����");
			sort = new JMenu("����");
			help = new JMenu("����");
			
			fopen = new JMenuItem("����");
			fsave = new JMenuItem("����");
			fexit = new JMenuItem("�ݱ�");
			
			sname = new JCheckBoxMenuItem("�̸�");
			sjob = new JCheckBoxMenuItem("����");
			proinfo = new JMenuItem("���α׷�����");
			
			//��ü ���̱�
			file.add(fopen); file.add(fsave); file.addSeparator();//���м��ֱ�
			file.add(fexit); 
			
			gr.add(sname);	gr.add(sjob);//�׷�ȭ
			sort.add(sname); sort.add(sjob);
			help.add(proinfo);
			
			bar.add(file);
			bar.add(sort);
			bar.add(help);
			
			//�̺�Ʈ ����
			//[����] �޴� �̺�Ʈ ����
			fopen.addActionListener(this);
			fsave.addActionListener(this);
			fexit.addActionListener(this);
			
			//[����] �޴� �̺�Ʈ ����
			sname.addItemListener(this);
			
		}

		@Override
		public void itemStateChanged(ItemEvent e) {
			//���� �̺�Ʈ ó��
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
			//������ ��������
			for(int i=0; i<row; i++){
				for(int j=0; j<col; j++){
						arr[i][j] = (String) showtable.table.getValueAt(i, j);
				}
			}
			//2���� �迭 �������� �˰���
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
			//������ �ֱ�
			for(int i=0; i<row; i++){
				for(int j=0; j<col; j++){
					showtable.table.setValueAt(arr[i][j], i, j);
				}
			}
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getActionCommand().equals("����")) open();
			if(e.getActionCommand().equals("����")) save();
			if(e.getActionCommand().equals("�ݱ�")) exit();
		}
		public void open(){
			StringTokenizer st;
			Vector vec;
			
			readOpen = new FileDialog(Application.this, "��������", FileDialog.LOAD);
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
					//1����� ������ �ֱ�
					while(st.hasMoreTokens()){
						vec.add(st.nextToken());
					}
					//���;ȿ� ���͸� ����ִ� ���� add�� �ƴ϶� addElement
					showtable.data.addElement(vec);
				}
				//������ �ѷ��ֱ�
				showtable.datamodel.fireTableDataChanged();
				read.close();
				
			}catch(Exception ex){
				System.out.println(ex);
				}
		}
		public void save(){
			//����â�� ���� ��ü ����
			saveOpen = new FileDialog(Application.this, "��������", FileDialog.SAVE);
			saveOpen.setVisible(true);
			
			fileDir = saveOpen.getDirectory();
			fileName = saveOpen.getFile();
			saveFileName = fileDir + "\\" + fileName;
			
			String str = "";
			String temp = "";
			
			try{//���� ��Ʈ���� ���� : ����� ���� ���
				BufferedWriter save = new BufferedWriter(new FileWriter(saveFileName));
				for(int i=0; i<showtable.table.getRowCount(); i++){
					temp = showtable.data.elementAt(i).toString();
					str += temp.substring(1, temp.length() - 1) + "\n";	
				}
				save.write(str);
				save.close();
			//����ó��	
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
			//���κ��� �����
			setBorder(new TitledBorder(new LineBorder(Color.BLUE, 2),"�Է�"));
			
			String text[]={"��  ��","�ڵ�����ȣ","�ֹι�ȣ"};
			tf = new JTextField[3];
			setLayout(new GridLayout(3, 2, 5, 10));			//3�� 2�� ���ǰ���5 ���ǰ���10
			
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
			addBtn = new JButton("�߰�");
			updateBtn = new JButton("����");
			delBtn = new JButton("����");
			
			addBtn.setBackground(Color.LIGHT_GRAY);
			updateBtn.setBackground(Color.YELLOW);
			delBtn.setBackground(Color.CYAN);
			
			add(addBtn); add(updateBtn); add(delBtn);
			//�̺�Ʈ����
			addBtn.addActionListener(this);
			updateBtn.addActionListener(this);
			delBtn.addActionListener(this);
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getActionCommand().equals("�߰�")) addData();
			else if(e.getActionCommand().equals("����")) updateData();
			else if(e.getActionCommand().equals("����")) delData();
		}
		//����� ���� �޼ҵ� addData ����
		public void addData(){
			Vector<String> vector = new Vector<String>();
			
			vector.add(west.tf[0].getText());
			vector.add(west.tf[1].getText());
			//�ֹι�ȣ�� Vector��ü�� �����ϱ� ���� ���� �ֹι�ȣ �������� üũ
			juminNo = west.tf[2].getText();
			//[�߿�] ����ǥ���� ����, �ֹι�ȣ �ڸ��� Ȯ��
			String regex = "[0-9]{6}-[0-9]{7}";
			boolean check = Pattern.matches(regex, juminNo);
			
			if(check == false){
				JOptionPane.showMessageDialog(null, "�ֹι�ȣ�� ����ǥ���Ŀ� ���� �ʴ´�", "���", JOptionPane.ERROR_MESSAGE);
				west.tf[2].setText(null);	//�ֹι�ȣ �ʱ�ȭ
				west.tf[2].requestFocus();	//Ű���� Ŀ���� �� �ڸ��� �ֶ�
				return;						//�׻��� �׷��� �ֶ�
			}
			//�ֹι�ȣ ��ȿ�� Ȯ��
			int sum = 0;
			int weigth[] = {2,3,4,5,6,7,0,8,9,2,3,4,5};
			for(int i=0; i<13; i++){
				if(juminNo.charAt(i)=='-') continue;
					sum = sum + (juminNo.charAt(i)-48) * weigth[i];
			}
			int temp = 11 - (sum % 11);
			int result = temp % 10;
			if(result ==juminNo.charAt(13)-48){
				//shoeMessageDiaLog ���ڰ��� 2���� ������ �޼���â, 4���� ������ �޼���â
				JOptionPane.showMessageDialog(null, "�ֹι�ȣ ����!");
			}
			else{
				JOptionPane.showMessageDialog(null, "�ֹι�ȣ ������!", "���", JOptionPane.ERROR_MESSAGE);
				return;
			}
			vector.add(west.tf[2].getText());
			
			west.tf[0].setText(null);
			west.tf[1].setText(null);
			west.tf[2].setText(null);
			west.tf[0].requestFocus();
			
			showtable.data.addElement(vector);
			//data ���Ϳ� ����� �����͵��� datamodel�� Jtable�� �����ֱ�
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
			//����Ȯ��â
			int yes_no_select = JOptionPane.showConfirmDialog(null, "���� ���� �Ͻðڽ��ϱ�?", "����������", JOptionPane.YES_NO_OPTION);
			
			if(yes_no_select == JOptionPane.YES_OPTION){
				JTable tb = showtable.table;
				int deleteRow = tb.getSelectedRow();
				
				//���� �������� �ʾ�����
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
		�޼ҵ尡 5���̱� ������ �������̵��� 5���� �� �ҷ��´�
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
		//���ʿ��Ѱ��� �ҷ����� �ʱ����ؼ��� ������ ���� �Է��ϸ�ȴ�
	class ShowTable extends MouseAdapter{
		
		DefaultTableModel	datamodel;
		JTable				table;
		JScrollPane			scrollPane;
		
		String colName[] = {"�̸�","�ڵ�����ȣ","�ֹι�ȣ"};
		//�߿�
		Vector<Vector<String>>	data;
		Vector<String> column_name;
		
		public ShowTable(){
			//Arrlist�� �����ʰ� Vector 2���� ���� ������ ������,����ȭ �����̴�.
			data = new Vector<Vector<String>>();	//�⺻[10,10]�������̰� �߰� �� �� 10����
			column_name = new Vector<String>();
			
			for(int i=0; i<colName.length; i++){
				column_name.add(colName[i]);
			}
			datamodel = new DefaultTableModel(data, column_name);
			table = new JTable(datamodel);
			scrollPane = new JScrollPane(table);
			
			//�̺�Ʈ����
			table.addMouseListener(this);
		}
		
		//�������� �������̵�
		@Override
		public void mouseClicked(MouseEvent e) {
			//���콺 Ŭ���� ���� ��ȣ�� �����´�
			updateRow = table.getSelectedRow();
			//���̺��� ������ Ŭ���� west�� �����͸� �����´�
			for(int i=0; i<3; i++){
			west.tf[i].setText((String) showtable.table.getValueAt(updateRow, i));
			}
		}
	}
	public static void main(String[] args) {
		new Application();
		
	}

}


//�Լ��� ���α׷���:	y = f(x) ������ �Լ��� ������ ���α׷��� ���