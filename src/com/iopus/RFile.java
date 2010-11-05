package com.iopus;

import java.io.*;
import java.util.ArrayList;

/**
 * @脚本名 RFile.java
 * @功能 创建TXT文件并进行读、写、修改操作
 * @作者 周忆 zymaxs@126.com
 * @版本 v1.0
 * @创建时间 09-12-14 11:30
 * @最后修改时间 09-12-14 15:00
 */

public class RFile {
	public BufferedReader bufread;
	private File file;
	private String readString = "";
	private ArrayList<String> readArray;

	/**
	 * 初始化文件 如果该文件不存在将被新建
	 * 
	 * @param path
	 *            文件路径
	 */
	public RFile(String path, boolean autocratefile) {
		this.file = new File(path);
		if (!file.exists()) {
			if (autocratefile) {
				try {
					file.createNewFile();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.err.println(file + "新创建！");
			} else {
				System.err.println(file + "不存在！");
			}
		}

	}

	/**
	 * 读取文本文件.
	 * 
	 * @return 文件内容
	 */
	public String readTxtFile() {
		String read;
		FileReader fileread;
		try {
			fileread = new FileReader(file);
			bufread = new BufferedReader(fileread);
			int temp = 0;
			try {
				while ((read = bufread.readLine()) != null) {
					readString = readString + read + "\r\n";
					temp += 1;

				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// System.out.println("文件内容是:" + readString);

		return readString;
	}

	/**
	 * 按行读取文本文件.
	 * 
	 * @return 文件内容
	 */
	public ArrayList<String> readTxtFileToArrayList() {
		String read;
		FileReader fileread;
		this.readArray = new ArrayList<String>();
		try {
			fileread = new FileReader(file);
			bufread = new BufferedReader(fileread);
			int temp = 0;
			try {
				while ((read = bufread.readLine()) != null) {
					readArray.add(read);
					temp += 1;

				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// System.out.println("文件内容是:" + readString);

		return readArray;
	}

}
