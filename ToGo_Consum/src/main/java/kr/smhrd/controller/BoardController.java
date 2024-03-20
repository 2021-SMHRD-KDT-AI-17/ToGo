package kr.smhrd.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import kr.smhrd.entity.Board;
import kr.smhrd.mapper.BoardMapper;

@Controller
public class BoardController {
	
	@Autowired
	private BoardMapper boardMapper;
	
	// BoardMain.jsp로 이동 + DB값 가져오기
	@RequestMapping("/goBoardMain")
	public String goBoardMain(Model model) {
		
		List<Board> b_list=boardMapper.boardList();
		model.addAttribute("b_list", b_list);
		
		
		return "BoardMain";
	}
	
	//BoardWrite.jsp로 이동
	
	@RequestMapping("/goBoardWrite")
	public String goBoardWrite() {
		return "BoardWrite";
	}
	
	// 게시글 등록
	@RequestMapping("/BoardInsert")
	public String BoardInsert(Board board, HttpServletRequest request) {
		
		//1. 요청객제(request)
		//2. 파일을 저장할 경로(String)
		String path=request.getRealPath("resources/image");
	//	System.out.println(path);
		//3. 허용가능한 용량 크기(int)
		int size = 1024*1024*10;
		//4. 인코딩 타입(String)
		String encoding ="UTF-8";
		//5. 파일이름 중복제거
		DefaultFileRenamePolicy rename=new DefaultFileRenamePolicy();
		
		try {
			MultipartRequest multi = new MultipartRequest(request, path, size, encoding, rename);
			String title = multi.getParameter("title");
			String writer = multi.getParameter("writer");
			String filename = multi.getFilesystemName("filename");
			String content = multi.getParameter("content");
			
			board = new Board(title, writer, filename, content);
			
			System.out.println(board.toString());
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
	
		int cnt = boardMapper.insertBoard(board);
		
		if(cnt>0) {
			
				System.out.println("업로드 성공!!!");
		}else {
			System.out.println("업로드 실패 !!!");
		}
		
		
		return "redirect:/goBoardMain";
	}
	
	// boardDetail.jsp로 이동
	
	@RequestMapping("/boardContent") //url?num1=value&num2=value2
	public String boardContent(@RequestParam("num") int num, Model model) {
		
		boardMapper.boardCount(num); //num 값에 해당하는 게시물 조회수 1 증가
		Board board = boardMapper.boardContent(num); //num값에 해당하는 하나의 게시물 가져오기
		
		
		model.addAttribute("board",board);
		
		return "BoardDetail";
	}
	
	//게시글 삭제
	@RequestMapping("/deleteBoard")
	public String deleteBoard(@RequestParam("num") int num, Model model) {
		
		
		boardMapper.deleteBoard(num);
		List<Board> b_list=boardMapper.boardList(); //리스트 다시불러오기
		model.addAttribute("b_list", b_list);
		// return redirect:/goBoardMain;
		return "BoardMain";
	}
	
}
