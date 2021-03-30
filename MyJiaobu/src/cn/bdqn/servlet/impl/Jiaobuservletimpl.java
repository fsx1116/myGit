package cn.bdqn.servlet.impl;

import java.util.List;

import cn.bdqn.dao.Jiaobudao;
import cn.bdqn.dao.impl.Jiaobudaoimpl;
import cn.bdqn.domian.Jb;
import cn.bdqn.servlet.Jiaobuservlet;

public class Jiaobuservletimpl implements Jiaobuservlet{

	Jiaobudao jiao = new Jiaobudaoimpl();
	@Override
	//查询全部脚步
	public List<Jb> select() {
		return jiao.select();
	}
	
	//添加脚步
	@Override
	public void insert(Jb tjjb) {
		jiao.insert(tjjb);
	}

	//删除脚步
	@Override
	public void delect(long id) {
		jiao.delect(id);
		
	}

}
