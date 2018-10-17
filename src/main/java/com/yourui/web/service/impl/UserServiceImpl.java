package com.yourui.web.service.impl;

import com.yourui.web.dao.UsersMapper;
import com.yourui.web.model.Users;
import com.yourui.web.model.UsersExample;
import com.yourui.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 用户操作实现类
 * @author YI
 * @date 2018-7-19 16:08:12
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UsersMapper usersMapper;

    @Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
    @Override
    public boolean queryUsernameIsExist(String username) {
        UsersExample example = new UsersExample();
        UsersExample.Criteria criteria = example.createCriteria();

        criteria.andUsernameEqualTo(username);

        long count = usersMapper.countByExample(example);

        return count == 0 ? false : true;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public void saveUser(Users user) {

        usersMapper.insert(user);
    }

    @Override
    public List<Users> queryUsername(String username) {
        UsersExample example = new UsersExample();
        UsersExample.Criteria criteria = example.createCriteria();

        criteria.andUsernameEqualTo(username);

        return usersMapper.selectByExample(example);
    }

    @Override
    public List<Users> queryUsernameAndPassWord(String username, String password) {
        UsersExample example = new UsersExample();
        UsersExample.Criteria criteria = example.createCriteria();

        criteria.andUsernameEqualTo(username);
        criteria.andPasswordEqualTo(password);

        return usersMapper.selectByExample(example);
    }
}
