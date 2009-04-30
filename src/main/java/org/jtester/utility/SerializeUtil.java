package org.jtester.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class SerializeUtil {
	public static <T> void serialize(T o, String filename) {
		SerializeUtil.mkdirs(filename);

		try {
			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename));
			out.writeObject(o);
			out.close();
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@SuppressWarnings("unchecked")
	public static <T> T deSerialize(Class<T> claz, String filename) {
		try {
			InputStream inputStream = SerializeUtil.isFileExisted(filename);
			ObjectInputStream in = new ObjectInputStream(inputStream);
			Object obj = in.readObject();
			in.close();
			return (T) obj;
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
	}

	private static InputStream isFileExisted(String filename) throws FileNotFoundException {
		if (filename.startsWith("classpath:")) {
			String file = filename.replaceFirst("classpath:", "");
			return SerializeUtil.class.getClassLoader().getResourceAsStream(file);
		} else {
			File file = new File(filename);
			if (!file.exists()) {
				throw new RuntimeException("object serializable file doesn't exist");
			}
			return new FileInputStream(file);
		}
	}

	private static void mkdirs(String filename) {
		filename = filename.replace('/', File.separatorChar);
		filename = filename.replace('\\', File.separatorChar);
		File fo = new File(filename);
		// 文件不存在,就创建该文件,先创建文件的目录
		if (!fo.exists()) {
			String path = filename.substring(0, filename.lastIndexOf(File.separatorChar));
			File pFile = new File(path);
			pFile.mkdirs();
		}
	}
}