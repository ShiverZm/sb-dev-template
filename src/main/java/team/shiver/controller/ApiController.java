package team.shiver.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import team.shiver.mapper.UserMapper;
import team.shiver.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Api(description = "对外公共接口")
@Controller
@ResponseBody
@RequestMapping("/api")
public class ApiController {
    @Autowired
    private UserMapper userMapper;

    @ApiOperation(value = "用户列表value" ,  notes="用户列表note")
    @RequestMapping("/user")
    public String testSelect() {
        System.out.println(("----- selectAll method test ------"));
        List<User> userList = userMapper.selectList(null);
        //userList.forEach(System.out::println);
        return userList.get(0).toString();
    }
}
