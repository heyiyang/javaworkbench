package Compute;
import axun.com.*;

/*
 * �����Կ�ʼ����ʽ���������̣�������ɺ󣬵���chess�е�computeComplete������
 * 
 * 
 */
public class Compute {
	Chess chess=null;
	int[][] chessboard=null;
	int comLevel=1;
	int player=1;

	public Compute(int[][] chessboard,int player,int comLevel,Chess c){
		this.chess=c;
		this.chessboard=chessboard;
		this.comLevel=comLevel;
		this.player=player;
		
		chess.lockChessboard();
		//chess.printMessage("�������ڼ��㡭��");
		Thread comThread=new Thread(new ComputeThread(chess,this));
		comThread.start();
		
		
	}
}

/*
 * ���Լ����õĵ����߳�
 */
class ComputeThread implements Runnable{
	private Chess chess=null;
	private Compute compute=null;
	
	public ComputeThread(Chess chess,Compute com){
		this.chess=chess;
		this.compute=com;
	}

	public void run() {
		// TODO Auto-generated method stub
		
		MyComputer computer=new MyComputer(compute.chessboard,compute.player,compute.comLevel,chess);
		computer.think();
		Point point=computer.getPoint();
		
		chess.computeComplete(point);
		
		
		//�򵥵���
		if(compute.comLevel==1){
			
		}
	}
	
	
}