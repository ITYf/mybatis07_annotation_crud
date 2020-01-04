package top.ityf.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import top.ityf.domain.User;

import java.util.List;

/**
 * ClassName:UserDao
 * Package: top.ityf.dao
 * Description: 在 mybatis 中针对CRUD一共有四个注解
 * @Select @Insert @Update @Delete
 *
 * @Date: 2020/1/4 14:27
 * @Author: YanFei
 */
public interface UserDao {
    /**
     * 查询所有用户
     * */
    @Select("select * from user")
    List<User> findAll();

    /**
     * 保存用户
     * */
    @Insert("insert into user(username,address,sex,birthday) values(#{username},#{address},#{sex},#{birthday})")
    void saveUser(User user);

    /**
     * 更新用户
     * */
    @Update("update user set username=#{username},sex=#{sex},address=#{address},birthday=#{birthday} where id=#{id}")
    void updateUser(User user);

    /**
     * 删除用户
     * */
    @Delete("delete from user where id=#{id}")
    void deleteUser(Integer id);

    /**
     * 根据id查询用户信息
     * */
    @Select("select * from user where id=#{id}")
    User findById(Integer id);

    /**
     * 根据用户名称模糊查询
     * */
//    @Select("select * from user where username like #{username} ")
    @Select("select * from user where username like '%${value}%' ")
    List<User> findUserByName(String name);

    /**
     * 查询总记录条数
     * */
    @Select("select count(*) from user")
    int findTotal();
}
