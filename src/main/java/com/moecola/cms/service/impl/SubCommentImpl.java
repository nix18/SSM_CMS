package com.moecola.cms.service.impl;

import com.moecola.cms.dao.ISubCommentDao;
import com.moecola.cms.domain.SubComment;
import com.moecola.cms.service.ISubCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * 副评论业务层
 */
@Service("subCommentService")
public class SubCommentImpl implements ISubCommentService {
    @Autowired
    private ISubCommentDao subCommentDao;

    @Override
    public long addSubComment(SubComment subComment, HttpServletRequest request, HttpSession session) throws Exception {
        return subCommentDao.addSubComment(subComment);
    }

    @Override
    public long delSubComment(Long scId, HttpServletRequest request) throws Exception {
        return subCommentDao.delSubComment(scId);
    }

    @Override
    public List<SubComment> findSubCommentByScId(Long scId) {
        return null;
    }

    @Override
    public List<SubComment> findSubCommentByScByCid(Long scByCid) {
        List<SubComment> subComments = subCommentDao.findSubCommentByScByCid(scByCid);
        return subComments;
    }

    @Override
    public List<SubComment> findAllSubComment() {
        return null;
    }
}
