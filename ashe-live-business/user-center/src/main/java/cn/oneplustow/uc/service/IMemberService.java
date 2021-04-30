package cn.oneplustow.uc.service;


import cn.oneplustow.uc.entity.Member;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Collection;
import java.util.List;

/**
 * 会员 业务层
 * 
 * @author ruoyi
 */
public interface IMemberService extends IService<Member>
{
    List<Member> selectSimpleMemberListById(Collection<String> ids);

    /**
     * 根据条件分页查询会员列表
     * 
     * @param member 会员信息
     * @return 会员信息集合信息
     */
    List<Member> selectMemberList(Member member);

    /**
     * 通过会员名查询会员
     * 
     * @param memberName 会员名
     * @return 会员对象信息
     */
    Member selectMemberByMemberName(String memberName);

    /**
     * 通过会员ID查询会员
     * 
     * @param memberId 会员ID
     * @return 会员对象信息
     */
    Member selectMemberById(Long memberId);


    /**
     * 校验nickName,phone,email是否唯一
     *
     * @param member 会员信息
     * @return 结果
     */
    boolean checkUnique(String nickName,String phone,String email);

    /**
     * 新增会员信息
     * 
     * @param member 会员信息
     * @return 结果
     */
    boolean insertMember(Member member);



    /**
     * 修改会员基本信息
     * 
     * @param member 会员信息
     * @return 结果
     */
    boolean updateMemberProfile(Member member);

    /**
     * 修改会员头像
     * 
     * @param memberName 会员名
     * @param avatar 头像地址
     * @return 结果
     */
    boolean updateMemberAvatar(String memberName, String avatar);

    /**
     * 重置会员密码
     * 
     * @param memberName 会员名
     * @param password 密码
     * @return 结果
     */
    boolean resetMemberPwd(String memberName, String password);

    /**
     * 批量删除会员信息
     * 
     * @param memberIds 需要删除的会员ID
     * @return 结果
     */
    boolean deleteMemberByIds(List<Long> memberIds);

    boolean updateMember(Member user);

    boolean updateMemberStatus(String id, String status);
}
