package com.tian.usercenter.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tian.usercenter.mapper.UserTeamMapper;
import com.tian.usercenter.model.domain.UserTeam;
import com.tian.usercenter.service.UserTeamService;
import org.springframework.stereotype.Service;

/**
 * 用户队伍服务实现类
 *
 * @author Tian
 */
@Service
public class UserTeamServiceImpl extends ServiceImpl<UserTeamMapper, UserTeam>
        implements UserTeamService {
}




