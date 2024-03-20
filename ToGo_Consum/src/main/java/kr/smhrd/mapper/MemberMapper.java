package kr.smhrd.mapper;



import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import kr.smhrd.entity.Member;

// 이 클래스가 mapper파일임을 알려줘야함
@Mapper
public interface MemberMapper {

	public void memberInsert(Member member); //MemberMapper.xml에 id에 memberInsert넣기

	public Member memberSelect(Member member);

	@Select("select * from member where email = #{email}")
	public Member emailCheck(String inputE);

	public void memberUpdate(Member member);

	public List<Member> goShowMember(); //메서드 -> 리스트 타입으로 반환

	public void deleteMember(String email);
}

