package axun.com;
import userInterface.com.*;

import java.awt.BorderLayout;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import componets.ConfirmDialog;

import axun.com.Chess.bt1Listener;


public class Login {
	//GUI
	private LoginUI lg=new LoginUI();
	
	//�˺�����
	private String userId="��ѵ";
	private String password="123456";
	
	private boolean isLogin=false;
	public String serverIp="localhost";	//��������ַ
	public int serverPort=6666;	//�������˿�
	Socket s=null;
	Socket chats=null;
	
	public Login(){
		lg.exitbt.addActionListener(new ExitbtListener());
		lg.loginbt.addActionListener(new LoginbtListener());
		lg.jbtRegister.addActionListener(new JbtRegisterListener());
	
	}
	
	public void login(String userId,String password,String serverIp){
		this.userId=userId;
		this.password=password;
		this.serverIp=serverIp;
		//���ӷ�����
		LoginServer ls=new LoginServer(serverIp,serverPort,userId,password);
		if(ls.getResult()==0){
			s=ls.getSocket();
			chats=ls.getChatSocket();
			isLogin=true;
			lg.j.dispose();	//������ɾͷ���
			new GameCenter(s,chats,getServerIp(),userId);
		}else if(ls.getResult()==2){
			new ConfirmDialog("��¼ʧ�ܣ��û������������",lg.j);
		}else if(ls.getResult()==1){
			new ConfirmDialog("��¼ʧ�ܣ��޷����ӷ�����!��ȷ���������ĵ�ַ����ȷ�ģ�",lg.j);
		}
		
	}
	
	public boolean isLogin(){
		return isLogin;
	}
	
	
	
	public String getServerIp(){
		return serverIp;
	}
	
	public int getServerPort(){
		return serverPort;
	}
	
	public static void main(String[] args) {
		Login lg=new Login();
		
	}
	
	//////////////////////////////////////////////////
	//һ���Ǽ���
	///////////////////////////////////////////////////
	
	/**
	 *�˳���ť����
	 */
	class ExitbtListener implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			System.exit(0);
			
		}
		
	}
	
	/**
	 *��½��ť����
	 */
	class LoginbtListener implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			login(lg.userIdtf.getText(),lg.passwordtf.getText(),lg.serverIptf.getText());
			
		}
		
	}
	
	//ע�ᰴť����
	class JbtRegisterListener implements ActionListener{

		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			new Registe(serverIp);
		}
		
	}
}

//��½

class LoginServer {
	private String ip="localhost";
	private int port=2222;
	private DataOutputStream dos=null;
	private DataInputStream dis=null;
	private Socket s=null;
	private Socket chats=null;
	private boolean result=false;
	private int flag=2;		//0�ɹ���1���޷����ӷ�������2 �û������������
	
	public LoginServer(String ips,int p,String userId,String password){
		
		ip=ips;
		port=p;
		flag=2;
		//while(true){
			try{
				//�����������ӣ�����һ��ר����������
				s=new Socket(ip,port);
				chats=new Socket(ip,6667);
				OutputStream os=s.getOutputStream();
				InputStream is=s.getInputStream();
				
				dos=new DataOutputStream(os);
				dis=new DataInputStream(is);
				
				//�����û���������
				System.out.println("�����û��������롭��");
				dos.writeUTF(userId);
				dos.writeUTF(password);
				if(dis.readBoolean()){
					flag=0;
				}
				else flag=2;
			}catch(Exception e){
				System.out.println("�����������ʧ�ܣ�����д��ȷ�ķ�����ip��");
				flag=1;
				
			}
			
		//}
		
	
	}
	
	public Socket getSocket(){
		return s;
	}
	
	public Socket getChatSocket(){
		return chats;
	}
	
	public int getResult(){
		return flag;
	}
}