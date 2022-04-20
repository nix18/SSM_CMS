package com.moecola.cms.service.impl;

import com.moecola.cms.dao.IAccountDao;
import com.moecola.cms.domain.Account;
import com.moecola.cms.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@Service("accountService")
public class AccountServiceImpl implements IAccountService {
    @Autowired
    private IAccountDao accountDao;
    @Override
    public int signup(Account account) throws Exception {
        System.out.println("INFO：业务层注册");
        int signup = 0;
        try {
            signup = accountDao.signup(account);
        } catch (Exception e) {
            throw new Exception("WARN：用户名已存在或数据库写入错误");
        }
        if(signup!=0){
            return signup;
        }
        throw new Exception("WARN：注册失败");
    }

    @Override
    public int adminSignup(Account account) throws Exception {
        System.out.println("INFO：业务层管理员注册");
        int signup = 0;
        try {
            signup = accountDao.adminSignup(account);
        } catch (Exception e) {
            throw new Exception("WARN：用户名已存在或数据库写入错误");
        }
        if(signup!=0){
            return signup;
        }
        throw new Exception("WARN：注册失败");
    }

    @Override
    public Account findAccountByID(Long uid) throws Exception {
        Account account=null;
        try {
            account = accountDao.findAccountById(uid).get(0);
        }catch (Exception e){
            throw new Exception("WARN：用户不存在");
        }
        return account;
    }

    @Override
    public Account findAccountByName(String name) throws Exception {
        System.out.println("INFO：业务层通过用户名查用户");
        Account account=null;
        try {
            account = accountDao.findAccountByName(name).get(0);
        }catch (Exception e){
            throw new Exception("WARN：用户不存在");
        }
        return account;
    }

    @Override
    public boolean login(Account account) throws Exception {
        String username=account.getUsername();
        String pwd;
        try {
            Account accountbyname = accountDao.findAccountByName(username).get(0);
            pwd = accountbyname.getPassword();
            if (account.getPassword().equals(pwd)) {
                System.out.println("INFO：用户ID：" + accountbyname.getUid() + " 用户名：" + accountbyname.getUsername() + "已登录");
                return true;
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
            throw new Exception("WARN：登录账户不存在");
        }
        return false;
    }

    @Override
    public boolean islogin(HttpServletRequest request) {
        if(request.getSession().getAttribute("logined")!=null){
            return true;
        }
        return false;
    }

    @Override
    public int updateinfo(Account account,HttpServletResponse response,HttpServletRequest request) throws Exception {
        int a = 0;
        try {
            Account accounttemp=findAccountByID(account.getUid());
            Account logined=(Account)request.getSession().getAttribute("logined");
            if (logined.getIsAdmin()==1) {
                if (account.getUid()!=logined.getUid()) {
                    accounttemp.setIsAdmin(account.getIsAdmin());
                }
            }
            if (account.getUsername().length() != 0) accounttemp.setUsername(account.getUsername());
            if (account.getProfile().length() != 0) accounttemp.setProfile(account.getProfile());
            if (account.getPassword().length() != 0) accounttemp.setPassword(account.getPassword());
            a=accountDao.updateinfo(accounttemp);
        }catch (Exception e){
            System.out.println(e.getMessage());
            throw new Exception("WARN：更新账户错误");
        }
        return a;
    }

    @Override
    public int deleteAccount(Long id,HttpServletResponse response,HttpServletRequest request) throws Exception {
        return accountDao.deleteAccount(id);
    }

}
