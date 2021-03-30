package cn.bdqn.utils;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public final class Utlis {
	private static String url = null;
	private static String user = null;
	private static String pwd = null;
	private static String driverClass = null;
	private static ThreadLocal<Connection> threadLocal = new ThreadLocal<Connection>();

	private Utlis() {

	}

	static {
		InputStream is = null;
		try {
			is = Utlis.class.getClassLoader().getResourceAsStream("jdbc.properties");
			Properties props = new Properties();
			props.load(is);

			url = props.getProperty("url");
			user = props.getProperty("username");
			pwd = props.getProperty("password");
			driverClass = props.getProperty("driverClass");
			Class.forName(driverClass);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

		public static Connection getConnection() throws Exception {
		Connection conn = threadLocal.get();
		if (conn == null) {
			conn = DriverManager.getConnection(url, user, pwd);
			threadLocal.set(conn);
		}
		return conn;
	}

	public static void beginTx() {
		// TODO Auto-generated method stub
		try {
			Connection conn = getConnection();
			conn.setAutoCommit(false);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

		public static void rollBack() {
		Connection conn = null;
		try {
			conn = getConnection();
			conn.rollback();
			threadLocal.remove(); 
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(null, null, conn);
		}
	}

	
	public static void commit() {
		Connection conn = null;
		try {
			conn = getConnection();
			conn.commit();
			threadLocal.remove(); 
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(null, null, conn);
		}
	}

		public static void executeUpdate(String sql, Object... params) {

		Connection conn = null;
		PreparedStatement ps = null;
		try {
					conn = getConnection();
					ps = conn.prepareStatement(sql);
					for (int i = 0; params != null && i < params.length; i++) {
				ps.setObject(i + 1, params[i]);
			}
					ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(null, ps, null);
		}
	}

		public static <T> T executeSingleObjectQuery(Class<T> clazz, String sql,
			Object... params) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		T t = null;
		try {
			
			conn = getConnection();
			
			ps = conn.prepareStatement(sql);
			
			for (int i = 0; params != null && i < params.length; i++) {
				ps.setObject(i + 1, params[i]);
			}
			
			rs = ps.executeQuery();
			if (rs.next()) {
				t = clazz.newInstance();

				ResultSetMetaData rsm = rs.getMetaData(); 
				Integer count = rsm.getColumnCount(); 
				for (int i = 0; i < count; i++) {
					String columnLabel = rsm.getColumnLabel(i + 1); 

					Field field = clazz.getDeclaredField(columnLabel);
					field.setAccessible(true);
					field.set(t, rs.getObject(columnLabel));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(null, ps, null);
		}
		return t;
	}

	
	public static <T> List<T> cx(Class<T> aClass, String sql, Object... params) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<T> ts = new ArrayList<T>();

		try {
			
			conn = getConnection();
			
			ps = conn.prepareStatement(sql);
			
			for (int i = 0; params != null && i < params.length; i++) {
				ps.setObject(i + 1, params[i]);
			}
			
			rs = ps.executeQuery();

			while (rs.next()) {
				T t=aClass.newInstance();
				ResultSetMetaData rsm = rs.getMetaData(); 
				Integer count = rsm.getColumnCount();
				for (int i = 0; i < count; i++) {
					String columnLabel = rsm.getColumnLabel(i + 1); 

					Field field = aClass.getDeclaredField(columnLabel);
					field.setAccessible(true);
					field.set(t, rs.getObject(columnLabel));
				
			}
				ts.add(t);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			close(null, ps, conn);
		}
		return ts;

	}

	
	public static void close(ResultSet rs, Statement st, Connection conn) {
		try {
			if (rs != null) {
				rs.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			if (st != null) {
				st.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			if (conn != null) {
				conn.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
