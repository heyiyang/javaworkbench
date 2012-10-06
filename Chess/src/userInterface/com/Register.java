package userInterface.com;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Register extends JFrame {
	
//	��Ļ������ɫ��ɫ
	private int scWidth=Toolkit.getDefaultToolkit().getScreenSize().width;
	private int scHeight=Toolkit.getDefaultToolkit().getScreenSize().height;
	private Color brown = new Color(142,78,30);
	
//	private jpMain
	JPanel jpMain ;
	

//	���JLAbel ID,�ǳƣ����룬ȷ�����룬ѡ��ͷ��
	JLabel jlID,jlName,jlPassword,jlPasswordConfirm,jlHeadImage;
	
	JLabel jlWelcome1,jlWelcome2;
	
//	����JTextField ID, �ǳ�
	public JTextField jtfID,jtfName;
	
//	����JPasswordField ���룬ȷ������;
	public JPasswordField jpfPassword,jpfPasswordConfirm;
	
//	һ��JComboBox ͷ��
	public JComboBox jcbHeadImage;
	
//	����JButton ȷ����ȡ��
	public JButton jbtOK, jbtCancel;
	
	JLabel jlOK,jlCancel;
	
	ImageIcon []  headImage = new ImageIcon[22];
	
	
	
//	���캯��
	public Register(){
		this.setBounds((scWidth-400)/2,(scHeight-300)/2,400,300);
		this.setTitle("^����������^ ע��");
		this.setResizable(false);
		this.setIconImage(new ImageIcon("image/LOGO.png").getImage());
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
//		��ʼ��jpMain
		jpMain = new JpMain();
		jpMain.setLayout(null);
		
//		jpMain �ϵĸ������� ��
		
//		JLabel ID
		jlID = new JLabel("�˺�",JLabel.RIGHT);
		jlID.setBounds(10, 20, 70, 30);
		jlID.setFont(new Font("΢���ź�",1,18));
//		jlID.setBorder(new LineBorder(Color.BLACK,3));
		
//		JLabel Name
		jlName = new JLabel("�ǳ�",JLabel.RIGHT);
		jlName.setBounds(10,60, 70, 30);
		jlName.setFont(new Font("΢���ź�",1,18));
//		jlName.setBorder(new LineBorder(Color.BLACK,3));
		
//		JLabel password
		jlPassword = new JLabel("����",JLabel.RIGHT);
		jlPassword.setBounds(10, 100, 70, 30);
		jlPassword.setFont(new Font("΢���ź�",1,18));
//		jlPassword.setBorder(new LineBorder(Color.BLACK,3));
		
//		JLabel passwordConfirm
		jlPasswordConfirm = new JLabel("ȷ������",JLabel.RIGHT);
		jlPasswordConfirm.setBounds(0, 140, 80, 30);
		jlPasswordConfirm.setFont(new Font("΢���ź�",1,18));
//		jlPasswordConfirm.setBorder(new LineBorder(Color.BLACK,3));
		
//		JLabel headImage
		jlHeadImage = new JLabel("ѡ��ͷ��",JLabel.CENTER);
		jlHeadImage.setBounds(260, 140, 100, 40);
		jlHeadImage.setFont(new Font("΢���ź�",1,20));
//		jlHeadImage.setBorder(new LineBorder(Color.BLACK,3));
		
//		JTextField ID
		jtfID = new JTextField();
		jtfID.setBounds(90, 20, 150, 30);
		jtfID.setOpaque(false);
		jtfID.setBorder(new LineBorder(Color.BLACK,2));
		jtfID.setFont(new Font("΢���ź�",1,16));
		
//		JTextField Name
		jtfName = new JTextField();
		jtfName.setBounds(90,60,150,30);
		jtfName.setOpaque(false);
		jtfName.setBorder(new LineBorder(Color.BLACK,2));
		jtfName.setFont(new Font("΢���ź�",1,16));
		
//		JPasswordField password
		jpfPassword = new JPasswordField();
		jpfPassword.setBounds(90, 100, 150, 30);
		jpfPassword.setOpaque(false);
		jpfPassword.setBorder(new LineBorder(Color.BLACK,2));
		jpfPassword.setFont(new Font("΢���ź�",1,16));
		jpfPassword.setEchoChar('*');
		
//		JPasswordField passwordConfirm
		jpfPasswordConfirm = new JPasswordField();
		jpfPasswordConfirm.setBounds(90, 140, 150, 30);
		jpfPasswordConfirm.setOpaque(false);
		jpfPasswordConfirm.setBorder(new LineBorder(Color.BLACK,2));
		jpfPasswordConfirm.setFont(new Font("΢���ź�",1,16));
		jpfPasswordConfirm.setEchoChar('*');
		
//		headImage[] ��ʼ��
		for(int i=0;i<22;i++){
			headImage [i] =new ImageIcon(new ImageIcon("image/headImage/"+i+".png").getImage());
		}
		
//		JComboBox headImage  ���� headImage[]
		jcbHeadImage = new JComboBox(headImage);
		jcbHeadImage.setBounds(250, 20, 135, 128);
		
//		JButton jbtOK
		jbtOK = new JButton(new ImageIcon("image/ok.gif"));
		jbtOK.setBounds(60, 190, 50, 50);
		jbtOK.setOpaque(false);
		jbtOK.setContentAreaFilled(false);
		jbtOK.setBorder(null);
		//jbtOK.addActionListener(new OKListener());
		
//		JButton jbtCancel
		jbtCancel = new JButton(new ImageIcon("image/cancel.gif"));
		jbtCancel.setBounds(140, 190, 50, 50);
		jbtCancel.setOpaque(false);
		jbtCancel.setContentAreaFilled(false);
		jbtCancel.setBorder(null);
		jbtCancel.addActionListener(new CancelListener());
		
		
		jlOK = new JLabel ("ȷ��");
		jlOK.setBounds(75, 240, 50, 30);
		jlOK.setFont(new Font("΢���ź�",1,14));
		
		
		jlCancel = new JLabel("ȡ��");
		jlCancel.setBounds(150, 240, 50, 30);
		jlCancel.setFont(new Font("΢���ź�",1,14));
		
		
//		��ӭ
		jlWelcome1 = new JLabel(new ImageIcon("image/register.gif"));
		jlWelcome1.setBounds(230, 210, 50, 50);
		jlWelcome1.setOpaque(false);
		jlWelcome1.setBorder(null);
//		jlWelcome.setContentAreaFilled(false);
//		jbtWelcome.setEnabled(false);
		
		jlWelcome2 = new JLabel(new ImageIcon("image/welcome2.gif"));
		jlWelcome2.setBounds(330, 210, 50, 50);
		jlWelcome2.setOpaque(false);
		jlWelcome2.setBorder(null);
		
		
		jpMain.add(jlID);
		jpMain.add(jlName);
		jpMain.add(jlPassword);
		jpMain.add(jlPasswordConfirm);
		jpMain.add(jlHeadImage);
		jpMain.add(jtfID);
		jpMain.add(jtfName);
		jpMain.add(jpfPassword);
		jpMain.add(jpfPasswordConfirm);
		jpMain.add(jcbHeadImage);
		jpMain.add(jbtOK);
		jpMain.add(jbtCancel);
		jpMain.add(jlOK);
		jpMain.add(jlCancel);
		jpMain.add(jlWelcome1);
		jpMain.add(jlWelcome2);
		
		this.add(jpMain);
		this.setVisible(true);
	}
	

//	ȷ����ť jbtOK ����
	
//	ȡ����ťjbtCancel ����
	public class CancelListener implements ActionListener {
		public void actionPerformed(ActionEvent e){
			dispose();
		}
	}
	
	public class JpMain extends JPanel {
		
		
		
		public JpMain () {
			
		}
		
//		JpMain ��дpaint ������ ��ӱ���
		public void paintComponent(Graphics g){
			g.drawImage(new ImageIcon("image/registerbackground.jpg").getImage(), 0, 0, 400, 300, this);
		}
		
		
	}
	
	
	
	public static void main(String[] args) {
		Register r =new Register();
		
	}
}
