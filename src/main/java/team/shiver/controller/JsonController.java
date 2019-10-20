package team.shiver.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import team.shiver.mapper.UserMapper;
import team.shiver.model.JsonResult;
import team.shiver.model.User;
import team.shiver.service.IUserService;

import java.util.List;

@Api(description = "json数据格式")
@Controller
@ResponseBody
@RequestMapping("/json")
public class JsonController {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private IUserService userService;

    @RequestMapping("/users")
    public List<User> testSelect() {
        List<User> userList = userMapper.selectList(null);
        return userList;
    }

    @RequestMapping("/user")
    public User testSelectOne() {
        User user = new User();
        user.setName("正所谓");
        user.setEmail("1113673178@qq.com");
        user.setAge(14);
        return user;
    }

    @ApiOperation(value = "json格式用户集" ,  notes="json格式用户集")
    @RequestMapping("/usersResult")
    public JsonResult<List<User>>testResult() {
        List<User> userList = userMapper.selectList(null);
        return new JsonResult<List<User>>(userList,"调用成功");
    }

    @RequestMapping("/firstUser")
    public JsonResult<User> testFirstUser() {
        User user = userService.getFirstUser();
        return new JsonResult<User>(user,"调用成功");
    }
}
