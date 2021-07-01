package cn.oneplustow.uc.mapper;


import cn.oneplustow.uc.entity.Member;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * 用户表 数据层
 * 
 * @author ruoyi
 */
public interface MemberMapper extends BaseMapper<Member>
{
    /**
     * 根据条件分页查询用户列表
     * 
     * @param member 用户信息
     * @return 用户信息集合信息
     */
    public List<Member> selectMemberList(Member member);



}
