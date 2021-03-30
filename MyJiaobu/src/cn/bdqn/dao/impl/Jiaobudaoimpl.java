package cn.bdqn.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import cn.bdqn.dao.Jiaobudao;
import cn.bdqn.domian.Jb;
import cn.bdqn.utils.Utlis;

public class Jiaobudaoimpl implements Jiaobudao{

	
//	查询脚步
	@Override
	public List<Jb> select() {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Jb> list = new ArrayList<Jb>();
			try {
				conn = Utlis.getConnection();
				String sql="select id,area,time,felling from Travel ";
				ps=conn.prepareStatement(sql);
				rs=ps.executeQuery();
				while(rs.next()){
					Integer Id=rs.getInt("id");
					String area=rs.getString("area");
					String felling=rs.getString("felling");
					Timestamp time=rs.getTimestamp("time");
					Jb jb=new Jb();
					jb.setId(Id);
					jb.setarea(area);
					jb.setfelling(felling);
					jb.settime(time);
					list.add(jb);
				}
			}catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}finally{
				Utlis.close(rs,ps,conn);
			}
			return list;
	}
	
	//添加脚步
	public void insert(Jb tjjb) {
		Connection conn=null;
		PreparedStatement ps=null;
		try {
			conn=Utlis.getConnection();
			String sql="insert into Travel(area,time,felling) values(?,?,?)";
			ps=conn.prepareStatement(sql);
			ps.setString(1, tjjb.getarea());
			ps.setTimestamp(2, new Timestamp(tjjb.gettime().getTime()));
			ps.setString(3, tjjb.getfelling());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}finally{
			Utlis.close(null,ps,conn);
		}
	}

	//删除脚步
	@Override
	public void delect(long id) {
		Connection conn=null;
		PreparedStatement ps=null;
		try {
			conn=Utlis.getConnection();
			String sql="delete from Travel where id = ?";
			ps=conn.prepareStatement(sql);
			ps.setLong(1, id);
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}finally{
			Utlis.close(null,ps,conn);
		}
	}
}
