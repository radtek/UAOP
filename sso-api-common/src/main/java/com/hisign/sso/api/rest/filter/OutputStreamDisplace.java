package com.hisign.sso.api.rest.filter;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * REST输出流替换
 * @author chailiangzhi
 * @date 2015-12-26
 * 
 */
public class OutputStreamDisplace extends OutputStream {

	/**
	 * 输出缓存
	 */
	private final ByteArrayOutputStream buffer = new ByteArrayOutputStream();

	/* (non-Javadoc)
	 * @see java.io.OutputStream#write(int)
	 */
	@Override
	public void write(int i) throws IOException {
		buffer.write(i);
	}

	/* (non-Javadoc)
	 * @see java.io.OutputStream#write(byte[])
	 */
	@Override
	public void write(byte[] b) throws IOException {
		buffer.write(b);
	}

	/* (non-Javadoc)
	 * @see java.io.OutputStream#write(byte[], int, int)
	 */
	@Override
	public void write(byte[] b, int off, int len) throws IOException {
		buffer.write(b, off, len);
	}

	/**
	 * 获得持有的输出数据
	 * @return
	 */
	public byte[] getBytes() {
		return buffer.toByteArray();
	}

}
