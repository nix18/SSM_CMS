package com.moecola.cms.service;

import com.moecola.cms.domain.Account;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 业务层账户接口
 */
public interface IAccountService {
    /**
     * 注册
     * @param account
     * @return
     */
    public int signup(Account account) throws Exception;

    /**
     * 管理员注册
     * @param account
     * @return
     */
    public int adminSignup(Account account) throws Exception;
    /**
     * 通过ID查询单个用户
     * @param id
     * @return
     */
    public Account findAccountByID(Long id) throws Exception;

    /**
     * 通过用户名查询单个用户
     * @param name
     * @return
     */
    public Account findAccountByName(String name) throws Exception;

    /**
     * 登录
     * @param account
     */
    public boolean login(Account account) throws Exception;

    /**
     * 判断是否已登录
     * @param request
     * @return
     */
    public boolean islogin(HttpServletRequest request);

    /**
     * 修改用户信息
     * @param account
     * @return
     */
    public int updateinfo(Account account,HttpServletResponse response,HttpServletRequest request) throws Exception;

    /**
     * 销户
     * @param id
     * @return
     */
    public int deleteAccount(Long id,HttpServletResponse response,HttpServletRequest request) throws Exception;

}
