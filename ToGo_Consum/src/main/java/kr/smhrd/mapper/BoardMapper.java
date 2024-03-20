package kr.smhrd.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.smhrd.entity.Board;

@Mapper
public interface BoardMapper {

	public int insertBoard(Board board);

	public List<Board> boardList();

	public Board boardContent(int num);

	public void deleteBoard(int num);

	public void boardCount(int num);
	
}
