package com.chunjin.crawer;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Log {

	public static SimpleDateFormat timeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	public static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

	public static void WriteStringToFile(String filePath, String content) {
		try {
			File file = new File(filePath);
			PrintStream ps = new PrintStream(new FileOutputStream(file));
			ps.append(content);// 在已有的基础上添加字符串
			ps.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void WriteStringToFile2(String filePath, String content) {
		try {
			FileWriter fw = new FileWriter(filePath, true);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write("content");// 往已有的文件上添加字符串
			bw.close();
			fw.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void WriteStringToFile3(String filePath) {
		try {
			PrintWriter pw = new PrintWriter(new FileWriter(filePath));
			pw.println("abc ");
			pw.println("def ");
			pw.println("hef ");
			pw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void WriteStringToFile4(String filePath) {
		try {
			RandomAccessFile rf = new RandomAccessFile(filePath, "rw");
			rf.writeBytes("op\r\n");
			rf.writeBytes("app\r\n");
			rf.writeBytes("hijklllll");
			rf.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void info(String content) {
		try {
			String filePath = "E:\\PatentLog\\Info_"+dateFormat.format(new Date())+".txt";
			FileOutputStream fos = new FileOutputStream(filePath, true);
			content = timeFormat.format(new Date()) +"   "+content+"\r\n";
			System.out.println(content);
			fos.write(content.getBytes());
			fos.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void error(String content) {
		try {
			String filePath = "E:\\PatentLog\\Error_"+dateFormat.format(new Date())+".txt";
			FileOutputStream fos = new FileOutputStream(filePath, true);
			content = timeFormat.format(new Date()) +"   "+content+"\r\n";
			System.out.println(content);
			fos.write(content.getBytes());
			fos.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void Record(String content) {
		try {
			String filePath = "E:\\PatentLog\\Success"+dateFormat.format(new Date())+".txt";
			FileOutputStream fos = new FileOutputStream(filePath, true);
			content = timeFormat.format(new Date()) +"   "+content+"\r\n";
			System.out.println(content);
			fos.write(content.getBytes());
			fos.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws InterruptedException {
		String filePath = "E:\\link.txt";
		// new WriteStringToTxt().WriteStringToFile(filePath);
		// new WriteStringToTxt().WriteStringToFile2(filePath);
		// new WriteStringToTxt().WriteStringToFile3(filePath);
		// new WriteStringToTxt().WriteStringToFile4(filePath);
		info("Hello");
	Thread.sleep(2000);
	info("Hello3333");
	}


}
