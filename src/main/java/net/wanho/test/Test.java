package net.wanho.test;

import net.wanho.mapper.UserMapper;
import net.wanho.pojo.User;
import net.wanho.util.MybatisUtil;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import java.io.InputStream;
import java.util.List;

/**
 * Created by 十四 on 2019/6/5.
 */
public class Test {

    private SqlSession sqlSession;
    private UserMapper userMapper;

    @Before
    public void before() {
        /*//获取连接
        InputStream inputStream = Test.class.getClassLoader().getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory sf = new SqlSessionFactoryBuilder().build(inputStream);
        //创建会话
        sqlSession = sf.openSession();*/
        sqlSession=MybatisUtil.getSession();
        userMapper = sqlSession.getMapper(UserMapper.class);
   }

    @org.junit.Test
    public void testAddUser() {

        User user = new User(null,"zhangsan","zs");
        SqlSession session= MybatisUtil.getSession();
        SqlSession session1= MybatisUtil.getSession();
        System.out.println(session==session1);
        sqlSession.update("net.wanho.mapper.UserMapper.addUser", user);
        sqlSession.commit();
        MybatisUtil.closeSession();
    }

    @org.junit.Test
    public void selectAllUser() {
        List<User> users=sqlSession.selectList("net.wanho.mapper.UserMapper.selectAllUser");
        System.out.println(users);
        sqlSession.commit();
        MybatisUtil.closeSession();
    }

    @org.junit.Test
    public void selectUserById() {
        /*User user=sqlSession.selectOne("net.wanho.mapper.UserMapper.selectUserById",8);
        System.out.println(user);
        sqlSession.commit();
        MybatisUtil.closeSession();*/
        User user = userMapper.selectUserById(8);
        System.out.println(user);
    }

    @org.junit.Test
    public void selectUserByName() {
        User user = userMapper.selectUserByName("十四");
        System.out.println(user);
    }
    @org.junit.Test
    public void selectUserByName2() {
        List<User> users = userMapper.selectUserByName2("zhang");
        System.out.println(users);
    }

   /* @org.junit.Test
    public void selectUserByName3() {
        User user =new User();
        user.setUsername("%zhang%");
        List<User> users = userMapper.selectUserByName3(user);
        System.out.println(users);
    }*/

    //新增返回主键
    @org.junit.Test
    public void addUserReturnKey() {
        User user = new User(null,"张三","zs");
        userMapper.addUserReturnKey(user);
        sqlSession.commit();
        System.out.println(user.getId());
    }
    //新增返回主键2
    @org.junit.Test
    public void addUserReturnKey2() {
        User user = new User(null,"张三","zs");
        userMapper.addUserReturnKey(user);
        sqlSession.commit();
        System.out.println(user.getId());
    }
    @org.junit.Test
    public void deleteUser() {
        userMapper.deleteUser(33);
        sqlSession.commit();
    }
    @org.junit.Test
    public void updateUser() {
        User user = new User(32,"张三san","zs");
        userMapper.updateUser(user);
        sqlSession.commit();
    }
    //多个参数传递
    @org.junit.Test
    public void updateUser2() {
        User user = new User(32,"张三三","zs");
        userMapper.updateUser2("张三三1","zs",32);
        sqlSession.commit();
    }
    //多个复杂参数传递
    @org.junit.Test
    public void updateUser3() {
        User user = new User(32,"张三三","zs");
        userMapper.updateUser3(user,32);
        sqlSession.commit();
    }
    //基于注解的sql
    @org.junit.Test
    public void updateUser4() {
        User user = new User(32,"张三三1","zs");
        userMapper.updateUser4(user,32);
        sqlSession.commit();
    }
}
