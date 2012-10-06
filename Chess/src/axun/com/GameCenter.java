package axun.com;
import userInterface.com.*;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.List;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;

import java.net.Socket;

import javax.swing.ImageIcon;
import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;

import userInterface.com.GameHall;

import com.sun.media.sound.Toolkit;

public class GameCenter extends JFrame{
	

	
	//********************************************************************************************
	GameHall gh=new GameHall();			//��Ϸ��������
	
	private String[] clientInfo;
	private String[] clientSocket;
	private int[] clientState;
	private int clientNumber=0;
	int hostNumber=0;
	
	private String serverIp="localhost";	//��������ַ
	private final int serverPort=6666;	//�������˿�
	static int state=8881;			//��ǰ״̬
	
	private DataOutputStream dos=null;
	private DataInputStream dis=null;
	Socket s=null;
	Socket chats=null;
	
	
	String rivalIp="localhost";	//����ip
	String rivalName="null";
	private String myId="";
	
	
	
	public GameCenter(Socket s,Socket chats,String serverIp,String myId){
		
		/*gh.exitbtListener(new ExitbtListener());
		gh.enterhostbtListener(new EnterHostbtListener());
		gh.clientltListener(new ClientltListener());
		gh.chatbtListener(new ChatbtListener());
		gh.createHostbtListener(new CreateHostbtListener());
		*/
		gh.jbtNewGameListener(new CreateHostbtListener());
		gh.inputListener(new JtaInputListener());
		gh.jbtVSPC.addActionListener(new pcbtListener());
		
		
		
		this.serverIp=serverIp;
		this.s=s;
		this.chats=chats;
		
		changeMyId(myId);
		//�����߳�
		new Thread(new ServerCom(s,serverIp,serverPort,this)).start();
		new Thread(new ChatListener(this)).start();
		
		clientInfo=new String[20];
		clientSocket=new String[20];
		clientState=new int[20];
		
	}
	
	public void clearclientlt(){
		gh.clearClient();
		clientNumber=0;
		hostNumber=0;
	}
	
	public void addclient(String name,String socket,int state){
		gh.addClient(name, socket, state);
		clientInfo[clientNumber]=name;
		clientSocket[clientNumber]=socket;
		clientState[clientNumber]=state;
		ghEnterHostListener(clientNumber);
		clientNumber++;
		
		
	}
	
	void ghEnterHostListener(int i){
		if(clientState[i]==8882){

			gh.enterHost[hostNumber].removeAll();
			gh.enterHost[hostNumber].addActionListener(new GhEnterHostListener(i));
			gh.enterHost[hostNumber].setText(clientInfo[i]+","+clientSocket[i]);
			gh.enterHost[hostNumber].setEnabled(true);
			gh.jlUserName[hostNumber].setText(clientInfo[i]);
			hostNumber++;
		}
		
	}
	
	public void endAddClient(){
		gh.endAddClient();
	}
	
	public int getState(){
		
		return state;
	}
	
	//�ı���ҵ�����״̬
	public static void changeState(int i){
		state=i;
	}
	
	public void changeMyId(String id){
		myId=id;
		gh.setID(id);
	}
	
	
	//////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////�����Ǽ���
	////////////////////////////////////////////////////////////////////////////
	/**
	 *�˳���ť����
	 */
	class ExitbtListener implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			System.exit(0);
			
		}
		
	}
	
	/**
	 * �������� ��ť����
	 */
	
	class CreateHostbtListener implements ActionListener{

		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			state=8882;
			Chess chess=new Chess();
			chess.selectMode(1);
			
			chess.changeMyId(myId);
			
		}
		
	}
	/**
	 * ������Ϸ ��ť����
	 *
	 */
	
	class EnterHostbtListener implements ActionListener{
		
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			Chess chess=new Chess();
			chess.setIp(rivalIp);
			chess.selectMode(2);
			state=8883;
			chess.changeMyId(myId);
		}
		
	}
	
	/*gh ����������ť����
	 * 
	 */
	class GhEnterHostListener implements ActionListener{
		private int number=0;
		public GhEnterHostListener(int num){
			number=num;
		}

		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			Chess chess=new Chess();
			chess.setIp(clientSocket[number]);
			chess.selectMode(2);
			state=8883;
			chess.changeMyId(myId);
		}
		
	}
	/*
	 * gh ��������ť
	 */
		class EmptyHostListener implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			Chess chess=new Chess();
			chess.setIp(rivalIp);
			chess.selectMode(4);
			state=8884;
			chess.changeMyId(myId);
		}
			
		}
		
	/*
	 * �˻���ս ��ť����
	 * 
	 */
		
		class pcbtListener implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			Chess chess=new Chess();
			chess.setIp(rivalIp);
			chess.selectMode(4);
			state=8884;
			chess.changeMyId(myId);
		}
			
		}
	
	/**
	 * �û��б� ����
	 *
	 */
	
	class ClientltListener implements ItemListener{

		public void itemStateChanged(ItemEvent arg0) {
			// TODO Auto-generated method stub
			/*rivalIp=clientSocket[gh.clientlt.getSelectedIndex()];
			rivalName=clientInfo[gh.clientlt.getSelectedIndex()];
			gh.infolb.setText("�Է�ip:"+rivalIp);
			*/
		}
	}
	/**
	 * ������Ϣ ����
	 */
	class JtaInputListener extends KeyAdapter{
		private DataOutputStream dos=null;
		
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}

		public void keyPressed(KeyEvent e) {
			// TODO Auto-generated method stub
			if(e.getKeyChar()==KeyEvent.VK_ENTER){
					try{
						dos=new DataOutputStream(chats.getOutputStream());
						dos.writeUTF(gh.jtaInput.getText());
						gh.jtaInput.setText("");
						gh.jtaInput.append("\b");
					}catch(Exception ep){
						gh.jtaDialog.append("failed:"+e.toString());
					}
			}
		}

		
		
	}
	
	
}

//�������ͨ�ţ��ж��û��Ƿ�����

class ServerCom implements Runnable{
	private String ip=null;
	private int port;
	private DataOutputStream dos=null;
	private DataInputStream dis=null;
	private Socket s=null;
	
	private GameCenter gc=null;
	
	public ServerCom(Socket s,String ips,int serverport,GameCenter gc){
		ip=ips;
		port=serverport;
		this.s=s;
		this.gc=gc;
		try{
			this.dis=new DataInputStream(s.getInputStream());
			this.dos=new DataOutputStream(s.getOutputStream());
		}catch(Exception e){}
		
	}
	
	public void run() {
		// TODO Auto-generated method stub
		
		while(true){
			try{
				int r=dis.readInt();
				//�Ƿ�����
				if(r==7771){
					System.out.println("�յ���������Ϣ��"+7771);
					dos.writeInt(gc.state);
					//if(gc.getState()==8883) dos.writeUTF(gc.rivalName);
				}
				//��ȡ�����û��б�
				if(r==7772){
					System.out.println("�յ���������Ϣ��"+7772);
					dos.writeInt(6661);
					System.out.println("���ͷ�������Ϣ��"+6662);
					System.out.println("��ʼ���������û��б���");
					String clientInfo=null;
					String clientSocketString=null;
					int state;
					int cn=0;
					gc.clearclientlt();		//������б�
					
					while(true){
						clientInfo="δ����";
						clientSocketString="null";
						if((clientInfo=dis.readUTF()).equals("END")) break;  //����END��ʾ����
						if((clientSocketString=dis.readUTF()).equals("END")) break;
						state=dis.readInt();
						System.out.println("�յ��û���Ϣ��������"+clientInfo+"IP��ַ��"+clientSocketString);
						gc.addclient(clientInfo, clientSocketString,state);
						cn++;
					}
					System.out.println("�û��б������ϣ����������û�"+cn+"�ˣ�");
					gc.endAddClient();
				}
				
				//��ȡ������Ϸ��˫��id
				if(r==7773){
					System.out.println("���ն�ս˫���б���");
					dos.writeInt(6662);
					String rivalName1=null;
					String rivalName2=null;
					while(true){
						rivalName1=dis.readUTF();
						rivalName2=dis.readUTF();
						if(rivalName1.equalsIgnoreCase("END")) break;
						if(rivalName2.equalsIgnoreCase("END")) break;
						//gc.gh.chatta.append(rivalName1+"��"+rivalName2+"��ս�ˣ�");
					}
				}
				
			}catch(Exception e){
				System.out.println("������������쳣��10���Ӻ��������ӡ���");
				try{
					Thread.sleep(10000);
					
				}catch(Exception ecp){}
			}
			
		}
	}
	
}

/**
 * �������������͵���Ϣ
 */
class ChatListener implements Runnable{
	private DataInputStream dis=null;
	private GameCenter gc=null;
	
	public ChatListener(GameCenter gc){
		this.gc=gc;
	}

	public void run() {
		// TODO Auto-generated method stub
		String read=null;
		while(true){
			try{
				dis=new DataInputStream(gc.chats.getInputStream());
				read=dis.readUTF();
				System.out.println(read);
				gc.gh.jtaDialog.append(read+"\n");
			}catch(Exception e){
				
			}
		}
	}
	
}
 
