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
	
	JFrame main_fr = new JFrame("메모장 만들기");
	FileDialog readOpen;
	FileDialog saveOpen;
	JTextArea area;
	
	public Memo(){
		JMenuBar main_bar = new JMenuBar();
		JMenu main_file = new JMenu("파일");
		JMenuItem file_load =new JMenuItem("열기");
		JMenuItem file_save = new JMenuItem("저장");
		
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
		case "저장":
			String save_name = saveName();
			try{
				save(save_name);
			}catch(Exception ex){
				System.out.println(ex);
			}
			break;
		case "열기":
			String read_name = readName();
			try{
				read(read_name);
			}catch(Exception ex){
				ex.printStackTrace();
			}
		
		}
		
	}
	/*
	 * <형식>
	 * 		보조 스트림		변수	=	new	보조스트림		  (기본연결 스트림)
	 * 		bufferedReader read = 	new	BufferedReader(new FileReader(readfile));
	 * 
	 * 			************************************
	 * 	입력스트림 *	->	보조 스트림	->	보조 스트림     *-> 출력 스트림
	 * 			************************************
	 * 
	 *     *로 덮은 공간이 프로그램의 범위이다. 그 외에는 기본 스트림이다.
	 *     여기서 보조 스트림을 쓰는 이유는 Input/Output의 성능을 향상 시키기 위해서 쓰는 것이다.
	 * 		실제 프로그램은 cpu,memory가 좋아도 하드디스크의 성능이 좋지 못하면 좋은 성능을 기대 할 수 없다.
	 * 		진짜 파일을 읽고 쓰는 역할은 FileReader가 하는 역할인데 
	 * 		이것의 속도를 향상 시키기 위해서 BufferedReader가 속도를 향상 시킨다.
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
		readOpen = new FileDialog(main_fr,"문서열기" , FileDialog.LOAD);
		readOpen.setVisible(true);
		
		String fileDir = readOpen.getDirectory();
		String filename = readOpen.getFile();
		
		String readFileName = fileDir + "\\" + filename;
		
		return readFileName;
	}
	
	
	public String saveName(){
		saveOpen = new FileDialog(main_fr, "문서저장", FileDialog.SAVE);
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
