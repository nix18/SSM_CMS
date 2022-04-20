package com.moecola.cms.controller;

import com.moecola.cms.domain.*;
import com.moecola.cms.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;
import com.google.common.collect.Lists;

import com.moecola.cms.domain.MainComment;
import com.moecola.cms.domain.SubComment;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("article")
public class ArticleController {
    @Autowired
    private IArticleService articleService;
    @Autowired
    private IAccountService accountService;
    @Autowired
    private IResourceService resourceService;
    @Autowired
    private IMainCommentService mainCommentService;
    @Autowired
    private ISubCommentService subCommentService;

    @RequestMapping("manage")
    public String manageArticle(Model model,HttpServletRequest request){
        model.addAttribute("Tags",articleService.Tags());
        try {
            Account signin = (Account) request.getSession().getAttribute("logined");
            List<Article> articles = articleService.findArticleByUid(signin.getUid());
            model.addAttribute("articles",articles);
            return "article_manage";
        } catch (Exception e) {
            return "redirect:/account/relogin";
        }
    }
    @RequestMapping(value = "addArticle",method = {RequestMethod.GET,RequestMethod.POST})
    public String addArticle(Model model,HttpServletRequest request,HttpSession session){
        model.addAttribute("Tags",articleService.Tags());
        if(request.getMethod().equals("POST")) {
            List<Resource> resources = resourceService.findRbyAid(articleService.articleNextId());
            model.addAttribute("Tags",articleService.Tags());
            model.addAttribute("aId", articleService.articleNextId());
            Article article = new Article();
            try {
                articleService.addArticle(article, request, session);
            } catch (Exception e) {
                model.addAttribute("error", "初始化文章新增失败");
                e.printStackTrace();
                return "info";
            }
        }
        Account account=(Account)request.getSession().getAttribute("logined");
        Article article=articleService.userLastArticle(account.getUid()).get(0);
        Long aId=article.getaId();
        List<Resource> resources=resourceService.findRbyAid(aId);
        model.addAttribute("aId",aId);
        model.addAttribute("article",article);
        model.addAttribute("resources",resources);
        return "article_add";
    }
    @RequestMapping("addArticle/{aId}/do")
    public String addArticleDo(Article article,@PathVariable("aId") Long aId,Model model, HttpServletRequest request,RedirectAttributes redirectAttributes){
        model.addAttribute("Tags",articleService.Tags());
        try {
            article.setaId(aId);
            if(article.getaTag().length() == 0){
                article.setaTag("默认");
            }
            String url = articleService.updateArticle(article,request);
            redirectAttributes.addFlashAttribute("succ","保存文章成功");
            redirectAttributes.addFlashAttribute("url",url);
            return "redirect:"+request.getHeader("referer");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            redirectAttributes.addFlashAttribute("msg","保存文章失败,原因："+e.getMessage().replace("WARN：",""));
        }
        return "redirect:"+request.getHeader("referer");
    }
    @RequestMapping("{aId}")
    public String findArticleByID(@PathVariable("aId") Long aId, Model model){
        model.addAttribute("Tags",articleService.Tags());
        Article article= null;
        Account account=null;
        List<MainComment> mainComments = null;
        List<SubComment> subComments = null;
        LinkedHashMap<MainComment, List<SubComment>> comments = new LinkedHashMap<MainComment, List<SubComment>>();
        try {
            article = articleService.findArticleById(aId).get(0);
            mainComments = mainCommentService.findMainCommentByAID(aId);
            for(MainComment mainComment : mainComments){
                subComments = subCommentService.findSubCommentByScByCid(mainComment.getcId());
                comments.put(mainComment, subComments);
            }
        } catch (Exception e) {
            model.addAttribute("error","文章不存在或其他原因");
            return "info";
        }
        articleService.updateReadCount(aId);
        articleService.reloadtime(article.getaTime(),article.getaId());
        try{
            account = accountService.findAccountByID(article.getaByUid());
        }catch(Exception e){
            account=new Account(-1,"用户已注销","用户不存在","");
        }
        List<Article> next=articleService.articlenextone(aId);
        if(next.size()!=0){
            model.addAttribute("hasnext",next.get(0).getaId());
            model.addAttribute("nextname",next.get(0).getaTitle());
        }else {
            model.addAttribute("hasnext",(long)0);
        }
        List<Article> last=articleService.articlelastone(aId);
        if(last.size()!=0){
            model.addAttribute("haslast",last.get(0).getaId());
            model.addAttribute("lastname",last.get(0).getaTitle());
        }else {
            model.addAttribute("haslast",(long)0);
        }
        model.addAttribute("article",article);
        model.addAttribute("account",account);
        model.addAttribute("mainComments",mainComments);
        model.addAttribute("comments",comments);
        return "article_detail";
    }
    @RequestMapping("/search/{index}")
    public String searchArticle(HttpServletRequest request,Model model,@PathVariable("index") int index) throws Exception {
        model.addAttribute("Tags",articleService.Tags());
        List<Article> searchList = articleService.searchArticle(request.getParameter("search"));
        int ceil = (int) Math.ceil(searchList.size() / 5.0);
        long[] indexs=new long[ceil];
        Arrays.fill(indexs,0);
        for(int i=1;i<=ceil;i++){
            indexs[i-1]=i;
        }
        model.addAttribute("currindex",index);//当前页码
        model.addAttribute("indexs",indexs);//页码数组
        model.addAttribute("maxindex",ceil);//最大页码
        model.addAttribute("articleCount",searchList.size());//总文章数
        model.addAttribute("currpath","/article/search/");//当前目录
        List<Article> articles=null;
        try {
             articles= Lists.partition(searchList,5).get(index-1);
        }catch (Exception e){
            System.out.println("搜索结果为空");
        }
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
        return "search";
    }

    @RequestMapping("/Tag/{aTag}/{index}")
    public String searchByTag(HttpServletRequest request, Model model, @PathVariable("aTag") String aTag, @PathVariable("index") int index) throws Exception {
        model.addAttribute("Tags",articleService.Tags());
        List<Article> searchByTags = articleService.articleTag(aTag);
        int ceil = (int) Math.ceil(searchByTags.size() / 5.0);
        long[] indexs=new long[ceil];
        Arrays.fill(indexs,0);
        for(int i=1;i<=ceil;i++){
            indexs[i-1]=i;
        }
        model.addAttribute("currindex",index);//当前页码
        model.addAttribute("indexs",indexs);//页码数组
        model.addAttribute("maxindex",ceil);//最大页码
        model.addAttribute("articleCount",searchByTags.size());//总文章数
        model.addAttribute("currpath","/article/Tag/"+aTag+"/");//当前目录
        List<Article> articles=null;
        try {
            articles = Lists.partition(searchByTags,5).get(index-1);
        }catch (Exception e){
            System.out.println("搜索结果为空");
        }
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
        return "search";
    }

    @RequestMapping("updateArticle/{aId}")
    public String updateArticle(@PathVariable("aId") Long aId,Model model,HttpServletRequest request){
        model.addAttribute("Tags",articleService.Tags());
        model.addAttribute("aId",aId);
        Account signin = (Account) request.getSession().getAttribute("logined");
        Article article = articleService.findArticleById(aId).get(0);
        if (article.getaByUid()!=signin.getUid()){
            if(signin.getIsAdmin()==0) {
                model.addAttribute("error", "非管理员用户不能修改其他人的文章");
                return "info";
            }
        }
        model.addAttribute("article",article);
        Account account=(Account)request.getSession().getAttribute("logined");
        List<Resource> resources = resourceService.findRbyAid(aId);
        model.addAttribute("resources",resources);
        model.addAttribute("aId",aId);
        return "article_update";
    }
    @RequestMapping("updateArticle/{aId}/do")
    public String updateArticleDo(Article article, Model model, @PathVariable("aId") Long aId, HttpServletRequest request, RedirectAttributes redirectAttributes){
        model.addAttribute("Tags",articleService.Tags());
        try {
            article.setaId(aId);
            if(article.getaTag().length() == 0){
                article.setaTag("默认");
            }
            String url = articleService.updateArticle(article,request);
            if(url.length()!=0){
                redirectAttributes.addFlashAttribute("url",url);
                redirectAttributes.addFlashAttribute("succ","修改文章成功");
                return "redirect:"+request.getHeader("referer");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            redirectAttributes.addFlashAttribute("error","修改文章失败,原因："+e.getMessage().replace("WARN：",""));
        }
        return "redirect:"+request.getHeader("referer");
    }
    @RequestMapping("delArticle/{aId}")
    public String delArticle(@PathVariable("aId") Long aId,Model model,HttpServletRequest request,RedirectAttributes redirectAttributes){
        model.addAttribute("Tags",articleService.Tags());
        Account signin = (Account) request.getSession().getAttribute("logined");
        Article article = articleService.findArticleById(aId).get(0);
        if (article.getaByUid()!=signin.getUid()){
            if(signin.getIsAdmin()==0) {
                model.addAttribute("error", "非管理员用户不能管理其他人的文章");
                return "info";
            }
        }
        try {
            List<Resource> rbyAids = resourceService.findRbyAid(aId);
            for (Resource rbyAid:rbyAids
            ) {
                resourceService.deleteR(rbyAid.getrId(),request);
            }
            articleService.delArticle(aId,request);
            redirectAttributes.addFlashAttribute("succ","删除文章成功");
            return "redirect:"+request.getHeader("referer");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            redirectAttributes.addFlashAttribute("msg","删除文章失败，原因："+e.getMessage().replace("WARN：",""));
        }
        return "redirect:"+request.getHeader("referer");
    }

    @RequestMapping("top10/{index}")
    public String top10Article(Model model, HttpServletRequest request, @PathVariable("index") int index) throws Exception {
        model.addAttribute("Tags",articleService.Tags());
        List<Article> top10s = articleService.top10Article();
        int ceil = (int) Math.ceil(top10s.size() / 5.0);
        long[] indexs=new long[ceil];
        Arrays.fill(indexs,0);
        for(int i=1;i<=ceil;i++){
            indexs[i-1]=i;
        }
        model.addAttribute("currindex",index);//当前页码
        model.addAttribute("indexs",indexs);//页码数组
        model.addAttribute("maxindex",ceil);//最大页码
        model.addAttribute("articleCount",top10s.size());//总文章数
        model.addAttribute("currpath","/article/top10/");//当前目录
        List<Article> articles=null;
        try {
            articles = Lists.partition(top10s,5).get(index-1);
        }catch (Exception e){
            System.out.println("未知错误");
        }
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

    //管理文章所有的评论
    @RequestMapping("manageComment/{aId}")
    public String managerComment(Model model, @PathVariable("aId") Long aId){
        model.addAttribute("Tags",articleService.Tags());
        List<MainComment> mainComments = null;
        List<SubComment> subComments = null;
        LinkedHashMap<MainComment, List<SubComment>> comments = new LinkedHashMap<MainComment, List<SubComment>>();
        mainComments = mainCommentService.findMainCommentByAID(aId);
        for(MainComment mainComment : mainComments){
            subComments = subCommentService.findSubCommentByScByCid(mainComment.getcId());
            comments.put(mainComment, subComments);
        }
        model.addAttribute("comments", comments);
        return "comment_manage";
    }
}
