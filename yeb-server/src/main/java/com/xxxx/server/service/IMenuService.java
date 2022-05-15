package com.xxxx.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xxxx.server.pojo.Menu;
import com.xxxx.server.pojo.Role;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author LHC
 * @since 2022-05-10
 */
public interface IMenuService extends IService<Menu> {

    /**
     * @Author: ZedQ
     * @Date: 2022/5/11 23:48
     * @Description: 根据用户id查询菜单
     * @return: null
     */
    List<Menu> getMenusByAdminId();


    /**
     * 根据角色获取菜单列表
     * @return
     */
    List<Menu> getMenuRoleList();

    /**
     * @Author: ZedQ
     * @Date: 2022/5/15 21:24
     * @Description: 查询所有菜单
     * @return: null
     */
    List<Role> getAllMenus();
}
