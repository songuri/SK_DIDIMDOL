/*본격적인 코딩을 하기 전에 이런식으로 틀을 잡아놓고 하는 것이다.

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseListener;

import javax.swing.*;

import org.w3c.dom.events.MouseEvent;

//메인 클래스
public class Application extends JFrame{
	//inner Constructor 전역변수처럼 사용할려고 맨 위에 선언을 한 것이다.
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
	
	//JPanel은 상속을 받을 필요가 없다. MouseListener할때의 예제.
	class ShowTable  implements MouseListener{
		
		 * (non-Javadoc)
		 * @see java.awt.event.MouseListener#mouseClicked(java.awt.event.MouseEvent)
		 * 좋든 싫든 간에  MouseListener를 Implements했으면 아래 5가지의 모든 함수 들을 구현 해줘야 한다.
		 * 하지만 내가 필요로 하지 않는 모든 기능들 까지 구현해야한다.
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
		 이것은 아까처럼 interface를 상속 받는 것이 아니라 나는 마우스 클릭 하나의 이벤트만 처리할것이기 
		 * 떄문에 위처럼 MouseLisener interface를 상속받아서 불필요한 모든 이벤트를 구현할 필요 없이
		 * MouseAdapter 클래스를 직접 상속 받아서 내가 필요한 기능한 오버라이드 해서 사용 하도록 한다.
		 
		@Override
		public void mouseClicked(java.awt.event.MouseEvent e) {
			
		}
	}
	
	
	public static void main(String[] args){
		new Application();
	}
}
*/