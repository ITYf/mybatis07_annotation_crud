package top.ityf;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import top.ityf.dao.UserDao;
import top.ityf.domain.User;

import java.io.InputStream;
import java.lang.annotation.Retention;
import java.util.Date;
import java.util.List;

/**
 * ClassName:AnnotationCRUDTest
 * Package: top.ityf
 * Description:
 *
 * @Date: 2020/1/4 22:48
 * @Author: YanFei
 */
public class AnnotationCRUDTest {
    private InputStream in;
    private SqlSessionFactory factory;
    private SqlSession sqlSession;
    private UserDao userDao;

    @Before
    public void init() throws Exception {
        in = Resources.getResourceAsStream("SqlMapConfig.xml");
        factory = new SqlSessionFactoryBuilder().build(in);
        sqlSession = factory.openSession();
        userDao = sqlSession.getMapper(UserDao.class);
    }

    @After
    public void destory() throws Exception {
        sqlSession.commit();
        sqlSession.close();
        in.close();
    }

    @Test
    public void testSave() {
        User user = new User();
        user.setUsername("mybatis annotation");
        user.setAddress("北京市昌平区");

        userDao.saveUser(user);
    }

    @Test
    public void testUpdate() {
        User user = new User();
        user.setId(38);
        user.setUsername("mybatis annotation save");
        user.setSex("男");
        user.setAddress("深圳市南山区");
        user.setBirthday(new Date());

        userDao.updateUser(user);
    }

    @Test
    public void testDelete() {
        userDao.deleteUser(19);
    }

    @Test
    public void testFindOne() {
        User user = userDao.findById(34);
        System.out.println(user);
    }

    @Test
    public void testFindByName(){
//        List<User> users = userDao.findUserByName("%user%");
        List<User> users = userDao.findUserByName("user");
        for(User user:users){
            System.out.println(user);
        }
    }

    @Test
    public void testFindTotal(){
        Integer count = userDao.findTotal();
        System.out.println(count);
    }
}
