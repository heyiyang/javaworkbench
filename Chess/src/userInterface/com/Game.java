package userInterface.com;

import javax.swing.*;

import java.awt.*;

import javax.swing.event.*;
import java.awt.event.*;
import javax.swing.border.*;

public class Game extends JFrame{

//	��Ļ������ɫ��ɫ
	private int scWidth=Toolkit.getDefaultToolkit().getScreenSize().width;
	private int scHeight=Toolkit.getDefaultToolkit().getScreenSize().height;
	private Color brown = new Color(142,78,30);
	
//	������
	JPanel jpMain = new JpMain();
	
//		���JButton ��ʼ��Ϸ�����¿�ʼ���˳���Ϸ���˻���ս������
	JButton jbtStart,jbtRestart,jbtExit,jbtVsPC,jbtRegret;
	
//		����JScrollPane, �����¼&�Ի�&����, �ֱ����JTextArea jpRecord,jpDialg��jpInput
	JScrollPane jspRecord, jspDialog, jspInput;
	public JTextArea jtaRecord,jtaDialog,jtaInput;
	
	public String inputMessage;					// ����� ����Ϣ
	
	
//		һ����Panel ��ʾ��ʾ��Ϣ �� ʱ��
	JPanel jpInformation, jpTime;
	
//		�ĸ�JLabel ������ʾ���ͷ���������ɫ
	JLabel jlPlayer1,jlPlayer2,jlPlayer1Disk,jlPlayer2Disk;
	
//	���췽��
	public Game(){
		this.setBounds((scWidth-800)/2, (scHeight-600)/2, 800,600);
		this.setTitle("^��������������^ ��Ϸ");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setResizable(false);
		
		
		jpMain.setBounds(0, 0, 800, 600);
		jpMain.setLayout(null);
		
//		��ʼ��Ϸ ��ť  jbtStart
		jbtStart = new JButton ("��ʼ��Ϸ");
		jbtStart.setBounds(30, 40, 45, 45);
		jbtStart.setBorder(new LineBorder(Color.BLACK,3));
		jbtStart.addActionListener(new StartListener());
		
		
//		�˻���ս ��ť jbtVsPC
		jbtVsPC = new JButton("�˻���ս");
		jbtVsPC.setBounds(30, 125, 45, 45);
		jbtVsPC.setBorder(new LineBorder(Color.BLACK,3));
		jbtVsPC.addActionListener(new VsPCListener());
		
		
//		���� ��ť jbtRegret
		jbtRegret = new JButton("����");
		jbtRegret.setBounds(30, 210, 45, 45);
		jbtRegret.setBorder(new LineBorder(Color.BLACK,3));
		jbtRegret.addActionListener(new RegretListener());
	
//		���¿�ʼ ��ť jbtRestart
		jbtRestart = new JButton("���¿�ʼ");
		jbtRestart.setBounds(30, 295, 45, 45);
		jbtRestart.setBorder(new LineBorder(Color.BLACK,3));
		jbtRestart.addActionListener(new RestartListener());
		
//		�˳���Ϸ ��ť JbtExit
		jbtExit = new JButton("�˳���Ϸ");
		jbtExit.setBounds(30, 380, 45, 45);
		jbtExit.setBorder(new LineBorder(Color.BLACK,3));
		jbtExit.addActionListener(new ExitListener());
		
//		JscrollPane �����¼�� ����JTextArea jtaRecord
		jtaRecord = new JTextArea();
		jtaRecord.setLineWrap(true);
		jtaRecord.setOpaque(false);
		jtaRecord.setEditable(false);
		jspRecord = new JScrollPane(jtaRecord);
		jspRecord.setBounds(560,20,220,100);
		jspRecord.setBorder(new LineBorder(Color.BLACK,3));
		jspRecord.setOpaque(false);
		jspRecord.getViewport().setOpaque(false);
		
//		JScrollPane �Ի��򣬰��� JTextArea jtaDialog
		jtaDialog = new JTextArea();
		jtaDialog.setLineWrap(true);
		jtaDialog.setOpaque(false);
		jtaDialog.setEditable(false);
		jspDialog = new JScrollPane(jtaDialog);
		jspDialog.setBounds(560, 140, 220, 300);
		jspDialog.setBorder(new LineBorder(Color.BLACK,3));
		jspDialog.setOpaque(false);
		jspDialog.getViewport().setOpaque(false);
		
//		JTextArea �����  ����JTextArea jtaInput
		jtaInput = new JTextArea();
		jtaInput.setLineWrap(true);
		jtaInput.setOpaque(false);
		jtaInput.addKeyListener(new InputListener());
		jspInput = new JScrollPane(jtaInput);
		jspInput.setBounds(560, 460, 220, 80);
		jspInput.setBorder(new LineBorder(Color.BLACK,3));
		jspInput.setOpaque(false);
		jspInput.getViewport().setOpaque(false);
		
//		���1ͷ�񣬹̶�������ʾ���ǽ�����Ϸ�����  icon1�������1ͷ��ͼƬ������·������ʵ���ж�
		ImageIcon icon1 = new ImageIcon("image/headImage/1small.png");
		jlPlayer1 = new JLabel(icon1);
		jlPlayer1.setBounds(200, 500, 45, 45);
		
//		���2ͷ�񣬹̶����ұ���ʾ���Ǽ�����Ϸ����һ��ߵ���   icon2�������2ͷ��ͼƬ������·������ʵ���ж�
		ImageIcon icon2 = new ImageIcon("image/headImage/3small.png");
		jlPlayer2 = new JLabel(icon2);
		jlPlayer2.setBounds(400, 500, 45, 45);
		
//		���1���ѣ���ɫ����ʵ������жϣ� icon3�������1���ӣ�����·������ʵ���ж�
		ImageIcon icon3 = new ImageIcon("image/whiteDisk.gif");
		jlPlayer1Disk = new JLabel(icon3);
		jlPlayer1Disk.setBounds(250, 500, 45, 45);
		
//		���2���ѣ���ɫ����ʵ������жϣ� icon4�������2���ӣ�����·������ʵ���ж�
		ImageIcon icon4 = new ImageIcon("image/blackDisk.gif");
		jlPlayer2Disk = new JLabel(icon4);
		jlPlayer2Disk.setBounds(350, 500, 45, 45);		
		
//		��Ӹ�������
		this.add(jbtStart);
		this.add(jbtVsPC);
		this.add(jbtRegret);
		this.add(jbtRestart);
		this.add(jbtExit);
		this.add(jspRecord);
		this.add(jspDialog);
		this.add(jspInput);
		this.add(jlPlayer1);
		this.add(jlPlayer2);
		this.add(jlPlayer1Disk);
		this.add(jlPlayer2Disk);
		
		this.add(jpMain);
		
		this.setVisible(true);
	}
	
	
//	��ʼ ��ť jbtStart ����
	public class StartListener implements ActionListener {
		public void actionPerformed (ActionEvent e){
			JOptionPane.showMessageDialog(null, "��ʼ��Ϸ");
		}
	}
	
//	�˻���ս jbtVsPC ����
	public class VsPCListener implements ActionListener {
		public void actionPerformed (ActionEvent e){
			JOptionPane.showMessageDialog(null, "�˻���ս");
		}
	}
	
//	���� jbtRegret ����
	public class RegretListener implements ActionListener {
		public void actionPerformed (ActionEvent e){
			JOptionPane.showMessageDialog(null, "����");
		}
	}
	
//	���¿�ʼ jbtRestart ����
	public class RestartListener implements ActionListener {
		public void actionPerformed (ActionEvent E){
			JOptionPane.showMessageDialog(null,"���¿�ʼ");
		}
	}
	 
//	�˳���Ϸ jbtExit ����
	public class ExitListener implements ActionListener {
		public void actionPerformed (ActionEvent e){
			JOptionPane.showMessageDialog(null, "�˳�");
		}
	}
	
//	���� jtaInput ����
	public class InputListener extends KeyAdapter {
			
			
			public void keyPressed (KeyEvent e){
				if(e.getKeyChar()==KeyEvent.VK_ENTER){		//��������� �س�
				JTextArea jtaf=(JTextArea)e.getSource();
				inputMessage = jtaf.getText();				// ��������Ϣ  ��ֵ ��  inputMessage
				jtaf.setText("");							// ����� ���
				
				
				}
		}
	}
	
	
	
//	JpMain
	class JpMain extends JPanel {
		public JpMain(){
			super();
		}
		
//		��дjpMain��paint��������ӱ���,�������
		public void paintComponent (Graphics g){
			g.drawImage(new ImageIcon("image/background.jpg").getImage(), 0, 0, 800, 600, this);
			g.drawLine(100, 40, 100, 490);
			g.drawLine(100,	40, 550, 40);
			g.drawLine(100, 490, 550, 490);
			g.drawLine(550, 40, 550, 490);
		}
	}
	
//	���Է���
	public static void main(String[] args) {
		new Game();
	}
}
