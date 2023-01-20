package com.history;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import util.Mthos;

public class ListHistory {

	private static String path1;

	private static String path2;

	public ListHistory() {

		try {

			path1 = new File(".").getCanonicalPath() + Mthos.saberSeparador() + "log" + Mthos.saberSeparador()
					+ "output.txt";

			path2 = new File(".").getCanonicalPath() + Mthos.saberSeparador() + "log" + Mthos.saberSeparador()
					+ "output1.txt";

		}

		catch (Exception e) {

		}

	}

	public static void writeHistory(List<String> list) throws IOException {

		FileOutputStream fos = new FileOutputStream(path1);

		ObjectOutputStream oos = new ObjectOutputStream(fos);

		oos.writeObject(list);

		oos.close();

	}

	public static ArrayList<String> readHistory() throws ClassNotFoundException, IOException {

		FileInputStream fis = new FileInputStream(path1);

		ObjectInputStream ois = new ObjectInputStream(fis);

		@SuppressWarnings("unchecked")

		ArrayList<String> historyList = (ArrayList<String>) ois.readObject();

		ois.close();

		return historyList;

	}

	public static void writeHistory(HashMap<String, String> map) throws IOException {

		FileOutputStream fos = new FileOutputStream(path2);

		ObjectOutputStream oos = new ObjectOutputStream(fos);

		oos.writeObject(map);

		oos.close();

	}

	public static HashMap<String, String> readHistoryMap() throws ClassNotFoundException, IOException {

		FileInputStream fis = new FileInputStream(path2);

		ObjectInputStream ois = new ObjectInputStream(fis);

		@SuppressWarnings("unchecked")

		HashMap<String, String> historyMap = (HashMap<String, String>) ois.readObject();

		ois.close();

		return historyMap;

	}

	@SuppressWarnings("resource")

	public static void cleanHistory() throws IOException {

		new RandomAccessFile(path1, "rw").getChannel().truncate(1);

		new RandomAccessFile(path2, "rw").getChannel().truncate(1);

	}

}
