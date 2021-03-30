package cn.bdqn.dao;

import java.util.List;

import cn.bdqn.domian.Jb;

public interface Jiaobudao {

	//全部脚步
	public List<Jb> select();
	
	//添加脚步
	public void insert(Jb tjjb);
	
	//删除脚步
	public void delect(long id);
}
