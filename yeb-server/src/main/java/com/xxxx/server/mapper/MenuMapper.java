package com.xxxx.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xxxx.server.pojo.Menu;
import com.xxxx.server.pojo.Role;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author LHC
 * @since 2022-05-10
 */
public interface MenuMapper extends BaseMapper<Menu> {

    /**
     * @Author: ZedQ
     * @Date: 2022/5/11 23:53
     * @Description: 根据用户id查询菜单
     * @return: null
     */
    List<Menu> getMenusByAdminId(Integer id);

    /**
     * 根据角色获取菜单列表
     * @return
     */
    List<Menu> getMenuRoleList();

    /**
     * @Author: ZedQ
     * @Date: 2022/5/15 21:26
     * @Description: 查询所有菜单
     * @return: null
     */
    List<Role> getAllMenus();
}
