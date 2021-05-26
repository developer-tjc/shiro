package com.tang.mapper;

import com.tang.pojo.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface UserMapper {
    @Select("select * from my.a_user where login=#{uname}")
    User selAllUser(@Param("uname") String uname);
}
