package com.zldigital.model;
/**
 * @author zhouxiang 
 * @version Mar 6, 2013 10:09:26 AM
 */
public class Obstruction {
	
	public int x;
	public int y;
	public int type;
	public int direct;
	private boolean live = true;
	
	public Obstruction(int x, int y, int type, int direct) {
		super();
		this.x = x;
		this.y = y;
		this.type = type;
		this.direct = direct;
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

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getDirect() {
		return direct;
	}

	public void setDirect(int direct) {
		this.direct = direct;
	}

	public boolean isLive() {
		return live;
	}

	public void setLive(boolean live) {
		this.live = live;
	}
	
}
