/*�������� �ڵ��� �ϱ� ���� �̷������� Ʋ�� ��Ƴ��� �ϴ� ���̴�.

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseListener;

import javax.swing.*;

import org.w3c.dom.events.MouseEvent;

//���� Ŭ����
public class Application extends JFrame{
	//inner Constructor ��������ó�� ����ҷ��� �� ���� ������ �� ���̴�.
	MenuMain menuMain = new MenuMain();
	West west = new West();
	Buttons button = new Buttons();
	ShowTable showtable = new ShowTable();
	
	//Constructor
	public Application(){
		
	}
	class MenuMain extends JPanel implements ActionListener , ItemListener{

		public MenuMain(){
			
		}
		@Override
		public void itemStateChanged(ItemEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	class West extends JPanel{
		//Constructor
		public West(){
			
		}
	}
	
	class Buttons extends JPanel implements ActionListener{

		public Buttons(){
			
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
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
		 
		@Override
		public void mouseClicked(java.awt.event.MouseEvent e) {
			
		}
	}
	
	
	public static void main(String[] args){
		new Application();
	}
}
*/