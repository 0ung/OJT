package com.example.bitmaxCRUD.service;

import com.example.bitmaxCRUD.customException.NotFound;
import com.example.bitmaxCRUD.dto.BoardRequestDTO;
import com.example.bitmaxCRUD.dto.BoardResponseDTO;
import com.example.bitmaxCRUD.entity.Board;
import com.example.bitmaxCRUD.repository.BoardRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    // 1. 게시글 생성
    public BoardResponseDTO createBoard(BoardRequestDTO request) {

        Board board = Board.builder()
                .title(request.getTitle())
                .content(request.getContent())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        boardRepository.save(board);

        return BoardResponseDTO.from(board);
    }

    // 2-1. 게시글 조회 (하나)
    public BoardResponseDTO getBoard(Long id) {
        Board board = boardRepository.findById(id)
                .orElseThrow(() -> new NotFound("게시글을 찾을 수 없습니다."));

        return BoardResponseDTO.from(board);
    }

    // 2-2. 게시글 목록 조회

    public List<BoardResponseDTO> getBoardList() {

        List<Board> boards = boardRepository.findAll();

        return boards.stream().map(BoardResponseDTO::from).toList();
    }

    // 3. 게시글 수정
    @Transactional
    public BoardResponseDTO updateBoard(Long id, BoardRequestDTO request) {

        Board board = boardRepository.findById(id)
                .orElseThrow(() -> new NotFound("게시글을 찾을 수 없습니다."));

        board.setTitle(request.getTitle());
        board.setContent(request.getContent());
        board.setUpdatedAt(LocalDateTime.now());

        return BoardResponseDTO.from(board);
    }

    // 4. 게시글 삭제
    public void deleteBoard(Long id) {

        Board board = boardRepository.findById(id)
                .orElseThrow(() -> new NotFound("게시글을 찾을 수 없습니다."));

        boardRepository.delete(board);
    }

}
