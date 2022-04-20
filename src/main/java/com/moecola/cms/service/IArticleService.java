package com.moecola.cms.service;

import com.moecola.cms.domain.Article;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
 * 业务层文章接口
 */
@Service("articleService")
public interface IArticleService {
    /**
     * 增加文章
     * @param article
     * @return
     */
    public String addArticle(Article article, HttpServletRequest request, HttpSession session) throws Exception;

    /**
     * 删除文章
     * @param aId
     * @return
     */
    public boolean delArticle(Long aId,HttpServletRequest request) throws Exception;

    /**
     * 通过ID查询文章
     * @param aId
     * @return
     */
    public List<Article> findArticleById(Long aId);

    /**
     * 通过UID查询文章集
     * @param aByUid
     * @return
     */
    public List<Article> findArticleByUid(Long aByUid);

    /**
     * 查询所有文章
     * @return
     */
    public List<Article> findAllArticle();

    /**
     * 使用页码查询文章
     * @param index
     * @return
     */
    public List<Article> findArticleByIndex(Long index);

    /**
     * 查询文章数
     * @return
     */
    public Long articleCount();

    /**
     * 通过关键词搜索文章
     * @param word
     * @return
     */
    public List<Article> searchArticle(String word);

    /**
     * 修改文章属性
     * @param article
     * @return
     */
    public String updateArticle(Article article,HttpServletRequest request) throws Exception;

    /**
     * 查询下一个自增ID
     * @return
     */
    public Long articleNextId();

    /**
     * 查询上一篇
     * @param aId
     * @return
     */
    public List<Article> articlenextone(Long aId);

    /**
     * 查询上一篇
     * @param aId
     * @return
     */
    public List<Article> articlelastone(Long aId);

    public List<Article> articleTag(String aTag);

    public List<String> Tags();

    public List<Article> top10Article();

    public long updateReadCount(Long aId);

    /**
     * 解决更新阅读量导致时间变化的问题
     * @param t
     * @param aId
     * @return
     */
    public long reloadtime(Date aTime, Long aId);
    
    public List<Article> userLastArticle(Long uid);
}
