package com.clover.database;

import com.mysql.cj.Session;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;
import java.nio.channels.SeekableByteChannel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DatabaseUtil {
    private static SqlSessionFactory sessionFactory = null;
    static{
        //mybatis的配置文件
        String resource = "conf.xml";
        //使用类加载器加载mybatis的配置文件（它也加载关联的映射文件）
        InputStream is = DatabaseUtil.class.getClassLoader().getResourceAsStream(resource);
        //构建sqlSession的工厂
        sessionFactory = new SqlSessionFactoryBuilder().build(is);
    }

    public static int selectId(String table,Object param){
        SqlSession session = sessionFactory.openSession();
        Object re = session.selectOne(table,param);
        session.close();
        if(re == null){
            return 0;
        }else{
            return (Integer) re;
        }
    }

    public static void batchInsert(String table, List<Object> param){
        SqlSession session = sessionFactory.openSession();
        session.insert(table,param);
        session.commit();
        session.close();
    }

    public static void insert(String table, Object param){
        SqlSession session = sessionFactory.openSession();
        session.insert(table,param);
        session.commit();
        session.close();
    }

    public static void main(String[] args) {
        Map<String,Object> map = new HashMap<>();
        map.put("authorId",1);
        map.put("authorName","b");
        map.put("authorHost","c");
        map.put("sex","men");
        map.put("age",20);
        map.put("haoxiaoNum",12);
        map.put("fansNum",15);
        map.put("qiushiNum",5);
        List<Object> list = new ArrayList<Object>();
        list.add(map);
        insert("qiubai.insertAuthor",list);
        System.out.println();
    }
}
