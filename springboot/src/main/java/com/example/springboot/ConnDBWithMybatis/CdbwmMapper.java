package com.example.springboot.ConnDBWithMybatis;

import com.example.springboot.withHtml.entities.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


@Mapper
public interface CdbwmMapper {
     User selectUser();
     List selectUsers();
     Integer returnNumbers();
     Integer returnNumber();
     User selectUserById(String name);
     Integer returnNumberById(String name);
     int insertDataSan(String name,String sex,int age);
     List<User> testTable(@Param("user") User user);
}
