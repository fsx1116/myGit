package cn.bdqn.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.bdqn.domian.Jb;
import cn.bdqn.servlet.Jiaobuservlet;
import cn.bdqn.servlet.impl.Jiaobuservletimpl;

@WebServlet("/ye")
public class Chakan extends HttpServlet{

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
		List<Jb> jio = jiaobu.select();

		req.setAttribute("jio",jio);
		
		req.getRequestDispatcher("xianshi.jsp").forward(req, resp);
	}
}
