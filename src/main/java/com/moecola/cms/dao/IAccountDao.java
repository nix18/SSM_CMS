package com.moecola.cms.dao;

import com.moecola.cms.domain.Account;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 持久层账户接口
 */
@Repository("accountDao")
public interface IAccountDao {
    /**
     * 注册
     * @param account
     * @return
     */
    @Insert("insert into account(username,profile,password) values(#{username},#{profile},#{password})")
    public int signup(Account account);

    /**
     * 管理员注册
     * @param account
     * @return
     */
    @Insert("insert into account(username,profile,password,isAdmin) values(#{username},#{profile},#{password},#{isAdmin})")
    public int adminSignup(Account account);
    /**
     * 通过id查询单个用户
     * @param uid
     * @return
     */
    @Select("select * from account where uid = #{uid}")
    public List<Account> findAccountById(Long uid);

    /**
     * 通过username查询单个用户
     * @param username
     * @return
     */
    @Select("select * from account where username = #{username}")
    public List<Account> findAccountByName(String username);

    /**
     * 修改用户信息
     * @param account
     * @return
     */
    @Update("update account set password=#{password},profile=#{profile},username=#{username},isAdmin=#{isAdmin} where uid=#{uid}")
    public int updateinfo(Account account);

    /**
     * 销户
     * @param uid
     * @return
     */
    @Delete("delete from account where uid=#{uid}")
    public int deleteAccount(Long uid);
}
