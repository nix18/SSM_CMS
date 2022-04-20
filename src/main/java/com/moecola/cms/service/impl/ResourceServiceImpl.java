package com.moecola.cms.service.impl;

import com.moecola.cms.dao.IResourceDao;
import com.moecola.cms.domain.Account;
import com.moecola.cms.domain.Resource;
import com.moecola.cms.service.IResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service("resourceService")
public class ResourceServiceImpl implements IResourceService {
    @Autowired
    private IResourceDao resourceDao;
    public static String frandom(int length){
        String random = "";
        /*随机数函数*/
        java.util.Random r=new java.util.Random();
        for(int i = 0;i<length;i++){
            /*生成36以内的随机数，不含36，并转化为36位*/
            random += Integer.toString(r.nextInt(36), 36);
        }
        return random;
    }
    @Override
    public long uploadR(HttpServletRequest request, MultipartFile upload, Long aId, Model model) throws Exception {
        if (upload.getSize()!=0&&request.getSession().getAttribute("logined")!=null) {
            System.out.println("INFO：资源文件上传");
            //确定上传位置
            String path=request.getSession().getServletContext().getRealPath("/static/uploads/");
            //判断路径是否存在
            File file=new File(path);
            if(!file.exists()){
                //创建文件夹
                file.mkdirs();
            }

            String rName=upload.getOriginalFilename();
            rName=frandom(8)+"_"+rName;
            rName=rName.replace(" ","");
            upload.transferTo(new File(path,rName));
            String realpath=path+rName;
            System.out.println("INFO：存储路径："+realpath);
            StringBuffer tempurl=request.getRequestURL();
            String url= "/uploads/"+rName;
            System.out.println("INFO：调用路径："+url);
            String cat=rName.substring(rName.lastIndexOf(".")+1);
            System.out.println("INFO：CAT："+cat);
            Account loginedaccount= (Account) request.getSession().getAttribute("logined");
            long rByUid = loginedaccount.getUid();
            Resource resource=new Resource(rName,url,cat,rByUid, aId ,realpath);
            model.addAttribute("url",url);
            return resourceDao.uploadR(resource);
        }
        throw new Exception("WARN：未登录或上传文件为空");
    }

    @Override
    public Resource findRbyRId(Long rId) {
        return resourceDao.findRbyRId(rId).get(0);
    }

    @Override
    public List<Resource> findRbyAid(Long rByAid) {
        return resourceDao.findRbyAid(rByAid);
    }

    @Override
    public List<Resource> findRbyUid(Long rByUid) {
        return resourceDao.findRbyUid(rByUid);
    }

    @Override
    public long deleteR(Long rId,HttpServletRequest request) throws Exception {
        if(request.getSession().getAttribute("logined")!=null) {
            File file = new File(resourceDao.findRbyRId(rId).get(0).getRealPath());
            file.delete();
            System.out.println("INFO：本地Rid:"+rId+"删除成功");
            return resourceDao.deleteR(rId);
        }
        throw new Exception("WARN：未登录");
    }
}
