package userInterface.com;

import javax.swing.*;
import java.awt.*;

import javax.swing.event.*;
import java.awt.event.*;
import javax.swing.border.*;


@SuppressWarnings("serial")
public class ChangeInfo  extends JFrame {
	
	private int scWidth=Toolkit.getDefaultToolkit().getScreenSize().width;
	private int scHeight=Toolkit.getDefaultToolkit().getScreenSize().height;
	private Color brown = new Color(142,78,30);
	
	
	JPanel jpMain ;

//	�ĸ�label�� �޸��ǳƣ��޸����룬 ȷ�����룬�޸�ͷ��
	JLabel jlName,jlPassWord,jlPassWordConfirm,jlHeadImage;
	
//	JTextField, �����ǳ�
	JTextField jtfName;
	
//	JPasswordField �������룬�������루ȷ�ϣ���
	JPasswordField jpfPassword,jpfPasswordConfirm;
	
//	һ��ѡ��� ѡ��ͷ��
	JComboBox jcbHeadImage;
	
//	�������ܰ�ť�� ȷ�� & ȡ��
	JButton jbtOK,jbtCancel;
	JLabel jlOK, jlCancel;
	
//	���췽��
	public ChangeInfo () {
//		����Frame �Ĵ�С��title, 
		this.setBounds((scWidth-400)/2,(scHeight-500)/3, 400, 480);
		this.setTitle("�޸ĸ�������");
		this.setIconImage(new ImageIcon("image/LOGO.png").getImage());
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setResizable(false);
		
//		jpMain ��ʼ��
		jpMain = new JpMain();
		
		jpMain.setLayout(null);
		jpMain.setBounds(0, 0, 400, 500);
		
//		jpMain�ĸ�����
//		JLabel  jlName  �޸��ǳ�
		jlName =  new JLabel ("�޸��ǳ�",JLabel.RIGHT);
		jlName.setFont(new Font("΢���ź�",1,24));
		jlName.setBounds(30,30,130,40);
//		jlName.setBorder(new LineBorder(Color.BLACK,3));
		
		
//		JLabel jlPassWord  �޸�����
		jlPassWord = new JLabel ("�޸�����",JLabel.RIGHT);
		jlPassWord.setFont(new Font("΢���ź�",1,24));
		jlPassWord.setBounds(30,90,130,40);
//		jlPassWord.setBorder(new LineBorder(Color.BLACK,3));
		
//		JLabel jlPassWordConfirm ȷ������
		jlPassWordConfirm = new JLabel ("ȷ������",JLabel.RIGHT);
		jlPassWordConfirm.setFont(new Font("΢���ź�",1,24));
		jlPassWordConfirm.setBounds(30,150,130,40);
//		jlPassWordConfirm.setBorder(new LineBorder(Color.BLACK,3));
		
//		JLabel jlHeadImage �޸�ͷ��
		jlHeadImage = new JLabel ("�޸�ͷ��",JLabel.RIGHT);
		jlHeadImage.setFont(new Font("΢���ź�",1,24));
		jlHeadImage.setBounds(30,250,130,40);
//		jlHeadImage.setBorder(new LineBorder(Color.BLACK,3));
		
//		JTextFied jtfName
		jtfName = new JTextField();
		jtfName.setFont(new Font("΢���ź�",1,16));
		jtfName.setBounds(180, 30, 170, 40);
		jtfName.setOpaque(false);
		jtfName.setBorder(new LineBorder(Color.BLACK,3));
		
//		JPasswordField jpfPassWord
		jpfPassword = new JPasswordField();
		jpfPassword.setFont(new Font("΢���ź�",1,16));
		jpfPassword.setOpaque(false);
		jpfPassword.setEchoChar('*');
		jpfPassword.setBounds(180,90,170,40);
		jpfPassword.setBorder(new LineBorder(Color.BLACK,3));
		
//		JPasswordField jpfPasswordConfirm
		jpfPasswordConfirm = new JPasswordField();
		jpfPasswordConfirm.setFont(new Font("΢���ź�",1,16));
		jpfPasswordConfirm.setOpaque(false);
		jpfPasswordConfirm.setEchoChar('*');
		jpfPasswordConfirm.setBounds(180,150,170,40);
		jpfPasswordConfirm.setBorder(new LineBorder(Color.BLACK,3));
		
//		ͷ��������洢, ��Ӧ����String����洢���ǵ�·��
		ImageIcon [] headImage =new ImageIcon [22];
		String [] headImagePath = new String[22];
		for(int i=0;i<22;i++){
			headImagePath [i]="image/headImage/"+i+".png";
			headImage[i]= new ImageIcon(headImagePath[i]);
		}
//		JComboBox jcbHeadImage
		jcbHeadImage = new JComboBox(headImage);
		jcbHeadImage.setBounds(180, 210, 170, 128);
//		jcbHeadImage.setOpaque(false);
		jcbHeadImage.setBorder(null);
		
		jbtOK = new JButton(new ImageIcon("image/ok.gif"));
		jbtOK.setBounds(120, 355, 50, 50);
		jbtOK.setOpaque(false);
		jbtOK.setContentAreaFilled(false);
//		jbtOK.addActionListener(new OKListener());
		jbtOK.setBorder(null);
		
		jbtCancel = new JButton(new ImageIcon("image/Cancel.gif"));
		jbtCancel.setBounds(230, 355, 50, 50);
		jbtCancel.addActionListener(new CancelListener());
		jbtCancel.setOpaque(false);
		jbtCancel.setContentAreaFilled(false);
//		jbtCancel(new OKListener());
		jbtCancel.setBorder(null);

		jlOK = new JLabel("ȷ��");
		jlOK.setFont(new Font("΢���ź�",1,16));
		jlOK.setBounds(130, 405, 50, 30);
		
		jlCancel = new JLabel("ȡ��");
		jlCancel.setFont(new Font("΢���ź�",1,16));
		jlCancel.setBounds(235, 405, 50, 30);
		
		
//		jpMain ��Ӹ�����
		jpMain.add(jlName);
		jpMain.add(jlPassWord);
		jpMain.add(jlPassWordConfirm);
		jpMain.add(jlHeadImage);
		jpMain.add(jtfName);
		jpMain.add(jpfPassword);
		jpMain.add(jpfPasswordConfirm);
		jpMain.add(jcbHeadImage);
		jpMain.add(jbtOK);
		jpMain.add(jbtCancel);
		jpMain.add(jlOK);
		jpMain.add(jlCancel);
		
		this.add(jpMain);
		this.setVisible(true);
		
	}


	
//	OK ��ť ����
	public class OKListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			JOptionPane.showMessageDialog(null, "�޸ĳɹ�");
		}
	}
	
//	Cancel ��ť ����
	public class CancelListener implements ActionListener {
		public void actionPerformed(ActionEvent e){
			dispose();
		}
	}
	
//	����� jpMain 
	class JpMain extends JPanel {
		public JpMain(){
			super();
		}
//		��дpaint������ �� ����
		public void paintComponent (Graphics g){
			g.drawImage(new ImageIcon("image/changeinfobackground.jpg").getImage(), 0, 0	, 400, 480, this);
		}
	}
	
	
	
	
	
	
	
//	���Է���
	public static void main(String [] args){
		new ChangeInfo();
	}
}
