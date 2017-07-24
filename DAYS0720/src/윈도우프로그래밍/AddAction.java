package 윈도우프로그래밍;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.*;

public class AddAction implements ActionListener{

	JTable ta;
	JTextField txt1, txt2 , txt3;
	public AddAction(JTable table,JTextField text1 , JTextField text2 , JTextField text3){
		ta = table;
		txt1 = text1;
		txt2 = text2;
		txt3 = text3;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String arr[] = new String[3];
		arr[0] = txt1.getText();
		arr[1] = txt2.getText();
		arr[2] = txt3.getText();
		DefaultTableModel model = (DefaultTableModel) ta.getModel();
		model.addRow(arr);
		txt1.setText(null);
		txt2.setText(null);
		txt3.setText(null);
		
		
	}

}
