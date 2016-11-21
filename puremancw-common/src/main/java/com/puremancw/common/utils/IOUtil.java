/**
 * 
 */
package com.puremancw.common.utils;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;

import org.omg.CORBA_2_3.portable.OutputStream;

/**
 * <p>Tilte: IOUtil</p>
 * @author puremancw
 * @date 2016年11月21日 上午9:57:32
 
 */
public class IOUtil {

	public static void writeStream(String content, String charset, OutputStream outputStream) throws IOException {
		if(content != null) {
			outputStream.write(content.getBytes(charset));
			outputStream.flush();
		}
	}
	
	public static String readStream(HttpURLConnection connection, String charset) throws IOException {
		if(connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
			return IOUtil.readStream(connection.getInputStream(), charset);

		} else {
			return IOUtil.readStream(connection.getErrorStream(), charset);
		}
	}
	
	public static String readStream(InputStream inputStream, String charset) throws IOException {
		byte[] result = readStreamBytes(inputStream);
		if(result == null) {
			return null;
		}
		return new String(result, charset);
	}
	
	public static byte[] readStreamBytes(HttpURLConnection connection) throws IOException {
		if(connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
			return IOUtil.readStreamBytes(connection.getInputStream());
		} else {
			return IOUtil.readStreamBytes(connection.getErrorStream());
		}
	}
	
	public static byte[] readStreamBytes(InputStream inputStream) throws IOException {
		byte[] cache = new byte[2048];
		int len;
		byte[] bytes = new byte[0];
		while ((len = inputStream.read(cache)) > 0) {
			byte[] temp = bytes;
			bytes = new byte[bytes.length + len];
			System.arraycopy(temp, 0, bytes, 0, temp.length);
			System.arraycopy(cache, 0, bytes, temp.length, len);
		}
		
		if(bytes.length == 0) {
			return new byte[0];
		}
		return bytes;
	}
}
