package com.udianqu.wash.dao;

import com.udianqu.wash.core.MyBatisRepository;
import com.udianqu.wash.model.WashOrder;
import com.udianqu.wash.model.WashOrderExample;
import com.udianqu.wash.viewmodel.ReportVM;
import com.udianqu.wash.viewmodel.WashOrderVM;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

@MyBatisRepository
public interface WashOrderMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_wash_order
     *
     * @mbggenerated Mon Jul 20 12:31:35 CST 2015
     */
    int countByExample(WashOrderExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_wash_order
     *
     * @mbggenerated Mon Jul 20 12:31:35 CST 2015
     */
    int deleteByExample(WashOrderExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_wash_order
     *
     * @mbggenerated Mon Jul 20 12:31:35 CST 2015
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_wash_order
     *
     * @mbggenerated Mon Jul 20 12:31:35 CST 2015
     */
    int insert(WashOrder record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_wash_order
     *
     * @mbggenerated Mon Jul 20 12:31:35 CST 2015
     */
    int insertSelective(WashOrder record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_wash_order
     *
     * @mbggenerated Mon Jul 20 12:31:35 CST 2015
     */
    List<WashOrder> selectByExample(WashOrderExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_wash_order
     *
     * @mbggenerated Mon Jul 20 12:31:35 CST 2015
     */
    WashOrder selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_wash_order
     *
     * @mbggenerated Mon Jul 20 12:31:35 CST 2015
     */
    int updateByExampleSelective(@Param("record") WashOrder record, @Param("example") WashOrderExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_wash_order
     *
     * @mbggenerated Mon Jul 20 12:31:35 CST 2015
     */
    int updateByExample(@Param("record") WashOrder record, @Param("example") WashOrderExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_wash_order
     *
     * @mbggenerated Mon Jul 20 12:31:35 CST 2015
     */
    int updateByPrimaryKeySelective(WashOrder record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_wash_order
     *
     * @mbggenerated Mon Jul 20 12:31:35 CST 2015
     */
    int updateByPrimaryKey(WashOrder record);

	List<WashOrderVM> loadWashOrderlistWithPage(Map<String, Object> map);

	int loadWashOrderCount(Map<String, Object> map);

	WashOrderVM loadOrderObjById(@Param(value="id") Integer id);

	WashOrderVM selectByOrderNo(String orderNo);

	void updateByOrderNo(Map<String, Object> map);

	List<WashOrderVM> getOrderByMap(Map<String, Object> map);

	List<WashOrderVM> getOrderByUserId(Map<String, Object> map);

	int count(Map<String, Object> map);

	List<ReportVM> loadReportlist(Map<String, Object> map);
}