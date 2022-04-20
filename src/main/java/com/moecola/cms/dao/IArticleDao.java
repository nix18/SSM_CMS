package com.moecola.cms.dao;

import com.moecola.cms.domain.Article;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
 * 持久层文章接口
 */
@Repository("articleDao")
public interface IArticleDao {
    /**
     * 增加文章
     * @param article
     * @return
     */
    @Insert("insert into article(aTitle,aIntro,aContent,aUrl,aByUid) values(#{aTitle},#{aIntro},#{aContent},#{aUrl},#{aByUid})")
    public long addArticle(Article article);

    /**
     * 通过ID删除文章
     * @param aId
     * @return
     */
    @Delete("delete from article where aId=#{aId}")
    public long delArticle(Long aId);

    /**
     * 通过ID查询文章
     * @param aId
     * @return
     */
    @Select("select * from article where aId=#{aId}")
    public List<Article> findArticleById(Long aId);

    /**
     * 通过UID查询文章集
     * @param aByUid
     * @return
     */
    @Select("select * from article where aByUid=#{aByUid}")
    public List<Article> findArticleByUid(Long aByUid);

    /**
     * 查询所有文章
     * @return
     */
    @Select("select * from article")
    public List<Article> findAllArticle();

    /**
     * 使用页码查询文章
     * @param index
     * @return
     */
    @Select("select * from article where aIsPosted=1 order by aTime desc limit #{index},5")
    public List<Article> findArticleByIndex(Long index);

    /**
     * 查询文章数
     * @return
     */
    @Select("select count(aId) from article where aIsPosted=1")
    public Long articleCount();

    /**
     * 通过关键词搜索文章
     * @param word
     * @return
     */
    @Select("select * from article where aIsPosted=1 and aTitle like concat('%',#{word},'%') or aContent like concat('%',#{word},'%') order by aId desc")
    public List<Article> searchArticle(String word);

    /**
     * 修改文章属性
     * @param article
     * @return
     */
    @Update("update article set aIsPosted=#{aIsPosted},aTitle=#{aTitle},aIntro=#{aIntro},aContent=#{aContent},aUrl=#{aUrl}, aTag=#{aTag} where aId=#{aId}")
    public long updateArticle(Article article);

    /**
     * 查询下一个自增ID
     * @return
     */
    @Select("SELECT auto_increment FROM information_schema.`TABLES` WHERE TABLE_SCHEMA='testcms' AND TABLE_NAME='article'")
    public Long articleNextId();

    /**
     * 查询上一篇
     * @param aId
     * @return
     */
    @Select("select * from article where aId>#{aId} and aIsPosted=1 order by aId asc  limit 1")
    public List<Article> articlenextone(Long aId);

    /**
     * 查询下一篇
     * @param aId
     * @return
     */
    @Select("select * from article where aId<#{aId} and aIsPosted=1 order by aId desc  limit 1")
    public List<Article> articlelastone(Long aId);

    @Select("select * from article where aIsPosted=1 and aTag=#{aTag}")
    public List<Article> articleTag(String aTag);

    @Select("select distinct aTag from article where aIsPosted=1")
    public List<String> Tags();

    @Update("update article set readCount=readCount+1 where aId=#{aId}")
    public long updateReadCount(Long aId);

    @Select("select * from article where aIsPosted=1 order by readCount desc limit 10")
    public List<Article> top10Article();

    /**
     * 解决更新阅读量导致时间变化的问题
     * @param aTime
     * @param aId
     * @return
     */
    @Update("update article set aTime=#{aTime} where aId=#{aId}")
    public long reloadtime(@Param("aTime") Date aTime, @Param("aId") Long aId);

    @Select("select * from article where aByUid=#{uid} order by aId desc limit 1")
    public List<Article> userLastArticle(Long uid);
}
