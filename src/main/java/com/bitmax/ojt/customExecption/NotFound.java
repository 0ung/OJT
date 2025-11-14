package com.bitmax.ojt.customExecption;

/**
 * 📌 NotFound Exception
 *
 * - "요청한 데이터를 찾을 수 없을 때" 사용하는 커스텀 런타임 예외
 * - RuntimeException을 상속함 → 체크 예외가 아님(unchecked)
 *
 * 왜 RuntimeException을 상속할까?
 *   ✔ throws 선언이 필요 없음
 *   ✔ try/catch 강제 없음
 *   ✔ 서비스 레이어에서 던지면 컨트롤러에서 잡아 처리하기 쉽다
 *
 * 어떤 경우에 발생시키나?
 *   - 게시글 조회했는데 DB에 데이터 없을 때
 *   - update/delete 대상이 존재하지 않을 때
 *
 * 어떻게 처리되나?
 *   - BoardService에서 throw new NotFound("메시지")
 *   - BoardController에서 catch(NotFound e)로 잡아 HTTP 404 반환
 *
 * 즉, 이 예외는
 *   "DB에서 특정 리소스를 찾지 못했다"는 의미를 명확하게 전달하기 위한 클래스다.
 */
public class NotFound extends RuntimeException {

    /**
     * 생성자
     * - 예외 메시지를 부모 클래스(RuntimeException)로 전달
     */
    public NotFound(String message) {
        super(message);
    }
}
