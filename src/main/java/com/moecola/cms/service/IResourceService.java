package com.moecola.cms.service;

import com.moecola.cms.domain.Resource;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 业务层资源接口
 */
public interface IResourceService {
    /**
     * 上传资源文件后存入信息
     * @param request
     * @param upload
     * @return
     */
    public long uploadR(HttpServletRequest request, MultipartFile upload, Long aId, Model model) throws Exception;

    /**
     * 通过RId查询资源文件记录
     * @param rId
     * @return
     */
    public Resource findRbyRId(Long rId);

    /**
     * 通过RByAid查询资源文件记录
     * @param rByAid
     * @return
     */
    public List<Resource> findRbyAid(Long rByAid);

    /**
     * 通过RByUid查询资源文件记录
     * @param rByUid
     * @return
     */
    public List<Resource> findRbyUid(Long rByUid);

    /**
     * 删除资源文件后删除数据库记录
     * @param rId
     * @return
     */
    public long deleteR(Long rId,HttpServletRequest request)throws Exception;
}
