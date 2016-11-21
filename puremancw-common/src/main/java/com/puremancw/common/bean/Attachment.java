/**
 * 
 */
package com.puremancw.common.bean;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 该类代表一个附件，用于HTTP请求中传送文件
 * <p>Tilte: Attachment</p>
 * @author puremancw
 * @date 2016年11月21日 上午10:34:16
 
 */
public class Attachment {
	
	private String fileName;
	
	private byte[] data;
	
	private String contentType = "application/octet-stream";

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	
	
}
