package kr.smhrd.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


import kr.smhrd.entity.Member;
import kr.smhrd.mapper.MemberMapper;

// 비동기 방식만 가능
// 데이터, 객체 리턴
//페이지 이동 불가능
@org.springframework.web.bind.annotation.RestController
public class RestController {
	
	@Autowired
	private MemberMapper memberMapper;
	
	
	// Email 중복 체크 /emailCheck
		@RequestMapping("/emailCheck")
		public @ResponseBody int emailCheck(@RequestParam("inputE") String inputE) { //페이지 이동없이 응답 @ResponseBody
			Member member = memberMapper.emailCheck(inputE);
			
			if(member == null) { // 중복이없음
				//사용가능
				return 1;  //중복된게 없음
			}else {
				//사용불가능
				return 0;
			}
		}
}
