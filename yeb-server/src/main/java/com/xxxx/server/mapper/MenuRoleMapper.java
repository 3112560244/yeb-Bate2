package com.xxxx.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xxxx.server.pojo.MenuRole;
import com.xxxx.server.pojo.RespBean;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author LHC
 * @since 2022-05-10
 */
public interface MenuRoleMapper extends BaseMapper<MenuRole> {

    /**
     * @Author: ZedQ
     * @Date: 2022/5/15 22:05
     * @Description: 更新角色菜单
     * @return: null
     */
    int insertRecord(@Param("rid") Integer rid, @Param("mids") List<Integer> mids);
}
