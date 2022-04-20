package com.moecola.cms.service.impl;

import com.moecola.cms.dao.IArticleDao;
import com.moecola.cms.domain.Account;
import com.moecola.cms.domain.Article;
import com.moecola.cms.service.IArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service("articleService")
public class ArticleServiceImpl implements IArticleService {
    @Autowired
    private IArticleDao articleDao;
    @Override
    public String addArticle(Article article, HttpServletRequest request, HttpSession session) throws Exception {
        if(request.getSession().getAttribute("logined")==null){
            throw new Exception("WARN：未登录");
        }
        if(request.getSession().getAttribute("logined")!=null) {
            StringBuffer tempurl = request.getRequestURL();
            Account logined = (Account) session.getAttribute("logined");
            article.setaByUid(logined.getUid());
            String url = "/article/" + articleDao.articleNextId();
            article.setaUrl(url);
            long status = articleDao.addArticle(article);
            return url;
        }
        throw new Exception("WARN：其他错误");
    }

    @Override
    public boolean delArticle(Long aId,HttpServletRequest request) throws Exception{
        if(request.getSession().getAttribute("logined")==null){
            throw new Exception("WARN：未登录");
        }
        long b = articleDao.delArticle(aId);
        if (b==0){
            throw new Exception("WARN：删除的文章不存在");
        }
        return true;
    }

    @Override
    public List<Article> findArticleById(Long aId) {
        return articleDao.findArticleById(aId);
    }

    @Override
    public List<Article> findArticleByUid(Long aByUid) {
        return articleDao.findArticleByUid(aByUid);
    }

    @Override
    public List<Article> findAllArticle() {
        return articleDao.findAllArticle();
    }

    @Override
    public List<Article> findArticleByIndex(Long index) {
        return articleDao.findArticleByIndex(index);
    }

    @Override
    public Long articleCount() {
        return articleDao.articleCount();
    }

    @Override
    public List<Article> searchArticle(String word) {
        return articleDao.searchArticle(word);
    }

    @Override
    public String updateArticle(Article article,HttpServletRequest request) throws Exception {
        if(article.getaId()==null){
            throw new Exception("WARN：要修改的文章ID为空");
        }
        if(article.getaId()!=null) {
            Article temparticle = findArticleById(article.getaId()).get(0);
            if (article.getaTitle().length() != 0) temparticle.setaTitle(article.getaTitle());
            if (article.getaIntro().length() != 0) temparticle.setaIntro(article.getaIntro());
            if (article.getaContent().length() != 0) temparticle.setaContent(article.getaContent());
            if (article.getaUrl()!=null) temparticle.setaUrl(article.getaUrl());
            temparticle.setaTag(article.getaTag());
            temparticle.setaIsPosted(article.getaIsPosted());
            long status = articleDao.updateArticle(temparticle);
            if(status==0){
                throw new Exception("WARN：其他错误");
            }
            return temparticle.getaUrl();
        }
        throw new Exception("WARN：其他错误");
    }

    @Override
    public Long articleNextId() {
        return articleDao.articleNextId();
    }

    @Override
    public List<Article> articlenextone(Long aId) {
        return articleDao.articlenextone(aId);
    }

    @Override
    public List<Article> articlelastone(Long aId) {
        return articleDao.articlelastone(aId);
    }

    @Override
    public List<Article> articleTag(String aTag) {
        return articleDao.articleTag(aTag);
    }

    @Override
    public List<String> Tags() {
        return articleDao.Tags();
    }

    @Override
    public List<Article> top10Article() {
        return articleDao.top10Article();
    }

    @Override
    public long updateReadCount(Long aId) {
        return articleDao.updateReadCount(aId);
    }

    @Override
    public long reloadtime(Date aTime, Long aId) {
        return articleDao.reloadtime(aTime,aId);
    }
    
    @Override
    public List<Article> userLastArticle(Long uid){
        return articleDao.userLastArticle(uid);
    }
}
