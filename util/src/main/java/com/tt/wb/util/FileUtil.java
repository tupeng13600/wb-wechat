package com.tt.wb.util;

import org.springframework.core.io.support.PropertiesLoaderUtils;

import java.io.*;
import java.util.Properties;

/**
 * @author 作者 peng.tu
 * @version 创建时间：2015年10月21日
 */
public class FileUtil {

	/**
	 * 读取配置文件
	 * 
	 * @return
	 */
	public static Properties getProperties(String pName) {
		Properties properties = null;
		try {
			properties = PropertiesLoaderUtils.loadAllProperties(pName);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return properties;
	}
		
	/**
	 * 删除文件
	 * 
	 * @param filePathAndName
	 */
	public static void delFile(String filePathAndName) {
		try {
			File myDelFile = new File(filePathAndName);
			myDelFile.delete();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 移动文件
	 * 
	 * @param oldPath
	 *            源地址
	 * @param newPath
	 *            目的地址
	 */
	public static void moveFile(String oldPath, String newPath) {
		copyFile(oldPath, newPath);
		delFile(oldPath);
	}
	
	/**
	 * 复制文件
	 * 
	 * @param oldPath
	 * @param newPath
	 * @throws IOException
	 */
	public static void copyFile(String oldPath, String newPath) {
		FileOutputStream fs = null;

		String sep = System.getProperty("file.separator");
		String newFolderPath = newPath.substring(0, newPath.lastIndexOf(sep));

		File dirPath = new File(newFolderPath);
		if (!dirPath.exists()) {
			dirPath.mkdirs();
		}

		try {
			int byteread = 0;
			File oldfile = new File(oldPath);
			if (oldfile.exists()) { // 文件存在时
				InputStream inStream = new FileInputStream(oldPath); // 读入原文件
				fs = new FileOutputStream(newPath); // 输出文件
				byte[] buffer = new byte[1024];
				while ((byteread = inStream.read(buffer)) != -1) {
					fs.write(buffer, 0, byteread);
				}
				inStream.close();
				fs.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * 读取文件
	 * @param buffer
	 * @param filePath
	 * @throws IOException
	 */
	private static void readToBuffer(StringBuffer buffer, String filePath) throws IOException {
        InputStream is = new FileInputStream(filePath);
        String line; // 用来保存每行读取的内容
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        line = reader.readLine(); // 读取第一行
        while (line != null) { // 如果 line 为空说明读完了
            buffer.append(line); // 将读到的内容添加到 buffer 中
            buffer.append("\n"); // 添加换行符
            line = reader.readLine(); // 读取下一行
        }
        reader.close();
        is.close();
    }

	/**
	 * 将文本文件的内容读取到字符串中
	 * @param filePath
	 * @return
	 * @throws IOException
	 */
    public static String readFile(String filePath) throws IOException {
        StringBuffer sb = new StringBuffer();
        readToBuffer(sb, filePath);
        return sb.toString();
    }

}
