package com.moecola.cms.service.impl;

import com.moecola.cms.dao.IMainCommentDao;
import com.moecola.cms.domain.MainComment;
import com.moecola.cms.service.IMainCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * 主评论业务层
 */
@Service("mainCommentService")
public class MainCommentImpl implements IMainCommentService {
    @Autowired
    private IMainCommentDao mainCommentDao;

    @Override
    public boolean addMainComment(MainComment mainComment, HttpServletRequest request, HttpSession session) throws Exception {
        long status = mainCommentDao.addMainComment(mainComment);
        return true;
    }

    @Override
    public boolean delMainComment(Long cId, HttpServletRequest request) throws Exception {
        long status = mainCommentDao.delMainComment(cId);
        return true;
    }

    @Override
    public List<MainComment> findMainCommentByID(Long cId) {
        return null;
    }

    @Override
    public List<MainComment> findMainCommentByAID(Long cByAid) {
        List<MainComment> mainComments = mainCommentDao.findMainCommentByAID(cByAid);
        return mainComments;
    }

    @Override
    public List<MainComment> findMainCommentByName(Long cName) {
        return null;
    }

    @Override
    public List<MainComment> findAllMainComment() {
        return null;
    }
}
