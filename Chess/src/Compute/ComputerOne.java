package Compute;
import axun.com.*;

public class ComputerOne {
	int width=14;
	int height=14;
	int x;
	int y;
	int[][] chess=null;
	public Point computerDo(int width,int height,int[][]chess){
        int max_black,max_white,max_temp,max=0;
        this.chess=chess;
        this.width=width;
        this.height=height;
        //setisOdd(true);
        System.out.println("��������� ...");
        for(int i = 0; i <= width; i++){
            for(int j = 0; j <= height; j++){
                if(chess[i][j]==0){//�㷨�ж��Ƿ�����
                    max_white=checkMax(i,j,2);//�жϰ��ӵ����ֵ
                    max_black=checkMax(i,j,1);//�жϺ��ӵ����ֵ
                    max_temp=Math.max(max_white,max_black);
                    if(max_temp>max){
                        max=max_temp;
                        this.x=i;
                        this.y=j;
                    }
                }
            }
        }
        setX(this.x);
        setY(this.y);
        //chess[this.x][this.y]=2;
        return new Point(this.x,this.y);
    }
    
    //��¼�������Ӻ�ĺ�������
    public void setX(int x){
        this.x=x;
    }
    
    //��¼�������Ӻ����������
    public void setY(int y){
        this.y=y;
    }
    
    //��ȡ�������ӵĺ�������
    public int getX(){
        return this.x;
    }
    
    //��ȡ�������ӵ���������
    public int getY(){
        return this.y;
    }

    //����������ĳһ�����ϰ˸��������ӵ����ֵ��
    //��˸�����ֱ��ǣ����ҡ��ϡ��¡����ϡ����¡����ϡ�����
    public int checkMax(int x, int y,int black_or_white){
        int num=0,max_num,max_temp=0;
        int x_temp=x,y_temp=y;
        int x_temp1=x_temp,y_temp1=y_temp;
        //judge right
        for(int i=1;i<5;i++){
            x_temp1+=1;
            if(x_temp1>this.width)
                break;
            if(chess[x_temp1][y_temp1]==black_or_white)
                num++;
            else
                break;
        }
        //judge left
        x_temp1=x_temp;
        for(int i=1;i<5;i++){
            x_temp1-=1;
            if(x_temp1<0)
                break;
            if(chess[x_temp1][y_temp1]==black_or_white)
                num++;
            else
                break;
        }
        if(num<5)
            max_temp=num;

        //judge up
        x_temp1=x_temp;
        y_temp1=y_temp;
        num=0;
        for(int i=1;i<5;i++){
            y_temp1-=1;
            if(y_temp1<0)
                break;
            if(chess[x_temp1][y_temp1]==black_or_white)
                num++;
            else
                break;
        }
        //judge down
        y_temp1=y_temp;
        for(int i=1;i<5;i++){
            y_temp1+=1;
            if(y_temp1>this.height)
                break;
            if(chess[x_temp1][y_temp1]==black_or_white)
                num++;
            else
                break;
        }
        if(num>max_temp&&num<5)
            max_temp=num;

        //judge left_up
        x_temp1=x_temp;
        y_temp1=y_temp;
        num=0;
        for(int i=1;i<5;i++){
            x_temp1-=1;
            y_temp1-=1;
            if(y_temp1<0 || x_temp1<0)
                break;
            if(chess[x_temp1][y_temp1]==black_or_white)
                num++;
            else
                break;
        }
        //judge right_down
        x_temp1=x_temp;
        y_temp1=y_temp;
        for(int i=1;i<5;i++){
            x_temp1+=1;
            y_temp1+=1;
            if(y_temp1>this.height || x_temp1>this.width)
                break;
            if(chess[x_temp1][y_temp1]==black_or_white)
                num++;
            else
                break;
        }
        if(num>max_temp&&num<5)
            max_temp=num;

        //judge right_up
        x_temp1=x_temp;
        y_temp1=y_temp;
        num=0;
        for(int i=1;i<5;i++){
            x_temp1+=1;
            y_temp1-=1;
            if(y_temp1<0 || x_temp1>this.width)
                break;
            if(chess[x_temp1][y_temp1]==black_or_white)
                num++;
            else
                break;
        }
        //judge left_down
        x_temp1=x_temp;
        y_temp1=y_temp;
        for(int i=1;i<5;i++){
            x_temp1-=1;
            y_temp1+=1;
            if(y_temp1>this.height || x_temp1<0)
                break;
            if(chess[x_temp1][y_temp1]==black_or_white)
                num++;
            else
                break;
        }
        if(num>max_temp&&num<5)
            max_temp=num;
        max_num=max_temp;
        return max_num;
    }

}
