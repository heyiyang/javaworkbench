package userInterface.com;
import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.TextArea;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.sun.java.swing.plaf.windows.resources.windows;

import axun.com.*;

public class Chessboard extends Canvas{
	//GUI
	public JFrame f=new JFrame("������");
	public JPanel pright=new JPanel();
	public JPanel pleft=new JPanel();
	public JPanel p1=new JPanel();
	public JPanel p2=new JPanel();
	public JPanel p3=new JPanel();
	public JButton startbt=new JButton("��ʼ��Ϸ");
	public JButton restartbt=new JButton("���¿�ʼ");
	public JButton setbt=new JButton("����");
	public JButton undobt=new JButton("����");
	public JButton exitbt=new JButton("�˳���Ϸ");
	public JButton hhbt=new JButton("˫�˶�ս");
	public JButton netbt=new JButton("��������");
	public JButton netbt2=new JButton("������Ϸ");
	public JButton hcbt=new JButton("�˻���ս");
	public JButton endbt=new JButton("�����˾�");
	public JLabel lb1=new JLabel("�����¼��");
	public JLabel timelb=new JLabel("00:00:00");			//��ʱ��ǩ
	public TextArea tr1=new TextArea();
	public JLabel myIdlb=new JLabel("����");
	public JLabel rivalIdlb=new JLabel("����");
	
	//�����õ�
	public TextArea chatbox=new TextArea("");
	public TextArea chatinputbox=new TextArea("");
	public JButton chatbt=new JButton("����");
	
	
	//����


	private boolean isStart=false;		//�Ƿ��Ѿ���ʼ��Ϸ
	public boolean lock=false;				//�Ƿ��������̣�����֮��������
	private int computer=0;						//���Եȼ���0��ʾ˫�˶�ս��123�ֱ��ʾ�Ѷ�
	private boolean net=false;						//net��ʾ�Ƿ��������ս
	private boolean undo=true;				//�Ƿ��������
	private boolean wait=false;				//�Ƿ����ڵȴ��Է�����
	private int mode=0;						//�Ƿ��Ѿ�ѡ����һ��ģʽ
	int state=0;				//��ǰ״̬
	String  rivalId=null;
	String myId=null;
	
	private int[][] x;
	private int[][] record;	//�����¼
	private int step;		//
	private int player;
	private int time;		//��ʱ ����ʾ������
	
	public Chessboard(){
		f.setLayout(new BorderLayout());
		f.setSize(800,600);
		
		f.setLocation(300, 160);
		f.setResizable(false);			//���ɸı��С
		
		f.add(pleft,"West");
		pleft.setLayout(new GridLayout(10,1));
		//pleft.add(netbt);
		//pleft.add(netbt2);
		//pleft.add(hhbt);
		pleft.add(hcbt);
		pleft.add(setbt);
		//pleft.add(exitbt);
		pleft.add(myIdlb);
		pleft.add(rivalIdlb);
		
		
		
		
		
		setSize(500,500);
		f.add(this,"Center");
		this.setBackground(Color.green);
		
		
		
		pright.setLayout(new GridLayout(3,1));
		pright.setBackground(Color.yellow);
		f.add(pright,"East");
		pright.add(p1);
		pright.add(p2);
		pright.add(p3);
		
		tr1.setEnabled(true);
		tr1.setBackground(Color.LIGHT_GRAY);
		tr1.setRows(10);
		tr1.setColumns(20);
		tr1.setEditable(false);
		
		p1.setLayout(new GridLayout(6,1,0,10));
		p1.add(startbt);
		p1.add(restartbt);
		p1.add(undobt);
		p1.add(endbt);
		
		
		p1.add(timelb);
		p1.add(lb1);
		p2.add(tr1);
		
		chatbox.setRows(10);
		chatbox.setColumns(20);
		
		chatinputbox.setRows(1);
		chatinputbox.setColumns(10);
		chatbox.setEditable(false);
		p3.setLayout(new GridLayout(3,1));
		p3.add(chatbox);
		p3.add(chatinputbox);
		p3.add(chatbt);
		
		
		
		f.setVisible(true);			//ע�����������
	}
	
	public void paint(Graphics g){
		makeGUI(g);
		draw_circle(g,x);
		
	}
	
	//������
	public void draw_circle(Graphics g,int[][]x){
		for(int i=0;i<15;i++){
			for(int j=0;j<15;j++){	
				
				if(x[i][j]==1){	//����
					
					g.setColor(Color.black);
					g.fillOval((j*30+30)-13, (i*30+30)-13, 26, 26);//���������������ص㣬��������
				}
				else if(x[i][j]==2){	//����
					g.setColor(Color.white);
					g.fillOval((j*30+30)-13, (i*30+30)-13, 26, 26);//���������������ص�
				}
			}
		}
	}
	
	//������
	public void makeGUI(Graphics g){
		for(int i=30;i<=450;i+=30){
			g.drawLine(i, 30, i, 450);
			g.drawLine(30,i,450,i);
		}
		for(int i=0;i<15;i++){
			g.drawString(i+"",25+i*30, 20);
			g.drawString(i+"",10,35+i*30);
		}
	}
	
	
	//��������
	public void lockChessboard(){
		lock=true;
	}
	//��������
	public void unlockChessboard(){
		lock=false;
	}
	
	//ʤ���Ի���
	public void ShowWinDialog(int winner){
		Dialog dialog=new Dialog(f);
		dialog.setTitle("ʤ����");
		dialog.setModal(true);
		dialog.setLayout(new FlowLayout());
		dialog.setLocation(400, 250);
	
	
		if(winner==1){
			JLabel lb1=new JLabel("��ϲ�ڷ���ʤ��");
			dialog.add(lb1);
		}
		else {
			JLabel lb1=new JLabel("��ϲ�׷���ʤ��");
			dialog.add(lb1);
		}
		JButton bt1=new JButton("ȷ��");
		dialog.add(bt1);
		dialog.pack();
		dialog.setVisible(true);
		
		}
	
	public void addChessman(int i,int j,int player){
		System.out.println("("+i+","+j+")");
		x[i][j]=player;
		record(i,j,player);
		
	}
	
	public void record(int i,int j,int player){
		record[step][0]=i;
		record[step][1]=j;
		record[step][2]=player;
		step++;
	}
	

	
	
}
