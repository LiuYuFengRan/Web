package com.lyf.service;

import com.lyf.pojo.Brand;
import com.lyf.pojo.PageBean;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BdService {
    /*
    查询所有
     */
    List<Brand> selectAll();

    /**
     * 添加数据
     * @param brand
     */
    void add(Brand brand);


    /**
     * 批量删除
     * @param ids
     */

    /*使用param注解映射名字*/
    void deleteByIds(int []ids);

    /**
     *分页查询
     * @param currentPage   当前页码
     * @param pageSize  每页展示条数
     * @return
     */
    PageBean<Brand> selectByPage(int currentPage,int pageSize);

    /**
     * 分页条件查询
     * @param currentPage
     * @param pageSize
     * @param brand
     * @return
     */
    PageBean<Brand> selectByPageAndCondition(int currentPage,int pageSize,Brand brand);

}
