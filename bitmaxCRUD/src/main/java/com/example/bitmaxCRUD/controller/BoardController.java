package com.example.bitmaxCRUD.controller;

import com.example.bitmaxCRUD.customException.NotFound;
import com.example.bitmaxCRUD.dto.BoardRequestDTO;
import com.example.bitmaxCRUD.dto.BoardResponseDTO;
import com.example.bitmaxCRUD.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/boards")
public class BoardController {

    private final BoardService boardService;

    // 1. 게시글 생성
    @PostMapping
    public ResponseEntity<?> createBoard(@RequestBody BoardRequestDTO request) {
        try {
            BoardResponseDTO result = boardService.createBoard(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(result);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("서버 오류");
        }
    }

    // 2-1. 게시글 조회
    @GetMapping("/{id}")
    public ResponseEntity<?> getBoard(@PathVariable Long id) {
        try {
            BoardResponseDTO dto = boardService.getBoard(id);
            return ResponseEntity.ok(dto);
        } catch (NotFound e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("서버 오류");
        }
    }

    // 2-2. 게시글 목록
    @GetMapping
    public ResponseEntity<?> getBoardList() {
        try {
            return ResponseEntity.ok(boardService.getBoardList());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("서버 오류");
        }
    }

    // 3. 게시글 수정
    @PutMapping("/{id}")
    public ResponseEntity<?> updateBoard(@PathVariable Long id, @RequestBody BoardRequestDTO request) {
        try {
            BoardResponseDTO result = boardService.updateBoard(id, request);
            return ResponseEntity.ok(result);
        } catch (NotFound e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("서버 오류");
        }
    }

    // 4. 게시글 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBoard(@PathVariable Long id) {
        try {
            boardService.deleteBoard(id);
            return ResponseEntity.ok("삭제 완료");
        } catch (NotFound e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("서버 오류");
        }
    }
}

