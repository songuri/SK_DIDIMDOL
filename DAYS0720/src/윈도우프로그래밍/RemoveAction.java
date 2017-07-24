package 윈도우프로그래밍;

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
		int row = ta.getSelectedRow();  //선택되어진 테이블의 번호를 받아 올 수 있다.
		
		if(row == -1) { //행을 선택하지 않았을때는  알림창을 띄어 준다.
			JOptionPane.showMessageDialog(null, "삭제할  행을 선택하지 않았습니다.","경고" , JOptionPane.ERROR_MESSAGE);
			return ;
		}
		//DefaultTableModel 의 역할  
		/*
		 * JTable자체만 으로는 추가 삭제 할 수 없다.
		 * Model을 만들어서 추가나 삭제를 할 수 있다.
		 * DefaultTableModel 클래스에서 현재 내가 만든 테이블의 모델을 가져오고 이제 추가적인 기능을 사용 할 수 있다.
		 */
		DefaultTableModel model = (DefaultTableModel) ta.getModel();
		model.removeRow(row);
		
	}

}
