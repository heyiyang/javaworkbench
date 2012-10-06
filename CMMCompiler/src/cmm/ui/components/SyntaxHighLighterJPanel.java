package cmm.ui.components;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.MutableAttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import javax.swing.text.rtf.RTFEditorKit;
import javax.swing.undo.CannotRedoException;
import javax.swing.undo.CannotUndoException;
import javax.swing.undo.UndoManager;

import cmm.functions.CMMToken;
import cmm.functions.GramParse;
import cmm.functions.WordParse;
import cmm.ui.MainFrame;

public class SyntaxHighLighterJPanel extends JPanel{
	private JTextPane textPane=new JTextPane();
	protected StyleContext m_context;
	protected DefaultStyledDocument m_doc;
	private MutableAttributeSet keyAttr, normalAttr,commentAttr,identAttr,intAttr,realAttr,errorAttr,stringAttr;
	private MutableAttributeSet inputAttributes = new RTFEditorKit().getInputAttributes();
	//
	private Vector<CMMToken> token=new Vector<CMMToken>();
	private boolean ctrl=false;
	private UnDomagr unDomagr=new UnDomagr();
	private boolean textChanged=false;
	
	//�����Զ���ȫ
	//private Complement complement=new Complement();
	
	public SyntaxHighLighterJPanel(){
		
		this.setLayout(new BorderLayout());
		this.add(textPane,BorderLayout.CENTER);
		m_context = new StyleContext();
		m_doc = new DefaultStyledDocument(m_context);
		textPane.setDocument(m_doc);
		
		textPane.getDocument().addUndoableEditListener(unDomagr);
		textPane.getDocument().addDocumentListener(new DocumentListener(){

			public void changedUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				textChanged=true;
			}

			public void insertUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				textChanged=true;
			}

			public void removeUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				textChanged=true;
			}
			
		});
		
//		this.addKeyListener(new KeyAdapter() {
//			public void keyReleased(KeyEvent ke) {
//				get();
//				parse();
//			}
//		});
		textPane.addKeyListener(new ZListener());
		textPane.setBackground(new Color(204,232,207));
		
		//����
		errorAttr = new SimpleAttributeSet();
		StyleConstants.setForeground(errorAttr, Color.RED);	//������ɫ
		StyleConstants.setUnderline(errorAttr,true);
		
		//int����
		intAttr = new SimpleAttributeSet();
		StyleConstants.setForeground(intAttr, new Color(47,132,97));	//������ɫ
		//real����
		realAttr = new SimpleAttributeSet();
		StyleConstants.setForeground(realAttr, new Color(47,132,97));	//������ɫ
		//�ַ�������
		stringAttr = new SimpleAttributeSet();
		StyleConstants.setForeground(stringAttr, new Color(163,73,164));	//������ɫ
		
		//��ʶ������
		identAttr = new SimpleAttributeSet();
		StyleConstants.setForeground(identAttr, Color.BLUE);	//������ɫ
		
		//�����ַ�
		
		
		// ����ؼ�����ʾ����
		keyAttr = new SimpleAttributeSet();
		StyleConstants.setForeground(keyAttr, new Color(141,35,65));	//������ɫ
		StyleConstants.setBold(keyAttr, true);
		
		//����ע����ʾ��ɫ
		commentAttr=new SimpleAttributeSet();
		StyleConstants.setForeground(commentAttr, Color.gray);
		
		// ����һ���ı���ʾ����
		normalAttr = new SimpleAttributeSet();
		StyleConstants.setForeground(normalAttr, Color.black);
		StyleConstants.setBackground(normalAttr, new Color(204,232,207));
		StyleConstants.setBold(normalAttr, true);	//���Ǵ���
		StyleConstants.setUnderline(normalAttr, false);	//��Ҫ�»���

		new MyThread().start();
	}
	private void start(){
		get();
		parse();
	}
	private void parse(){
		//��ȫ��Ⱦ��Ĭ����ɫ
		String textString=textPane.getText();
		m_doc.setCharacterAttributes(0, textString.length(), normalAttr, false);
		for(int i=0;i<token.size();i++){
			CMMToken currToken=token.get(i);
			int index=currToken.getCharIndex()-1;
			int length=currToken.getToken().length();
			m_doc.setCharacterAttributes(index, length, normalAttr, false);
			switch(currToken.getType()){
			case(0):
				m_doc.setCharacterAttributes(index, length, errorAttr, false);
				break;
			case(1):
				m_doc.setCharacterAttributes(index, length, intAttr, false);
				break;
			case(2):
				m_doc.setCharacterAttributes(index, length, realAttr, false);
				break;
			case(3):
				m_doc.setCharacterAttributes(index, length, identAttr, false);
				break;
			case(5):
				m_doc.setCharacterAttributes(index, length, keyAttr, false);
				break;
			case(6):
				m_doc.setCharacterAttributes(index, length, commentAttr, false);
				break;
			case(7):
				m_doc.setCharacterAttributes(index, length, stringAttr, false);
				break;
			default:

				m_doc.setCharacterAttributes(index, length, normalAttr, false);
			}
			
		}
		
		
	}
	
	//��ȡtoken
	private void get(){
		//�ʷ�����
		WordParse wordParse=new WordParse(textPane.getText());
		wordParse.start();
		CMMToken[] cmmTokens=wordParse.getCmmTokens();
		CMMToken[] errorTokens=wordParse.getErrorCMMTokens();
		int size=wordParse.getTokenAmount();
		token.removeAllElements();
		for(int i=0;i<size;i++){
			token.add(cmmTokens[i]);
		}
		size=wordParse.getErrorTokenAmount();
		for(int i=0;i<size;i++){
			token.add(errorTokens[i]);
		}
		
		//��ȡ�﷨�����Ĵ�����
		GramParse gramParse=new GramParse(MainFrame.textPane.getInputString(),wordParse.getCmmTokens(),wordParse.getTokenAmount());
		gramParse.start();
		
		ArrayList<CMMToken> gramErrorTokens=gramParse.getErrorTokens();
		for(int i=0;i<gramErrorTokens.size();i++){
			CMMToken token1=gramErrorTokens.get(i);
			token1.setType(0);
			token.add(token1);
		}

		//�Զ���ȫ
		//complement.setTokens(cmmTokens,wordParse.getTokenAmount());
	}
	
	

	//�½�һ���̣߳���ʱˢ��
	class MyThread extends Thread{

		public void run() {
			int i=0;
			while(true){
				try{
					sleep(200);	//sleep
					if(textChanged){	//�������ݸı��ˣ�������Ⱦɫ
						get();
						parse();
					}
					textChanged=false;
				}catch(InterruptedException e){

				}
			}
			
		}
	}
	
	//��������
	public int getLineCount() {
		int line=0;
		String string=textPane.getText();
		int length=string.length();
		for(int i=0;i<length;i++){
			if(string.charAt(i)=='\n') line++;
		}
		return line;
	}
	
	private class UnDomagr extends UndoManager{

		@Override
		public synchronized void redo() throws CannotRedoException {
			// TODO Auto-generated method stub
			super.redo();
		}

		@Override
		public synchronized void undo() throws CannotUndoException {
			// TODO Auto-generated method stub
			super.undo();
		}
		
	}
	
	private class ZListener implements KeyListener{

		public void keyPressed(KeyEvent e) {
			if(e.getKeyCode()==KeyEvent.VK_Z){
				if(e.isControlDown()) unDomagr.undo();
			}
		}

		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}

		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}

	public Vector<CMMToken> getToken() {
		return token;
	}
	
	public String getText(){
		return textPane.getText();
	}
	public void setText(String text){
		textPane.setText(text);
	}
}
