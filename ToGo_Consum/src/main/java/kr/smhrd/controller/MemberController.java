package kr.smhrd.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.smhrd.entity.Member;
import kr.smhrd.entity.Message;
import kr.smhrd.mapper.MemberMapper;
import kr.smhrd.mapper.MessageMapper;


//Handler Mapping이 Controller를 찾기위해 @(Annotation)으로 Controller라고 명시해야 함
// servlet-context.xml 파일에 어떤 패키지에서 Controller를 찾을 건지 명시
@Controller
public class MemberController { //POJO
	
	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	
	
	@Autowired
	private  MemberMapper memberMapper;
	
	@Autowired
	private MessageMapper messageMapper;
	
	
	// Main.jsp로 이동하는 메소드
	@RequestMapping("/")
	public String main() {
	
		return "Main"; // /WEB-INF/views/home.jsp
	}
	
	@RequestMapping("/goMain")
	public String goMain() {
		return "Main";
	}
	
	// 회원가입 /memberInsert 라고 요청이 들어왔을때
	@RequestMapping("/memberInsert")
	public String memberInsert(Member member, Model model) {
		
		//System.out.println(member.toString());
		
		memberMapper.memberInsert(member);
		model.addAttribute("email", member.getEmail()); //모델에 저장
		
		return "JoinSuccess";
	}
	
	// 로그인 /memberSelect 라고 요청이 들어왔을 때 ( main -> control -> interface(dao jsp에서는 ->)
	@RequestMapping("/memberSelect")
	public String memberSelect(Member member, HttpSession session) {
		
		//System.out.println(member.toString());
		Member loginMember = memberMapper.memberSelect(member); //로그인 성공했을때
		session.setAttribute("loginMember", loginMember);
		// 로그인한 사람의 메세지 가져오기
		List<Message> m_list = messageMapper.messageList(member.getEmail());
		session.setAttribute("m_list", m_list);
		return "Main";
	}
	
	// 회원정보 수정하는 페이지로 이동 /showUpdate
	@RequestMapping("/showUpdate")
	public String showUpdate() {
		return "UpdateMember";
	}
	
	// 회원정보 수정
	@RequestMapping("/memberUpdate")
	public String memberUpdate(Member member, HttpSession session) {
		//System.out.println(member.toString());
		memberMapper.memberUpdate(member); //interface로
		session.setAttribute("loginMember",member);
		return "Main";
	}
	
	//로그아웃 기능
	@RequestMapping("/memberLogout")
	public String memberLogout(HttpSession session) {
		//session.removeAttribute("loginMember");
		session.invalidate();
		return "Main";
	}
	
	// 회원정보 페이지로 이동 + 전체회원정보 가져오기
	@RequestMapping("/goShowMember")
	public String goShowMember(Model model){
		List<Member> list = memberMapper.goShowMember();
		model.addAttribute("list", list); //(name, value)
		return "ShowMember";
	}
	
	//회원삭제
	@RequestMapping("/deleteMember")
	public String deleteMember(@RequestParam("email") String email) { // deleteMember?email=?  -> 요청데이터 즉 email값 사용
		memberMapper.deleteMember(email);
		
		return "redirect:/goShowMember";
	}
	// sendRedirect 방식
	
	// forward 방식
	
	
}
