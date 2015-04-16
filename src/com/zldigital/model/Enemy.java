package com.zldigital.model;

import java.util.Vector;

import com.zldigital.abstracts.Tank;
import com.zldigital.utils.Constant;

/**
 * @author zhouxiang
 * @version Mar 2, 2013 3:59:38 PM
 */
public class Enemy extends Tank implements Runnable {
	// 存放全部敌人
	private Vector<Enemy> enemys = new Vector<Enemy>();
	// 存放障碍物
	private Vector<Obstruction> obstructions = new Vector<Obstruction>();

	public Vector<Obstruction> getObstructions() {
		return obstructions;
	}

	public void setObstructions(Vector<Obstruction> obstructions) {
		this.obstructions = obstructions;
	}

	public Enemy(int x, int y) {
		super(x, y);
		// TODO Auto-generated constructor stub
	}

	/**
	 * 延时
	 */
	private void timelag() {
		try {
			Thread.sleep(50);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 判断是否碰到了别的敌人坦克
	 */
	public boolean isTouchOtherEnemy() {
		boolean b = false;

		switch (this.direct) {
		case Constant.UP:
			// 我的坦克向上
			// 取出所有的敌人坦克
			for (int i = 0; i < enemys.size(); i++) {
				// 取出第一个坦克
				Tank et = enemys.get(i);
				// 如果不是自己
				if (et != this) {
					// 如果敌人的方向是向下或者向上
					if (et.direct == Constant.UP || et.direct == Constant.DOWN) {
						// 左点
						if (this.x >= et.x && this.x <= et.x + 20
								&& this.y >= et.y && this.y <= et.y + 30) {
							return true;
						}
						if (this.x + 20 >= et.x && this.x + 20 <= et.x + 20
								&& this.y >= et.y && this.y <= et.y + 30) {
							return true;
						}
					}
					if (et.direct == Constant.RIGHT
							|| et.direct == Constant.LEFT) {
						if (this.x >= et.x && this.x <= et.x + 30
								&& this.y >= et.y && this.y <= et.y + 20) {
							return true;
						}
						if (this.x + 20 >= et.x && this.x + 20 <= et.x + 30
								&& this.y >= et.y && this.y <= et.y + 20) {
							return true;
						}
					}
				}
			}
			break;
		case Constant.RIGHT:
			// 坦克向右
			// 取出所有的敌人坦克
			for (int i = 0; i < enemys.size(); i++) {
				// 取出第一个坦克
				Tank et = enemys.get(i);
				// 如果不是自己
				if (et != this) {
					// 如果敌人的方向是向下或者向上
					if (et.direct == Constant.UP || et.direct == Constant.DOWN) {
						// 上点
						if (this.x + 30 >= et.x && this.x + 30 <= et.x + 20
								&& this.y >= et.y && this.y <= et.y + 30) {
							return true;
						}
						// 下点
						if (this.x + 30 >= et.x && this.x + 30 <= et.x + 20
								&& this.y + 20 >= et.y
								&& this.y + 20 <= et.y + 30) {
							return true;
						}
					}
					if (et.direct == Constant.RIGHT
							|| et.direct == Constant.LEFT) {
						if (this.x + 30 >= et.x && this.x + 30 <= et.x + 30
								&& this.y >= et.y && this.y <= et.y + 20) {
							return true;
						}
						if (this.x + 30 >= et.x && this.x + 30 <= et.x + 30
								&& this.y + 20 >= et.y
								&& this.y + 20 <= et.y + 20) {
							return true;
						}
					}
				}
			}
			break;
		case Constant.DOWN:
			// 坦克向下
			// 取出所有的敌人坦克
			for (int i = 0; i < enemys.size(); i++) {
				// 取出第一个坦克
				Tank et = enemys.get(i);
				// 如果不是自己
				if (et != this) {
					// 如果敌人的方向是向下或者向上
					if (et.direct == Constant.UP || et.direct == Constant.DOWN) {
						// 我的左点
						if (this.x >= et.x && this.x <= et.x + 20
								&& this.y + 30 >= et.y
								&& this.y + 30 <= et.y + 30) {
							return true;
						}
						// 我的右点
						if (this.x + 20 >= et.x && this.x + 20 <= et.x + 20
								&& this.y + 30 >= et.y
								&& this.y + 30 <= et.y + 30) {
							return true;
						}
					}
					if (et.direct == Constant.RIGHT
							|| et.direct == Constant.LEFT) {
						if (this.x >= et.x && this.x <= et.x + 30
								&& this.y + 30 >= et.y
								&& this.y + 30 <= et.y + 20) {
							return true;
						}

						if (this.x + 20 >= et.x && this.x + 20 <= et.x + 30
								&& this.y + 30 >= et.y
								&& this.y + 30 <= et.y + 20) {
							return true;
						}
					}
				}
			}
			break;
		case Constant.LEFT:
			// 向左
			// 取出所有的敌人坦克
			for (int i = 0; i < enemys.size(); i++) {
				// 取出第一个坦克
				Tank et = enemys.get(i);
				// 如果不是自己
				if (et != this) {
					// 如果敌人的方向是向下或者向上
					if (et.direct == Constant.UP || et.direct == Constant.DOWN) {
						// 我的上一点
						if (this.x >= et.x && this.x <= et.x + 20
								&& this.y >= et.y && this.y <= et.y + 30) {
							return true;
						}
						// 下一点
						if (this.x >= et.x && this.x <= et.x + 20
								&& this.y + 20 >= et.y
								&& this.y + 20 <= et.y + 30) {
							return true;
						}
					}
					if (et.direct == Constant.RIGHT
							|| et.direct == Constant.LEFT) {
						// 上一点
						if (this.x >= et.x && this.x <= et.x + 30
								&& this.y >= et.y && this.y <= et.y + 20) {
							return true;
						}
						if (this.x >= et.x && this.x <= et.x + 30
								&& this.y + 20 >= et.y
								&& this.y + 20 <= et.y + 20) {
							return true;
						}
					}
				}
			}
			break;
		}

		return b;
	}

	/**
	 * 判断是否碰到了别的敌人坦克
	 */
	public boolean isTouchObstruction() {
		boolean b = false;

		switch (this.direct) {
		case Constant.UP:
			// 取出所有的敌人坦克
			for (int i = 0; i < enemys.size(); i++) {
				// 取出第一个坦克
				Tank et = enemys.get(i);
				for (int j = 0; j < obstructions.size(); j++) {
					Obstruction obstruction = this.obstructions.get(j);
					// 如果敌人的方向是向下或者向上
					if (et.direct == Constant.UP || et.direct == Constant.DOWN) {
						// 左点
						if (obstruction.x >= et.x && obstruction.x <= et.x + 20
								&& obstruction.y >= et.y && obstruction.y <= et.y + 30) {
							return true;
						}
						if (obstruction.x + 20 >= et.x && obstruction.x + 20 <= et.x + 20
								&& obstruction.y >= et.y && obstruction.y <= et.y + 30) {
							return true;
						}
					}
					if (et.direct == Constant.RIGHT
							|| et.direct == Constant.LEFT) {
						if (obstruction.x >= et.x && obstruction.x <= et.x + 30
								&& obstruction.y >= et.y && obstruction.y <= et.y + 20) {
							return true;
						}
						if (obstruction.x + 20 >= et.x && obstruction.x + 20 <= et.x + 30
								&& obstruction.y >= et.y && obstruction.y <= et.y + 20) {
							return true;
						}
					}
				}
			}
			break;
		case Constant.RIGHT:
			// 坦克向右
			// 取出所有的敌人坦克
			for (int i = 0; i < enemys.size(); i++) {
				// 取出第一个坦克
				Tank et = enemys.get(i);
				for (int j = 0; j < obstructions.size(); j++) {
					Obstruction obstruction = this.obstructions.get(j);
					// 如果敌人的方向是向下或者向上
					if (et.direct == Constant.UP || et.direct == Constant.DOWN) {
						// 上点
						if (obstruction.x + 30 >= et.x && obstruction.x + 30 <= et.x + 20
								&& obstruction.y >= et.y && obstruction.y <= et.y + 30) {
							return true;
						}
						// 下点
						if (obstruction.x + 30 >= et.x && obstruction.x + 30 <= et.x + 20
								&& obstruction.y + 20 >= et.y
								&& obstruction.y + 20 <= et.y + 30) {
							return true;
						}
					}
					if (et.direct == Constant.RIGHT
							|| et.direct == Constant.LEFT) {
						if (obstruction.x + 30 >= et.x && obstruction.x + 30 <= et.x + 30
								&& obstruction.y >= et.y && obstruction.y <= et.y + 20) {
							return true;
						}
						if (obstruction.x + 30 >= et.x && obstruction.x + 30 <= et.x + 30
								&& obstruction.y + 20 >= et.y
								&& obstruction.y + 20 <= et.y + 20) {
							return true;
						}
					}
				}
			}
			break;
		case Constant.DOWN:
			// 坦克向下
			// 取出所有的敌人坦克
			for (int i = 0; i < enemys.size(); i++) {
				// 取出第一个坦克
				Tank et = enemys.get(i);
				for (int j = 0; j < obstructions.size(); j++) {
					Obstruction obstruction = this.obstructions.get(j);
					// 如果敌人的方向是向下或者向上
					if (et.direct == Constant.UP || et.direct == Constant.DOWN) {
						// 我的左点
						if (obstruction.x >= et.x && obstruction.x <= et.x + 20
								&& obstruction.y + 30 >= et.y
								&& obstruction.y + 30 <= et.y + 30) {
							return true;
						}
						// 我的右点
						if (obstruction.x + 20 >= et.x && obstruction.x + 20 <= et.x + 20
								&& obstruction.y + 30 >= et.y
								&& obstruction.y + 30 <= et.y + 30) {
							return true;
						}
					}
					if (et.direct == Constant.RIGHT
							|| et.direct == Constant.LEFT) {
						if (obstruction.x >= et.x && obstruction.x <= et.x + 30
								&& obstruction.y + 30 >= et.y
								&& obstruction.y + 30 <= et.y + 20) {
							return true;
						}

						if (obstruction.x + 20 >= et.x && obstruction.x + 20 <= et.x + 30
								&& obstruction.y + 30 >= et.y
								&& obstruction.y + 30 <= et.y + 20) {
							return true;
						}
					}
				}
			}
			break;
		case Constant.LEFT:
			// 向左
			// 取出所有的敌人坦克
			for (int i = 0; i < enemys.size(); i++) {
				// 取出第一个坦克
				Tank et = enemys.get(i);
				for (int j = 0; j < obstructions.size(); j++) {
					Obstruction obstruction = this.obstructions.get(j);
					// 如果敌人的方向是向下或者向上
					if (et.direct == Constant.UP || et.direct == Constant.DOWN) {
						// 我的上一点
						if (obstruction.x >= et.x && obstruction.x <= et.x + 20
								&& obstruction.y >= et.y && obstruction.y <= et.y + 30) {
							return true;
						}
						// 下一点
						if (obstruction.x >= et.x && obstruction.x <= et.x + 20
								&& obstruction.y + 20 >= et.y
								&& obstruction.y + 20 <= et.y + 30) {
							return true;
						}
					}
					if (et.direct == Constant.RIGHT
							|| et.direct == Constant.LEFT) {
						// 上一点
						if (obstruction.x >= et.x && obstruction.x <= et.x + 30
								&& obstruction.y >= et.y && obstruction.y <= et.y + 20) {
							return true;
						}
						if (obstruction.x >= et.x && obstruction.x <= et.x + 30
								&& obstruction.y + 20 >= et.y
								&& obstruction.y + 20 <= et.y + 20) {
							return true;
						}
					}
				}
			}
			break;
		}

		return b;
	}

	public void run() {
		// TODO Auto-generated method stub
		while (true) {
			int b = 50;
			this.timelag();
			switch (this.getDirect()) {
			case Constant.UP:
				while (b-- != 0) {
					if (this.y > 0 && !this.isTouchOtherEnemy() && !this.isTouchObstruction()) {
						this.y -= this.speed;
						this.timelag();
					}
				}
				break;
			case Constant.DOWN:
				while (b-- != 0) {
					if (this.y < 300 && !this.isTouchOtherEnemy() && !this.isTouchObstruction()) {
						this.y += this.speed;
						this.timelag();
					}
				}
				break;
			case Constant.LEFT:
				while (b-- != 0) {
					if (this.x > 0 && !this.isTouchOtherEnemy() && !this.isTouchObstruction()) {
						this.x -= this.speed;
						this.timelag();
					}
				}
				break;
			case Constant.RIGHT:
				while (b-- != 0) {
					if (this.x < 400 && !this.isTouchOtherEnemy() && !this.isTouchObstruction()) {
						this.x += this.speed;
						this.timelag();
					}
				}
				break;
			default:
				break;
			}
			this.setDirect((int) (Math.random() * 4));

			// 退出线程不让线程成为僵尸线程,isLive 坦克是否活着
			if (this.isLive() == false) {
				System.out.println("退出");
				break;
			}

			if (this.getBullets().size() < 5) {
				this.shot(x, y, this.getDirect());
			}
		}
	}

	public Vector<Enemy> getEnemys() {
		return enemys;
	}

	public void setEnemys(Vector<Enemy> enemys) {
		this.enemys = enemys;
	}

}
