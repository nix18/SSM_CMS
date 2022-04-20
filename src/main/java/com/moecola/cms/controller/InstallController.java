package com.moecola.cms.controller;

import com.moecola.cms.domain.Account;
import com.moecola.cms.service.IAccountService;
import com.moecola.cms.service.IArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Map;
import java.util.Properties;

@Controller
public class InstallController {
    @Autowired
    private IAccountService accountService;
    @Autowired
    private IArticleService articleService;
    @RequestMapping("install")
    public String installIndex(Model model){
        model.addAttribute("Tags",articleService.Tags());
        return "redirect:/install/1";
    }
    @RequestMapping("install/{id}")
    public String installCMS(@PathVariable("id") int id,Model model){
        model.addAttribute("Tags",articleService.Tags());
        model.addAttribute("id",id);
        return "install/install";
    }
    @RequestMapping("install/{id}/do")
    public String installCMSDo(@PathVariable("id") int id, HttpServletRequest request, HttpServletResponse response,Model model){
        model.addAttribute("Tags",articleService.Tags());
        if(id==1){
            if(request.getParameter("check")!=null) {
                if (request.getParameter("check").equals("1")) {
                    if (request.getParameter("check1") != null)
                        if (request.getParameter("check1").equals("1")) {
                            return "redirect:/install/2";
                        }
                }
            }
        }
        if(id==2){
            Account account=new Account();
            account.setUsername(request.getParameter("username"));
            account.setProfile(request.getParameter("profile"));
            account.setPassword(request.getParameter("password"));
            account.setIsAdmin(1);
            try {
                accountService.adminSignup(account);
            } catch (Exception e) {
                model.addAttribute("msg","管理员注册失败，原因："+e.getMessage().replace("WARN：",""));
                return "info";
            }
            return "redirect:/install/3";
        }
        if(id==3){
            return "redirect:/";
        }
        model.addAttribute("msg","初始化失败");
        return "info";
    }
    @RequestMapping("loaderio-0c42fd726b6e92a1ee9f3265ca8cbf4d")
    public String verifi(){
        return "loaderio-0c42fd726b6e92a1ee9f3265ca8cbf4d";
    }
}
