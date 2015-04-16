

package com.zldigital.utils;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

import javax.imageio.ImageIO;

/**
 * @author zhouxiang 
 * @version Apr 2, 2013 1:25:53 PM
 */
public class LoadImage {

	// 存放炸弹效果图片
	public static List<BufferedImage> bombImages = new ArrayList<BufferedImage>();
	// 存放坦克图片
	public static Vector<BufferedImage> tankImages = new Vector<BufferedImage>();
	public static List<BufferedImage> obstructions = new LinkedList<BufferedImage>();
	// 子弹图片
	public static BufferedImage bulletImage = null;
	// 背景图片
	public static BufferedImage backImage = null;
	// 图片路径
	public static String imagePath = System.getProperty("user.dir") + "/image";

	/**
	 * 初始化图片
	 * @throws IOException 
	 */
	public static void init() throws IOException{
		// 加载炸弹效果图片
		for (int i = 1; i <= 3; i++) {
			String path = imagePath + "/bomb_" + i + ".gif";
			bombImages.add(ImageIO.read(new File(path)));
		}
		// 加载坦克图片
		for (int i = 1; i <= 8; i++) {
			String path = imagePath + "/10" + i + ".png";
			tankImages.add(ImageIO.read(new File(path)));
		}
		for (int i = 1; i <= 2; i++) {
			String path = imagePath + "/2" + i + ".png";
			obstructions.add(ImageIO.read(new File(path)));
		}
		// 加载子弹图片
		String path = imagePath + "/94.png";
		bulletImage = ImageIO.read(new File(path));
		// 加载背景图片
		String path_back = imagePath + "/43.jpg";
		backImage = ImageIO.read(new File(path_back));
	}
	
	public static void main(String[] args) {
		try {
			init();
			System.out.println(tankImages.size());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
