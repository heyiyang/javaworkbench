package cmm.ui.components;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 * �������ȥ��ı�ͼƬ�İ�ť����Ҫ����ͼƬ
 * @author axun
 *
 */
public class MyButton extends JButton{
	private ImageIcon enterIcon=null;
	private ImageIcon exitIcon=null;
	public MyButton(){
		this.addMouseListener(new ChangeIconListener());
	}
	

	public ImageIcon getExitIcon() {
		return exitIcon;
	}

	public void setExitIcon(ImageIcon exitIcon) {
		this.exitIcon = exitIcon;
		setIcon(exitIcon);
	}

	public ImageIcon getEnterIcon() {
		return enterIcon;
	}
	
	public void setIcon(ImageIcon icon){
		super.setIcon(icon);
	}
	
	private class ChangeIconListener implements MouseListener{

		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		public void mouseEntered(MouseEvent e) {
			if(enterIcon!=null) setIcon(enterIcon);
		}

		public void mouseExited(MouseEvent e) {
			if(exitIcon!=null) setExitIcon(exitIcon);
		}

		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
		
	}

	public void setEnterIcon(ImageIcon enterIcon) {
		this.enterIcon = enterIcon;
	}

}
