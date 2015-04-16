package com.zldigital.model;

import java.util.Vector;

import com.zldigital.abstracts.Tank;
import com.zldigital.utils.Constant;

/**
 * @author zhouxiang
 * @version Mar 2, 2013 12:20:24 PM
 */
public class Hero extends Tank implements Runnable{
	private String status;
	// 存放障碍物
	private Vector<Obstruction> obstructions = new Vector<Obstruction>();

	public Hero(int x, int y) {
		super(x, y);
		Thread thread = new Thread(this);
		thread.start();
		this.setStatus("upStand");
	}

	/**
	 * 判断是否碰到了障碍物
	 */
	public boolean isTouchObstruction() {
		for (int j = 0; j < obstructions.size(); j++) {
			Obstruction obstruction = this.obstructions.get(j);
			/*System.out.println("this.x = " + this.x + " this.y = " + this.y
					+ " obstruction.getX() = " + obstruction.getX()
					+ " obstruction.getY() = " + obstruction.getY());*/
			if (this.direct == Constant.UP) {
				if (this.getX() + 35 > obstruction.getX()
						&& this.getX() < obstruction.getX() + 24
						&& this.getY() > obstruction.getY()
						&& this.getY() < obstruction.getY() + 19) {
					return true;
				}
			}
			
			if (this.direct == Constant.DOWN) {
				if (this.getX() + 35 > obstruction.getX()
						&& this.getX() < obstruction.getX() + 24
						&& this.getY()+70 > obstruction.getY()
						&& this.getY() < obstruction.getY() + 18) {
					return true;
				}
			}
			
			if (this.direct == Constant.RIGHT) {
				if (obstruction.x >= this.x && obstruction.x <= this.x + 50
						&& obstruction.y >= this.y
						&& obstruction.y <= this.y + 50) {
					return true;
				}
			}
			
			if (this.direct == Constant.LEFT) {
				if (obstruction.x + 38 >= this.x && obstruction.x <= this.x
						&& obstruction.y >= this.y
						&& obstruction.y <= this.y + 50) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * 向上移动
	 */
	public void upMove() {
		if (this.y > 0 && !this.isTouchObstruction()) {
			this.y -= this.speed;
		}
		
	}

	/**
	 * 向下移动
	 */
	public void downMove() {
		if (this.y < 350 && !this.isTouchObstruction()) {
			this.y += this.speed;
		}
	}

	/**
	 * 向左移动
	 */
	public void leftMove() {
		if (this.x > 0 && !this.isTouchObstruction()) {
			this.x -= this.speed;
		}
	}

	/**
	 * 向右移动
	 */
	public void rightMove() {
		if (this.x < 490 && !this.isTouchObstruction()) {
			this.x += this.speed;
		}
	}

	public Vector<Obstruction> getObstructions() {
		return obstructions;
	}

	public void setObstructions(Vector<Obstruction> obstructions) {
		this.obstructions = obstructions;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void run() {
		// TODO Auto-generated method stub
		while(true){
			// 左移动
			if(this.status.equals("leftMoving")){
				this.leftMove();
			}
			// 右移动
			if(this.status.equals("rightMoving")){
				this.rightMove();
			}
			// 上移动
			if(this.status.equals("upMoving")){
				this.upMove();
			}
			// 下移动
			if(this.status.equals("downMoving")){
				this.downMove();
			}
			
			try {
				Thread.sleep(35);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
