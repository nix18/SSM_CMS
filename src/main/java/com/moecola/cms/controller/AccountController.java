package com.moecola.cms.controller;

import com.moecola.cms.domain.Account;
import com.moecola.cms.service.IAccountService;
import com.moecola.cms.service.IArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("account")
public class AccountController {
    @Autowired
    private IAccountService accountService;
    @Autowired
    private IArticleService articleService;
    //表现层注册
    @RequestMapping("signup")
    public String signup(Model model){
        model.addAttribute("Tags",articleService.Tags());
        return "account_signup";
    }
    @RequestMapping("signup/do")
    public String signinDo(Account account,Model model,RedirectAttributes redirectAttributes,HttpServletRequest request){
        model.addAttribute("Tags",articleService.Tags());
        int signup = 0;
        try {
            signup = accountService.signup(account);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            redirectAttributes.addFlashAttribute("error","注册失败，原因："+e.getMessage().replace("WARN：",""));
            return "redirect:"+request.getHeader("referer");
        }
        if(signup!=0){
            redirectAttributes.addFlashAttribute("succ","注册成功");
            return "redirect:/account/relogin";
        }
        redirectAttributes.addFlashAttribute("error","注册失败，原因：其他原因");
        return "redirect:"+request.getHeader("referer");
    }

    //表现层登录
    @RequestMapping("login")
    public String login(Model model){
        model.addAttribute("Tags",articleService.Tags());
        return "relogin";
    }
    @RequestMapping("login/do")
    public String loginDo(Account account, HttpSession session, HttpServletRequest request,Model model,RedirectAttributes redirectAttributes) throws Exception {
        model.addAttribute("Tags",articleService.Tags());
        boolean islogin = false;
        try {
            islogin = accountService.login(account);
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error","登陆失败，原因："+e.getMessage().replace("WARN：",""));
            return "redirect:/account/relogin";
        }
        if(islogin){
            account=accountService.findAccountByName(account.getUsername());
            session.setAttribute("logined",account);
            System.out.println("INFO：检查状态"+request.getSession().getAttribute("logined"));
            session.setAttribute("islogin",accountService.islogin(request));
            redirectAttributes.addFlashAttribute("succ","登录成功");
            return "redirect:"+request.getHeader("referer");
        }
        redirectAttributes.addFlashAttribute("error","账号或密码错误");
        return "redirect:/account/relogin";
    }

    //表现层注销
    @RequestMapping("logout")
    public String logout(HttpSession session, Model model,HttpServletRequest request,RedirectAttributes redirectAttributes){
        //注销session
        model.addAttribute("Tags",articleService.Tags());
        session.invalidate();
        redirectAttributes.addFlashAttribute("succ","登出成功");
        return "redirect:"+request.getHeader("referer");
    }

    //表现层检查登陆状态
    @RequestMapping("islogin")
    public String isLogin(Model model){
        model.addAttribute("Tags",articleService.Tags());
        return "redirect:/account/relogin";
    }

    //表现层重新登陆
    @RequestMapping("relogin")
    public String reLogin(Model model){
        model.addAttribute("Tags",articleService.Tags());
        return "relogin";
    }
    //表现层修改自身信息
    @RequestMapping("updateinfo")
    public  String updateinfo(HttpServletRequest request,Model model) {
        model.addAttribute("Tags",articleService.Tags());
        Account logined= (Account) request.getSession().getAttribute("logined");
        Account account=null;
        try {
            account=accountService.findAccountByID(logined.getUid());
        } catch (Exception e) {
            model.addAttribute("error","账户不存在");
            return "info";
        }
        model.addAttribute("accountname",account.getUsername());
        model.addAttribute("accountprofile",account.getProfile());
        return "account_update";
    }
    @RequestMapping("updateinfo/do")
    public String updateinfoDo(Account account, Model model, HttpServletResponse response, HttpServletRequest request, RedirectAttributes redirectAttributes) throws Exception {
        model.addAttribute("Tags",articleService.Tags());
        int updatestatus=0;
        try {
            Account logined= (Account) request.getSession().getAttribute("logined");
            account.setUid(logined.getUid());//用户只能修改自己的信息
            updatestatus = accountService.updateinfo(account, response, request);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            redirectAttributes.addFlashAttribute("error","修改失败，原因："+e.getMessage().replace("WARN：",""));
            return "redirect:"+request.getHeader("referer");
        }
        if(updatestatus!=0){
            redirectAttributes.addFlashAttribute("succ","修改成功");
            return "redirect:"+request.getHeader("referer");
        }
        redirectAttributes.addFlashAttribute("error","修改失败，原因：其他原因");
        return "redirect:"+request.getHeader("referer");
    }
    @RequestMapping("detail/{uid}")
    public String accountDetail(@PathVariable("uid") Long uid,Model model){
        model.addAttribute("Tags",articleService.Tags());
        Account account=null;
        try{
            account=accountService.findAccountByID(uid);
            model.addAttribute("account",account);
            return "account_detail";
        }catch(Exception e){
            model.addAttribute("error","用户不存在");
            return "info";
        }
    }
}
