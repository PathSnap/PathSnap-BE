package com.pathsnap.Backend.WebSocket.WebSocketService;

import com.pathsnap.Backend.Record.Component.CheckRecord;
import com.pathsnap.Backend.Record.Entity.Record1Entity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WebSocketAuthService {

    private final CheckRecord checkRecord;

    // 권한 확인
    public void webSocketUser(String userId, String recordId) {
        // record 불러오기
        Record1Entity record = checkRecord.exec(recordId);

        // record에서 userId 추출
        String recordUserId = record.getUser().getUserId();

        // userId와 recordUserId 비교
        if (!userId.equals(recordUserId)) {
            throw new IllegalArgumentException("접근 권한이 없습니다.");
        }
    }
}
