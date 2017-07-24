package 채팅프로그램;


import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Client extends JFrame{
	//필드선언
	private JPanel contentPane;
	private JTextField tf_ID, tf_PORT, tf_IP;
	
	
	//생성자 구현
	public Client(){
		init();
	}
	
	public void init(){
		setTitle("채팅-클라이언트");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100,100,288,392);
		
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("ID");
		lblNewLabel.setBounds(58,51,90,34);
		contentPane.add(lblNewLabel);
		
		tf_ID = new JTextField();
		tf_ID.setBounds(92,58,150,21);
		tf_ID.setColumns(10);
		contentPane.add(tf_ID);
		
		JLabel lblServer = new JLabel("Server IP");
		lblServer.setBounds(18,111,90,34);
		contentPane.add(lblServer);
		
		tf_IP = new JTextField();
		tf_IP.setBounds(92,118,150,21);
		tf_IP.setColumns(10);
		contentPane.add(tf_IP);
		
		JLabel lblPort = new JLabel("PORT");
		lblPort.setBounds(38,171,90,34);
		contentPane.add(lblPort);
		
		tf_PORT = new JTextField();
		tf_PORT.setBounds(92,178,150,21);
		tf_PORT.setColumns(10);
		contentPane.add(tf_PORT);
		
		JButton btnNewButton = new JButton("접속");
		btnNewButton.setBounds(36,266,206,52);
		btnNewButton.setBackground(Color.YELLOW);
		contentPane.add(btnNewButton);
		
		btnNewButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//추가
				try{
					String _id = tf_ID.getText().trim();
					String _ip = tf_IP.getText().trim();
					int _port = Integer.parseInt(tf_PORT.getText().trim());
				
					MainView view = new MainView(_id,_ip,_port);
					setVisible(false);
				}catch(Exception ex){
					System.out.println(ex);
				}	
				
			}
		});
		

	}
	
	public static void main(String[] args) {
		Client client = new Client();
		client.setVisible(true);

	}
}
