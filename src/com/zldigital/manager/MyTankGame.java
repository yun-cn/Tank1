package com.zldigital.manager;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import com.zldigital.model.Node;
import com.zldigital.utils.AePlayWave;
import com.zldigital.utils.Constant;
import com.zldigital.utils.Record;

/**
 * @author zhouxiang
 * @version Mar 1, 2013 11:26:01 PM
 */
public class MyTankGame extends JFrame implements ActionListener{
	// 绘画类
	MyJPanel myJPanel = null;
	// 开始提升Jpanel
	MyMenuJpanel myStartJpanel = null;
	
	// 菜单
	JMenuBar menuBar = null;
	JMenu menu = null;
	JMenuItem menuItem = null;
	JMenuItem menuItem_keepExit = null;
	JMenuItem menuItem_continuation = null;
	
	// 坐标信息
	private List<Node> nodeList = new ArrayList<Node>();
	
	public MyTankGame() {
		createMenu();
		myStartJpanel = new MyMenuJpanel();
		Thread thread = new Thread(myStartJpanel);
		thread.start();
		this.add(myStartJpanel);
		this.setSize(550, 450);
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private void createMenu() {
		// TODO Auto-generated method stub
		menuBar = new JMenuBar();
		menu = new JMenu("菜单");
		menuItem = new JMenuItem("开始游戏");
		menuItem.addActionListener(this);
		menuItem.setActionCommand("begin");
		menuItem_keepExit = new JMenuItem("保存退出");
		menuItem_keepExit.addActionListener(this);
		menuItem_keepExit.setActionCommand("keepExit");
		menuItem_continuation = new JMenuItem("恢复上次游戏");
		menuItem_continuation.addActionListener(this);
		menuItem_continuation.setActionCommand("continuation");
		menu.add(menuItem);
		menu.add(menuItem_keepExit);
		menu.add(menuItem_continuation);
		menuBar.add(menu);
		this.setJMenuBar(menuBar);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MyTankGame frame = new MyTankGame();
	}

	public void actionPerformed(ActionEvent e) {
		// 开始游戏
		if(e.getActionCommand().equalsIgnoreCase("begin")){
			if(myJPanel != null){
				return;
			}
			myJPanel = new MyJPanel();
			// 刷新面板
			Thread thread = new Thread(myJPanel);
			thread.start();
			// 声音播放
			AePlayWave aePlayWave = new AePlayWave(Constant.url+Constant.backMusic);
			aePlayWave.start();
			// 添加处理事件
			this.addKeyListener(myJPanel);
			this.add(myJPanel);
			this.remove(myStartJpanel);
			this.setVisible(true);
		// 保持退出
		}else if(e.getActionCommand().equalsIgnoreCase("keepExit")){
			Record.setEnemys(this.myJPanel.enemys);
			Record.setHero(this.myJPanel.hero);
			Record.keepGame();
			System.exit(0);
		}else if(e.getActionCommand().equalsIgnoreCase("continuation")){
			nodeList = Record.readTxt(Record.path);
			if(myJPanel != null){
				return;
			}
			myJPanel = new MyJPanel(nodeList);
			// 刷新面板
			Thread thread = new Thread(myJPanel);
			thread.start();
			// 添加处理事件
			this.addKeyListener(myJPanel);
			this.add(myJPanel);
			this.remove(myStartJpanel);
			this.setVisible(true);
		}
	}
}
