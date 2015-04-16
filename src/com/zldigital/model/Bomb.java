package com.zldigital.model;

import com.zldigital.abstracts.Tank;

/**
 * @author zhouxiang 
 * @version Mar 4, 2013 5:12:56 PM
 */
public class Bomb {

	private int x;
	private int y;
	// 炸弹生命周期 9
	private int life = 150;
	// 炸弹是否消失
	private boolean isLive = true; 
	// 当前坦克
	//private Tank tank = null;
	
	public Bomb(int x, int y) {
		super();
		this.x = x;
		this.y = y;
		//this.tank = tank;
	}
	
	/**
	 *  炸弹逐渐消失
	 */
	public void lifeIsDisappearing(){
		if(this.life > 0){
			this.life--;
		}
	}
	
	/**
	 * 删除当前子弹
	 */
	public void removeTankBomb(Tank tank){
		tank.getBombs().remove(this);
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

	public int getLife() {
		return life;
	}

	public void setLife(int life) {
		this.life = life;
	}

	public boolean isLive() {
		return isLive;
	}

	public void setLive(boolean isLive) {
		this.isLive = isLive;
	}
	
}
