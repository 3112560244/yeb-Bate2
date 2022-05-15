package com.xxxx.server.controller;

import com.xxxx.server.pojo.RespBean;
import com.xxxx.server.pojo.Role;
import com.xxxx.server.service.impl.RoleServiceImpl;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * @Author: ZedQ
 * @Date: 2022/5/15 20:18
 * @Description:
 */
@RestController
@RequestMapping(value = "/system/basic/permiss")
public class PermissController {

    @Autowired
    private RoleServiceImpl roleService;

    @ApiOperation(value = "获取所有角色")
    @GetMapping("/")
    public List<Role> addRole(){
        return roleService.list();
    }


    @ApiOperation(value = "添加角色")
    @PostMapping("/")
    public RespBean addRole(@RequestBody Role role){

        if(!role.getName().startsWith("ROLE_")){
            role.setName("ROLE_"+role.getName());
        }
        if(roleService.save(role)){
            return RespBean.success("添加成功");
        }
        return RespBean.error("添加失败");
    }

    @ApiOperation(value = "修改角色")
    @PutMapping("/")
    public RespBean updateRole(@RequestBody Role role){
        if(roleService.updateById(role)){
            return RespBean.success("修改成功");
        }
        return RespBean.error("修改失败");
    }


    @ApiOperation(value = "删除角色")
    @DeleteMapping("/role/{id}")
    public RespBean deleteRole(@PathVariable Integer id){
        if(roleService.removeById(id)){
            return RespBean.success("删除成功");
        }
        return  RespBean.error("删除失败");
    }


    @ApiOperation(value = "批量删除")
    @DeleteMapping("/")
    public RespBean deleteRoles(Integer[] ids){
        if(roleService.removeByIds(Arrays.asList(ids))){
            return RespBean.success("批量删除成功");
        }
        return  RespBean.error("批量删除失败");
    }

}
