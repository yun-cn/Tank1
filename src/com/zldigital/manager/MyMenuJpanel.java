package com.zldigital.manager;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JPanel;

/**
 * @author zhouxiang 
 * @version Mar 24, 2013 11:32:22 AM
 */
public class MyMenuJpanel extends JPanel implements Runnable{

	int times = 0;
	
	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		g.fillRect(0, 0, 550, 450);
		if(times % 2 == 0){
			g.setColor(Color.yellow);
			Font font = new Font("华文新魏",Font.BOLD,30);
			g.setFont(font);
			g.drawString("Stage:1", 200, 220);
		}
	}

	// 闪烁效果
	public void run() {
		while(true){
			try {
				Thread.sleep(600);
			} catch (Exception e) {
				e.printStackTrace();
				// TODO: handle exception
			}
			times++;
			this.repaint();
		}
	}
	
}
