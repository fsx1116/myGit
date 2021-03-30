package cn.bdqn.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.bdqn.domian.Jb;
import cn.bdqn.servlet.Jiaobuservlet;
import cn.bdqn.servlet.impl.Jiaobuservletimpl;

@WebServlet("/shanchu")
public class Shanchu extends HttpServlet{

	Jiaobuservlet jiaobu = new Jiaobuservletimpl();
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doPost(req, resp);
	}
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		Long id=Long.parseLong(req.getParameter("id"));
		jiaobu.delect(id);
		resp.setContentType("text/html;charset=utf-8");
		resp.getWriter().write("删除成功！");
	}
}
