package com.zldigital.model;

import java.util.Vector;

import com.zldigital.abstracts.Tank;
import com.zldigital.utils.Constant;

/**
 * @author zhouxiang
 * @version Mar 2, 2013 11:39:23 PM
 */
public class Bullet implements Runnable{

	
	public Bullet(int x, int y, int direct, Tank tank) {
		super();
		this.x = x;
		this.y = y;
		this.direct = direct;
		this.tank = tank;
	}

	// 子弹横坐标
	private int x;
	// 子弹纵坐标
	private int y;
	// 方向
	private int direct;
	// speed速度
	private int speed = 3;
	// 消失的子弹
	private boolean isLive = true;
	// 当前坦克
	private Tank tank = null;
	
	public void run() {
		
		while (true) {
			switch (direct) {
			case Constant.UP:
				this.y -= this.speed;
				break;
			case Constant.DOWN:
				this.y += this.speed;
				break;
			case Constant.LEFT:
				this.x -= this.speed;
				break;
			case Constant.RIGHT:
				this.x += this.speed;
				break;
			default:
				break;
			}
			
			try {
				Thread.sleep(50);
			} catch (Exception e) {
				e.printStackTrace();
			}
			//System.out.println("让子弹飞一会 x: "+x+"  y: "+y);
			
			if(x < 0 || x > 550 || y < 0 || y > 450){
				// 删除出界子弹
				this.removeBullet();
				// 子弹消失
				isLive = false;
				break;
			}
		}
	}
	
	public boolean isObstruction(Vector<Obstruction> obstructions) {
		boolean b=false;
		for(int i = 0; i < obstructions.size(); i++){
			Obstruction obstruction = obstructions.get(i);
			if (this.getX()+16 > obstruction.getX() && this.getX() < obstruction.getX() + 24
					&& this.getY() + 29 > obstruction.getY()
					&& this.getY()  < obstruction.getY()+ 11) {
				// 坦克朝上或下被Kill,子弹和坦克同时消失
				//obstruction.setLive(false);
				this.setLive(false);
				b = true;
				obstructions.remove(obstruction);
				break;
			}
		}
		return b;
	}
	
	/**
	 * 删除当前子弹
	 */
	private void removeBullet(){
		this.tank.getBullets().remove(this);
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

	public int getDirect() {
		return direct;
	}

	public void setDirect(int direct) {
		this.direct = direct;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public boolean isLive() {
		return isLive;
	}

	public void setLive(boolean isLive) {
		this.isLive = isLive;
	}

	public Tank getTank() {
		return tank;
	}

	public void setTank(Tank tank) {
		this.tank = tank;
	}
}
