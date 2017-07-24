package ���������α׷���;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class RemoveAction implements ActionListener{
	JTable ta;
	public RemoveAction(JTable table){
		ta = table;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		int row = ta.getSelectedRow();  //���õǾ��� ���̺��� ��ȣ�� �޾� �� �� �ִ�.
		
		if(row == -1) { //���� �������� �ʾ�������  �˸�â�� ��� �ش�.
			JOptionPane.showMessageDialog(null, "������  ���� �������� �ʾҽ��ϴ�.","���" , JOptionPane.ERROR_MESSAGE);
			return ;
		}
		//DefaultTableModel �� ����  
		/*
		 * JTable��ü�� ���δ� �߰� ���� �� �� ����.
		 * Model�� ���� �߰��� ������ �� �� �ִ�.
		 * DefaultTableModel Ŭ�������� ���� ���� ���� ���̺��� ���� �������� ���� �߰����� ����� ��� �� �� �ִ�.
		 */
		DefaultTableModel model = (DefaultTableModel) ta.getModel();
		model.removeRow(row);
		
	}

}
