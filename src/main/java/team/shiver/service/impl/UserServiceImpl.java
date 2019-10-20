package team.shiver.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team.shiver.mapper.UserMapper;
import team.shiver.model.User;
import team.shiver.service.IUserService;

@Service
public class UserServiceImpl implements IUserService{
    @Autowired
    private UserMapper userMapper;

    @Override
    public User getFirstUser() {
        return userMapper.selectList(null).get(0);
    }
}
