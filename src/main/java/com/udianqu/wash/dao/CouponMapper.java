package com.udianqu.wash.dao;

import com.udianqu.wash.core.MyBatisRepository;
import com.udianqu.wash.model.Coupon;
import com.udianqu.wash.model.CouponExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

@MyBatisRepository
public interface CouponMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_coupon
     *
     * @mbggenerated Mon Jul 20 12:31:35 CST 2015
     */
    int countByExample(CouponExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_coupon
     *
     * @mbggenerated Mon Jul 20 12:31:35 CST 2015
     */
    int deleteByExample(CouponExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_coupon
     *
     * @mbggenerated Mon Jul 20 12:31:35 CST 2015
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_coupon
     *
     * @mbggenerated Mon Jul 20 12:31:35 CST 2015
     */
    int insert(Coupon record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_coupon
     *
     * @mbggenerated Mon Jul 20 12:31:35 CST 2015
     */
    int insertSelective(Coupon record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_coupon
     *
     * @mbggenerated Mon Jul 20 12:31:35 CST 2015
     */
    List<Coupon> selectByExample(CouponExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_coupon
     *
     * @mbggenerated Mon Jul 20 12:31:35 CST 2015
     */
    Coupon selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_coupon
     *
     * @mbggenerated Mon Jul 20 12:31:35 CST 2015
     */
    int updateByExampleSelective(@Param("record") Coupon record, @Param("example") CouponExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_coupon
     *
     * @mbggenerated Mon Jul 20 12:31:35 CST 2015
     */
    int updateByExample(@Param("record") Coupon record, @Param("example") CouponExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_coupon
     *
     * @mbggenerated Mon Jul 20 12:31:35 CST 2015
     */
    int updateByPrimaryKeySelective(Coupon record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_coupon
     *
     * @mbggenerated Mon Jul 20 12:31:35 CST 2015
     */
    int updateByPrimaryKey(Coupon record);
}