package com.moecola.cms.dao;

import com.moecola.cms.domain.MainComment;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 主评论持久层
 */
@Repository("mainCommentDao")
public interface IMainCommentDao {
    /**
     * 增加评论
     * @param mainComment
     * @return
     */
    @Insert("insert into mainComment(cName, cContent, cByAid) values(#{cName}, #{cContent}, #{cByAid})")
    public long addMainComment(MainComment mainComment);

    /**
     * 删除评论
     * @param cId
     * @return
     */
    @Delete("delete from mainComment where cId=#{cId}")
    public long delMainComment(Long cId);

    /**
     * 通过评论id查找评论
     * @param cId
     * @return
     */
    @Select("select * from mainComment where cId = #{cId}")
    public List<MainComment> findMainCommentByID(Long cId);

    /**
     * 通过文章ID查找评论集
     * @param cByAid
     * @return
     */
    @Select("select * from mainComment where cByAID = #{cByAiD} order by cTime desc")
    public List<MainComment> findMainCommentByAID(Long cByAid);

    /**
     * 通过评论名查找评论集
     * @param cName
     * @return
     */
    @Select("select * from mainComment where cName = #{cName}")
    public List<MainComment> findMainCommentByName(Long cName);

    /**
     * 查找所有评论
     * @return
     */
    @Select("select * from mainComment")
    public List<MainComment> findAllMainComment();

}
