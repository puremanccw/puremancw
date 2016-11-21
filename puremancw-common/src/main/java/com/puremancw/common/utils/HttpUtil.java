/**
 * 
 */
package com.puremancw.common.utils;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import com.puremancw.common.bean.Attachment;

/**
 * <p>Tilte: HttpUtil</p>
 * @author puremancw
 * @date 2016年11月18日 下午5:24:44
 
 */
public class HttpUtil {
	
	private static final String DEFAULT_CHARSET = "utf-8";
	
	public static String sendHttpRequest(String method, String url) {
		return url;
		
	}
	
	/**
	 * 发送普通的http请求
	 * @return
	 */
	public byte[] sentHttpRequestAndGetBytes(String method, Map<String, String> headerMap, String url, Map<String, String> params) {
		try{
			StringBuffer stringBuffer = new StringBuffer();
			for(String key : params.keySet()) {
				stringBuffer.append(key).append("=").append(URLEncoder.encode(params.get(key), DEFAULT_CHARSET)).append("&");
			}
			if(method.equals("GET") && params != null && params.size() > 0) {
				url = url + "?" + stringBuffer.substring(0, stringBuffer.length() - 1);
			}
			HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
			connection.setDoInput(true);
			connection.setDoOutput(true);
			connection.setRequestMethod(method);
			
			if(!ObjectUtil.isNull(headerMap)) {
				for(String headerName : headerMap.keySet()) {
					connection.setRequestProperty(headerName, headerMap.get(headerName));
				}
			}
			
			connection.connect();
			if(method.equals("POST") && params != null && params.size() > 0) {
				OutputStream outputStream = connection.getOutputStream();
				outputStream.write(stringBuffer.substring(0, stringBuffer.length() - 1).getBytes(DEFAULT_CHARSET));
				outputStream.flush();
			}
			return IOUtil.readStreamBytes(connection);
		} catch(Exception e) {
			throw new RuntimeException();
		}
	}
	
	/**
	 * 发送带文件的HTTP请求
	 * @param url
	 * @param params
	 * @param attachmentMap
	 * @return
	 * @throws IOException
	 */
	public static String sendHttpMultipartRequest(String url, Map<String, String[]> params, Map<String, Attachment[]> attachmentMap) throws IOException {
		return sendHttpMultipartRequest(new HashMap<String, String>(), url, DEFAULT_CHARSET, params, attachmentMap);
	}
	
	/**
	 * 发送带文件的HTTP请求
	 * 
	 * @param headerMap
	 * @param url
	 * @param charset
	 * @param params
	 * @param attachmentMap
	 * @return
	 * @throws IOException
	 */
	public static String sendHttpMultipartRequest(Map<String, String> headerMap, String url, String charset, Map<String, String[]> params, Map<String, Attachment[]> attachmentMap) throws IOException {
		final String enter = "\r\n";
		String BOUNDARY = "------HttpForward" + UUID.randomUUID().toString();
		HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
		connection.setDoInput(true);
		connection.setDoOutput(true);
		connection.setRequestMethod("POST");
		if(!ObjectUtil.isNull(headerMap)) {
			for(String headerName : headerMap.keySet()) {
				connection.setRequestProperty(headerName, headerMap.get(headerName));
			}
		}
		connection.setRequestProperty("connection", "Keep-Alive");
		connection.setRequestProperty("Content_Type", "multipart/form-data; boundary=" + BOUNDARY);
		OutputStream outputStream = connection.getOutputStream();
		if(params != null && params.size() > 0) {
			for(String key : params.keySet()) {
				String[] values = params.get(key);
				for(String value : values) {
					StringBuilder stringBuilder = new StringBuilder();
					stringBuilder.append("--").append(BOUNDARY).append(enter);
					stringBuilder.append("Content-Disposition: form-data;name=\"" + key + "\"" + enter + enter);
					byte[] format = stringBuilder.toString().getBytes(charset);
					outputStream.write(format);
					outputStream.write(value.getBytes(charset));
					outputStream.write(enter.getBytes(charset));
				}
			}
		}
		if(attachmentMap != null && attachmentMap.size() > 0) {
			for(String key : attachmentMap.keySet()) {
				Attachment[] attachments = attachmentMap.get(key);
				for(Attachment attachment : attachments) {
					StringBuilder stringBuilder = new StringBuilder();
					stringBuilder.append("--").append(BOUNDARY).append(enter);
					stringBuilder.append("Content-Disposition: form-data;name=\"" + key + "\";filename=\"" + attachment.getFileName() + "\"" + enter);
					stringBuilder.append("Content-Type:" + attachment.getContentType() + enter + enter);
					byte[] format = stringBuilder.toString().getBytes(charset);
					outputStream.write(format);
					outputStream.write(attachment.getData());
					outputStream.write(enter.getBytes(charset));
				}
			}
		}
		
		outputStream.write((enter + "--" + BOUNDARY + "--" + enter).getBytes(charset));
		outputStream.flush();
		return IOUtil.readStream(connection, charset);
		
	}
}
