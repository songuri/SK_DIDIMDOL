/*package ���������α׷�������;

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


 * ���� Ŭ������ �ܺο��� �ܵ����� ��� �� �� ����
 * ���� Ŭ������ �ܺ�Ŭ������ �޼ҵ带 ��� �� �� �ִ�.
 * ����ϱ� ���� ��.�� �б⵵ ���� ���� 
 * ���� �ȵ���̵� ���ø����̼ǿ��� ���� ��� �ȴ�.
 

//���� Ŭ����
public class Application extends JFrame{
	//inner Constructor ��������ó�� ����ҷ��� �� ���� ������ �� ���̴�.
	MenuMain menuMain = new MenuMain();
	West west = new West();
	Buttons buttons = new Buttons();
	ShowTable showtable = new ShowTable();
	
	//Constructor
	public Application(){
		setTitle("�� ���� �ý���");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		
		 * �̷������� ��ü�� �־� �ָ� �� ���� ���� ���� ��.�� �������� �����ϰ� 
		 
		add(menuMain.bar, BorderLayout.NORTH);  //menuMain���� bar�� ���� �ֻ��� ��ü �̴�.
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
		JCheckBoxMenuItem	sname ,sjob;  //�̸� ����.
		ButtonGroup gr = new ButtonGroup();
		
		
		FileDialog	readOpen , saveOpen;
		String fileDir, filename, savefileName ,readfileName;
		
		public MenuMain(){
			bar = new JMenuBar();
			
			file = new JMenu("����");
			sort = new JMenu("����");
			help = new JMenu("����");
			
			fopen = new JMenuItem("����");
			fsave = new JMenuItem("����");
			fexit = new JMenuItem("�ݱ�");
			
			sname = new JCheckBoxMenuItem("�̸�");
			sjob  = new JCheckBoxMenuItem("����");
			
			proinfo = new JMenuItem("���α׷� ����");
			
			//�ʱ�ȭ ��.�� 
			
			//���� �޴��� �����۵��� �߰� ���ִ°�
			file.add(fopen); file.add(fsave);
			file.addSeparator(); //���м��� �־� �ִ� ���̴�.
			file.add(fexit);
			
			//���� �޴��� �����۵��� �׷�ȭ ���Ѽ� �߰� �����ִ°� 
			gr.add(sname); gr.add(sjob); // �׷�ȭ => �̸� ����.
			sort.add(sname);sort.add(sjob);
			
			//���� �޴��� �����۵��� �߰� ���� �ִ°�.
			help.add(proinfo);
			
			bar.add(file);	bar.add(sort); bar.add(help);
			
			//�̺�Ʈ ����
			
			//[���� ����,����,����]
			fopen.addActionListener(this);
			fsave.addActionListener(this);
			fexit.addActionListener(this);
			
			//[����]
			sname.addItemListener(this);
			
			
		}
		@Override
		public void itemStateChanged(ItemEvent e) {
			// TODO Auto-generated method stub
		
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(e.getActionCommand().equals("����"))	open();
			else if(e.getActionCommand().equals("����"))	save();
			else if(e.getActionCommand().equals("�ݱ�"))	exit();
			
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
			//���� �����(west �ǳ��� ���ι����� �� ��.��)
			setBorder(new TitledBorder(new LineBorder(Color.blue, 2),"�Է�")); //���κ���!!! �׷��� ���ϰ� �α��� �������ִ� ���̴�.
			String text[] = {"�̸�" , "�ڵ��� ��ȣ" , "�ֹι�ȣ"};
			tf = new JTextField[3];
			setLayout(new GridLayout(3,2,5,10));
			
			for(int i = 0 ; i < text.length ; i ++){
				la = new JLabel(text[i]);
				tf[i] = new JTextField(20); // ������ 20����ŭ ���� �� �ֵ��� ���� �ȴ�.
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
			addBtn = new JButton("�߰�");
			updateBtn = new JButton("����");
			delBtn = new JButton("����");
			
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
	
	//JPanel�� ����� ���� �ʿ䰡 ����. MouseListener�Ҷ��� ����.
	class ShowTable  implements MouseListener{
		
		 * (non-Javadoc)
		 * @see java.awt.event.MouseListener#mouseClicked(java.awt.event.MouseEvent)
		 * ���� �ȵ� ����  MouseListener�� Implements������ �Ʒ� 5������ ��� �Լ� ���� ���� ����� �Ѵ�.
		 * ������ ���� �ʿ�� ���� �ʴ� ��� ��ɵ� ���� �����ؾ��Ѵ�.
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
		 �̰��� �Ʊ�ó�� interface�� ��� �޴� ���� �ƴ϶� ���� ���콺 Ŭ�� �ϳ��� �̺�Ʈ�� ó���Ұ��̱� 
		 * ������ ��ó�� MouseLisener interface�� ��ӹ޾Ƽ� ���ʿ��� ��� �̺�Ʈ�� ������ �ʿ� ����
		 * MouseAdapter Ŭ������ ���� ��� �޾Ƽ� ���� �ʿ��� ����� �������̵� �ؼ� ��� �ϵ��� �Ѵ�.
		 
		
		DefaultTableModel	datamodel;
		JTable				table;
		JScrollPane			scrollPane;
		
		String colName[] = {"�̸�" , "�ڵ��� ��ȣ" , "�ֹ� ��ȣ"};
		//[�߿�]
		Vector<Vector<String>>	data; 
		Vector<String>			column_name;
		
		public ShowTable(){
			data = new Vector<Vector<String>>(); // �⺻ 10��  �����ϸ� 10���� ����.
			column_name = new Vector<String>();
			
			for(int i = 0 ; i < colName.length ; i ++){
				column_name.add(colName[i]);
			}
			//1�ܰ�
			datamodel = new DefaultTableModel(data, column_name);		
			//2�ܰ�
			table = new JTable(datamodel);
			//3�ܰ�
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