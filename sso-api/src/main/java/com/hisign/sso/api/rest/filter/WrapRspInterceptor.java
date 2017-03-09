/**
 * 
 */
package com.hisign.sso.api.rest.filter;

import java.io.IOException;
import java.io.OutputStream;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.ext.WriterInterceptor;
import javax.ws.rs.ext.WriterInterceptorContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hisign.sso.api.constant.UAOPConstant;

/**
 * 包装REST返回报文
 * @author chailiangzhi
 * @date 2017-3-9
 * 
 */
public class WrapRspInterceptor implements WriterInterceptor {

	/**
	 * 
	 */
	private final Logger logger = LoggerFactory.getLogger(getClass());

	/* (non-Javadoc)
	 * @see javax.ws.rs.ext.WriterInterceptor#aroundWriteTo(javax.ws.rs.ext.WriterInterceptorContext)
	 */
	@Override
	public void aroundWriteTo(WriterInterceptorContext writerInterceptorContext) throws IOException,
			WebApplicationException {
		OutputStream outStream = writerInterceptorContext.getOutputStream();
		OutputStreamDisplace outStreamDisplace = new OutputStreamDisplace();
		writerInterceptorContext.setOutputStream(outStreamDisplace);
		writerInterceptorContext.proceed();
		// 截获原始报文
		String rspMsg = new String(outStreamDisplace.getBytes(), UAOPConstant.CHARSET_NAME);
		logger.info("response body is: \n" + rspMsg + "\n");
		String rspMsgWrap = "{flag:\"0\",data:" + rspMsg + "}";
		logger.info("rspMsgWrap is: \n" + rspMsgWrap + "\n");

		// 输出包装后的报文到前端
		outStream.write(rspMsgWrap.getBytes(UAOPConstant.CHARSET_NAME));
		outStream.flush();
		if (writerInterceptorContext.getProperty("error") == null) {
			// logRspToDB(rspMsg);
		}

	}

}
