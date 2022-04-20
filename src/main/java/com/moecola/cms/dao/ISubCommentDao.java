package com.moecola.cms.dao;

import com.moecola.cms.domain.SubComment;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 副评论持久层
 */
@Repository("subCommentDao")
public interface ISubCommentDao {

    /**
     * 增加副评论
     * @param subComment
     * @return
     */
    @Insert("insert into subComment(scName, scContent, scByCid) values(#{scName}, #{scContent}, #{scByCid})")
    public long addSubComment(SubComment subComment);

    /**
     * 删除副评论
     * @param scId
     * @return
     */
    @Delete("delete from subComment where scId = #{scId}")
    public long delSubComment(Long scId);

    /**
     * 通过scID查找副评论
     * @param scId
     * @return
     */
    @Select("select * from subComment where scId = #{scId} ")
    public List<SubComment> findSubCommentByScId(Long scId);

    /**
     * 通过scByCid查找副评论集
     * @param scByCid
     * @return
     */
    @Select("select * from subComment where scByCid = #{scByCid} order by scTime desc")
    public List<SubComment> findSubCommentByScByCid(Long scByCid);

    /**
     * 查找全部副评论
     * @return
     */
    @Select("select * from subComment")
    public List<SubComment> findAllSubComment();

}
