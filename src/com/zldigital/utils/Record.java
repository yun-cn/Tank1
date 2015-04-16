package com.zldigital.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.zldigital.model.Enemy;
import com.zldigital.model.Hero;
import com.zldigital.model.Node;

/**
 * @author zhouxiang
 * @version Mar 27, 2013 11:11:07 AM
 */
public class Record {

	// 敌人生命数
	private static int enemyLife = 20;
	// 英雄生命数
	private static int heroLife = 3;
	// 成绩
	private static int allScore = 0;
	// 敌人
	private static List<Enemy> enemys = new ArrayList<Enemy>();
	// 英雄
	private static Hero hero = null;
	// 坐标信息
	private static StringBuffer dataStr = new StringBuffer();
	// 文件路径
	public static String path = "D:\\tank.txt";
	// 坐标信息
	private static List<Node> nodeList = new ArrayList<Node>();
	
	/**
	 * 保存文件
	 */
	public static void keepGame() {
		/*if (allScore != 0) {
			dataStr.append(allScore).append("\n");
		}*/
		/*if(hero != null && hero.isLive()){
			dataStr.append(hero.x).append(Constant.split).append(hero.y)
			.append(Constant.split).append(hero.direct).append(
					Constant.split).append(hero.getType()).append("\n");
		}*/
		for (Enemy enemy : enemys) {
			if(enemy.isLive()){
				dataStr.append(enemy.x).append(Constant.split).append(enemy.y)
				.append(Constant.split).append(enemy.direct).append(
						Constant.split).append(enemy.getType()).append("\n");
			}
		}
		writeTxt(dataStr.toString(), path);
	}

	/**
	 * 写入文件
	 * @param data
	 */
	private static void writeTxt(String data, String path) {
		BufferedWriter writer = null;
		File file = new File(path);
		try {
			writer = new BufferedWriter(new FileWriter(file));
			writer.write(data);
			writer.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (writer != null) {
				try {
					writer.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * 读取文本信息
	 * @param path
	 */
	public static List<Node> readTxt(String path){
		BufferedReader reader = null;
		File file = new File(path);
		try {
			reader = new BufferedReader(new FileReader(file));
			String data = null;
			while((data = reader.readLine())!=null){
				String[] nodes = data.split(Constant.split);
				Node node = new Node();
				node.setX(ObjectUtils.isNull(nodes[0]) ? 0 : new Integer(nodes[0]));
				node.setY(ObjectUtils.isNull(nodes[1]) ? 0 : new Integer(nodes[1]));
				node.setDirect(ObjectUtils.isNull(nodes[2]) ? 0 : new Integer(nodes[2]));
				node.setType(ObjectUtils.isNull(nodes[3]) ? 0 : new Integer(nodes[3]));
				nodeList.add(node);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return nodeList;
	}

	public static int getEnemyLife() {
		return enemyLife;
	}

	public static void setEnemyLife(int enemyLife) {
		Record.enemyLife = enemyLife;
	}

	public static int getHeroLife() {
		return heroLife;
	}

	public static void setHeroLife(int heroLife) {
		Record.heroLife = heroLife;
	}

	public static void lossLifeOfEnemy() {
		// TODO Auto-generated method stub
		enemyLife--;
		allScore++;
	}

	public static void lossLifeOfHero() {
		// TODO Auto-generated method stub
		heroLife--;
	}

	public static int getAllScore() {
		return allScore;
	}

	public static void setAllScore(int allScore) {
		Record.allScore = allScore;
	}

	public static List<Enemy> getEnemys() {
		return enemys;
	}

	public static void setEnemys(List<Enemy> enemys) {
		Record.enemys = enemys;
	}

	public static Hero getHero() {
		return hero;
	}

	public static void setHero(Hero hero) {
		Record.hero = hero;
	}

	public static List<Node> getNodeList() {
		return nodeList;
	}

	public static void setNodeList(List<Node> nodeList) {
		Record.nodeList = nodeList;
	}
}
