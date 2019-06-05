package net.wanho.mapper;

import net.wanho.pojo.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * Created by 十四 on 2019/6/5.
 */
public interface UserMapper {
    List<User> selectAllUser();

    User selectUserById(Integer id);

    User selectUserByName(String name);

    List<User> selectUserByName2(String name);

    List<User> selectUserByName3(User user);

    void addUser(User user);

    void deleteUser(Integer id);

    void updateUser(User user);
    //多个参数传递
    void updateUser2(@Param("name") String name,@Param("pwd") String password,@Param("id") Integer id);
    //多个复杂参数传递
    void updateUser3(@Param("user") User user,@Param("id") Integer id);
    //基于注解的sql
    @Update("UPDATE USER SET username=#{user.name},password=#{user.password} where id=#{id}")
    void updateUser4(@Param("user") User user,@Param("id") Integer id);

    //新增返回主键
    void addUserReturnKey(User user);
    //新增返回主键2
    void addUserReturnKey2(User user);
}
