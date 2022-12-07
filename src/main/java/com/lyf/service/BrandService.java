package com.lyf.service;

import com.lyf.mapper.BrandMapper;
import com.lyf.pojo.Brand;
import com.lyf.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class BrandService {
    SqlSessionFactory factory = SqlSessionFactoryUtils.getSqlSessionFactory();

    /**
     * 查询所有功能
     * @return
     */
    public List<Brand> selectAll(){
        //1.调用BrandMapper.selectAll()方法
        //2.获取对应sqlSession
        SqlSession sqlSession = factory.openSession();
        //3.获取BrandMapper
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);
        //4.调用方法
        List<Brand> brands=mapper.selectAll();
        //5.释放资源
        sqlSession.close();
        return brands;
    }

    /**
     * 添加
     * @param brand
     */
    public void add(Brand brand){
        //2.获取对应sqlSession
        SqlSession sqlSession = factory.openSession();
        //3.获取BrandMapper
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);

        //4.调用方法
        mapper.add(brand);

        //提交事务
        sqlSession.commit();

        //释放资源
        sqlSession.close();

    }


    /**
     * 根据id查询
     * @param id
     * @return
     */
    public Brand selectById(int id){
        //1.调用BrandMapper.selectById()方法
        //2.获取对应sqlSession
        SqlSession sqlSession = factory.openSession();
        //3.获取BrandMapper
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);
        //4.调用方法
       Brand brand=mapper.selectById(id);
        //5.释放资源
        sqlSession.close();

        return brand;

    }


    /**
     * 修改
     * @param brand
     */
    public void update(Brand brand){
        //2.获取对应sqlSession
        SqlSession sqlSession = factory.openSession();
        //3.获取BrandMapper
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);

        //4.调用方法
        mapper.update(brand);

        //提交事务
        sqlSession.commit();

        //释放资源
        sqlSession.close();
    }

}
