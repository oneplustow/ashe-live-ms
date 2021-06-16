package cn.oneplustow.uc.service.impl;

import cn.oneplustow.api.sc.service.UserService;
import cn.oneplustow.api.sc.vo.SaveUserDto;
import cn.oneplustow.common.exception.WarningMessageException;
import cn.opl.mapstruct.MapStructContext;
import cn.oneplustow.uc.entity.Member;
import cn.oneplustow.uc.mapper.MemberMapper;
import cn.oneplustow.uc.service.IMemberService;
import cn.oneplustow.uc.vo.SaveMemberDto;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 用户 业务层处理
 *
 * @author ruoyi
 */
@Service
public class MemberServiceImpl extends ServiceImpl<MemberMapper, Member> implements IMemberService {

    @Autowired
    private UserService userService;
    @Autowired
    private MapStructContext mapStructContext;

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

    /**
     * 新增保存用户信息
     * todo 需要加分布式事务
     * @param member 用户信息
     * @return 结果
     */
    @Override
    public boolean insertMember(SaveMemberDto saveMemberDto) {
        SaveUserDto saveuserdto = mapStructContext.conver(saveMemberDto, SaveUserDto.class);
        Long userid = userService.saveMemberUser(saveuserdto);
        if(userid == null){
            throw new WarningMessageException("注册用户失败");
        }
        Member member = mapStructContext.conver(saveMemberDto, Member.class);
        member.setUserId(userid);
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
        return false;
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


}
