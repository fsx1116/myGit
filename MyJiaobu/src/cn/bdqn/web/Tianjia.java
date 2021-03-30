package cn.bdqn.web;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.bdqn.domian.Jb;
import cn.bdqn.servlet.Jiaobuservlet;
import cn.bdqn.servlet.impl.Jiaobuservletimpl;

@WebServlet("/tianjia")
public class Tianjia extends HttpServlet{

	Jiaobuservlet jio = new Jiaobuservletimpl();
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doPost(req, resp);
	}
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String area=req.getParameter("area");
		String felling=req.getParameter("felling");
		if(area==null || "".equals(area.trim())){
			req.setAttribute("jiobu", "国家/地区不能为空");
			req.getRequestDispatcher("/tianjia.jsp").forward(req,resp);
		}else if(felling==null || "".equals(felling.trim())){
			req.setAttribute("jiobu", "所见所感不能为空");
			req.getRequestDispatcher("/tianjia.jsp").forward(req,resp);
		}else{
			Jb wk=new Jb();
			wk.setfelling(felling);
			wk.setarea(area);
			wk.settime(new Date());
			jio.insert(wk);
			resp.sendRedirect(req.getContextPath()+"/ye");
		}
	}
}
