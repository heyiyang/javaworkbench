package userInterface.com;

import javax.swing.*;

import axun.com.*;

import java.awt.*;

import javax.swing.border.LineBorder;
import javax.swing.event.*;

import java.awt.Event;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


public class GameHall extends JFrame {
	
//	**************************************		��Ҫ��������		**********************
//	jpMain
	JPanel jpMain =new JpMain();
	
//	����б� 
	JLabel [] jlUserList= new JLabel [20];
	public ImageIcon [] userHeadImage = new ImageIcon [30];  //ͷ��
	public String [] userNameString = new String[20];		//����
	public int [] userState = new int [20]; //���״̬  8881 ���߿��У� 8882 �����������ȴ��� 8883 ��Ϸ;
	int number=0;
//	��Ϸ������ ͬ����б�
	public String [] userIP =new String [30];
	JScrollPane jspRoom;
	JPanel jpRoom ;
	JPanel [] jpGame = new JPanel[30];
	JButton [] jlHost = new JButton[30];
	JLabel [] jlTable = new JLabel[30];
	JButton [] jlChallenge = new JButton[30];
	public JLabel [] jlUserName=new JLabel[30];
	
	public JButton enterHost[]=new JButton[20];
	
	
//	��ť
	public JButton  jbtNewGame,jbtChangeInfo, jbtHelp, jbtExit ,jbtVSPC;
	public JCheckBox jcbMusic;
	JLabel jlNewGame,jlVSPC, jlChangeInfo,jlHelp,jlExit,jlMusic;
	
//	������Ϣ
	JPanel jpMyInfo;
	JLabel jlMyHead, jlMyinfo;
	JLabel jlID;
	JLabel jlMyName;
	public ImageIcon myHead = new ImageIcon("image/headImage/1.png");
	public String myID="123" , myName="kian";
	public int myWin=9, myLose=9, myGrade=3;
	
//	�����
	JScrollPane jspInput;
	public JTextArea jtaInput;
	public String inputMessage;
	
//	�Ի���
	JScrollPane jspDialog;
	public JTextArea jtaDialog ;

//	*************************************	��Ҫ��������		**************************
	
//	*************************************	������Ա			**************************
//	JSP userList
	JScrollPane jspUserList ;
	List jpUserList = new List();
	
	

//	ȡ ��Ļ��� �߶�
	private int scWidth=Toolkit.getDefaultToolkit().getScreenSize().width;
	private int scHeight=Toolkit.getDefaultToolkit().getScreenSize().height;
	private Color brown = new Color(142,78,30);


//	������LOGO
	Image titleImage=new ImageIcon("image/logo.png").getImage();
	

	
//	************************************	������Ա			******************************
	
//	���췽��
	public GameHall(){
//		Frame ���ô�С���֣�title 
		this.setBounds((scWidth-900)/2,(scHeight-600)/3, 900, 600);
		this.setIconImage(titleImage);
		this.setResizable(false);
		this.setTitle("���������� -- ��Ϸ����");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		//this.setResizable(false);
		
//		jpMain ���ô�С������
		jpMain.setBounds(0, 0, 900, 600);
		jpMain.setLayout(null);

		
//		�û��б�  userList
		//jpUserList.setLayout(new GridLayout(30,0));
		//jpUserList.setOpaque(false);
		/*for(int i=0;i<18;i++){
			jlUserList[i] = new JLabel("���"+i,new ImageIcon("image/headImage/1small.png"),JLabel.CENTER);
//			jlUserList[i]=new JLabel(userHeadImage[1]);
			jpUserList.add(jlUserList[i].toString());
		}*/
		jspUserList = new JScrollPane(jpUserList);
		jspUserList.setBounds(20, 80, 140, 350);
		jspUserList.setOpaque(false);
		jspUserList.setBorder(null);
		jspUserList.getViewport().setOpaque(false);

		
		
		
		
//		������Ϣ
		jpMyInfo = new JPanel();
		jpMyInfo.setBounds(590, 13, 290, 130);
		jpMyInfo.setLayout(null);
		jpMyInfo.setOpaque(false);
		
//		ͷ��
		jlMyHead = new JLabel(myHead);
		jlMyHead.setBounds(0, 0, 128, 128);
//		��Ϣ������ID,�ǳƣ�ʤ�����ȼ�
		JLabel jlMyInfo = new JLabel();
		jlMyInfo.setLayout(new GridLayout(5,1,2,3));
		jlMyInfo.setBounds(130, 10, 155, 110);
//		ID
		jlID = new JLabel("ID:"+myID);
		jlID.setFont(new Font("΢���ź�",1,20));
		jlID.setForeground(Color.BLACK);	
//		�ǳ�     �������壬��ɫ
		jlMyName = new JLabel("�ǳƣ�"+myName);
		jlMyName.setFont(new Font("΢���ź�",1,20));
		jlMyName.setForeground(Color.BLACK);	
//		ʤ��     �������壬��ɫ
		JLabel jlMyWin  = new JLabel("ʤ�֣�"+myWin);
		jlMyWin.setFont(new Font("΢���ź�",1,20));
		jlMyWin.setForeground(Color.BLACK);		
//		����  �������壬��ɫ
		JLabel jlMyLose = new JLabel("���֣�"+myLose);
		jlMyLose.setFont(new Font("΢���ź�",1,20));
		jlMyLose.setForeground(Color.BLACK);
//		�ȼ� �������壬 ��ɫ
		JLabel jlMyGrade = new JLabel("�ȼ���"+myGrade);
		jlMyGrade.setFont(new Font("΢���ź�",1,20));
		jlMyGrade.setForeground(Color.BLACK);
		
		
//		jlMyInfo�����������ID���ǳƣ�ʤ�����ȼ�
		jlMyInfo.add(jlID);
		jlMyInfo.add(jlMyName);
		jlMyInfo.add(jlMyWin);
		jlMyInfo.add(jlMyLose);
		jlMyInfo.add(jlMyGrade);
		
//		jpMyInfo�����������ͷ����Ϣ
		jpMyInfo.add(jlMyHead);
		jpMyInfo.add(jlMyInfo);
		
//		�����
		jtaInput = new JTextArea();
		jtaInput.setOpaque(false);
		jtaInput.setLineWrap(true);							//�Զ�����
		//jtaInput.addKeyListener(new InputListener());		//����������س�������Ϣ
		
		jspInput = new JScrollPane(jtaInput);
		jspInput.setBounds(650, 500, 200, 50);
		jspInput.setOpaque(false);
		jspInput.getViewport().setOpaque(false);
		jspInput.setBorder(null);
		
//		�Ի��� dialog
		jtaDialog = new JTextArea();
		jtaDialog.setLineWrap(true);
		jtaDialog.setOpaque(false);
		jtaDialog.setEditable(false);
		jspDialog =new JScrollPane(jtaDialog);
		jspDialog.setBounds(600,175,270,280);
		jspDialog.setOpaque(false);
		jspDialog.getViewport().setOpaque(false);
		jspDialog.setBorder(null);
		
//		��Ϸ��  jspRoom  ���� jpRoom,  jpRoom �������ɸ�jpGame 
		
		jpRoom = new JPanel();
//		jpRoom.setBounds(180, 80, 400, 1400);
		jpRoom.setPreferredSize(new Dimension(380, 1000));
//		jpRoom.setBackground(Color.WHITE);
		jpRoom.setOpaque(false);
		jpRoom.setLayout(null);
		jspRoom = new JScrollPane(jpRoom);				
		jspRoom.setBounds(165, 80, 410, 400);
		jspRoom.setOpaque(false);
		jspRoom.getViewport().setOpaque(false);
		jspRoom.setBorder(new LineBorder(Color.WHITE,3));

		
		
		
//		���ø���game, ˫��ͷ�������� �����Ǽ���
		for(int i=0;i<20;i++){
			jpGame[i]= new JPanel();
			jpGame[i].setBounds(10+(i%2)*190, 20+(i/2)*80, 200, 80);			//����λ��
			jpGame[i].setLayout(null);
			jpGame[i].setOpaque(false);
			
			
			jlHost[i]= new JButton(new ImageIcon("image/headImage/1small.png"));			//������ҵ�ͷ��button
			jlHost[i].setBounds(0, 0, 50, 50);
			jlHost[i].setBorder(null);
			jlHost[i].setOpaque(false);
			jlHost[i].setContentAreaFilled(false);
			jlHost[i].addActionListener(new HostLinstener());
			
			jlTable [i] = new JLabel(new ImageIcon("image/tablesmall.png"));				//����ͼƬ�� label
			jlTable[i].setBounds(60, 0, 50, 50);
			jlTable[i].setBorder(null);
			jlTable[i].setOpaque(false);
			
			
			enterHost[i]=new JButton(new ImageIcon("image/chair.png"));
			enterHost[i].setBounds(120, 0, 50, 50);
			enterHost[i].setOpaque(false);
			enterHost[i].setContentAreaFilled(false);
//			enterHost[i].setBorder(null);
//			enterHost[i].setEnabled(false);
			
//			jlChallenge[i] = new JButton(new ImageIcon("image/headImage/2small.png"));		//����ͷ���˻���Ի����
//			jlChallenge[i].setBounds(120, 0, 50, 50);
//			jlChallenge[i].setBorder(null);
//			jlChallenge[i].setOpaque(false);
//			jlChallenge[i].setContentAreaFilled(false);
			//jlChallenge[i].addActionListener(new ChallengeListener());
			
			jlUserName[i] = new JLabel("û������");
			jlUserName[i].setBounds(10, 50, 100, 20);
			
			
			jpGame[i].add(jlHost[i]);
			jpGame[i].add(jlTable[i]);
			jpGame[i].add(enterHost[i]);
			jpGame[i].add(jlUserName[i]);
			
			jpRoom.add(jpGame[i]);
		
			
			
//				jpGame[i]= new JPanel();
//				
//				enterHost[i]=new JButton("û��������");
//				enterHost[i].setEnabled(false);
//				jpGame[i].add(enterHost[i]);
//				
//				jpRoom.add(jpGame[i]);
			
		}
		
//		������ť
//		�½���Ϸ jbtNewGame
		jbtNewGame = new JButton (new ImageIcon("image/newgame.gif"));
		jbtNewGame.setBounds(175,  490, 50,50);
		jbtNewGame.setOpaque(false);
		jbtNewGame.setBorder(null);
		jbtNewGame.setContentAreaFilled(false);
		//jbtNewGame.addActionListener(new NewGameListener());
		jbtNewGame.setToolTipText("����ң��½���Ϸ");
		
//		�½���Ϸ��ǩ
		jlNewGame = new JLabel("�½���Ϸ",JLabel.CENTER);
		jlNewGame.setFont(new Font("΢���ź�",1,14));
		jlNewGame.setBounds(180, 540, 60, 30);
		
		
		
//		�����
		jbtVSPC = new JButton(new ImageIcon("image/vspc.gif"));
		jbtVSPC.setBounds(245, 490, 50, 50);
		jbtVSPC.setOpaque(false);
		jbtVSPC.setBorder(null);
		jbtVSPC.setContentAreaFilled(false);
		
//		����Ա�ǩ
		jlVSPC = new JLabel("�����",JLabel.CENTER);
		jlVSPC.setFont(new Font("΢���ź�",1,14));
		jlVSPC.setBounds(245, 540, 60, 30);
		
		
//		�޸ĸ�����Ϣ��jbtChangeInfo
		jbtChangeInfo = new JButton (new ImageIcon("image/changeinfo.gif"));
		jbtChangeInfo.setBounds(315	, 490, 50,50);
		jbtChangeInfo.setOpaque(false);
		jbtChangeInfo.setBorder(null);
		jbtChangeInfo.setContentAreaFilled(false);
		//jbtChangeInfo.addActionListener(new ChangeInfoListener());
		jbtChangeInfo.setToolTipText("����ң��޸ĸ�����Ϣ");
		
//		�޸ĸ�����Ϣ��ǩ
		jlChangeInfo = new JLabel("�޸���Ϣ",JLabel.CENTER);
		jlChangeInfo.setBounds(310, 540, 60, 30);
		jlChangeInfo.setFont(new Font("΢���ź�",1,14));
		
//		������Ϣ��jbtHelp
		jbtHelp = new JButton (new ImageIcon("image/help.gif"));
		jbtHelp.setBounds(380,  490, 50,50);
		jbtHelp.setOpaque(false);
		jbtHelp.setBorder(null);
		jbtHelp.setContentAreaFilled(false);
		jbtHelp.addActionListener(new HelpListener());
		jbtHelp.setToolTipText("����ң��鿴������Ϣ");
		
//		������Ϣ��ǩ
		jlHelp = new JLabel("������Ϣ",JLabel.CENTER);
		jlHelp.setBounds(375, 540, 60, 30);
		jlHelp.setFont(new Font("΢���ź�",1,14));
		
//		�˳���jbtExit
		jbtExit = new JButton (new ImageIcon("image/exit.gif"));
		jbtExit.setBounds(450,  490, 50,50);
		jbtExit.setOpaque(false);
		jbtExit.setBorder(null);
		jbtExit.setContentAreaFilled(false);
		jbtExit.addActionListener(new ExitListener());
		jbtExit.setToolTipText("������,�˳���Ϸ");
		
//		�˳���ǩ
		jlExit = new JLabel("��    ��",JLabel.LEFT);
		jlExit.setBounds(455, 540, 60, 30);
		jlExit.setFont(new Font("΢���ź�",1,14));
		
//		���ֿ��أ� jcbMusic
//		jcbMusic = new JCheckBox("���ֿ���", new ImageIcon("image/music.gif"), false);
		jcbMusic = new JCheckBox(new ImageIcon("image/music.gif"));
		jcbMusic.setBounds(520, 490, 100, 50);
		jcbMusic.setBorder(new LineBorder(Color.WHITE,1));
		jcbMusic.setOpaque(false);
		jcbMusic.addActionListener(new MusicListener());

//		���ֱ�ǩ
		jlMusic = new JLabel("��������",JLabel.CENTER);
		jlMusic.setBounds(505, 540, 100, 30);
		jlMusic.setFont(new Font("΢���ź�",1,14));
		
		
//		jpMain ��Ӹ�������  �û��б�������Ϣ,�Ի�������� ��Ϸ��
		jpMain.add(jspUserList);
		jpMain.add(jpMyInfo);
		jpMain.add(jspInput);
		jpMain.add(jspDialog);
		jpMain.add(jspRoom);
		jpMain.add(jbtNewGame);
		jpMain.add(jbtChangeInfo);
		jpMain.add(jbtHelp);
		jpMain.add(jbtExit);
		jpMain.add(jcbMusic);
		jpMain.add(jbtVSPC);
		jpMain.add(jlNewGame);
		jpMain.add(jlVSPC);
		jpMain.add(jlHelp);
		jpMain.add(jlChangeInfo);
		jpMain.add(jlExit);
		jpMain.add(jlMusic);
		
		this.add(jpMain);
		this.setVisible(true);
		
		
		
		//*****************************************************************
		jbtChangeInfo.addActionListener(new JbtChangeInfoListener());
		jbtHelp.addActionListener(new JbtHelpListener());
	}
	
	
	
//	���Է���
	public static void main(String[] args) {
		
		GameHall gh=new GameHall();
		
		
	}
	
//	********************************		����			******************************
//	��Ϸ���ڣ�������ߣ�������ң�ͷ��ļ���
	public class HostLinstener implements ActionListener {
		public void actionPerformed (ActionEvent e){
			JOptionPane.showMessageDialog(null, "������Ϣ");
		}
	}
	
//	��Ϸ���ڣ������ұߣ���ս��� ���ߵ��� ���� ���У�ͷ��ļ���
	public void challengeListener() {
		
	}
	
//	����� jtaInput �ļ�����  ���س�������Ϣ
	public void inputListener(KeyAdapter k) {
		
		jtaInput.addKeyListener(k);
	}
	
//	��ť �½���Ϸ jbtNewGame �ļ����� ����½���Ϸ
	public void jbtNewGameListener(ActionListener listener){
		
		jbtNewGame.addActionListener(listener);
	}

//	��ť �޸ĸ�����Ϣ ���� jbtChangeInfo
	public class ChangeInfoListener implements ActionListener {
		public void actionPerformed (ActionEvent e){
			
		}
	}
//	��ť ���� ���� jbtHelp
	
	public class HelpListener implements ActionListener {
		public void actionPerformed (ActionEvent e){
			
		}
	}
//	��ť �˳� ���� jbtExit
	public class ExitListener implements ActionListener {
		public void actionPerformed (ActionEvent e ){
			System.exit(0);
		}
	}
	
//	ѡ��ť ���ֿ��ؼ��� jcbMusic
	public class MusicListener implements ActionListener {
		public void actionPerformed (ActionEvent e){
			JOptionPane.showMessageDialog(null, "���ֹر�");
		}
	}
	
//	******************************			����			********************************
	
	
	
	
	
//	****************************** 			����			*******************************
	//�����û��б�
	public void addClient(String name,String ip,int state){
		this.userNameString[number]=name;
		this.userIP[number]=ip;
		this.userState[number]=state;
		
		jlUserList[number]=new JLabel("1211111111");
		jlUserList[number].setText(name+"("+state+")");
		if(state==8881){
			jpUserList.add(name+"(����)");
		}
		if(state==8882){
			jpUserList.add(name+"(����)");
		}
		if(state==8883){
			jpUserList.add(name+"(������Ϸ)");
		}
		if(state==8884){
			jpUserList.add(name+"(�˻���ս)");
		}
		
		jpGame[number]=new JPanel();
		jlChallenge[number]=new JButton("��������");
		//jlUserName[number].setText(name);
		
		/*if(state==8882){
			jtaDialog.append("������"+name+ip+state+"\n");
			
			
			//�������
			jpGame[number]= new JPanel();
			jpGame[number].setBounds(10+(number%2)*180, 20+(number/2)*50, 200, 80);			//����λ��
			jpGame[number].setLayout(null);
			
			
			jlHost[number]= new JButton(new ImageIcon("image/headImage/1small.png"));			//������ҵ�ͷ��button
			jlHost[number].setBounds(0, 0, 50, 50);
			jlHost[number].setBorder(null);
			jlHost[number].setOpaque(false);
			jlHost[number].setContentAreaFilled(false);
			jlHost[number].addActionListener(new HostLinstener());
			
			jlTable [number] = new JLabel(new ImageIcon("image/tablesmall.png"));				//����ͼƬ�� label
			jlTable[number].setBounds(70, 0, 50, 50);
			jlTable[number].setBorder(null);
			jlTable[number].setOpaque(false);
			
			
			jlChallenge[number] = new JButton(new ImageIcon("image/headImage/2small.png"));		//����ͷ���˻���Ի����
			jlChallenge[number].setBorder(null);
			jlChallenge[number].setBounds(140, 0, 50, 50);
			jlChallenge[number].setOpaque(false);
			jlChallenge[number].setContentAreaFilled(false);
			//jlChallenge[i].addActionListener(new ChallengeListener());
			
			jlUserName[number] = new JLabel(name);
			jlUserName[number].setBounds(20,55,50,25);
			
			
			jpGame[number].add(jlHost[number]);
			jpGame[number].add(jlTable[number]);
			jpGame[number].add(jlChallenge[number]);
			jpGame[number].add(jlUserName[number]);
			
			jpRoom.add(jpGame[number]);
			this.repaint();
			
			
			
		}
		*/
		//jtaDialog.append("�յ��û���"+name+ip+state+"\n");
		
		
		number++;
	}
	
	//�û��б��ݽ���
	public void endAddClient(){
		//��ʼ����Ϸ������ȫ�ǿ�����
		
		/*for(int i=0;i<18-number;i++){
		jpGame[i]= new JPanel();
		jpGame[i].setBounds(10+(i%2)*180, 20+(i/2)*50, 200, 50);			//����λ��
		
		
		jlHost[i]= new JButton(new ImageIcon("image/headImage/1small.png"));			//������ҵ�ͷ��button
		jlHost[i].setBorder(null);
		jlHost[i].setOpaque(false);
		jlHost[i].setContentAreaFilled(false);
		jlHost[i].addActionListener(new HostLinstener());
		
		jlTable [i] = new JLabel(new ImageIcon("image/tablesmall.png"));				//����ͼƬ�� label
		jlTable[i].setBorder(null);
		jlTable[i].setOpaque(false);
		
		
		jlChallenge[i] = new JButton(new ImageIcon("image/headImage/2small.png"));		//����ͷ���˻���Ի����
		jlChallenge[i].setBorder(null);
		jlChallenge[i].setOpaque(false);
		jlChallenge[i].setContentAreaFilled(false);
		//jlChallenge[i].addActionListener(new ChallengeListener());
		
		jpGame[i].add(jlHost[i]);
		jpGame[i].add(jlTable[i]);
		jpGame[i].add(jlChallenge[i]);
		
		jpRoom.add(jpGame[i]);
		repaint();
	}*/
	}
	
	//����û��б�
	public void clearClient(){
		number=0;
		jpUserList.removeAll();
		for(int i=0;i<20;i++){
			jlUserList[i]=null;
		}
		//jtaDialog.append("�������б�\n");
		//jtaDialog.append("�����Ϸ�����б�\n");
		//jpRoom.removeAll();
		for(int i=0;i<20;i++){
			//enterHost[i].setText("û������");
			jlUserName[i].setText("û������");
			//enterHost[i].
			enterHost[i].setEnabled(false);
			enterHost[i].addActionListener(new ActionListener(){

				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					
				}
				
			});
		}
		
	}
	
	
	
//	��ʼ���û��б��ͷ�����飬ȫ�����
	public void clearUserHeadImage() {
		
	}
	
//	�������б��ͷ������
	public void addUserHeadImage(ImageIcon arr[]){
		
	}
	
//	�޸ĸ�����Ϣ ��ID
	public void setID(String newID) {
		this.myID = newID;
		jlID.setText("ID:"+newID);
	}
	
//  �޸ĸ�����Ϣ ���ǳ�
	public void setName(String newName) {
		this.myName = newName;
		jlMyName.setText("Name:"+newName);
	}
	
//	ʤ�ּ�һ
	public void winGame (){
		this.myWin ++;
	}
	
//	���ּ�һ
	public void loseGame (){
		this.myLose --;
	}
	
//	����
	public void gradeUp(){
		this.myGrade++; 
	}
	
//	����
	public void gradeDown(){
		this.myGrade--;
	}
	//***************************����*******************************************
	class JbtChangeInfoListener implements ActionListener{

		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			new ChangeInfo();
		}
		
	}
	
	class JbtHelpListener implements ActionListener{

		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			new Help();
		}
		
	}
	
//	*****************************			���� 		********************************
}


//	jpMain
class JpMain extends JPanel	{
	
	public JpMain (){
		super();
	}
	
//	*****************  ����� ����  ************************
//	jpMain����дpaint������ ��������ͼ
	public void paintComponent(Graphics g){
		g.drawImage(new ImageIcon("image/background4.jpg").getImage(), 0, 0, 900,580, this);
	}
//	*****************************************************
	
}


