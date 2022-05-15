package com.xxxx.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xxxx.server.pojo.MenuRole;
import com.xxxx.server.pojo.RespBean;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author LHC
 * @since 2022-05-10
 */
public interface IMenuRoleService extends IService<MenuRole> {

    /**
     * @Author: ZedQ
     * @Date: 2022/5/15 21:57
     * @Description: 更新角色菜单
     * @return: null
     */
    RespBean updateMenuRole(Integer rid, List<Integer> mids);
}
