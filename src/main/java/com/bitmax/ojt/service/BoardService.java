package com.bitmax.ojt.service;

import com.bitmax.ojt.customExecption.NotFound;
import com.bitmax.ojt.dto.BoardRequestDTO;
import com.bitmax.ojt.dto.BoardResponseDTO;
import com.bitmax.ojt.entitiy.Board;
import com.bitmax.ojt.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service // 📌 이 클래스가 비즈니스 로직을 담당하는 서비스 계층임을 Spring에게 알림
@RequiredArgsConstructor // 📌 final 필드를 자동으로 생성자 주입해 줌(권장 방식)
public class BoardService {

    /*
     ============================
     🔥 DI(의존성 주입 방식) 설명
     ============================

     1) 필드 주입
        @Autowired
        private BoardRepository boardRepository;
        ❌ 지양 — 테스트 어려움, DI 프레임워크 없이 객체 생성 불가

     2) setter 주입
        private BoardRepository boardRepository;

        @Autowired
        public void setBoardRepository(BoardRepository boardRepository) {
            this.boardRepository = boardRepository;
        }
        ➖ 선택 — 런타임 중 변경 가능 (장점이자 단점)

     3) 생성자 주입 ⭐(현대 스프링 권장 방식)
        private final BoardRepository boardRepository;

        public BoardService(BoardRepository boardRepository) {
            this.boardRepository = boardRepository;
        }
        ➕ 불변성 유지, 테스트 용이, NPE 방지
        ➕ Lombok의 @RequiredArgsConstructor와 조합하면 깔끔하게 관리 가능
     */

    private final BoardRepository boardRepository; // 생성자 주입 사용 (권장)

    // ===========================================================
    // CREATE
    // ===========================================================
    public BoardResponseDTO createBoard(BoardRequestDTO request) {

        validate(request); // 📌 단순 null 체크

        // 📌 DTO → Entity 변환
        Board board = Board.builder()
                .title(request.getTitle())
                .content(request.getContent())
                .createdAt(LocalDateTime.now())  // 생성 시간 기록
                .updatedAt(LocalDateTime.now())  // 수정 시간 기록
                .build();

        boardRepository.save(board); // 📌 새 엔티티 INSERT

        return BoardResponseDTO.from(board); // 📌 Entity → ResponseDTO 변환
    }

    // ===========================================================
    // READ (단건)
    // ===========================================================
    public BoardResponseDTO getBoard(Long id) {

        // 📌 존재하지 않으면 커스텀 예외 던짐 (컨트롤러에서 catch)
        Board board = boardRepository.findById(id)
                .orElseThrow(() -> new NotFound("게시글을 찾을 수 없습니다."));

        return BoardResponseDTO.from(board);
    }

    // ===========================================================
    // READ (목록)
    // ===========================================================
    public List<BoardResponseDTO> getBoardList() {

        // 📌 전체 게시글 조회
        List<Board> boards = boardRepository.findAll();

        // -----------------------------
        // 🟢 Stream 방식
        // -----------------------------
        return boards.stream()
                .map(BoardResponseDTO::from) // Entity → DTO 변환
                .toList();

        // -----------------------------
        // 🟡 for문 방식
        // -----------------------------
        /*
        List<BoardResponseDTO> dtoList = new ArrayList<>();

        for (Board board : boards) {
            dtoList.add(BoardResponseDTO.from(board));
        }

        return dtoList;
        */
    }

    // ===========================================================
    // UPDATE
    // ===========================================================
    @Transactional
    public BoardResponseDTO updateBoard(Long id, BoardRequestDTO request) {
        validate(request);

        // 📌 수정 대상 엔티티 조회 (영속 상태)
        Board board = boardRepository.findById(id)
                .orElseThrow(() -> new NotFound("게시글을 찾을 수 없습니다."));

        // 📌 Dirty Checking (setter 호출만으로 update SQL 자동 생성)
        board.setTitle(request.getTitle());
        board.setContent(request.getContent());
        board.setUpdatedAt(LocalDateTime.now()); // 수정 시간 갱신

        // save() 필요 없음 — 왜? 영속 상태의 엔티티는 변경 감지됨
        return BoardResponseDTO.from(board);
    }

    // ===========================================================
    // DELETE
    // ===========================================================
    // 삭제 쿼리는 반드시 조건을 달고 진행
    public void deleteBoard(Long id) {

        Board board = boardRepository.findById(id)
                .orElseThrow(() -> new NotFound("게시글을 찾을 수 없습니다."));

        boardRepository.delete(board); // 📌 DELETE 실행
    }

    // ===========================================================
    // VALIDATE
    // ===========================================================
    private void validate(Object param) {
        if (param == null) {
            throw new NotFound("요청 데이터가 없습니다.");
        }
    }
}
