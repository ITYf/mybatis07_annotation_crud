package top.ityf;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import top.ityf.dao.UserDao;
import top.ityf.domain.User;

import java.io.InputStream;
import java.util.List;

/**
 * ClassName:MybatisAnnoTest
 * Package: top.ityf
 * Description: 测试基于注解的 mybatis使用
 *
 * @Date: 2020/1/4 15:08
 * @Author: YanFei
 */
public class MybatisAnnoTest {
    public static void main(String[] args) throws Exception {
        //1、获取字节输入流
        InputStream in = Resources.getResourceAsStream("SqlMapConfig.xml");
        //2、根据字节输入流构建SqlSessionFactory
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
        //3、根据SqlSessionFactory生产一个SqlSession
        SqlSession sqlSession = factory.openSession();
        //4、使用SqlSession获取Dao的代理对象
        UserDao userDao = sqlSession.getMapper(UserDao.class);
        //5、执行Dao的方法
        List<User> users = userDao.findAll();
        for(User user:users){
            System.out.println(user);
        }
        //6、释放资源
        sqlSession.close();
        in.close();
    }
}
