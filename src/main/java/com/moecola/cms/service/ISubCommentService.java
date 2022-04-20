package com.moecola.cms.service;

import com.moecola.cms.domain.SubComment;
import org.apache.ibatis.annotations.Select;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * 副评论业务层接口
 */
public interface ISubCommentService {
    /**
     * 增加副评论
     * @param subComment
     * @param request
     * @param session
     * @return
     * @throws Exception
     */
    public long addSubComment(SubComment subComment, HttpServletRequest request, HttpSession session) throws Exception;

    /**
     *
     * @param scId
     * @param request
     * @return
     * @throws Exception
     */
    public long delSubComment(Long scId, HttpServletRequest request) throws Exception;

    /**
     * 通过scID查找副评论
     * @param scId
     * @return
     */
    public List<SubComment> findSubCommentByScId(Long scId);

    /**
     * 通过scByCid查找副评论集
     * @param scByCid
     * @return
     */
    public List<SubComment> findSubCommentByScByCid(Long scByCid);

    /**
     * 查找全部副评论
     * @return
     */
    public List<SubComment> findAllSubComment();
}
