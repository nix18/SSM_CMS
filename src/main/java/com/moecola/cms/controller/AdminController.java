package com.moecola.cms.controller;

import com.moecola.cms.domain.Account;
import com.moecola.cms.domain.Article;
import com.moecola.cms.service.IAccountService;
import com.moecola.cms.service.IArticleService;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping("admin")
public class AdminController {
    @Autowired
    private IAccountService accountService;
    @Autowired
    private IArticleService articleService;
    @RequestMapping("/")
    public String adminmain(HttpServletRequest request,Model model){
        model.addAttribute("Tags",articleService.Tags());
        return "admin/admin";
    }
    //管理员通过id查用户
    @RequestMapping("findbyid/{uid}")
    public String findAccountById(@PathVariable("uid") Long uid, Model model,HttpServletRequest request,RedirectAttributes redirectAttributes) throws Exception {
        model.addAttribute("Tags",articleService.Tags());
        Account account=null;
        try{
            account = accountService.findAccountByID(uid);
        }catch (Exception e){
            redirectAttributes.addFlashAttribute("error",e.getMessage().replace("WARN：",""));
            return "redirect:"+request.getHeader("referer");
        }
        model.addAttribute("account",account);
        return "account_detail";
    }

    //管理员管理所有文章
    @RequestMapping("manageArticle")
    public String manageArticle(Model model){
        model.addAttribute("Tags",articleService.Tags());
        List<Article> articles = articleService.findAllArticle();
        model.addAttribute("articles",articles);
        return "admin/admin_article_manage";
    }

    //管理员修改任意用户信息
    @RequestMapping("updateinfo")
    public String updateinfo(Model model){
        model.addAttribute("Tags",articleService.Tags());
        return "admin/admin_account_update";
    }
    @RequestMapping("updateinfo/do")
    public String updateinfoDo(Account account, Model model, HttpServletResponse response, HttpServletRequest request, RedirectAttributes redirectAttributes) throws Exception {
        model.addAttribute("Tags",articleService.Tags());
        int updatestatus=0;
        try {
            updatestatus = accountService.updateinfo(account, response, request);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            redirectAttributes.addFlashAttribute("error","修改失败，原因：用户不存在或其他原因");
            return "redirect:"+request.getHeader("referer");
        }
        if(updatestatus!=0){
            redirectAttributes.addFlashAttribute("succ","修改成功");
            return "redirect:"+request.getHeader("referer");
        }
        model.addAttribute("error","修改失败，原因：其他原因");
        return "redirect:"+request.getHeader("referer");
    }
    //管理员销户
    @RequestMapping("deleteAccount/{uId}")
    public String deleteAccount(@PathVariable("uId") Long uid, HttpServletResponse response, HttpServletRequest request, RedirectAttributes redirectAttributes) throws Exception {
        redirectAttributes.addFlashAttribute("Tags",articleService.Tags());
        if(accountService.deleteAccount(uid,response,request)!=0){
            redirectAttributes.addFlashAttribute("succ","销户成功");
            return "redirect:"+request.getHeader("referer");
        }
        redirectAttributes.addFlashAttribute("error","销户失败，原因：用户不存在或其他原因");
        return "redirect:"+request.getHeader("referer");
    }
}
