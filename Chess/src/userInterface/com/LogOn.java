package userInterface.com;

import javax.swing.*;
import java.awt.*;

import javax.swing.border.LineBorder;
import javax.swing.event.*;
import java.awt.Event;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class LogOn	extends JFrame {

//	��Ļ������ɫ��ɫ
	private int scWidth=Toolkit.getDefaultToolkit().getScreenSize().width;
	private int scHeight=Toolkit.getDefaultToolkit().getScreenSize().height;
	private Color brown = new Color(142,78,30);
	
//	������
	private JPanel jpMain;
	

//	����JLabel, �û�ID�����룬������IP
	JLabel jlID,jlPassword,jlServerIp;
	
//	����JTextField�� �û�ID��������IP
	JTextField jtfID,jtfSeverIp;
	
//	һ��JPasswordField, ����
	JPasswordField jpfPassword;
	
//	����JButton�� ��¼��ע�ᣬ�˳�
	JButton jbtLogon,jbtRegister,jbtExit;
	
//	����JLabel   ��¼�� ע�ᣬ �˳�
	JLabel jlLog, jlRegister, jlExit;
	
	
	public LogOn(){
		setBounds((scWidth-400)/2,(scHeight-300)/2,400,300);
		setTitle("^��������������^  ��¼");
		setIconImage(new ImageIcon("image/LOGO.PNG").getImage());
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
//		jpMain��ʼ��
		jpMain= new JpMain();
		jpMain.setBounds(0, 0, 400, 300);
		jpMain.setLayout(null);
		
//		jpMain ������

//		JLabel ID
		jlID=new JLabel("�˺ţ�",JLabel.RIGHT);
		jlID.setBounds(10, 30, 130, 30);
//		jlID.setBorder(new LineBorder(Color.BLACK,3));
		jlID.setFont(new Font("΢���ź�",1,18));
		jlID.setForeground(Color.BLACK);
		
//		JLabel Password
		jlPassword = new JLabel("���룺",JLabel.RIGHT);
		jlPassword.setBounds(10, 70, 130, 30);
//		jlPassword.setBorder(new LineBorder(Color.BLACK,3));
		jlPassword.setFont(new Font("΢���ź�",1,18));
		jlPassword.setForeground(Color.BLACK);
		
//		JLabel ServerIP
		jlServerIp=new JLabel("������IP��",JLabel.RIGHT);
		jlServerIp.setBounds(10, 110, 130, 30);
//		jlServerIp.setBorder(new LineBorder(Color.BLACK,3));
		jlServerIp.setFont(new Font("΢���ź�",1,18));
		jlServerIp.setForeground(Color.BLACK);
		
//		JTextField ID
		JTextField jtfID = new JTextField();
		jtfID.setBounds(150, 30, 160, 30);
		jtfID.setBorder(new LineBorder(Color.BLACK,2));
		jtfID.setOpaque(false);
		jtfID.setFont(new Font("΢���ź�",1,18));
		jtfID.setForeground(Color.BLACK);
		
//		JPasswordField password
		JPasswordField jpfPassword = new JPasswordField();
		jpfPassword.setBounds(150, 70, 160, 30);
		jpfPassword.setBorder(new LineBorder(Color.BLACK,2));
		jpfPassword.setOpaque(false);
		jpfPassword.setFont(new Font("΢���ź�",1,18));
		jpfPassword.setEchoChar('*');
		
//		JTextField ServerIp
		jtfSeverIp=new JTextField();
		jtfSeverIp.setBorder(new LineBorder(Color.BLACK,2));
		jtfSeverIp.setOpaque(false);
		jtfSeverIp.setBounds(150, 110, 160, 30);
		jtfSeverIp.setFont(new Font("΢���ź�",1,18));
		
//		JButton logon
		jbtLogon=new JButton(new ImageIcon("image/log.png"));
		jbtLogon.setBounds(90, 170, 50, 50);
		jbtLogon.setOpaque(false);
		jbtLogon.setContentAreaFilled(false);
		jbtLogon.setBorder(null);
		jbtLogon.addActionListener(new LogonListener());
		
//		JButton register
		jbtRegister = new JButton(new ImageIcon("image/register.gif"));
		jbtRegister.setBounds(170, 170	,50, 50);
		jbtRegister.setOpaque(false);
		jbtRegister.setContentAreaFilled(false);
		jbtRegister.setBorder(null);
		jbtRegister.addActionListener(new RegisterListener());
		
//		JButton exit
		jbtExit = new JButton(new ImageIcon("image/exit.gif"));
		jbtExit.setOpaque(false);
		jbtExit.setContentAreaFilled(false);
		jbtExit.setBorder(null);
		jbtExit.setBounds(250, 170, 50, 50);
		jbtExit.addActionListener(new ExitListener());
		
//		JLabel 
		jlLog = new JLabel("��¼");
		jlLog.setFont(new Font("΢���ź�",1,14));
		jlLog.setBounds(105, 220, 100, 30);
		
		jlRegister = new JLabel("ע��");
		jlRegister.setFont(new Font("΢���ź�",1,14));
		jlRegister.setBounds(180, 220, 100, 30);
		
		jlExit = new JLabel("�˳�");
		jlExit.setFont(new Font("΢���ź�",1,14));
		jlExit.setBounds(260, 220, 100, 30);
		
		jpMain.add(jlID);
		jpMain.add(jlPassword);
		jpMain.add(jlServerIp);
		jpMain.add(jtfID);
		jpMain.add(jpfPassword);
		jpMain.add(jtfSeverIp);
		jpMain.add(jbtLogon);
		jpMain.add(jbtRegister);
		jpMain.add(jbtExit);
		jpMain.add(jlLog);
		jpMain.add(jlRegister);
		jpMain.add(jlExit);
		
		add(jpMain);
		
		setVisible(true);
	}
	


	
//	��¼ jbtLogon����
	public class LogonListener implements ActionListener {
		public void actionPerformed (ActionEvent e){
			new GameHall();
			dispose();
		}
	}
	
//	ע��jbtRegister����
	public class RegisterListener implements ActionListener {
		public void actionPerformed (ActionEvent e){
			new Register();
		}
	}
//	�˳� jbtExit����
	public class ExitListener implements ActionListener {
		public void actionPerformed (ActionEvent e){
			System.exit(0);
		}
	}
	
//	������ jpMain ��class
	class JpMain extends JPanel {	
		public JpMain (){
			super();
		}
//		JpMain ��дpaint ������ ��ӱ���
		public void paintComponent(Graphics g){
			g.drawImage(new ImageIcon("image/logbackground.jpg").getImage(), 0, 0, 400, 300, this);
		}
	}
	
	
	
//	���Է���
	public static void main(String [] args){
		LogOn g=new LogOn();
		
	}
}
