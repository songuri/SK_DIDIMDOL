package 채팅프로그램;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;



public class Server extends JFrame{
	//필드 선언
	private JPanel contentPane;
	private JTextField textField;
	private JButton start;
	JTextArea textArea;
	
	private ServerSocket serverSocket;
	private Socket socket;
	private int port;
	
	//스레드간의 정보를 공유할 Vector 갳체 필요
	private Vector vector = new Vector();
	
	public Server(){
		init();
	}
	public void init(){
			setTitle("채팅-서버");
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setBounds(400,150,280,400);
			
			contentPane = new JPanel();
			//사용자정의 배치 Layout방법
			contentPane.setLayout(null);
			setContentPane(contentPane);
			
			JScrollPane js = new JScrollPane();
			js.setBounds(0,0,264,254);
		
			contentPane.add(js);
			
			textArea = new JTextArea();
			textArea.setColumns(20);
			textArea.setRows(5);
			js.setViewportView(textArea);
			contentPane.add(textArea);
			
			textField = new JTextField();
			textField.setBounds(98, 264, 154, 37);
			textField.setColumns(10);
			contentPane.add(textField);
			
			JLabel lblNewLabel = new JLabel("포트 번호");
			lblNewLabel.setBounds(12,264,98,37);
			contentPane.add(lblNewLabel);
			
			start = new JButton("서버 실행");
			
			/*
			 * 이벤트 연결 및 이벤트 핸들러 처리를 한번에 해결하는 방법
			 */
			
			start.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					
					if(textField.getText().equals("")){
						JOptionPane.showMessageDialog(null, "포트번호를 입력하세요!","경고메세지",JOptionPane.ERROR_MESSAGE);
						textField.requestFocus();
						return;
					}
					else{
						try{
							port = Integer.parseInt(textField.getText());
							//사용자정의 메소드
							server_start(port);
						}catch(Exception ex){
							System.out.println(ex);
						}
					}
				}
			}); //end addActionListener
			
			start.setBackground(Color.green);
			start.setBounds(0,325,264,37);
			contentPane.add(start);
			textArea.setEditable(false);
	}
	//사용자 메소드 구현
	public void server_start(int port) throws IOException{
		serverSocket = new ServerSocket(port);
		start.setText("서버 실행중");
		//버튼 비활성화
		start.setEnabled(false);
		textField.setEnabled(false);
		
		if(serverSocket !=null){
			Connection();
		}
	}
	public void Connection(){
		new Thread(new Runnable(){

			@Override
			public void run() {
				while(true){
					try{
						textArea.append("사용자 접속 대기중...\n");
						socket = serverSocket.accept();
						textArea.append("사용자 접속!!\n");
						
						//접속을 계속 유지 하면서 데이터 송수신을 하기 위해서 스레드 핸들러 객체 생성
						ThreadHandler user = new ThreadHandler(socket,vector);
						vector.add(user);	//해당 벡터에 사용자 객체 추가
						user.start();
						
					}catch(Exception e){
						System.out.println(e);
					}
				}
				
			}
			
		}).start();
	}
	//스레드 행들러 클래스의 객체는 클라이언트당 한 개씩 생성된다.
	//서버로 들어온 클라리런트와의 연결음 개별적으로 처리하는 역할
	class ThreadHandler extends Thread{
		//필드 선언
		private InputStream is;			//메인스트림
		private OutputStream os;
		private DataInputStream dis;	//보조스트림
		private DataOutputStream dos;
		
		private Socket user_socket;
		private Vector user_vector;
		private String nick_name = "";
		
		//생성자 구현
		public ThreadHandler(Socket socket,Vector vector){
			user_socket = socket;
			user_vector = vector;
			
			User_network();
		}
	
	
		public void User_network(){
			try{
				is = user_socket.getInputStream();	//io 스트림 생성
				dis = new DataInputStream(is);
				
				os = user_socket.getOutputStream();
				dos = new DataOutputStream(os);
				
				nick_name = dis.readUTF();	//문자열 읽기 메소드
				textArea.append("접속자 ID" + nick_name +"\n");
				
				send_Message("정상 접속되었습니다.");
						
			}catch(Exception e){
				textArea.append("스트림 셋팅 에러!!");
			}
		}
		public void send_Message(String str){
			try{
				dos.writeUTF(str);
			}catch(IOException e){
				textArea.append("메세지에 송신 에러 발생");
			}
		}
		public void run(){
			while(true){
				try{
					String msg = dis.readUTF();
					InMessage(msg);
					
				}catch(Exception e){
					System.out.println(e);
				}
			}
		}
		
		public void InMessage(String str){
			textArea.append("사용자로부터 들어온 메세지: "+str+"\n");
			broad_cast(str);
		}
		
		public void broad_cast(String str){
			for(int i=0; i<user_vector.size(); i++){
				ThreadHandler imsi = (ThreadHandler) user_vector.elementAt(i);
				
				imsi.send_Message(nick_name+":"+str);
			}
		}
	}
	public static void main(String[] args) {
		Server frame = new Server();
		frame.setVisible(true);

	}

}