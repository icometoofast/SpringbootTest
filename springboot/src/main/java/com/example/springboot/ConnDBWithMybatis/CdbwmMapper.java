package com.example.springboot.ConnDBWithMybatis;

import com.example.springboot.entities.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

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
}
