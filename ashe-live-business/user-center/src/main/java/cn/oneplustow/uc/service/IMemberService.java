package cn.oneplustow.uc.service;


import cn.oneplustow.uc.entity.Member;
import cn.oneplustow.uc.vo.SaveMemberDto;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * 会员 业务层
 * 
 * @author ruoyi
 */
public interface IMemberService extends IService<Member>
{
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
     * 新增会员信息
     * 
     * @param member 会员信息
     * @return 结果
     */
    boolean insertMember(SaveMemberDto saveMemberDto);



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
     * 批量删除会员信息
     * 
     * @param memberIds 需要删除的会员ID
     * @return 结果
     */
    boolean deleteMemberByIds(List<Long> memberIds);
}
