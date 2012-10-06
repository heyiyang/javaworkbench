package axun.com;


import Compute.*;

import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Time;


import javax.swing.*;
import componets.*;





public class Chess extends JPanel{
	
	

	
	//GUI
	public JFrame f=new JFrame("������");
	private JPanel pright=new JPanel();
	private JPanel pleft=new JPanel();
	private JPanel p1=new JPanel();
	private JPanel p2=new JPanel();
	private JPanel p3=new JPanel();
	private JButton startbt=new JButton("��ʼ��Ϸ");
	private JButton restartbt=new JButton("���¿�ʼ");
	private JButton setbt=new JButton("����");
	private JButton undobt=new JButton("����");
	private JButton exitbt=new JButton("�˳���Ϸ");
	private JButton hhbt=new JButton("˫�˶�ս");
	private JButton netbt=new JButton("��������");
	private JButton netbt2=new JButton("������Ϸ");
	private JButton hcbt=new JButton("��ͨ����");
	
	private JButton hcbt2=new JButton("������");
	private JButton endbt=new JButton("�����˾�");
	private JLabel lb1=new JLabel("�����¼��");
	private JLabel timelb=new JLabel("00:00:00");			//��ʱ��ǩ
	private JTextArea tr1=new JTextArea();
	private JLabel myIdlb=new JLabel("����");
	private JLabel rivalIdlb=new JLabel("����");
	
	//�����õ�
	private JTextArea chatbox=new JTextArea();
	private JTextArea chatinputbox=new JTextArea();
	private JButton chatbt=new JButton("����");
	
	
//	*********************���ӵ� ************************
//	ȡ ��Ļ  ��� �߶�
	private int scWidth=Toolkit.getDefaultToolkit().getScreenSize().width;
	private int scHeight=Toolkit.getDefaultToolkit().getScreenSize().height;

	// JPanel jpMain 
	JPanel jpMain ;
	
	JScrollPane jspchatinputbox , jspchatbox , jsptr1;
	
	ImageIcon icon1,icon2,icon3,icon4;			// ���ͷ��, ����
	JLabel jlPlayer1,jlPlayer2,jlPlayer1Disk,jlPlayer2Disk; 		// ��ʾ���ͷ����ִ����
	
	JLabel jlNormal, jlHappy,jlAngry,jlSad, jlGameStart, jlEasyPC,jlCrazyPC,jlExit ,jlRegret;
//**************************************************************
	
	
	//�����õ��߳�
	
	
	//�����͵ı���
	
	//״̬��¼
	private boolean isStart=false;		//�Ƿ��Ѿ���ʼ��Ϸ
	public boolean lock=false;				//�Ƿ��������̣�����֮��������
	private int computer=0;						//���Եȼ���0��ʾ˫�˶�ս��123�ֱ��ʾ�Ѷ�
	private boolean net=false;						//net��ʾ�Ƿ��������ս
	private boolean undo=true;				//�Ƿ��������
	private boolean wait=false;				//�Ƿ����ڵȴ��Է�����
	private int mode=2;						//�Ƿ��Ѿ�ѡ����һ��ģʽ
	int state=0;				//��ǰ״̬
	String  rivalId=null;
	String myId=null;
	int compControl=0;			//���ư�ť��enable����
	public int undoNumber=100;			//���ƻ���Ĵ���
	
	
	private String chessboardMessage=null;	//�������ϴ�ӡ��Ϣ
	int[][] five;							//һ��5*5��С����
	private int last_x=-1,last_y=-1;

	private int[][] x;
	private int[][] record;	//�����¼
	private int step;		//
	private int player;
	private int time;		//��ʱ ����ʾ������
	
	private TimeRunner timeR=new TimeRunner();	//
	private Thread timeThread=new Thread(timeR);	//��ʱ�õ��߳�
	int i=0;
	
	//�����õ�
	
	boolean server=false;	  //�Ƿ�������
	String ip="localhost";				//ip��ַ
	final int port=12377;				//�˿ں�
	final int port2=12388;				//�����õĶ˿ں�
	private final int ON_LINE=6661;	//���͸�����������ʾ�Լ�����
	private final int ON_GAME=6662;	//���͸������������Լ�������Ϸ��
	private final int ON_WAIT=6662;	//���͸������������Լ��������������ڵȴ�
	
	NetTrans netTrans=null;
	NetChat netChat=null;
	Thread netTransThread=null;
	Thread netChatThread=null;
	
	
	
	//����AI�õ�
	private AutoPlay ap=new AutoPlay();
	
	//����
	private final int NET_BEGIN=300;	//��ʾ��ʼ��Ϸ
	private final int NET_END=301;	//��ʾ������Ϸ
	
	//��ʱ����
	Point point=null;
	TextArea inputTextArea=null;
	private String inputString=null;		//�ݴ�inputdialog�е�����
	

	
	
	public Chess(){
		
		
//		*********************************************************************************
//		f.setLayout(new BorderLayout());
//		f.setSize(800,600);
//		
//		f.setLocation(300, 160);
//		f.setResizable(false);			//���ɸı��С
//		
//		f.add(pleft,"West");
//		pleft.setLayout(new GridLayout(10,1));
//		//pleft.add(netbt);
//		//pleft.add(netbt2);
//		//pleft.add(hhbt);
//		pleft.add(hcbt);
//		pleft.add(setbt);
//		//pleft.add(exitbt);
//		pleft.add(myIdlb);
//		pleft.add(rivalIdlb);
//		
//		hhbt.addActionListener(new hhbtListener());				
//		hcbt.addActionListener(new hcbtListener());			//�˻���ս 	OK
//		netbt.addActionListener(new netbtListener());
//		netbt2.addActionListener(new netbt2Listener());
//		
//		
//		this.addMouseListener(new mousel());	//ע�����
//		setSize(500,500);
//		f.add(this,"Center");
//		this.setBackground(Color.green);
//		
//		startbt.addActionListener(new StartbtListener());		// ��ʼ�� OK
//		
//		restartbt.addActionListener(new RestartbtListener());	//���¿�ʼ�� OK
//		
//		undobt.addActionListener(new UndobtListener());			// ���� �� OK
//		endbt.addActionListener(new EndbtListener());			//�˳�  OK
//		
//		pright.setLayout(new GridLayout(3,1));
//		pright.setBackground(Color.yellow);
//		f.add(pright,"East");
//		pright.add(p1);
//		pright.add(p2);
//		pright.add(p3);
//		
//		tr1.setEnabled(true);
//		tr1.setBackground(Color.LIGHT_GRAY);
//		tr1.setRows(10);
//		tr1.setColumns(20);
//		tr1.setEditable(false);
//		
//		p1.setLayout(new GridLayout(6,1,0,10));
//		p1.add(startbt);
//		p1.add(restartbt);
//		p1.add(undobt);
//		p1.add(endbt);
//		
//		
//		p1.add(timelb);
//		p1.add(lb1);
//		p2.add(tr1);
//		
//		chatbox.setRows(10);
//		chatbox.setColumns(20);
//		
//		chatinputbox.setRows(1);
//		chatinputbox.setColumns(10);
//		chatbox.setEditable(false);
//		p3.setLayout(new GridLayout(3,1));
//		p3.add(chatbox);
//		p3.add(chatinputbox);
//		p3.add(chatbt);
////		chatbt.addActionListener(new chatbtListener());		// ���� ��ť   ��Ϊinput ������ keyListener  OK
//		
//		
//		f.setVisible(true);			//ע�����������
//		
		
		//**********************************����*********************************************
		
		f.setBounds((scWidth-800)/2, (scHeight-600)/3, 800,600);
		f.setResizable(false);
		f.setIconImage(new ImageIcon("image/logo.png").getImage());
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setTitle("��������������");
		
		jpMain = new JpMain() ;
		jpMain.setBounds(0, 0, 800, 600);
		jpMain.setLayout(null);
		
//		��ʼ��Ϸ��ť
		startbt = new JButton (new ImageIcon("image/startgame.gif"));
		startbt.setBounds(30, 70, 45, 45);
//		startbt.setBorder(new LineBorder(Color.BLACK,3));
		startbt.setOpaque(false);
		startbt.setContentAreaFilled(false);
		startbt.setBorder(null);
		startbt.addActionListener(new StartbtListener());
		
		
//		��ʼ��Ϸ��ǩ
		jlGameStart = new JLabel("��ʼ��Ϸ");
		jlGameStart.setBounds(20, 115, 100, 40);
		jlGameStart.setFont(new Font("΢���ź�",1,14));
		jlGameStart.setOpaque(false);
		
		
//		�˻���ս ��ť jbtVsPC
		hcbt = new JButton(new ImageIcon("image/easyPC.gif"));
		hcbt.setOpaque(false);
		hcbt.setContentAreaFilled(false);
		hcbt.setBorder(null);
		hcbt.setBounds(30, 165, 45, 45);
//		hcbt.setBorder(new LineBorder(Color.BLACK,3));
		hcbt.addActionListener(new hcbtListener());
		
//		�򵥵��Ա�ǩ
		jlEasyPC = new JLabel("�򵥵���");
		jlEasyPC.setBounds(20, 210, 100, 40);
		jlEasyPC.setFont(new Font("΢���ź�",1,14));
		jlEasyPC.setOpaque(false);
		
//		������ 
		hcbt2 = new JButton(new ImageIcon("image/crazyPC.gif"));
		hcbt2.setBounds(30, 250, 45, 45);
		hcbt2.setOpaque(false);
		hcbt2.setContentAreaFilled(false);
		hcbt2.setBorder(null);
//		hcbt2.setBorder(new LineBorder(Color.BLACK,3));
		hcbt2.addActionListener(new hcbt2Listener());
		
//		������ ��ǩ
		jlCrazyPC = new JLabel("������");
		jlCrazyPC.setBounds(20, 295, 100, 40);
		jlCrazyPC.setFont(new Font("΢���ź�",1,14));
		jlCrazyPC.setOpaque(false);
		
//		���� ��ť jbtRegret
		undobt = new JButton(new ImageIcon("image/regret.gif"));
		undobt.setBounds(30, 345, 45, 45);
		undobt.setBorder(null);
		undobt.setOpaque(false);
		undobt.setContentAreaFilled(false);
//		undobt.setBorder(new LineBorder(Color.BLACK,3));
		undobt.addActionListener(new UndobtListener());
		
//		�����ǩ
		jlRegret = new JLabel("���壨3��");
		jlRegret.setBounds(20, 390, 100, 40);
		jlRegret.setFont(new Font("΢���ź�",1,14));
		jlRegret.setOpaque(false);
		
		
		
//		
////		���¿�ʼ ��ť jbtRestart
//		restartbt = new JButton("���¿�ʼ");
//		restartbt.setBounds(30, 295, 45, 45);
//		restartbt.setBorder(new LineBorder(Color.BLACK,3));
//		restartbt.addActionListener(new RestartbtListener());
		
//		�˳���Ϸ ��ť JbtExit
		endbt = new JButton(new ImageIcon("image/exit.gif"));
		endbt.setBounds(30, 430, 45, 45);
		endbt.setOpaque(false);
		endbt.setContentAreaFilled(false);
		endbt.setBorder(null);
//		endbt.setBorder(new LineBorder(Color.BLACK,3));
		endbt.addActionListener(new EndbtListener());
		
//		�˳���Ϸ��ǩ
		jlExit = new JLabel("�˳���Ϸ");
		jlExit.setBounds(20, 475, 100, 40);
		jlExit.setFont(new Font("΢���ź�",1,14));
		jlExit.setOpaque(false);
		
//		JscrollPane �����¼�� ����JTextArea jtaRecord

		tr1.setLineWrap(true);
		tr1.setOpaque(false);
		tr1.setFont(new Font("΢���ź�",1,16));
//		tr1.setEditable(false);
		jsptr1 = new JScrollPane(tr1);
		jsptr1.setBounds(570,20,200,100);
//		jsptr1.setBorder(new LineBorder(Color.BLACK,3));
		jsptr1.setBorder(null);
		jsptr1.setOpaque(false);
		jsptr1.getViewport().setOpaque(false);
		
//		JScrollPane �Ի��򣬰��� JTextArea jtaDialog
		
		chatbox.setLineWrap(true);
		chatbox.setOpaque(false);
		chatbox.setFont(new Font("΢���ź�",1,16));
		chatbox.setEditable(false);
		jspchatbox = new JScrollPane(chatbox);
		jspchatbox.setBounds(570, 140, 200, 300);
//		jspchatbox.setBorder(new LineBorder(Color.BLACK,3));
		jspchatbox.setBorder(null);
		jspchatbox.setOpaque(false);
		jspchatbox.getViewport().setOpaque(false);	
		
//		JTextArea �����  ����JTextArea jtaInput
		
		chatinputbox.setLineWrap(true);
		chatinputbox.setOpaque(false);
		chatinputbox.setFont(new Font("΢���ź�",1,16));
		chatinputbox.addKeyListener(new InputListener());
		jspchatinputbox = new JScrollPane(chatinputbox);
		jspchatinputbox.setBounds(570, 460, 200, 78);
//		jspchatinputbox.setBorder(new LineBorder(Color.WHITE,3));
		jspchatinputbox.setBorder(null);
		jspchatinputbox.setOpaque(false);
		jspchatinputbox.getViewport().setOpaque(false);
		
//		���1ͷ�񣬹̶�������ʾ���ǽ�����Ϸ�����  icon1�������1ͷ��ͼƬ������·������ʵ���ж�
		icon1 = new ImageIcon("image/headImage/1small.png");
		jlPlayer1 = new JLabel(icon1);
		jlPlayer1.setBounds(200, 500, 45, 45);
		
//		���2ͷ�񣬹̶����ұ���ʾ���Ǽ�����Ϸ����һ��ߵ���   icon2�������2ͷ��ͼƬ������·������ʵ���ж�
		icon2 = new ImageIcon("image/headImage/3small.png");
		jlPlayer2 = new JLabel(icon2);
		jlPlayer2.setBounds(400, 500, 45, 45);
		
//		���1���ѣ���ɫ����ʵ������жϣ� icon3�������1���ӣ�����·������ʵ���ж�
		icon3 = new ImageIcon("image/whiteDisk.gif");
		jlPlayer1Disk = new JLabel(icon3);
		jlPlayer1Disk.setBounds(250, 500, 45, 45);
		
//		���2���ѣ���ɫ����ʵ������жϣ� icon4�������2���ӣ�����·������ʵ���ж�
		icon4 = new ImageIcon("image/blackDisk.gif");
		jlPlayer2Disk = new JLabel(icon4);
		jlPlayer2Disk.setBounds(350, 500, 45, 45);		
		
//		����
		this.addMouseListener(new mousel());	//ע�����
//		setSize(500,500);
//		f.add(this,"Center");
		this.setBounds(90,35,470,470);
	
		
//		this.setBackground(Color.green);
		
		
//		ʱ��
		timelb.setBounds(298, 510, 100, 30);
//		timelb.setBorder(new LineBorder(Color.BLACK,3));
		
		jlNormal = new JLabel(new ImageIcon("image/normal.gif"));
		jlNormal.setBounds(220, 10, 50, 50);
		jlNormal.setOpaque(false);
//		jlNormal.setVisible(false);
		
		jlHappy = new JLabel(new ImageIcon("image/happy.gif"));
		jlHappy.setBounds(220, 10, 50, 50);
		jlHappy.setOpaque(false);
		jlHappy.setVisible(false);
		
		jlAngry = new JLabel(new ImageIcon("image/angry.gif"));
		jlAngry.setBounds(220, 10, 50, 50);
		jlAngry.setOpaque(false);
		jlAngry.setVisible(false);
		
		jlSad = new JLabel(new ImageIcon("image/sad.gif"));
		jlSad.setBounds(220, 10, 50, 50);
		jlSad.setOpaque(false);
		jlSad.setVisible(false);
		
		jpMain.add(startbt);
		jpMain.add(hcbt);
		jpMain.add(undobt);
//		jpMain.add(restartbt);
		jpMain.add(hcbt2);
		jpMain.add(endbt);
		jpMain.add(jsptr1);
		jpMain.add(jspchatbox);
		jpMain.add(jspchatinputbox);
		jpMain.add(jlPlayer1);
		jpMain.add(jlPlayer2);
		jpMain.add(jlPlayer1Disk);
		jpMain.add(jlPlayer2Disk);
		jpMain.add(this);
		jpMain.add(timelb);
		jpMain.add(jlGameStart);
		jpMain.add(jlEasyPC);
		jpMain.add(jlCrazyPC);
		jpMain.add(jlRegret);
		jpMain.add(jlExit);
		jpMain.add(jlNormal);
		jpMain.add(jlHappy);
		jpMain.add(jlSad);
		jpMain.add(jlAngry);
		
		
		f.add(jpMain);
		
		jpMain.addMouseListener(new XYListener());
		f.setVisible(true);
		//********************************************************************************
		
		reset();
	}
	
	class XYListener extends MouseAdapter {
		public void mousePressed (MouseEvent e){
			System.out.println(e.getX()+"           +              "+e.getY());
		}
	}
	
//	JpMain ��class,  ��дpaint ������ ���챳��
	class JpMain extends JPanel {
		public JpMain(){
			super();
		}
		public void paintComponent (Graphics g){
			g.drawImage(new ImageIcon("image/��Ϸ��������2.jpg").getImage(), 0, 0, 800, 600, this);
		}
	}
	
	
	public void reset(){
		
		
		
		player=1;				//1��ʾ����
		x=new int[15][15];		//��¼����
		
		for(int i=0;i<15;i++){
			for(int j=0;j<15;j++){
				x[i][j]=0;		//��ʼ����0��ʾ����,1��ʾ���ӣ�2��ʾ����
				
				
			}
		}
		
		record=new int[255][3];
		for(int i=0;i<255;i++)
			for(int j=0;j<3;j++){
				record[i][j]=-1;	//-1��ʾ��û����
			}
		
		//5*5 С���̳�ʼ��
		five=new int[5][5];
		for(int i=0;i<5;i++)
			for(int j=0;j<5;j++){
				five[i][j]=0;
			}
		
		step=0;		
		tr1.setText("��δ��ʼ");
		time=57600;
		chessboardMessage="null";	//null��ʾû����Ϣ
		net=false;
		server=true;
		ip="localhost";
		
		
		lock=true;
		
		//״̬��¼
		isStart=false;		//�Ƿ��Ѿ���ʼ��Ϸ
		computer=0;						//���Եȼ���0��ʾ˫�˶�ս��123�ֱ��ʾ�Ѷ�
		net=false;						//net��ʾ�Ƿ��������ս
		undo=true;				//�Ƿ��������
		wait=false;				//�Ƿ����ڵȴ��Է�����
		mode=0;						//�Ƿ��Ѿ�ѡ����һ��ģʽ
		state=8881;
		
		//selectMode(4);
		stateChange();
	}
	
	public void createNetCon(){//�����߳�
		netTransThread=new Thread(new NetTransThread(this));
		netChatThread=new Thread(new NetChatThread(this));
		
		
	}
	
	//�����¼
	public void showstep(){
		tr1.setText("");//�����
		if(step==0) {
			tr1.setText("�����ӣ�");
			return;
		}
		String p=null;
		//װ�����岽��
		for(int i=0;i<step;i++){
			if(record[i][2]==1) p="�ڷ�";
			else p="�׷�";
			tr1.append("\n"+i+":"+p+"("+record[i][0]+","+record[i][1]+")");
		}
	}
	
	//����,ע��һ�λ�������
	public void undo(){
		if(undoNumber<=0) {
			undobt.setEnabled(false);	//������
			return;		//�������
		}
		
		if(mode==1||mode==2){
			sendNetStep(302);		//���ͻ�������
			new ConfirmDialog("���������ѷ��ͣ��ȴ��Է�ͬ�⣡",f);
			return;
		}
		
		if(step==0) {
			undobt.setEnabled(false);
			return;
		}
		x[record[step-1][0]][record[step-1][1]]=0;
		step--;
		if(step==0) {
			undobt.setEnabled(false);
			return;
		}
		x[record[step-1][0]][record[step-1][1]]=0;
		step--;
		
		undoNumber--;
		repaint();
	}
	
	//�������յ����ֵ�ͬ���������
	public void netUndo(){
		if(step==0) {
			undobt.setEnabled(false);
			return;
		}
		x[record[step-1][0]][record[step-1][1]]=0;
		step--;
		if(step==0) {
			undobt.setEnabled(false);
			return;
		}
		x[record[step-1][0]][record[step-1][1]]=0;
		undoNumber--;
		step--;
		repaint();
	}
	
	//
	public void netUndoServer(){
		if(step==0) {
			undobt.setEnabled(false);
			return;
		}
		x[record[step-1][0]][record[step-1][1]]=0;
		step--;
		if(step==0) {
			undobt.setEnabled(false);
			return;
		}
		x[record[step-1][0]][record[step-1][1]]=0;
		step--;
		repaint();
	}
	
	//�յ����ֻ�������
	public void netUndoRequest(){
		//����
		class ConfirmActionListener implements ActionListener{
			private Dialog d=null;
			public ConfirmActionListener(Dialog d){
				this.d=d;
			}

			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				sendNetStep(303);				//303��ʾͬ��
				netUndoServer();
				d.dispose();
			}
			
		}
		class CancelActionListener implements ActionListener{
			private Dialog d=null;
			public CancelActionListener(Dialog d){
				this.d=d;
			}

			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				sendNetStep(304);				//303��ʾͬ��
				d.dispose();
			}
			
		}
		
		Dialog dialog=new Dialog(f);
		dialog.setTitle("����������");
		dialog.setModal(true);
		dialog.setLayout(new GridLayout(3,1));
		dialog.setLocation(400, 250);
		
		
		JLabel lb1=new JLabel("�Է�������壬�Ƿ�ͬ�⣿");
		dialog.add(lb1);
		
		JButton bt1=new JButton("ͬ��");
		bt1.addActionListener(new ConfirmActionListener(dialog));
		JButton bt2=new JButton("��ͬ��");
		bt2.addActionListener(new CancelActionListener(dialog));
		dialog.add(bt1);
		dialog.add(bt2);
		dialog.pack();
		dialog.setResizable(false);
		dialog.setVisible(true);
		
		
		
		
		
		
	}
	
	
	
	//�������
	public void clear(){
		for(int i=0;i<15;i++){
			for(int j=0;j<15;j++){
				x[i][j]=0;		//��ʼ����0��ʾ����,1��ʾ���ӣ�2��ʾ����
			}
		}
		step=0;
		player=1;
	}
	
	//��������
	public void lockChessboard(){
		lock=true;
	}
	//��������
	public void unlockChessboard(){
		lock=false;
	}
	
	public void paint(Graphics g){
//		this.removeAll();
//		this.clear();
//		g.clearRect(0, 0, 500, 500);
		this.setOpaque(false);
		makeGUI(g);
		draw_circle(g);
		showstep();
		printMessage(g);
		stateChange();
	}
	
	
	//�������̽���ĸ����ؼ���״̬���Ƿ��ܹ����
	public void stateChange(){
		mode = 4;	//�������Դ���
		endbt.setEnabled(true);		//�κ�ʱ���ǿ����˳���Ϸ��
		jlRegret.setText("���壨"+undoNumber+"��");
		if(undoNumber<=0){
			undobt.setEnabled(false);
		}
		else{
			undobt.setEnabled(true);
		}
		if(mode==0){					//��δѡ��ģʽ
			startbt.setEnabled(true);
			restartbt.setEnabled(false);
			undobt.setEnabled(false);
			//endbt.setEnabled(false);
			
			
			return;
		}
		//��������
		if(mode==1){
			hcbt.setEnabled(false);		//˫�˶�ս���ܼӵ���
			hcbt2.setEnabled(false);
			if(compControl==11){
				startbt.setEnabled(false);
				undobt.setEnabled(false);
				//exitbt.setEnabled(true);
				
			}
			if(compControl==12){
				startbt.setEnabled(true);
				undobt.setEnabled(false);
				//exitbt.setEnabled(true);
				
			}
			if(compControl==13){
				startbt.setEnabled(false);
				undobt.setEnabled(true);
				//exitbt.setEnabled(true);
				
			}
		}
		
		if(mode==2){
			hcbt.setEnabled(false);		//˫�˶�ս���ܼӵ���
			hcbt2.setEnabled(false);
			startbt.setEnabled(false);	//�ͻ���ֻ�ܵȴ�������ʼ
			//exitbt.setEnabled(true);
			if(compControl==21){
				undobt.setEnabled(false);
				
			}
			if(compControl==22){
				undobt.setEnabled(true);
				
			}
			if(compControl==23){
				undobt.setEnabled(false);
				
			}
		}
		//�˻���ս
		if(mode==4){
			if(compControl==41){
				startbt.setEnabled(false);
				hcbt.setEnabled(true);		
				hcbt2.setEnabled(true);
				undobt.setEnabled(false);
			}
			if(compControl==42){
				startbt.setEnabled(true);
				hcbt.setEnabled(false);		
				hcbt2.setEnabled(false);
				undobt.setEnabled(false);
			}
			if(compControl==43){
				startbt.setEnabled(false);
				hcbt.setEnabled(false);		
				hcbt2.setEnabled(false);
				undobt.setEnabled(true);
			}
		}
		
		
		//��δ��ʼ��Ϸ
		/*if(isStart==false) {
			startbt.setEnabled(true);
			restartbt.setEnabled(false);
			undobt.setEnabled(false);
			endbt.setEnabled(true);
			if(mode==2) startbt.setEnabled(false);		//����Է���Ϸ��ֻ�ܵȶԷ���ʼ���Լ����ܿ�ʼ
			return;
		}
		else {
			startbt.setEnabled(false);
			endbt.setEnabled(true);
		}
		
		if(undo) undobt.setEnabled(true);
		else undobt.setEnabled(false);
		
		*/
	}
	
	//��ʼ��Ϸ,δ��ʼ֮ǰ��������������
	public void Start(){
		unlockChessboard();
	}
	
	//������
	public void draw_circle(Graphics g){
		for(int i=0;i<15;i++){
			for(int j=0;j<15;j++){	
				
				if(x[i][j]==1){	//����
					
					//g.setColor(Color.black);
					//g.fillOval((j*30+30)-13, (i*30+30)-13, 26, 26);//���������������ص㣬��������
					g.drawImage(new ImageIcon("image/black.gif").getImage(), j*30+15, i*30+15, 28, 28, this);
				}
				else if(x[i][j]==2){	//����
					//g.setColor(Color.white);
					//g.fillOval((j*30+30)-13, (i*30+30)-13, 26, 26);//���������������ص�
					g.drawImage(new ImageIcon("image/white.gif").getImage(), j*30+15, i*30+15, 28, 28, this);
				}
				//���һ����  ������
				if(x[i][j]!=0&&last_x==i&&last_y==j){
					g.setColor(Color.red);
					g.drawRect((j*30+30)-15, (i*30+30)-15, 30, 30);
					
				}
			}
		}
	}
	
	//������
	public void makeGUI(Graphics g){
//		for(int i=30;i<=450;i+=30){
//			g.drawLine(i, 30, i, 450);
//			g.drawLine(30,i,450,i);
//		}
//		for(int i=0;i<15;i++){
//			g.drawString(i+"",25+i*30, 20);
//			g.drawString(i+"",10,35+i*30);
//		}
//		
//		g.drawImage(new ImageIcon("image/���̵׸� 15X15 2.jpg .jpg").getImage(), 0, 0, 470, 470, this);
	}
	
	//�ж��Ƿ�ʤ��,�򵥵��ж� ��������
	public int win(){		//0��ʾû����Ӯ��1��ʾ����Ӯ��2��ʾ����Ӯ
		int m=0;
		int n=0;
		
		//����һ��5*5��С����,��С�����ڲ��ж��Ƿ�win
		for(int i=0;i<11;i++){
			for(int j=0;j<11;j++){
				
				 for(m=0;m<5;m++)
			         for(n=0;n<5;n++)
			                 five[m][n]=x[m+i][n+j];
				 
				 for(m=0;m<5;m++){
                     if(five[m][0]==1&&five[m][1]==1&&five[m][2]==1&&five[m][3]==1&&five[m][4]==1)
                         return (1);
                     if(five[0][m]==1&&five[1][m]==1&&five[2][m]==1&&five[3][m]==1&&five[4][m]==1)
                         return (1);}
		             if(five[0][0]==1&&five[1][1]==1&&five[2][2]==1&&five[3][3]==1&&five[4][4]==1)
		                  return (1);
		             if(five[0][4]==1&&five[1][3]==1&&five[2][2]==1&&five[3][1]==1&&five[4][0]==1)
		                  return (1);
				
				
		
				for(m=0;m<5;m++){
		            if(five[m][0]==2&&five[m][1]==2&&five[m][2]==2&&five[m][3]==2&&five[m][4]==2)
		                return 2;
		            if(five[0][m]==2&&five[1][m]==2&&five[2][m]==2&&five[3][m]==2&&five[4][m]==2)
		                return 2;}
				    if(five[0][0]==2&&five[1][1]==2&&five[2][2]==2&&five[3][3]==2&&five[4][4]==2)
				         return 2;
				    if(five[0][4]==2&&five[1][3]==2&&five[2][2]==2&&five[3][1]==2&&five[4][0]==2)
				         return 2;
				
	}
}
		
		return 0;
	}
	
	//ʤ���Ի���
	public void ShowWinDialog(){
		
		Dialog dialog=new Dialog(f);
		dialog.setTitle("ʤ����");
		dialog.setModal(true);
		dialog.setLayout(new FlowLayout());
		dialog.setLocation(400, 250);
	
	
		if(win()==1){
			JLabel lb1=new JLabel("��ϲ�ڷ���ʤ��");
			dialog.add(lb1);
		}
		else {
			JLabel lb1=new JLabel("��ϲ�׷���ʤ��");
			dialog.add(lb1);
		}
		JButton bt1=new JButton("ȷ��");
		bt1.addActionListener(new bt1Listener(dialog));
		dialog.add(bt1);
		dialog.pack();
		dialog.setVisible(true);
		
		}
	
	//input dialog
	public void inputDialog(String title,String message){
		Dialog dialog=new Dialog(f);
		dialog.setTitle(title);
		dialog.setModal(true);
		dialog.setLayout(new GridLayout(4,1));
		dialog.setLocation(400, 250);
		
		
		inputTextArea=new TextArea("localhost");
		
		JLabel lb1=new JLabel(message);

		JButton bt1=new JButton("ȷ��");
		JButton bt2=new JButton("ȡ��");
		JPanel pp=new JPanel();
		
		
		bt1.addActionListener(new inputDialogBt1Listener(dialog));
		bt2.addActionListener(new inputDialogBt2Listener(dialog));
		pp.setLayout(new GridLayout(1,4));
		
		dialog.add(lb1);
		dialog.add(inputTextArea);
		
		dialog.add(pp);
		pp.add(new JPanel());
		pp.add(bt1);
		pp.add(new JPanel());
		pp.add(bt2);
		
		
		dialog.setSize(300,200);
		
		dialog.setVisible(true);
		}
	
	//���õ��Եȼ�
	public void setComputer(int i){
		computer=i;
	}
	
	public void setNet(boolean i){
		net=i;
	}
	
	//�ı����
	public void changeExpression (int x){
		switch(i){
			case (1): jlNormal.setVisible(false);		//happy
					  jlSad.setVisible(false);
					  jlAngry.setVisible(false);
					  jlHappy.setVisible(true);
					  repaint();
					  break;
					  
			case (2):jlNormal.setVisible(false);		//sad
			  		 jlSad.setVisible(true);
			  		 jlAngry.setVisible(false);
			  		jlHappy.setVisible(false); 
			  		repaint();
			  		 break;
			case (3):jlNormal.setVisible(false);		//angry
			  		 jlSad.setVisible(false);
			  		 jlAngry.setVisible(true);
			  		 jlHappy.setVisible(false); 
			  		repaint();
			  		 break;
			case (4):jlNormal.setVisible(true);		//normal
			  		 jlSad.setVisible(false);
			  		 jlAngry.setVisible(false);
			  		 jlHappy.setVisible(false);
			  		repaint();
			  		 break; 		 
		}
	}
	
	//ת�����
	public void switchplayer(){
		if(player==1) player=2;
		else player=1;	//ת�����
	}
	
	
	public void compute(){
		
		Compute cm=new Compute(x,player,computer,this);
		//repaint();
	
	}
	
	public void computeComplete(Point p){
		
		addChessman(p.x,p.y);
		switchplayer();
		lock=false;
	}
	
	
	public void addChessman(int i,int j){
		System.out.println("("+i+","+j+")");
		last_x=i;last_y=j;
		x[i][j]=player;
		record(i,j,player);
		repaint();
		
		//ÿ��һ���� ���ж�ʤ���ж�ʤ��
		if(win()!=0) {
			ShowWinDialog();
		}
	}
	
	//��¼
	public void record(int i,int j,int player){
		record[step][0]=i;
		record[step][1]=j;
		record[step][2]=player;
		step++;
	}
	
	//��ʼ��������
	public void netStart(){
		

		netTransThread.start();
		netChatThread.start();
		
		
	}
	
	
	
	
	//�����Լ���������
	public boolean sendNetStep(int p){
		return netTrans.send(p);
	}
	
	
	public void printMessage(Graphics g){
		
		if(chessboardMessage!="null") {
			g.setColor(Color.orange);
			g.setFont(new Font("΢���ź�",1,16));
			g.drawString(chessboardMessage, 200, 14);
		}
		return;
	}
	
	//�������ϴ�ӡ��Ϣ
	public void printMessage(String m){
		chessboardMessage=m;
		repaint();
	}
	
	//��������ϵ���Ϣ
	public void clearMessage(){
		chessboardMessage ="null";
		repaint();
	}
	
	//point ��int ����ת��
	public Point inttoPoint(int p){
		point.setpoint((int)(p/15),p%15);
		return point;
	}
	public int pointtoInt(Point p){
		return p.x*15+p.y;
	}
	
	//��ʱ����,��ʼ��ʱ
	public void timeStart(){
		time=57600;
		timeR.turnOn();
		if(timeThread.isAlive()) {}
		else{
			timeThread.start();
			}
	}
	//���¿�ʼ��ʱ
	public  void timeRestart(){
		time=57600;
	}
	
	//������ʱ
	public void timeEnd(){
		timeR.shutdown();
	}
	
	//���죬��ʾ���յ��ĶԷ���Ϣ
	public void addChatbox(String m){
		chatbox.append(m);
	}
	//���� ������Ϣ
	public void sendMessage(String m){
		netChat.sendChat(m);
	}
	
	//����������봰��
	public void clearChatInputbox(){
		chatinputbox.setText("");
	}
	
	//�����սʱ ��ֹ��Ϸ
	public void netEnd(){
		endGame();
		if(server) compControl=14;
		else compControl=23;
		stateChange();
		printMessage("�Է��Ѿ�������Ϸ��");
	}
	
	//�����սʱ ��ʼ��Ϸ
	public void netBegin(){
		beginGame();
		printMessage("�Է��Ѿ���ʼ��Ϸ��");
		if(server){
			compControl=13;
		}else {
			compControl=22;
		}
			stateChange();
		lockChessboard();
	}
	
	//��Ϸ����
	public void endGame(){
		lockChessboard();
		timeEnd();
		clear();
		
		isStart=false;
		stateChange();
		printMessage("��Ϸ�Ѿ�������");
	}
	//��Ϸ��ʼ
	public void beginGame(){
		unlockChessboard();
		timeStart();
		clear();
		if(mode==4){
			compControl=43;
		}
		if(mode==1){
			compControl=13;
		}
		
		isStart=true;
		stateChange();
		undoNumber=100;			//ÿ�οɻ���3��
		printMessage("��Ϸ�Ѿ���ʼ��");
	}
	
	//��Ϸ���¿�ʼ
	public void restartGame(){
		isStart=true;
		timeRestart();
		
		clear();
		unlockChessboard();
		
		stateChange();
	}
	
	//ѡ��ģʽ
	public void selectMode(int m){
		if(m==1){
			mode=1;
			state=8882;
			server=true;
			undo=false;
			compControl=11;
			isStart=false;
			
			setNet(true);
			createNetCon();
			netStart();
			printMessage("�����������ȴ��Է����ӡ���");
			
			lockChessboard();	
			stateChange();
		}
		else if(m==2){
			mode=2;
			server=false;
			isStart=false;
			undo=false;
			compControl=21;
			setNet(true);
			
			printMessage("����������������");
			lockChessboard();	
			//inputDialog("������Ϸ", "����������ip��ַ��");
			createNetCon();
			netStart();	
			stateChange();
		}
		else if(m==3){
			mode=3;
			isStart=false;
			setComputer(0);
			setNet(false);
			lockChessboard();
			stateChange();
		}
		else if(m==4){
			mode=4;
			compControl=41;
			
			GameCenter.changeState(8884);
			isStart=false;
			setComputer(1);
			setNet(false);
			lockChessboard();
			stateChange();
		}
	}
	
	public void setIp(String ip){
		this.ip=ip;
	}
	
	public void changeMyId(String myId){
		this.myId=myId;
		myIdlb.setText("���ǣ�"+myId);
	}
	
	public void changeRivalId(String Id){
		this.rivalId=Id;
		rivalIdlb.setText("���֣�"+Id);
	}
	
	//main
	public static void main(String[] args) {
		new Chess();
		
	}
	

/////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////����AI///////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////

	
	
	
	
	
	
	
	
	
	
/////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////�����Ǽ����ӿ���///////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////
	
//���̼���
class mousel implements MouseListener{
	public void mouseClicked(java.awt.event.MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void mouseEntered(java.awt.event.MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void mouseExited(java.awt.event.MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void mousePressed(java.awt.event.MouseEvent e) {
		// TODO Auto-generated method stub
		if(lock) return;//���̱����� ���Ե��
		
		
		int xx=e.getY();
		int yy=e.getX();
		
		//���������λ�ã������ҵ�����������
		aa:for(int i=30;i<=450;i+=30){
			for(int j=30;j<=450;j+=30){
				if(Math.abs(xx-i)<=15&&Math.abs(yy-j)<=15){
					xx=i;
					yy=j;
					break aa;
				}
					
			}
		}
		int stepX=(xx-30)/30;int stepY=(yy-30)/30;
		System.out.println("�����"+stepX+","+stepY);
			if(x[stepX][stepY]!=0) return; //��λ���Ѿ����ӣ����Դ˴ε��
			
			if(net==true){
				sendNetStep(stepX*15+stepY);		//������֮ǰ�������꣬��Ȼ����ͬʱ���ж�ʤ������������������
			}
				addChessman((xx-30)/30,(yy-30)/30);	//�������ص���������
			
			repaint();
			
			switchplayer();			//˫�˶�ս
			
			//������Ϸ
			if(net==false){
				if(computer!=0)	{						//�˻���ս
					compute();
					//Ȼ�󷵻أ��ȴ����Լ������	ע��˴�û��switchplayer
					return;
				}
				}
			//�����ս
			else{
				lockChessboard();
				Point p=null;
				
				printMessage("�ȴ��Է����ӡ���");
				
			}
			
			undobt.setEnabled(true);
			
		repaint();
		
		
		
		
	}

	public void mouseReleased(java.awt.event.MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
	//�Ի������
	class bt1Listener implements ActionListener{
		private Dialog g=null;
		bt1Listener(Dialog g){
			this.g=g;
			
		}

		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			g.setVisible(false); //�رնԻ���
			endGame();
			repaint();	//�������ػ�
		}
		
	}
	
	
	//��ʼ��ť ����
	class StartbtListener implements ActionListener{
		
		public void actionPerformed(ActionEvent e) {
			beginGame();
			stateChange();
			
			timeStart();	//��ʼ��ʱ
			if(mode==1) sendNetStep(NET_BEGIN);
			if(net==false&&computer!=0){
				//compute();			//��������
				//switchplayer();
			}
			
			repaint();
		}
		
	}
	
	//���¿�ʼ��ť ����
	class RestartbtListener implements ActionListener{
		
		public void actionPerformed(ActionEvent e) {
			
			
			
			time=57600;//���¼�ʱ
			repaint();
		}
		
	}
	
	
	
	//���尴ť����
	class UndobtListener implements ActionListener{
		
		public void actionPerformed(ActionEvent e) {
			undo();
			repaint();
			stateChange();
		}
		
	}
	
	//�����˾�  ��ť����
	class EndbtListener implements ActionListener{
		
		public void actionPerformed(ActionEvent e) {
			endGame();
			try{
				sendNetStep(NET_END);
			}catch(Exception ecp){}
			timeEnd();		//ֹͣ��ʱ��
			timelb.setText("00:00:00");
			repaint();
			stateChange();
			
			GameCenter.changeState(8881);
			//�ͷ�socket
			try{
				netChat.s.close();
				netTrans.s.close();
			}catch(Exception ecp){}
			
			
			
			f.dispose();
		}
		
	}
	
	//��ʱ�õ��߳�
	class TimeRunner implements Runnable{
		private boolean flag=true;
		public void shutdown(){
			flag=false;
		}
		public void turnOn(){
			flag=true;
		}
		
		public void run() {
			while(true){
				try{
					Thread.sleep(1000);
				}catch(InterruptedException e){}
				time++;
				if(flag==true){
					timelb.setText(""+(new Time(time*1000).toString()));
				}
				else{
					timelb.setText("00:00:00");
				}
				
			}
		}
		
	}
	
	
	//�˻���ս �򵥵��԰�ť����
	class hcbtListener implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			compControl=42;
			computer=1;
			stateChange();
		}
		
	}
	
	//�˻���ս�������԰�ť
	class hcbt2Listener implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			compControl=42;
			computer=2;
			stateChange();
		}
		
	}
	
	//˫�˶�ս ��ť����
	class hhbtListener implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			selectMode(3);
		}
		
	}
	
	//�������� ��ť����
	class netbtListener implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			selectMode(1);
				
			
		}
		
	}
	
	//������Ϸ ��ť����
	class netbt2Listener implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			
			
			
			
			selectMode(2);
			
		}
		
	}
	
	//����Ի���� ������ť
	class inputDialogBt1Listener implements ActionListener{
		private Dialog g=null;
		inputDialogBt1Listener(Dialog g){
			this.g=g;
			
		}
		public void actionPerformed(ActionEvent e) {
			ip=inputTextArea.getText();
			
			g.setVisible(false);
		}
		
	}
	
	class inputDialogBt2Listener implements ActionListener{
		private Dialog g=null;
		inputDialogBt2Listener(Dialog g){
			this.g=g;
			
		}
		public void actionPerformed(ActionEvent e) {
			
				g.setVisible(false);
			
		}
		
	}
	
	//������Ϸ�Ի��� ��ť����
//	class chatbtListener implements ActionListener{
//
//		public void actionPerformed(ActionEvent e) {
//				sendMessage(chatinputbox.getText());
//			
//		}
//		
//	}

	
	// ����� ����
	class InputListener implements KeyListener {

		
		public void keyPressed(KeyEvent e) {
			// TODO Auto-generated method stub
			if(e.getKeyChar()==KeyEvent.VK_ENTER){	
				sendMessage(chatinputbox.getText());
			}
		}

		
		public void keyReleased(KeyEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		
		public void keyTyped(KeyEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		
		
	}

}


//***************************************************************************
//****************************����������ͨ���õ�����߳�*********************************
class NetTransThread implements Runnable{
	private Chess c=null;
	public NetTransThread(Chess c){
		this.c=c;
	}
	public void run() {
		// TODO Auto-generated method stub
		c.netTrans=new NetTrans(c.ip,c.port,c);
		c.netTrans.startNet(c.server);
		c.netTrans.receiveStart();
	}
	
}

class NetChatThread implements Runnable{
	private Chess c=null;
	public NetChatThread(Chess c){
		this.c=c;
	}
	public void run() {
		// TODO Auto-generated method stub
		c.netChat=new NetChat(c.ip,c.port2,c);
		c.netChat.startNet(c.server);
		c.netChat.receiveStart();
	}
	
}





//����ͨ�� ��
class NetTrans {
	
	//һ���� ����ͨ���õı���
	DataOutputStream dos=null;
	BufferedReader br=null;
	DataInputStream dis=null;
	String string=null;
	String ip="localhost";
	int port=12345;
	Chess c=null;
	Socket s=null;
	
	public NetTrans(String ip,int port,Chess c){
		this.ip=ip;
		this.port=port;
		this.c=c;
	}
	
	public void set(String ip,int port){
		this.ip=ip;
		this.port=port;
	}
	
	public boolean startNet(boolean server) {
		InputStream in=null;
		OutputStream out=null;
		string=null;
		
		
		try{
			if(server==false){
				System.out.println("����������������"+ip+port);
				
				s=new Socket(ip,port);
				out=s.getOutputStream();
				in=s.getInputStream();
				dis=new DataInputStream(in);
				dos=new DataOutputStream(out);
				
				System.out.println("���ӳɹ���");
				//System.out.println("���ն�������");
				//c.changeRivalId(dis.readUTF());	//���ն�������
				//System.out.println("�����Լ�����");
				//dos.writeUTF(c.myId);			//�����Լ�����
				c.printMessage("���ӳɹ����ȴ��Է���ʼ��Ϸ����");
				c.lock=true;
				c.state=8883;
			}else {
				ServerSocket ss=new ServerSocket(port);
				System.out.println("����������"+ip+":"+port+"�ȴ��ͻ������ӡ���");
				
				s=ss.accept();
				out=s.getOutputStream();
				in=s.getInputStream();
				dis=new DataInputStream(in);
				dos=new DataOutputStream(out);
				
				//System.out.println("�����Լ�����");
				//dos.writeUTF(c.myId);			//�����Լ�����
				//System.out.println("���ն�������");
				//c.changeRivalId(dis.readUTF());	//���ն�������
				
				c.printMessage("�ͻ������ӳɹ����뿪ʼ��Ϸ��");
				c.sendMessage(c.myId+"��������Ϸ");
				c.compControl=12;
				c.stateChange();
				GameCenter.changeState(8883);
				System.out.println("�ͻ������ӳɹ���");
				c.state=8883;
			}
			
			
			
		}catch(Exception e){
			c.clearMessage();
			c.printMessage("�������������������ã�");
			System.out.println("��������"+e);
			return false;
		}
		
		
		
		return true;
	}
	
	public void receiveStart(){
		Listen l=new Listen(c,dis);
		Thread t=new Thread(l);
		
		
		t.start();
		
	}
	
	public boolean send(int p){
		try{
			dos.writeInt(p);
		}catch(Exception e){
			System.out.println("�������ݷ�������"+e);
			return false;
		}
		System.out.println("�������꣺"+p);
		return true;
	}
	

	
}

//�����̣߳�������������
class Listen implements Runnable{
	
	private Chess c=null;
	private DataInputStream dis=null;
	private int p=0;
	
	Listen(Chess c,DataInputStream dis){
		this.c=c;
		this.dis=dis;
	}
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("�����߳��������ȴ��������ꡭ��");
		try{
			while(true){
				
					p=dis.readInt();
					System.out.println("�յ����꣺"+p);
				
					//�Ƿ��ʾ��������
				if(p==300){
					c.netBegin();
					continue;
				}
				else if(p==301){
					c.netEnd();
					continue;
				}
				else if(p==302){		//�������
					c.netUndoRequest();
					continue;
				}
				else if(p==303){
					new ConfirmDialog("�Է�ͬ�������Ļ�������",c.f);
					c.netUndo();
					continue;
				}
				else if(p==304){
					new ConfirmDialog("�Է���ͬ�����Ļ�������",c.f);
					continue;
				}
				
					c.addChessman((int)(p/15), p%15);
					c.switchplayer();
					c.repaint();
					c.unlockChessboard();
					c.clearMessage();
			}
		}catch(Exception e){
			c.printMessage("���������ѶϿ����Է��Ѿ��˳���Ϸ��");
			c.sendMessage("���������Ѿ��Ͽ����Է��Ѿ��˳�����Ϸ��");
			c.lockChessboard();
		}
	}
}




//���ͺͽ���������Ϣ
class NetChat {
	
	//һ���� ����ͨ���õı���
	DataOutputStream dos=null;
	BufferedReader br=null;
	DataInputStream dis=null;
	String string=null;
	String ip="localhost";
	int port=12345;
	Chess c=null;
	Socket s=null;
	
	public NetChat(String ip,int port,Chess c){
		this.ip=ip;
		this.port=port;
		this.c=c;
	}
	
	public void set(String ip,int port){
		this.ip=ip;
		this.port=port;
	}
	
	public boolean startNet(boolean server) {
		InputStream in=null;
		OutputStream out=null;
		string=null;
		
		
		try{
			if(server==false){
				System.out.println("�����߳�����������������");
				s=new Socket(ip,port);
				System.out.println("���ӳɹ���");
				
			}else {
				ServerSocket ss=new ServerSocket(port);
				System.out.println("�����߳̽���������"+ip+":"+port+"�ȴ��ͻ������ӡ���");
				s=ss.accept();
				
				System.out.println("�ͻ������ӳɹ���");
			}
			
			
			out=s.getOutputStream();
			in=s.getInputStream();
		}catch(Exception e){
			c.clearMessage();
			c.printMessage("�������������������ã�");
			System.out.println("��������"+e);
			return false;
		}
		
		dis=new DataInputStream(in);
		dos=new DataOutputStream(out);
		
		return true;
	}
	
	public void receiveStart(){
		
		ChatListen cl=new ChatListen(c,dis);
		Thread ct=new Thread(cl);
		
		
		ct.start();
	}
	
	public boolean sendChat(String m){
		try{
			dos.writeUTF(m);
		}catch(Exception e){
			System.out.println("������Ϣ��������"+e);
			c.addChatbox("�������Ӵ�����Ϣ����ʧ�ܣ�\n");
			return false;
		}
		System.out.println("����������Ϣ��"+m);
		c.addChatbox("��˵��"+"\n"+"   "+m+"\n");
		c.clearChatInputbox();
		return true;
	}
	
}

//�����߳� ����������Ϣ
class ChatListen implements Runnable{
	
	private Chess c=null;
	private DataInputStream dis=null;
	private String m=null;
	
	ChatListen(Chess c,DataInputStream dis){
		this.c=c;
		this.dis=dis;
	}
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("��������߳��������ȴ�������Ϣ����");
		try{
			while(true){
				
				m=dis.readUTF();
				System.out.println("�յ���Ϣ��"+m);
				c.addChatbox("��˵��"+"\n"+"   "+m+"\n");
			}
		}catch(Exception e){
			System.out.println("��������"+e);
		}
	}
}


