package org.crazyit.album.web;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

import java.util.*;
import java.io.*;

import org.crazyit.album.service.AlbumService;
import org.crazyit.album.exception.AlbumException;
import org.crazyit.album.web.base.BaseServlet;

/**
 * Description:
 * <br/>��վ: <a href="http://www.crazyit.org">���Java����</a>
 * <br/>Copyright (C), 2001-2014, Yeeku.H.Lee
 * <br/>This program is protected by copyright laws.
 * <br/>Program Name:
 * <br/>Date:
 * @author Yeeku.H.Lee kongyeeku@163.com
 * @version 1.0
 */
@WebServlet(urlPatterns="/proLogin")
public class ProLoginServlet extends BaseServlet
{
	public void service(HttpServletRequest request
		, HttpServletResponse response)throws IOException,ServletException
	{
		String name = request.getParameter("user");
		String pass = request.getParameter("pass");
		response.setContentType("text/javascript;charset=gbk");
		// ��ȡ�����
		PrintWriter out = response.getWriter();
		try
		{
			// ���idΪuser��pass����������
			out.println("$('#user,#pass').val('');");
			if (name != null && pass != null
				&& as.userLogin(name , pass))
			{
				HttpSession session = request.getSession(true);
				session.setAttribute("curUser" , name);
				out.println("alert('���Ѿ���¼�ɹ���')");
				out.println("$('#noLogin').hide(500)");
				out.println("$('#hasLogin').show(500)");
				// ���û�ȡ��Ƭ�б��ķ���
				out.println("onLoadHandler();");
			}
			else
			{
				out.println("alert('��������û��������벻���������ԣ�')");
			}
		}
		catch (AlbumException ex)
		{
			out.println("alert('" + ex.getMessage()
				+ "������û������������ԣ�')");
		}
	}
}