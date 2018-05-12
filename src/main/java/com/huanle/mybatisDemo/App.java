package com.huanle.mybatisDemo;

import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class App {
	private static SqlSessionFactory sqlSessionFactory;
	private static Reader reader;

	static {
		try { 
			reader = Resources.getResourceAsReader("com/huanle/mapping/Configure.xml");
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

//	public static SqlSessionFactory getSession() {
//		return sqlSessionFactory;
//	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SqlSession session = sqlSessionFactory.openSession();
		try {
			
//			请注意，这种方式是用 SqlSession 实例来直接执行已映射的SQL语句：
//          session.selectOne("com.yihaomen.mybatis.models.UserMapper.selectUserByID", 1) 
//			其实还有更简单的方法，而且是更好的方法，使用合理描述参数和SQL语句返回值的接口（比如 IUserOperation.class），
//			这样现在就可以至此那个更简单，更安全的代码，没有容易发生的字符串文字和转换的错误
			
			User user = (User) session.selectOne("com.huanle.mapping.UserMapper.GetUserByID", 1);
			if (user != null) {
				String userInfo = "名字：" + user.getName() + ", 所属部门：" + user.getDept() + ", 主页：" + user.getWebsite();
				System.out.println(userInfo);
			}
		} finally {
			session.close();
		}
	}
}
