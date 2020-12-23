package com.jiacool;
import com.jiacool.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;


public class mybatisTest {

//    测试代码
    @Test
    public void test1() throws IOException {
//        1.获取核心配置文件
        InputStream resourceAsStream = Resources.getResourceAsStream("SqlMapConfig.xml");
//        2.获取seqSession工厂对象
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
//        3.获取seqSession对象
        SqlSession sqlSession = sessionFactory.openSession();
//        4.执行sql语句 -- 查询
        List<Object> userList = sqlSession.selectList("userMapping.findAll");
//        5.打印结果
        System.out.println(userList);
//        6.释放资源
        sqlSession.close();
    }

    // 插入
    @Test
public void test2() throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("SqlMapConfig.xml");//获取核心配置文件
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);//获取seqSession工厂对象
        SqlSession sqlSession = sessionFactory.openSession();//获取sqlSession对象

    //      模拟User对象
        User user = new User();
        user.setUsername("alice");
        user.setPassword("1abc");

    //        4.执行slq语句  插入
            int i = sqlSession.insert("userMapping.save", user);
            System.out.println(i);
    //        注意：mybatis 执行更新操作  提交事务
        sqlSession.commit();
    //        5.关闭资源
        sqlSession.close();
    }

//    修改
    @Test
    public void test3() throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("SqlMapConfig.xml");//获取核心配置文件
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);//获取seqSession工厂对象
        SqlSession sqlSession = sessionFactory.openSession();//获取sqlSession对象

//      模拟User对象
        User user = new User();
        user.setId(6);
        user.setUsername("tom");
        user.setPassword("123");

//        4.执行slq语句  修改
        int i = sqlSession.update("userMapping.update", user);
        System.out.println(i);
//        注意：mybatis 执行更新操作  提交事务
        sqlSession.commit();
//        5.关闭资源
        sqlSession.close();
    }

    //删除
    @Test
    public void test4() throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("SqlMapConfig.xml");//获取核心配置文件
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);//获取seqSession工厂对象
        SqlSession sqlSession = sessionFactory.openSession();//获取sqlSession对象

//        4.执行slq语句  插入
        int i = sqlSession.update("userMapping.delete", 6);
        System.out.println(i);
//        注意：mybatis 执行更新操作  提交事务
        sqlSession.commit();
//        5.关闭资源
        sqlSession.close();
    }

    //产需一个User对象
    @Test
    public void test5() throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sessionFactory.openSession(true);//自动提交事务
        User user = sqlSession.selectOne("userMapping.findById", 1);
        System.out.println(user);
        sqlSession.close();
    }
}
