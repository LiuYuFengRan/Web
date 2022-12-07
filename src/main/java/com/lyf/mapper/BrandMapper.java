package com.lyf.mapper;

import com.lyf.pojo.Brand;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface BrandMapper {
    /**
     * 查询所有
     * @return
     */
    @Select("select * from tb_brand")
    @ResultMap("brandResultMap")
    List<Brand> selectAll();

    /**
     * 添加数据
     * @param brand
     */
    @Insert("insert into tb_brand values(null,#{brandName},#{companyName},#{ordered},#{description},#{status})")
    void add(Brand brand);

    /**
     * 根据id查询
     * @param id
     * @return
     */
    @ResultMap("brandResultMap")
    @Select("select * from tb_brand where id = #{id}")
    Brand selectById(int id);


    /**
     * 修改操作
     * @param brand
     */
    @ResultMap("brandResultMap")
    @Update("update tb_brand set brand_name =#{brandName},company_name =#{companyName},ordered =#{ordered},description =#{description}, status =#{status} where id=#{id}")
    void update(Brand brand);

    /**
     * 批量删除
     * @param ids
     */


    /*使用param注解映射名字*/
    void deleteByIds(@Param("ids") int []ids);

    /**
     * 分页查询
     * @param begin
     * @param size
     * @return
     */

  /*  分页查询：LIMTI
    参数一：开始索引 = (当前页码-1) *每页显示条数
    参数二：查询的条目数 = 每页显示条数*/

    @ResultMap("brandResultMap")
    @Select("select *from tb_brand limit #{begin},#{size}")
    List<Brand> selectByPage(@Param("begin") int begin,@Param("size") int size);

    /**
     * 查询总记录数
     * @return
     */

    @Select("select count(*) from tb_brand")
    int selectTotalCount();



    /**
     * 分页查询
     * @param begin
     * @param size
     * @return
     */

  /*  分页条件查询：LIMTI
    参数一：开始索引 = (当前页码-1) *每页显示条数
    参数二：查询的条目数 = 每页显示条数*/

    List<Brand> selectByPageAndCondition(@Param("begin") int begin,@Param("size") int size,@Param("brand") Brand brand);

    /**
     * 根据条件查询总记录数
     * @return
     */

    int selectTotalCountByCondition(Brand brand);


}
