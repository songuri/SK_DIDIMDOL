package MemoTest;

import java.awt.FileDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.imageio.IIOException;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTextArea;

public class Memo extends JFrame implements ActionListener{
	
	JFrame main_fr = new JFrame("�޸��� �����");
	FileDialog readOpen;
	FileDialog saveOpen;
	JTextArea area;
	
	public Memo(){
		JMenuBar main_bar = new JMenuBar();
		JMenu main_file = new JMenu("����");
		JMenuItem file_load =new JMenuItem("����");
		JMenuItem file_save = new JMenuItem("����");
		
		area = new JTextArea();
		
		file_load.addActionListener(this);
		file_save.addActionListener(this);
		
		main_file.add(file_load);
		main_file.add(file_save);
		
		
		main_bar.add(main_file);
		main_fr.setJMenuBar(main_bar);

		main_fr.add(area);
		
		
		main_fr.setBounds(200,200,500,400);
		main_fr.setVisible(true);
		main_fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String itempressed = e.getActionCommand();
		
		switch (itempressed){
		case "����":
			String save_name = saveName();
			try{
				save(save_name);
			}catch(Exception ex){
				System.out.println(ex);
			}
			break;
		case "����":
			String read_name = readName();
			try{
				read(read_name);
			}catch(Exception ex){
				ex.printStackTrace();
			}
		
		}
		
	}
	/*
	 * <����>
	 * 		���� ��Ʈ��		����	=	new	������Ʈ��		  (�⺻���� ��Ʈ��)
	 * 		bufferedReader read = 	new	BufferedReader(new FileReader(readfile));
	 * 
	 * 			************************************
	 * 	�Է½�Ʈ�� *	->	���� ��Ʈ��	->	���� ��Ʈ��     *-> ��� ��Ʈ��
	 * 			************************************
	 * 
	 *     *�� ���� ������ ���α׷��� �����̴�. �� �ܿ��� �⺻ ��Ʈ���̴�.
	 *     ���⼭ ���� ��Ʈ���� ���� ������ Input/Output�� ������ ��� ��Ű�� ���ؼ� ���� ���̴�.
	 * 		���� ���α׷��� cpu,memory�� ���Ƶ� �ϵ��ũ�� ������ ���� ���ϸ� ���� ������ ��� �� �� ����.
	 * 		��¥ ������ �а� ���� ������ FileReader�� �ϴ� �����ε� 
	 * 		�̰��� �ӵ��� ��� ��Ű�� ���ؼ� BufferedReader�� �ӵ��� ��� ��Ų��.
	 */

	public void read(String readfile) throws IOException{
		BufferedReader read = new BufferedReader(new FileReader(readfile));
		String line = read.readLine();
		
		while(line != null) {
			area.append(line + '\n') ;
			line = read.readLine();
		}
	}
	public void save(String saveFile) throws IOException{
		
		BufferedWriter save = new BufferedWriter(new FileWriter(saveFile));
		String line = area.getText();
		save.write(line);
		save.close();
	}
	
	public String readName(){
		readOpen = new FileDialog(main_fr,"��������" , FileDialog.LOAD);
		readOpen.setVisible(true);
		
		String fileDir = readOpen.getDirectory();
		String filename = readOpen.getFile();
		
		String readFileName = fileDir + "\\" + filename;
		
		return readFileName;
	}
	
	
	public String saveName(){
		saveOpen = new FileDialog(main_fr, "��������", FileDialog.SAVE);
		saveOpen.setVisible(true);
		String fileDir = saveOpen.getDirectory();
		String fileName = saveOpen.getFile();
		String saveFileName = fileDir+"\\"+fileName;
		return saveFileName;
	}
	
	
	public static void main(String[] args)throws IOException{
		new Memo();
	}
	

}
