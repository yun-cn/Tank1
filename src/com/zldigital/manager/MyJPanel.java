package com.zldigital.manager;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.zldigital.abstracts.Tank;
import com.zldigital.abstracts.DrawFactory;
import com.zldigital.model.Bomb;
import com.zldigital.model.Bullet;
import com.zldigital.model.Enemy;
import com.zldigital.model.Hero;
import com.zldigital.model.Node;
import com.zldigital.model.Obstruction;
import com.zldigital.utils.AePlayWave;
import com.zldigital.utils.Constant;
import com.zldigital.utils.LoadImage;
import com.zldigital.utils.Record;

/**
 * 1.重点：paintComponent方法在不断的执行绘画 2.上下左右移动 keyPressed 3.可以发射子弹，子弹最多发射数 Tank --->
 * shot 4.英雄坦克和敌克消失效果 killHeroTank(); killEnemyTank(); 爆炸效果 drawBomb 5.防止敌克重叠远动
 * 6.可以分关 7.可以玩游戏的时候暂停继续 8.可以记录玩家的成绩 9.可以记录玩家成绩 10.java如何操作声音文件
 * 
 * @author zhouxiang
 * @version Mar 3, 2013 12:52:58 AM
 */
class MyJPanel extends JPanel implements KeyListener, Runnable {
	// 存放全部敌人
	protected Vector<Enemy> enemys = new Vector<Enemy>();
	// 存放全部子弹
	private Vector<Bullet> bullets = null;
	// 存放全部炸弹
	private Vector<Bomb> bombs = new Vector<Bomb>();;
	// 存储障碍物
	private Vector<Obstruction> obstructions = new Vector<Obstruction>();
	// 英雄
	Hero hero = null;

	// 加载所需图片
	static {
		try {
			LoadImage.init();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public MyJPanel() {
		createHero();
		this.getenemyTank();
		this.getObstruction();
	}

	public MyJPanel(List<Node> nodes) {
		createHero();
		this.getObstruction();
		this.getenemyTank(nodes);
	}

	@Override
	protected void paintComponent(Graphics g) {
		// 初始化父类方法为在一幅新图显示之前视图区域是干净的。
		super.paintComponent(g);
		// 刷新重新绘制
		this.repaint();
		// 全局背景
		// g.fillRect(0, 0, 430, 330);
		g.drawImage(LoadImage.backImage, 0, 0, this.getWidth(), this
				.getHeight(), this);
		// 我的坦克自己画的
		if (this.hero.isLive()) {
			this.hero.setType(1);
			DrawFactory.drawTank(this.hero.getX(), this.hero.getY(), g,
					this.hero.getDirect(), this.hero.getType());
		}
		// 绘制敌人
		for (int i = 0; i < enemys.size(); i++) {
			Enemy enemy = enemys.get(i);
			// 隐藏——判断坦克是否活着（要知道面板在不断的绘画着,虽然enemy对象没有移出enemys,但会隐藏起来）
			if (enemy.isLive()) {
				DrawFactory.drawTank(enemy.getX(), enemy.getY(), g, enemy
						.getDirect(), enemy.getType());
				// 绘制子弹,取出子弹集
				drawBullet(g, enemy);
				// 绘制敌人炸弹
				drawBomb(g, enemy);
			}
		}
		// 绘制自己炸弹
		bombs = this.hero.getBombs();
		for (int i = 0; i < bombs.size(); i++) {
			drawBomb(g, this.hero);
		}

		// 绘制障碍物
		for (int i = 0; i < obstructions.size(); i++) {
			Obstruction obstruction = obstructions.get(i);
			if (obstruction.isLive()) {
				DrawFactory.drawObstruction(obstruction.getX(), obstruction
						.getY(), g, obstruction.getDirect(), obstruction
						.getType());
			}
		}
		// 绘制子弹,取出子弹集
		drawBullet(g, this.hero);
		/*
		 * // 显示敌人生命数 TankFactory.drawTank(130, 340, g, 0, 2);
		 * g.setColor(Color.black);
		 * g.drawString(String.valueOf(Record.getEnemyLife()), 170, 360); //
		 * 显示英雄坦克生命数 TankFactory.drawTank(230, 340, g, 0, 1);
		 * g.setColor(Color.black);
		 * g.drawString(String.valueOf(Record.getHeroLife()), 270, 360); //
		 * 击败敌人数量 TankFactory.drawTank(480, 30, g, 0, 2);
		 * g.setColor(Color.black);
		 * g.drawString(String.valueOf(Record.getAllScore()), 510, 50);
		 */
	}

	/**
	 * 创建英雄
	 */
	private void createHero() {
		hero = new Hero(190, 280);
		hero.setObstructions(obstructions);
	}
	
	/**
	 * 获取障碍物
	 */
	private void getObstruction() {
		// TODO Auto-generated method stub
		// 制作障碍物
		int i = 10;
		while (i-- != 0) {
			Obstruction obstruction = new Obstruction((i + 1) * 38, 200, 2, 2);
			this.obstructions.add(obstruction);
		}
	}

	/**
	 * 存储绘制敌人坦克，到用时在取出
	 * 
	 * @return
	 */
	private Vector<Enemy> getenemyTank() {
		// 制作4个敌人坦克
		int i = 4;
		while (i-- != 0) {
			Enemy enemy = new Enemy((i + 1) * 50, 100);
			enemy.setType(2);
			enemy.setDirect(0);
			Thread thread = new Thread(enemy);
			thread.start();
			enemys.add(enemy);
			enemy.setEnemys(enemys);
			enemy.setObstructions(obstructions);
			enemy.shot(enemy.getX(), enemy.getY(), enemy.getDirect());
		}

		return enemys;
	}

	/**
	 * 存储绘制敌人坦克，到用时在取出
	 * 
	 * @return
	 */
	private void addenemyTank() {
		// 制作敌人坦克
		if (Record.getEnemyLife() > 0) {
			Enemy enemy = new Enemy(0, 0);
			enemy.setType(2);
			enemy.setDirect(0);
			Thread thread = new Thread(enemy);
			thread.start();
			enemys.add(enemy);
			enemy.setObstructions(obstructions);
			enemy.setEnemys(enemys);
			enemy.shot(enemy.getX(), enemy.getY(), enemy.getDirect());
		}
	}

	/**
	 * 存储绘制敌人坦克，到用时在取出
	 * 
	 * @return
	 */
	private Vector<Enemy> getenemyTank(List<Node> nodes) {
		// 制作4个敌人坦克
		for (int i = 0; i < nodes.size(); i++) {
			Node node = nodes.get(i);
			Enemy enemy = new Enemy(node.getX(), node.getY());
			enemy.setDirect(node.getDirect());
			enemy.setType(node.getType());
			Thread thread = new Thread(enemy);
			thread.start();
			enemys.add(enemy);
			enemy.setObstructions(obstructions);
			enemy.setEnemys(enemys);
			enemy.shot(enemy.getX(), enemy.getY(), enemy.getDirect());
		}

		return enemys;
	}

	/**
	 * 绘制子弹效果
	 * 
	 * @param g
	 * @param tank
	 */
	private void drawBullet(Graphics g, Tank tank) {
		for (int i = 0; i < tank.getBullets().size(); i++) {
			Bullet bullet = tank.getBullets().get(i);
			// 隐藏——判断子弹是否活着（这里有从bullets集合中删除对象，只有这样子弹才能不断往集合中放，要知道子弹是可以无限的而坦克数量有限）
			if (bullet != null && bullet.isLive()) {
				g.drawImage(LoadImage.bulletImage, bullet.getX(),
						bullet.getY(), 30, 30, this);
				// g.draw3DRect(bullet.getX(), bullet.getY(), 2, 2, false);
			}
		}
	}

	/**
	 * 绘制坦克爆炸效果
	 * 
	 * @param g
	 * @param tank
	 */
	private void drawBomb(Graphics g, Tank tank) {
		for (int i = 0; i < tank.getBombs().size(); i++) {
			Bomb bomb = tank.getBombs().get(i);
			if (bomb.getLife() > 90) {
				g.drawImage(LoadImage.bombImages.get(0), bomb.getX(), bomb
						.getY(), 48, 48, this);
			} else if (bomb.getLife() > 40) {
				g.drawImage(LoadImage.bombImages.get(1), bomb.getX(), bomb
						.getY(), 48, 48, this);
			} else {
				g.drawImage(LoadImage.bombImages.get(2), bomb.getX(), bomb
						.getY(), 48, 48, this);
			}
			// 声音播放
			if (bomb.getLife() == 200) {
				AePlayWave aePlayWave = new AePlayWave(Constant.url
						+ "/image/bomb.wav");
				aePlayWave.start();
			}
			// 炸弹逐渐消失
			bomb.lifeIsDisappearing();
			// 炸弹消失
			if (bomb.getLife() == 0) {
				// 删除Hero中 Vector<Bomb> 集合中的炸弹
				bomb.removeTankBomb(tank);
			}
		}
	}

	/**
	 * 按键控制方法
	 * 
	 * @param e
	 */
	public void keyPressed(KeyEvent e) {
		// 坦克向上
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			hero.setDirect(Constant.UP);
			hero.setStatus("upMoving");
		}
		// 坦克向下
		if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			hero.setDirect(Constant.DOWN);
			hero.setStatus("downMoving");
		}
		// 坦克向左
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			hero.setDirect(Constant.LEFT);
			hero.setStatus("leftMoving");
		}
		// 坦克向右
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			hero.setDirect(Constant.RIGHT);
			hero.setStatus("rightMoving");
		}
		// 打出炮弹 x 键
		if (e.getKeyCode() == KeyEvent.VK_X) {
			// 发射子弹 并 限制存储子弹数量
			if (this.hero.getBullets().size() < Constant.bulletNumber
					&& this.hero.isLive()) {
				// 创建子弹
				this.hero.shot(hero.getX(), hero.getY(), hero.getDirect());
			}
		}
	}

	public void keyReleased(KeyEvent e) {
		// 坦克向上
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			hero.setStatus("upStand");
		}
		// 坦克向下
		if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			hero.setStatus("downStand");
		}
		// 坦克向左
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			hero.setStatus("leftStand");
		}
		// 坦克向右
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			hero.setStatus("rightStand");
		}
	}

	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
	}

	/**
	 * 每隔25秒去重绘
	 */
	public void run() {
		while (true) {
			try {
				Thread.sleep(25);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			
			// 子弹灭掉英雄坦克
			killHeroTank();
			// 子弹灭掉敌人坦克
			killEnemyTank();
			// 是否触碰障碍物
			isObstructionOfEnemy();
			isObstructionOfHero();
			// 游戏结束
			if (Record.getHeroLife() == 0){
				//JOptionPane.showMessageDialog(null, "GAME OVER","GAME OVER",JOptionPane.INFORMATION_MESSAGE);
				Object[] options ={ "退出游戏", "继续游戏" };
				int m = JOptionPane.showOptionDialog(this, "Game Over!", "标题",JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
				if(m == 0){
					Record.setHeroLife(-1);
					System.exit(0);
				}else{
					Record.setHeroLife(3);
					this.createHero();
				}
			}
		}
	}

	/**
	 * 子弹灭掉敌人坦克
	 */
	private void killEnemyTank() {
		// 判断子弹和坦克相撞
		for (int i = 0; i < this.enemys.size(); i++) {
			Enemy enemy = this.enemys.get(i);
			if (enemy.isLive()) {
				for (int j = 0; j < this.hero.getBullets().size(); j++) {
					Bullet bullet = this.hero.getBullets().get(j);
					if (bullet.isLive()) {
						// 是否击中坦克
						boolean isDie = this.hero.isKillTank(bullet, enemy);
						if (isDie) {
							Record.lossLifeOfEnemy();
							if (Record.getEnemyLife() > 0) {
								try {
									Thread.sleep(1500);
									this.addenemyTank();
								} catch (InterruptedException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}
						}
					}
				}
			}
		}
	}

	/**
	 * 子弹灭掉英雄坦克
	 */
	private void killHeroTank() {
		// 判断子弹和坦克相撞
		for (int i = 0; i < this.enemys.size(); i++) {
			Enemy enemy = this.enemys.get(i);
			if (this.hero.isLive() && enemy.isLive()) {
				for (int j = 0; j < enemy.getBullets().size(); j++) {
					Bullet bullet = enemy.getBullets().get(j);
					if (bullet.isLive()) {
						// 是否击中坦克
						boolean isDie = enemy.isKillTank(bullet, this.hero);
						if (isDie) {
							Record.lossLifeOfHero();
							if (Record.getHeroLife() > 0) {
								try {
									Thread.sleep(1500);
									createHero();
								} catch (InterruptedException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}
						}
						
					}
				}
			}
		}
	}

	/**
	 * 判断敌人子弹是否触碰障碍物
	 */
	private void isObstructionOfEnemy() {
		// 判断子弹和障碍物相撞
		for (int i = 0; i < this.enemys.size(); i++) {
			Enemy enemy = this.enemys.get(i);
			if (enemy.isLive()) {
				for (int j = 0; j < enemy.getBullets().size(); j++) {
					Bullet bullet = enemy.getBullets().get(j);
					if (bullet.isLive()) {
						// 是否触碰障碍物
						bullet.isObstruction(this.obstructions);
					}
				}
			}
		}
	}

	/**
	 * 判断英雄子弹是否触碰障碍物
	 */
	private void isObstructionOfHero() {
		// 判断子弹和障碍物相撞
		if (this.hero.isLive()) {
			for (int j = 0; j < this.hero.getBullets().size(); j++) {
				Bullet bullet = this.hero.getBullets().get(j);
				if (bullet.isLive()) {
					// 是否触碰障碍物
					bullet.isObstruction(this.obstructions);
				}
			}
		}
	}
}