package com.bitmax.ojt.controller;

import com.bitmax.ojt.customExecption.NotFound;
import com.bitmax.ojt.dto.BoardRequestDTO;
import com.bitmax.ojt.dto.BoardResponseDTO;
import com.bitmax.ojt.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * 📌 BoardController
 *
 * - 클라이언트(React)에서 오는 HTTP 요청을 처리하는 레이어
 * - Service를 호출하고, 응답을 ResponseEntity로 감싸서 반환
 * - HTTP 상태 코드(200, 201, 404, 500)를 Controller가 책임지고 결정함
 *
 * React Axios API(JSON):
 *   GET    /api/boards            → 목록 조회
 *   GET    /api/boards/{id}       → 단일 조회
 *   POST   /api/boards            → 생성
 *   PUT    /api/boards/{id}       → 수정
 *   DELETE /api/boards/{id}       → 삭제
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/boards") // React에서 axios BASE_URL과 정확히 맞춤
public class BoardController {

    private final BoardService boardService;

    // ===========================================================
    // CREATE (POST /api/boards)
    // ===========================================================
    @PostMapping
    public ResponseEntity<?> createBoard(@RequestBody BoardRequestDTO request) {
        try {
            BoardResponseDTO result = boardService.createBoard(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(result); // 201 CREATED
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("서버 오류");
        }
    }

    // ===========================================================
    // READ (GET /api/boards/{id})
    // ===========================================================
    @GetMapping("/{id}")
    public ResponseEntity<?> getBoard(@PathVariable Long id) {
        try {
            BoardResponseDTO dto = boardService.getBoard(id);
            return ResponseEntity.ok(dto); // 200 OK

        } catch (NotFound e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage()); // 404

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("서버 오류"); // 500
        }
    }

    // ===========================================================
    // READ LIST (GET /api/boards)
    // ===========================================================
    @GetMapping
    public ResponseEntity<?> getBoardList() {
        try {
            return ResponseEntity.ok(boardService.getBoardList()); // 200 OK

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("서버 오류"); // 500
        }
    }

    // ===========================================================
    // UPDATE (PUT /api/boards/{id})
    // ===========================================================
    @PutMapping("/{id}")
    public ResponseEntity<?> updateBoard(
            @PathVariable Long id,
            @RequestBody BoardRequestDTO request
    ) {
        try {
            BoardResponseDTO result = boardService.updateBoard(id, request);
            return ResponseEntity.ok(result); // 200 OK

        } catch (NotFound e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage()); // 404

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("서버 오류"); // 500
        }
    }

    // ===========================================================
    // DELETE (DELETE /api/boards/{id})
    // ===========================================================
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBoard(@PathVariable Long id) {
        try {
            boardService.deleteBoard(id);
            return ResponseEntity.ok("삭제 완료"); // 200 OK (204도 가능)

        } catch (NotFound e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage()); // 404

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("서버 오류"); // 500
        }
    }
}
