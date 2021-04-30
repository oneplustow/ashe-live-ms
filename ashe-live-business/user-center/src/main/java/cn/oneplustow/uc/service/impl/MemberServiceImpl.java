package cn.oneplustow.uc.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import cn.oneplustow.api.ac.util.SecurityUtils;
import cn.oneplustow.uc.entity.Member;
import cn.oneplustow.uc.mapper.MemberMapper;
import cn.oneplustow.uc.service.IMemberService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

/**
 * 用户 业务层处理
 *
 * @author ruoyi
 */
@Service
public class MemberServiceImpl extends ServiceImpl<MemberMapper, Member> implements IMemberService {
    @Override
    public List<Member> selectSimpleMemberListById(Collection<String> ids) {
        if (CollUtil.isEmpty(ids)) {
            return CollUtil.newArrayList();
        }
        LambdaQueryWrapper<Member> wrapper = new LambdaQueryWrapper();
        wrapper.select(Member::getId, Member::getSex,
                Member::getNickName, Member::getAccount,
                Member::getEmail, Member::getPhone)
                .in(Member::getId, ids);
        List<Member> sysMembers = list(wrapper);
        return sysMembers;
    }

    /**
     * 根据条件分页查询用户列表
     *
     * @param member 用户信息
     * @return 用户信息集合信息
     */
    @Override
    public List<Member> selectMemberList(Member member) {
        return baseMapper.selectMemberList(member);
    }

    /**
     * 通过用户名查询用户
     *
     * @param memberName 用户名
     * @return 用户对象信息
     */
    @Override
    public Member selectMemberByMemberName(String memberName) {
        return this.getOne(this.lambdaQuery().eq(Member::getNickName,memberName));
    }

    /**
     * 通过用户ID查询用户
     *
     * @param memberId 用户ID
     * @return 用户对象信息
     */
    @Override
    public Member selectMemberById(Long memberId) {
        return this.getById(memberId);
    }


    @Override
    public boolean checkUnique(String nickName, String phone, String email) {
        LambdaQueryChainWrapper<Member> lambdaQuery = this.lambdaQuery();
        if(StrUtil.isNotBlank(nickName)){
            lambdaQuery.eq(Member::getNickName,nickName);
        }
        if(StrUtil.isNotBlank(phone)){
            lambdaQuery.eq(Member::getPhone,phone);
        }
        if(StrUtil.isNotBlank(email)){
            lambdaQuery.eq(Member::getEmail,email);
        }
        int count = this.count(lambdaQuery);
        return count <= 0;
    }

    /**
     * 新增保存用户信息
     *
     * @param member 用户信息
     * @return 结果
     */
    @Override
    public boolean insertMember(Member member) {
        // 新增用户信息
        member.setPassword(SecurityUtils.encryptPassword(member.getPassword()));
        return this.save(member);
    }


    /**
     * 修改用户基本信息
     *
     * @param member 用户信息
     * @return 结果
     */
    @Override
    public boolean updateMemberProfile(Member member) {
        return this.updateById(member);
    }

    /**
     * 修改用户头像
     *
     * @param memberId 用户ID
     * @param avatar   头像地址
     * @return 结果
     */
    @Override
    public boolean updateMemberAvatar(String id, String avatar) {
        return this.update(this.lambdaUpdate().eq(Member::getId,id).set(Member::getAvatar,avatar));
    }


    /**
     * 重置用户密码
     *
     * @param memberName 用户名
     * @param password   密码
     * @return 结果
     */
    @Override
    public boolean resetMemberPwd(String id, String password) {
        password = SecurityUtils.encryptPassword(password);
        return this.update(this.lambdaUpdate().eq(Member::getId,id).set(Member::getPassword,password));
    }


    /**
     * 批量删除用户信息
     *
     * @param memberIds 需要删除的用户ID
     * @return 结果
     */
    @Override
    public boolean deleteMemberByIds(List<Long> memberIds) {
        return this.removeByIds(memberIds);
    }

    @Override
    public boolean updateMember(Member user) {
        return this.updateById(user);
    }

    @Override
    public boolean updateMemberStatus(String id, String status) {
        return this.update(this.lambdaUpdate().eq(Member::getId,id).set(Member::getStatus,status));
    }
}
