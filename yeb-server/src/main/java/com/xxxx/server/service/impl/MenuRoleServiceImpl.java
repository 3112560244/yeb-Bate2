package com.xxxx.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xxxx.server.mapper.MenuRoleMapper;
import com.xxxx.server.pojo.MenuRole;
import com.xxxx.server.pojo.RespBean;
import com.xxxx.server.service.IMenuRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
public class MenuRoleServiceImpl extends ServiceImpl<MenuRoleMapper, MenuRole> implements IMenuRoleService {

    @Autowired
    private MenuRoleMapper menuRoleMapper;

    /**
     * @Author: ZedQ
     * @Date: 2022/5/15 21:58
     * @Description: 更新角色菜单
     * @return: null
     */
    @Override
    public RespBean updateMenuRole(Integer rid, List<Integer> mids) {
        menuRoleMapper.delete(new QueryWrapper<MenuRole>().eq("rid",rid));
        if(mids == null || 0==mids.size())
            return RespBean.success("更新成功");
        if(menuRoleMapper.insertRecord(rid,mids)>0)
            return RespBean.success("更新成功");
        return RespBean.success("更新失败");
    }
}
