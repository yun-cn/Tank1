package com.zldigital.abstracts;

import java.awt.Color;
import java.awt.Graphics;

import com.zldigital.utils.Constant;
import com.zldigital.utils.LoadImage;

/**
 * @author zhouxiang
 * @version Mar 27, 2013 11:28:43 AM
 */
public final class DrawFactory {

	/**
	 * 绘制坦克方法
	 * 
	 * @param x
	 *            横坐标
	 * @param y
	 *            纵坐标
	 * @param g
	 *            画笔
	 * @param direct
	 *            坦克朝的方向
	 * @param type
	 *            坦克类型（0：英雄）
	 */
	public static void drawTank(int x, int y, Graphics g, int direct, int type) {

		switch (type) {
		case Constant.HERO:
			Constant.TANKTYPE = Constant.HERO;
			break;
		case Constant.ENEMY:
			Constant.TANKTYPE = Constant.ENEMY;
			break;
		default:
			break;
		}

		if (Constant.TANKTYPE == Constant.HERO) {
			switch (direct) {
			case 0:
				// 向上
				g.drawImage(LoadImage.tankImages.get(4), x, y, 48, 48, null);
				break;
			case 1:
				// 向下
				g.drawImage(LoadImage.tankImages.get(6), x, y, 48, 48, null);
				break;
			case 2:
				// 向左
				g.drawImage(LoadImage.tankImages.get(7), x, y, 48, 48, null);
				break;
			case 3:
				// 向右
				g.drawImage(LoadImage.tankImages.get(5), x, y, 48, 48, null);
				break;
			default:
				break;
			}
		} else {
			switch (direct) {
			case 0:
				// 向上
				g.drawImage(LoadImage.tankImages.get(0), x, y, 48, 48, null);
				break;
			case 1:
				// 向下
				g.drawImage(LoadImage.tankImages.get(2), x, y, 48, 48, null);
				break;
			case 2:
				// 向左
				g.drawImage(LoadImage.tankImages.get(3), x, y, 48, 48, null);
				break;
			case 3:
				// 向右
				g.drawImage(LoadImage.tankImages.get(1), x, y, 48, 48, null);
				break;
			default:
				break;
			}
		}
		

	}

	public static void drawObstruction(int x, int y, Graphics g, int direct,
			int type) {
		switch (1) {
		case 1:
			g.drawImage(LoadImage.obstructions.get(0), x, y-22, 38, 35, null);
			break;
		case 2:
			g.drawImage(LoadImage.obstructions.get(1), x, y-22, 38, 35, null);
			break;

		default:
			break;
		}
		
	}

	/*switch (direct) {
	case 0:
		// 向上
		g.drawImage(LoadImage.tankImages.get(3), x, y, 48, 48, null);
		
		 * // 画出左轮胎 g.fill3DRect(x, y, 5, 30, false); // 画出右轮胎
		 * g.fill3DRect(x + 15, y, 5, 30, false); // 画出中间驾驶室 g.fill3DRect(x +
		 * 5, y + 5, 10, 20, false); // 画出天窗 g.fillOval(x + 5, y + 10, 10,
		 * 10); // 画出炮筒 g.drawLine(x + 10, y + 15, x + 10, y);
		 
		break;
	case 1:
		// 向下
		g.drawImage(LoadImage.tankImages.get(0), x, y, 48, 48, null);
		
		 * // 画出左轮胎 g.fill3DRect(x, y, 5, 30, false); // 画出右轮胎
		 * g.fill3DRect(x + 15, y, 5, 30, false); // 画出中间驾驶室 g.fill3DRect(x +
		 * 5, y + 5, 10, 20, false); // 画出天窗 g.fillOval(x + 5, y + 10, 10,
		 * 10); // 画出炮筒 g.drawLine(x + 10, y + 15, x + 10, y + 30);
		 
		break;
	case 2:
		// 向左
		g.drawImage(LoadImage.tankImages.get(1), x, y, 48, 48, null);
		
		 * // 画出左轮胎 g.fill3DRect(x, y, 30, 5, false); // 画出右轮胎
		 * g.fill3DRect(x, y + 15, 30, 5, false); // 画出中间驾驶室 g.fill3DRect(x +
		 * 5, y + 5, 20, 10, false); // 画出天窗 g.fillOval(x + 10, y + 5, 10,
		 * 10); // 画出炮筒 g.drawLine(x + 15, y + 10, x, y + 10);
		 
		break;
	case 3:
		// 向右
		g.drawImage(LoadImage.tankImages.get(2), x, y, 48, 48, null);
		
		 * // 画出左轮胎 g.fill3DRect(x, y, 30, 5, false); // 画出右轮胎
		 * g.fill3DRect(x, y + 15, 30, 5, false); // 画出中间驾驶室 g.fill3DRect(x +
		 * 5, y + 5, 20, 10, false); // 画出天窗 g.fillOval(x + 10, y + 5, 10,
		 * 10); // 画出炮筒 g.drawLine(x + 15, y + 10, x + 30, y + 10);
		 
		break;
	default:
		break;
	}*/
}
