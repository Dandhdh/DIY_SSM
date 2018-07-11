package com.dyy.dao;

import com.dyy.pojo.User;
import com.dyy.pojo.UserExample;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;
import java.util.Map;

public interface UserMapper {

    int countByExample(UserExample example);

    int deleteByExample(UserExample example);

    int deleteByPrimaryKey(Integer userid);

    int insert(User record);

    int insertSelective(User record);

    List<User> selectByExampleWithBLOBs(UserExample example);

    List<User> selectByExample(UserExample example);

    User selectByPrimaryKey(Integer userid);

    int updateByExampleSelective(@Param("record") User record, @Param("example") UserExample example);

    int updateByExampleWithBLOBs(@Param("record") User record, @Param("example") UserExample example);

    int updateByExample(@Param("record") User record, @Param("example") UserExample example);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKeyWithBLOBs(User record);

    int updateByPrimaryKey(User record);


    /** 自定义查询接口 */

    User findByLogin(User user);

    List<User> findByParams(User u, RowBounds rowBound);

    int findAllCount(User u);

    List<User> findHotUser();

    List<User> findAllByQuery(User user) ;
    /** 分页查询 */
    List<User> list(Map<String, Object> map);
    /** 获取总数 */
    Long getTotal(Map<String, Object> map);
}