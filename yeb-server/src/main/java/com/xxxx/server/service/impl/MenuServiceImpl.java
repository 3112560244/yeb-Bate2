package com.xxxx.server.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xxxx.server.mapper.MenuMapper;
import com.xxxx.server.pojo.Admin;
import com.xxxx.server.pojo.Menu;
import com.xxxx.server.pojo.Role;
import com.xxxx.server.service.IMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author LHC
 * @since 2022-05-10
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements IMenuService {


    @Autowired
    private MenuMapper menuMapper;

    @Autowired
    private RedisTemplate redisTemplate;
    /**
     * @Author: ZedQ
     * @Date: 2022/5/11 23:49
     * @Description: 根据用户id查询菜单
     * @return: null
     */
    @Override
    public List<Menu> getMenusByAdminId() {
        Integer adminId = ((Admin) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
        ValueOperations<String, Object> valueOperations = redisTemplate.opsForValue();
        //从redis获取菜单数据
        List<Menu> menus = (List<Menu>) valueOperations.get("menu_" + adminId);
        //如果redis为空 从数据库获取
        if(CollectionUtils.isEmpty(menus)){
           menus =  menuMapper.getMenusByAdminId(adminId);
           //将数据设置到redis中
           valueOperations.set("menu_"+adminId,menus);
        }

        return menus;
    }

    /**
     * 根据角色获取菜单列表
     * @return
     */
    @Override
    public List<Menu> getMenuRoleList() {
        return menuMapper.getMenuRoleList();
    }

    /**
     * @Author: ZedQ
     * @Date: 2022/5/15 21:24
     * @Description: 查询所有菜单
     * @return: null
     */
    @Override
    public List<Role> getAllMenus() {

        return menuMapper.getAllMenus();
    }


}
