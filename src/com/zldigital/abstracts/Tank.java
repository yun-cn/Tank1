package com.zldigital.abstracts;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Vector;

import com.zldigital.model.Bomb;
import com.zldigital.model.Bullet;
import com.zldigital.model.Obstruction;
import com.zldigital.utils.Constant;

/**
 * @author zhouxiang
 * @version Mar 1, 2013 11:26:30 PM
 */
public abstract class Tank {

	public Tank(int x, int y) {
		// TODO Auto-generated constructor stub
		this.x = x;
		this.y = y;
	}

	// Tank横坐标
	public int x;
	// Tank纵坐标
	public int y;
	// 移动速度
	public int speed = 2;
	// 方向
	public int direct;
	// 颜色
	private int type;
	// 子弹
	private Bullet bullet = null;
	// 存放全部子弹
	private Vector<Bullet> bullets = new Vector<Bullet>();
	// 存放全部炸弹
	private Vector<Bomb> bombs = new Vector<Bomb>();
	// Tank是否活着
	private boolean isLive = true;

	/**
	 * 射击敌人
	 * 
	 * @param x
	 * @param y
	 * @param direct
	 */
	public void shot(int x, int y, int direct) {
		switch (direct) {
		case Constant.UP:
			bullet = new Bullet(x + 8, y, Constant.UP, this);
			break;
		case Constant.DOWN:
			bullet = new Bullet(x + 9, y + 35, Constant.DOWN, this);
			break;
		case Constant.LEFT:
			bullet = new Bullet(x - 13, y + 19, Constant.LEFT, this);
			break;
		case Constant.RIGHT:
			bullet = new Bullet(x + 25, y + 19, Constant.RIGHT, this);
			break;
		default:
			break;
		}
		Thread t = new Thread(bullet);
		t.start();
		this.bullets.add(this.bullet);
		// System.out.println("启动子弹");
	}

	/**
	 * Kill Tank 杀死坦克
	 * 
	 * @param bullet
	 *            子弹
	 * @param tank
	 *            坦克
	 */
	public boolean isKillTank(Bullet bullet, Tank tank) {
		// 是否击中坦克
		boolean isHit = false;
		// 判断坦克的方向
		switch (tank.getDirect()) {
		case Constant.UP:
		case Constant.DOWN:
			if (bullet.getX() > tank.getX() && bullet.getX() < tank.getX() + 40
					&& bullet.getY() > tank.getY()
					&& bullet.getY() < tank.getY() + 48) {
				// 坦克朝上或下被Kill,子弹和坦克同时消失
				tank.setLive(false);
				bullet.setLive(false);
				// 添加炸弹类
				Bomb bomb = new Bomb(tank.getX(), tank.getY());
				bombs.add(bomb);
				isHit = true;
			}
			break;
		case Constant.LEFT:
		case Constant.RIGHT:
			if (bullet.getX() > tank.getX() && bullet.getX() < tank.getX() + 48
					&& bullet.getY() > tank.getY()
					&& bullet.getY() < tank.getY() + 48) {
				// 坦克朝左或右被Kill,子弹和坦克同时消失
				tank.setLive(false);
				bullet.setLive(false);
				// 添加炸弹类
				Bomb bomb = new Bomb(tank.getX(), tank.getY());
				bombs.add(bomb);
				isHit = true;
			}
			break;
		default:
			break;
		}

		return isHit;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public int getDirect() {
		return direct;
	}

	public void setDirect(int direct) {
		this.direct = direct;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public Bullet getBullet() {
		return bullet;
	}

	public void setBullet(Bullet bullet) {
		this.bullet = bullet;
	}

	public Vector<Bullet> getBullets() {
		return bullets;
	}

	public void setBullets(Vector<Bullet> bullets) {
		this.bullets = bullets;
	}

	public boolean isLive() {
		return isLive;
	}

	public void setLive(boolean isLive) {
		this.isLive = isLive;
	}

	public Vector<Bomb> getBombs() {
		return bombs;
	}

	public void setBombs(Vector<Bomb> bombs) {
		this.bombs = bombs;
	}
}
