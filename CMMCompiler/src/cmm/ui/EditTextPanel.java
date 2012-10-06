/**
 * �༭����Ľ���
 * ���к�
 */
package cmm.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.MutableAttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;

import cmm.functions.CMMToken;
import cmm.ui.components.MyButton;
import cmm.ui.components.SyntaxHighLighterJPanel;

public class EditTextPanel extends JPanel{
	private SyntaxHighLighterJPanel jTextPane=new SyntaxHighLighterJPanel();
	private JTextPane indexjPane=new JTextPane();
	private static int lastLines=-1;	//��һ�ε��������ж��費��Ҫ�ػ��к�
	private JPanel errPanel=new JPanel();	//��ʾ����
	private ImageIcon errIcon1=new ImageIcon(EditTextPanel.class.getResource("/images/error.png"));
	private ImageIcon errIcon2=new ImageIcon(EditTextPanel.class.getResource("/images/error2.png"));
	private Vector<MyButton> errButtons=new Vector<MyButton>();
	private String lastContent="";
	
	
	public EditTextPanel(){
		this.setLayout(new BorderLayout());
		
		//����к�
		JPanel container=new JPanel();

		indexjPane.setPreferredSize(new Dimension(40,100));
		indexjPane.setBorder(BorderFactory.createEtchedBorder());
		indexjPane.setBackground(new Color(220,220,220));
		indexjPane.setEditable(false);
		container.setLayout(new BorderLayout());
		container.add(jTextPane,BorderLayout.CENTER);

		
		//��Ӵ�����ʾpanel
		JPanel containter2=new JPanel();
		BorderLayout borderLayout=new BorderLayout();
		borderLayout.setVgap(0);	//���ô�ֱ���
		containter2.setLayout(borderLayout);
		errPanel.setPreferredSize(new Dimension(18,100));
		errPanel.setBackground(new Color(158,204,218));
		
		
		containter2.add(errPanel,BorderLayout.WEST);
		containter2.add(indexjPane,BorderLayout.CENTER);
		container.add(containter2,BorderLayout.WEST);
		
		JScrollPane jScrollPane=new JScrollPane(container);
		this.add(jScrollPane,BorderLayout.CENTER);
		jTextPane.setText("");
		jTextPane.setBackground(new Color(204,232,207));
		jTextPane.setBorder(BorderFactory.createEtchedBorder());
		
		new MyThread().start();
	}
	
	public String getInputString(){
		return jTextPane.getText();
	}
	
	public void setInputString(String s){
		jTextPane.setText(s);
	}
	
	public void setText(String s){
		jTextPane.setText(s);
	}
	
	//��ʾ�к�
	public void showIndex(){

		int line=jTextPane.getLineCount()+2;
		if(line<1) line=1;	//������ʾ10�е��к�
		if(lastLines==line){	//����û�б仯���Ͳ����
			return;
		}else{
			DefaultStyledDocument m_doc;
			MutableAttributeSet normalAttr;
			StyleContext m_context = new StyleContext();
			m_doc = new DefaultStyledDocument(m_context);
			indexjPane.setDocument(m_doc);
			
			normalAttr = new SimpleAttributeSet();
			StyleConstants.setForeground(normalAttr, Color.black);
			StyleConstants.setBold(normalAttr, true);	//���Ǵ���
			StyleConstants.setUnderline(normalAttr, false);	//��Ҫ�»���
			StyleConstants.setAlignment(normalAttr,StyleConstants.ALIGN_LEFT);	//���Ҷ���
		
			
			
			lastLines=line;
			String s="";
			for(int i=1;i<=lastLines;i++){
				s+=(indexjPane.getText()+i+"\n");
			}
			indexjPane.setText(s);
			m_doc.setCharacterAttributes(0, indexjPane.getText().length()-1, normalAttr, false);
		}
	}
	
	/**
	 * ��ʾ�����־
	 */
	private void showError(){
		Vector<CMMToken> tokenVector=jTextPane.getToken();

		String content=jTextPane.getText();
		if(lastContent.equals(content)) return;	//�����ޱ仯������ˢ��
		else{
			lastContent=content;
		}
		//����Ƿ��иı�
		errPanel.removeAll();
		//errPanel.repaint();
		int index=1;
		int errCount=0;
		errButtons.removeAllElements();
		for(int i=0;i<tokenVector.size();i++){
			CMMToken token=tokenVector.get(i);
			if(token.getX()==index){
				if(token.getType()==0){
					MyButton errButton=new MyButton();
					errButtons.add(errButton);
					errButton.setContentAreaFilled(false);
					errButton.setBorderPainted(false);
					errButton.setExitIcon(errIcon1);
					errButton.setEnterIcon(errIcon2);
					errButton.setPreferredSize(new Dimension(13,13));
					errButton.setToolTipText(token.getMessageString());
					errPanel.add(errButton);
					index++;
					errCount++;
					continue;
				}
			}else if(token.getX()>index){
				JLabel label=new JLabel(" ");
				label.setPreferredSize(new Dimension(13,13));
				errPanel.add(label);
				index++;
				i--;	//����������ǰ��
				continue;
			}
			else{
				continue;
			}
			
		}
		
//		for(int i=0;i<errButtons.size();i++){
//			errButtons.get(i).repaint();
//		}
		
		errPanel.updateUI();	//ע�⡣û����һ�� ��ʾ������
	}
	
	//�½�һ���̣߳���ʱˢ��
	class MyThread extends Thread{

		public void run() {
			while(true){
				try{
					sleep(500);	//sleepһ����
					showIndex();
					showError();
				}catch(InterruptedException e){
					
				}
			}
			
		}
	}
}
