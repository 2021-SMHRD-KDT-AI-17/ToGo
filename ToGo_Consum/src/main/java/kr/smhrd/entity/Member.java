package kr.smhrd.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

//@Data   // 이걸 import해야함!
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@ToString
public class Member { //테이블이름이랑 같게
	
	@NonNull
	private String email;
	@NonNull
	private String pw;
	private String tel;
	private String address;
	
	
	
}
