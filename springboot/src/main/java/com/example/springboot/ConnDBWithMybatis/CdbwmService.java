package com.example.springboot.ConnDBWithMybatis;

import com.example.springboot.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CdbwmService {
    @Autowired
    CdbwmMapper cdbwmMapper;
    public User selectUser() {
        return cdbwmMapper.selectUser();
    }
    public List selectUsers() {
        return cdbwmMapper.selectUsers();
    }
    public int returnNumbers(){
        return cdbwmMapper.returnNumbers();
    }
    public int returnNumber(){
        return cdbwmMapper.returnNumber();
    }
    public User selectUserById(String name){
        return cdbwmMapper.selectUserById(name);
    }
    public int returnNumberById(String name){
        return cdbwmMapper.returnNumberById(name);
    }
    public int insertDataSan(String name, String sex, int age){
        return cdbwmMapper.insertDataSan(name,sex,age);
    }

}
