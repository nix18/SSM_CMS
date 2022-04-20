package com.moecola.cms.dao;

import com.moecola.cms.domain.Resource;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
/**
 * 持久层资源接口
 */
@Repository("resourceDao")
public interface IResourceDao {
    /**
     * 上传资源文件后存入信息
     * @param resource
     * @return
     */
    @Insert("insert into resource(rName,rUrl,rCat,rByUid,rByAid,realPath) values (#{rName},#{rUrl},#{rCat},#{rByUid},#{rByAid},#{realPath})")
    public long uploadR(Resource resource);

    /**
     * 通过RId查询资源文件记录
     * @param rId
     * @return
     */
    @Select("select * from resource where rId=#{rId}")
    public List<Resource> findRbyRId(Long rId);

    /**
     * 通过RByAid查询资源文件记录
     * @param rByAid
     * @return
     */
    @Select("select * from resource where rByAid=#{rByAid}")
    public List<Resource> findRbyAid(Long rByAid);

    /**
     * 通过RByUid查询资源文件记录
     * @param rByUid
     * @return
     */
    @Select("select * from resource where rByUid=#{rByUid}")
    public List<Resource> findRbyUid(Long rByUid);
    /**
     * 删除资源文件后删除数据库记录
     * @param rId
     * @return
     */
    @Delete("delete from resource where rId=#{rId}")
    public long deleteR(Long rId);
}
