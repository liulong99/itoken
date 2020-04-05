package com.funtl.itoken.service.admin.Controller;

import com.funtl.itoken.common.domain.TbSysUser;
import com.funtl.itoken.common.dto.BaseResult;
import com.funtl.itoken.common.utils.MapperUtils;
import com.funtl.itoken.service.admin.service.AdminService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Description :
 * @Author 刘龙
 * @Date 2020/4/2 14:27
 * @Version 1.0
 **/
@RestController
@RequestMapping(value = "v1/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    /**
     * 分页查询
     * @param pageNum
     * @param pageSize
     * @param tbSysUserJson
     * @return
     */
    @RequestMapping(value = "page/{pageNum}/{pageSize}",method = RequestMethod.GET)
    public BaseResult page(@PathVariable(required = true)int pageNum, @PathVariable(required = true)int pageSize,
                           @RequestParam(required = false)String tbSysUserJson){
        TbSysUser tbSysUser=null;
        if(tbSysUserJson!=null){
            try {
                tbSysUser= MapperUtils.json2pojo(tbSysUserJson,TbSysUser.class);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        PageInfo<TbSysUser> pageInfo = adminService.page(pageNum, pageSize, tbSysUser);
        //分页后的结果集
        List<TbSysUser> list = pageInfo.getList();;
        //封装Cursor对象
        BaseResult.Cursor cursor=new BaseResult.Cursor();
        cursor.setTotal(new Long(pageInfo.getTotal()).intValue());
        cursor.setOffset(pageInfo.getPageNum());
        cursor.setLimit(pageInfo.getPageSize());

        return BaseResult.ok(list,cursor);
    }

}
