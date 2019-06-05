package net.wanho.util;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;

/**
 * Created by 十四 on 2019/6/5.
 */
public class MybatisUtil {
   private static ThreadLocal<SqlSession> threadLocal;
   private static SqlSessionFactory sf;
   static {
       threadLocal=new ThreadLocal<SqlSession>();
       InputStream inputStream = MybatisUtil.class.getClassLoader().getResourceAsStream("mybatis-config.xml");
       sf= new SqlSessionFactoryBuilder().build(inputStream);
   }

    //获取连接
    public static SqlSession getSession() {
       SqlSession session=threadLocal.get();
       //session不存在
        if(session==null){
            //开启新的session,同时存到本地线程中
            session=sf.openSession();
            threadLocal.set(session);
        }
        return session;
    }

    //关闭连接
    public static void closeSession() {
       SqlSession session=threadLocal.get();
        if (session != null) {
            session.close();
            threadLocal.remove();
        }
    }
}
