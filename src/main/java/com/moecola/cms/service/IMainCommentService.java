package com.moecola.cms.service;

import com.moecola.cms.domain.MainComment;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * 业务层主评论接口
 */
@Service("mainCommentService")
public interface IMainCommentService {
    /**
     * 增加主评论
     * @param mainComment
     * @param request
     * @param session
     * @return
     * @throws Exception
     */
    public boolean addMainComment(MainComment mainComment, HttpServletRequest request, HttpSession session) throws Exception;

    /**
     * 删除主评论
     * @param cId
     * @param request
     * @return
     * @throws Exception
     */
    public boolean delMainComment(Long cId, HttpServletRequest request) throws Exception;

    /**
     * 通过评论id查找评论
     * @param cId
     * @return
     */
    public List<MainComment> findMainCommentByID(Long cId);

    /**
     * 通过文章ID查找评论集
     * @param cByAid
     * @return
     */
    public List<MainComment> findMainCommentByAID(Long cByAid);

    /**
     * 通过评论名查找评论集
     * @param cName
     * @return
     */
    public List<MainComment> findMainCommentByName(Long cName);

    /**
     * 查找所有评论
     * @return
     */
    public List<MainComment> findAllMainComment();
}
