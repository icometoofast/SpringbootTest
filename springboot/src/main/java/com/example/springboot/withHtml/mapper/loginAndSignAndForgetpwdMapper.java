package com.example.springboot.withHtml.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface loginAndSignAndForgetpwdMapper {
    int login(String email, String password) ;
    int insertEmailAndPwd(String email,String password);
    int isRepeatSign(String email);

}
