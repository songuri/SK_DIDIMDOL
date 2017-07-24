package ä�����α׷�;

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
	//�ʵ� ����
	private JPanel contentPane;
	private JTextField textField;
	private JButton start;
	JTextArea textArea;
	
	private ServerSocket serverSocket;
	private Socket socket;
	private int port;
	
	//�����尣�� ������ ������ Vector �Yü �ʿ�
	private Vector vector = new Vector();
	
	public Server(){
		init();
	}
	public void init(){
			setTitle("ä��-����");
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setBounds(400,150,280,400);
			
			contentPane = new JPanel();
			//��������� ��ġ Layout���
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
			
			JLabel lblNewLabel = new JLabel("��Ʈ ��ȣ");
			lblNewLabel.setBounds(12,264,98,37);
			contentPane.add(lblNewLabel);
			
			start = new JButton("���� ����");
			
			/*
			 * �̺�Ʈ ���� �� �̺�Ʈ �ڵ鷯 ó���� �ѹ��� �ذ��ϴ� ���
			 */
			
			start.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					
					if(textField.getText().equals("")){
						JOptionPane.showMessageDialog(null, "��Ʈ��ȣ�� �Է��ϼ���!","���޼���",JOptionPane.ERROR_MESSAGE);
						textField.requestFocus();
						return;
					}
					else{
						try{
							port = Integer.parseInt(textField.getText());
							//��������� �޼ҵ�
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
	//����� �޼ҵ� ����
	public void server_start(int port) throws IOException{
		serverSocket = new ServerSocket(port);
		start.setText("���� ������");
		//��ư ��Ȱ��ȭ
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
						textArea.append("����� ���� �����...\n");
						socket = serverSocket.accept();
						textArea.append("����� ����!!\n");
						
						//������ ��� ���� �ϸ鼭 ������ �ۼ����� �ϱ� ���ؼ� ������ �ڵ鷯 ��ü ����
						ThreadHandler user = new ThreadHandler(socket,vector);
						vector.add(user);	//�ش� ���Ϳ� ����� ��ü �߰�
						user.start();
						
					}catch(Exception e){
						System.out.println(e);
					}
				}
				
			}
			
		}).start();
	}
	//������ ��鷯 Ŭ������ ��ü�� Ŭ���̾�Ʈ�� �� ���� �����ȴ�.
	//������ ���� Ŭ�󸮷�Ʈ���� ������ ���������� ó���ϴ� ����
	class ThreadHandler extends Thread{
		//�ʵ� ����
		private InputStream is;			//���ν�Ʈ��
		private OutputStream os;
		private DataInputStream dis;	//������Ʈ��
		private DataOutputStream dos;
		
		private Socket user_socket;
		private Vector user_vector;
		private String nick_name = "";
		
		//������ ����
		public ThreadHandler(Socket socket,Vector vector){
			user_socket = socket;
			user_vector = vector;
			
			User_network();
		}
	
	
		public void User_network(){
			try{
				is = user_socket.getInputStream();	//io ��Ʈ�� ����
				dis = new DataInputStream(is);
				
				os = user_socket.getOutputStream();
				dos = new DataOutputStream(os);
				
				nick_name = dis.readUTF();	//���ڿ� �б� �޼ҵ�
				textArea.append("������ ID" + nick_name +"\n");
				
				send_Message("���� ���ӵǾ����ϴ�.");
						
			}catch(Exception e){
				textArea.append("��Ʈ�� ���� ����!!");
			}
		}
		public void send_Message(String str){
			try{
				dos.writeUTF(str);
			}catch(IOException e){
				textArea.append("�޼����� �۽� ���� �߻�");
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
			textArea.append("����ڷκ��� ���� �޼���: "+str+"\n");
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