package com.moecola.cms.controller;

import com.moecola.cms.domain.Account;
import com.moecola.cms.domain.Article;
import com.moecola.cms.service.IAccountService;
import com.moecola.cms.service.IArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

@Controller
public class IndexController {
    @Autowired
    private IArticleService articleService;
    @Autowired
    private IAccountService accountService;
    @RequestMapping("/")
    public String index(Model model){
        return "redirect:/1";
    }
    @RequestMapping("/{index}")
    public String listArticle(Model model,@PathVariable("index") Long index) throws Exception {
        model.addAttribute("Tags",articleService.Tags());
        int ceil = (int) Math.ceil(articleService.articleCount() / 5.0);
        long[] indexs=new long[ceil];
        Arrays.fill(indexs,0);
        for(int i=1;i<=ceil;i++){
            indexs[i-1]=i;
        }
        model.addAttribute("currindex",index);
        model.addAttribute("indexs",indexs);
        model.addAttribute("maxindex",ceil);
        model.addAttribute("articleCount",articleService.articleCount());
        model.addAttribute("currpath","/");
        List<Article> articles = articleService.findArticleByIndex(5*(index-1));
        LinkedHashMap<Article, Account> articlemap=new LinkedHashMap<Article, Account>();
        if(articles!=null){
            Account account=null;
            for (Article article:articles){
                try{
                    account=accountService.findAccountByID(article.getaByUid());
                    articlemap.put(article,account);
                }catch (Exception e){
                    account=new Account(-1,"用户已注销","用户不存在","");
                    articlemap.put(article,account);
                }
            }
        }
        model.addAttribute("articles",articlemap);//传入的文章哈希表
        return "index";
    }
    @RequestMapping("about")
    public String about(Model model){
        model.addAttribute("Tags",articleService.Tags());
        return "about";
    }
}
