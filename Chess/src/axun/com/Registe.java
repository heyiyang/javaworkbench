package axun.com;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import componets.ConfirmAndExitDialog;
import componets.ConfirmDialog;


import userInterface.com.*;

public class Registe {
	private Register rg=new Register();
	private String ip="localhost";
	private int port=6666;
	
	public Registe(String ip){
		rg.jbtOK.addActionListener(new JbtOKListener());
		this.ip=ip;
	}
	
	//ע��
	public void registe(){
		int result=0;
		Socket s=null;
		try{
			if(!(rg.jpfPassword.getText().equals(rg.jpfPasswordConfirm.getText()))){
				new ConfirmDialog("ע��ʧ�ܣ������������벻һ�£�",rg);
				return;
			}
			
			System.out.print(ip+","+port);
			s=new Socket(ip,port);

			OutputStream os=s.getOutputStream();
			InputStream is=s.getInputStream();
			
			DataOutputStream dos=new DataOutputStream(os);
			DataInputStream dis=new DataInputStream(is);
			
			//�����û���������
			System.out.println("����ע�ᡭ��");
			dos.writeUTF("Registe");
			//System.out.println("�����û�������"+rg.jtfID.getText());
			dos.writeUTF(rg.jtfID.getText());
			//System.out.println("�������롭��"+rg.jpfPassword.getText());
			dos.writeUTF(rg.jpfPassword.getText());
			//System.out.println("�����ǳơ���"+rg.jtfName.getText());
			dos.writeUTF(rg.jtfName.getText());

			result=dis.readInt();
		}catch(Exception e){
			System.out.println("�����������ʧ�ܣ�����д��ȷ�ķ�����ip��");
			result=2;
			
		}
		if(result==0) {
			System.out.println("ע��ɹ���");
			new ConfirmAndExitDialog("��ϲ��ע��ɹ���",rg);		//�Ի�������
			
			//break;
		
		}
		else if(result==1){
			System.out.println("ע��ʧ�ܣ�");
			new ConfirmDialog("ע��ʧ�ܣ����û����Ѵ��ڣ�",rg);
		}else if(result==2){
			System.out.println("ע��ʧ�ܣ�");
			new ConfirmDialog("ע��ʧ�ܣ��޷����ӷ�������",rg);
		}
		
		
	}
	
	//ע�ᰴť����
	class JbtOKListener implements ActionListener{

		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			registe();
		}

		
	}
}
