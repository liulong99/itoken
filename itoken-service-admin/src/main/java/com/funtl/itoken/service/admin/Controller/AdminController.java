package com.funtl.itoken.service.admin.Controller;

import com.funtl.itoken.common.domain.TbSysUser;
import com.funtl.itoken.common.dto.BaseResult;
import com.funtl.itoken.common.utils.MapperUtils;
import com.funtl.itoken.service.admin.service.AdminService;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.ognl.Evaluation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

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
     * 根据 ID 获取管理员
     *
     * @param userCode
     * @return
     */
    @RequestMapping(value = "{userCode}", method = RequestMethod.GET)
    public BaseResult get(@PathVariable(value = "userCode")String userCode) {
        TbSysUser tbSysUser = new TbSysUser();
        tbSysUser.setUserCode(userCode);
        TbSysUser obj = adminService.selectOne(tbSysUser);
        return BaseResult.ok(obj);
    }

    /**
     * 保存管理员
     *
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    public BaseResult save(
            @RequestParam(required = true) String tbSysUserJson,
            @RequestParam(required = true) String optsBy
    ) {
        int result = 0;

        TbSysUser tbSysUser = null;
        try {
            tbSysUser = MapperUtils.json2pojo(tbSysUserJson, TbSysUser.class);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (tbSysUser != null) {
            // 密码加密
            String password = DigestUtils.md5DigestAsHex(tbSysUser.getPassword().getBytes());
            tbSysUser.setPassword(password);

            // 新增用户
            if (StringUtils.isBlank(tbSysUser.getUserCode())) {
                tbSysUser.setUserCode(UUID.randomUUID().toString());
                result = adminService.insert(tbSysUser, optsBy);
            }

            // 修改用户
            else {
                result = adminService.update(tbSysUser, optsBy);
            }

            // 最少有一行数据受影响
            if (result > 0) {
                return BaseResult.ok("保存管理员成功");
            }
        }

        return BaseResult.ok("保存管理员失败");
    }

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
