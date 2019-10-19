package team.shiver.controller;


import team.shiver.mapper.UserMapper;
import team.shiver.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@ResponseBody
public class ApiController {
    @Autowired
    private UserMapper userMapper;

    @RequestMapping("/user")
    public String testSelect() {
        System.out.println(("----- selectAll method test ------"));
        List<User> userList = userMapper.selectList(null);
        //userList.forEach(System.out::println);
        return userList.get(0).toString();
    }
}
