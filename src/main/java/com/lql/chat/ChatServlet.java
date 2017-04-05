package com.lql.chat;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import net.sf.json.JSONObject;

/**
 * Servlet implementation class ChatServlet
 */
public class ChatServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ChatServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		String info = request.getParameter("info");
		System.out.println("输入参数：" + info);
		String requesturl = "http://www.tuling123.com/openapi/api?key=2207b4cd1a324b4ebb8d8f64976543ad&info=" + info;
		HttpGet request1 = new HttpGet(requesturl);
		HttpResponse response1 = HttpClients.createDefault().execute(request1);

		// 200即正确的返回码
		if (response1.getStatusLine().getStatusCode() == 200) {
			String result = EntityUtils.toString(response1.getEntity());
			System.out.println("返回结果：" + result);
			JSONObject jsonObject = JSONObject.fromObject(result);
			PrintWriter out = response.getWriter();
			
			out.write(jsonObject.getString("text"));
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
	// JAVA后台服务端代码（发送httpget请求，请求图灵机器人）
	/**
	 * 调用图灵机器人平台接口http://www.tuling123.com/openapi/cloud/access_api.jsp
	 * 需要导入的包：commons-logging-1.0.4.jar、 httpclient-4.3.1.jar、httpcore-4.3.jar
	 */
	/*
	 * public void getTulingApi() {
	 * 
	 * String INFO = URLEncoder.encode("北京今日天气", "utf-8"); String requesturl =
	 * "http://www.tuling123.com/openapi/api?key=注册激活获取的keykey&info="+INFO;
	 * HttpGet request = new HttpGet(requesturl); HttpResponse response =
	 * HttpClients.createDefault().execute(request);
	 * 
	 * //200即正确的返回码 if(response.getStatusLine().getStatusCode()==200){ String
	 * result = EntityUtils.toString(response.getEntity());
	 * System.out.println("返回结果："+result); } }
	 */
}
