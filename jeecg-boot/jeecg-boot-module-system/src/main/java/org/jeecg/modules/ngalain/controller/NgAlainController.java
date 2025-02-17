package org.jeecg.modules.ngalain.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.vo.DictModel;
import org.jeecg.common.system.vo.LoginUser;
import org.jeecg.modules.ngalain.service.NgAlainService;
import org.jeecg.modules.system.service.ISysDictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.alibaba.fastjson.JSONObject;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@Api(tags="ng-alain接口")
@RequestMapping("/sys/ng-alain")
public class NgAlainController {
    @Autowired
    private NgAlainService ngAlainService;
    @Autowired
    private ISysDictService sysDictService;
    @ApiOperation("获取ng-alain数据信息")
    @GetMapping(value = "/getAppData")
    @ResponseBody
    public JSONObject getAppData(HttpServletRequest request) throws Exception {
       String token=request.getHeader("X-Access-Token");
        JSONObject j = new JSONObject();
        LoginUser user = (LoginUser) SecurityUtils.getSubject().getPrincipal();
        JSONObject userObjcet = new JSONObject();
        userObjcet.put("name", user.getUsername());
        userObjcet.put("avatar", user.getAvatar());
        userObjcet.put("email", user.getEmail());
        userObjcet.put("token", token);
        j.put("user", userObjcet);
        j.put("menu",ngAlainService.getMenu(user.getUsername()));
        JSONObject app = new JSONObject();
        app.put("name", "jeecg-boot-angular");
        app.put("description", "jeecg+ng-alain整合版本");
        j.put("app", app);
        return j;
    }
    @ApiOperation("根据字典类型查看字典数据")
    @RequestMapping(value = "/getDictItems/{dictCode}", method = RequestMethod.GET)
    public Object getDictItems(@PathVariable String dictCode) {
        log.info(" dictCode : "+ dictCode);
        Result<List<DictModel>> result = new Result<List<DictModel>>();
        List<DictModel> ls = null;
        try {
            ls = sysDictService.queryDictItemsByCode(dictCode);
            result.setSuccess(true);
            result.setResult(ls);
        } catch (Exception e) {
            log.error(e.getMessage(),e);
            result.error500("操作失败");
            return result;
        }
        List<JSONObject> dictlist=new ArrayList<>();
        for (DictModel l : ls) {
            JSONObject dict=new JSONObject();
                try {
                    dict.put("value",Integer.parseInt(l.getValue()));
                } catch (NumberFormatException e) {
                    dict.put("value",l.getValue());
                }
            dict.put("label",l.getText());
            dictlist.add(dict);
        }
        return dictlist;
    }
    @ApiOperation("根据路径的table、key、value查询table表里的key、value字段数据")
    @RequestMapping(value = "/getDictItemsByTable/{table}/{key}/{value}", method = RequestMethod.GET)
    public Object getDictItemsByTable(@PathVariable String table,@PathVariable String key,@PathVariable String value) {
        return this.ngAlainService.getDictByTable(table,key,value);
    }
}
