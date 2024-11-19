package com.pathsnap.Backend.WebSocket.WebSocketService;

import com.pathsnap.Backend.Record.Component.CheckRecord;
import com.pathsnap.Backend.Record.Entity.Record1Entity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WebSocketAuthService {

    private final CheckRecord checkRecord;

    // record 조회
    public Record1Entity getRecord(String recordId) {
        return checkRecord.exec(recordId);
    }

    // userId와 recordUserId 비교
    private void checkUserAccess(String userId, Record1Entity record) {
        String recordUserId = record.getUser().getUserId();
        if (!userId.equals(recordUserId)) {
            throw new IllegalArgumentException("접근 권한이 없습니다.");
        }
    }

    // 비교 함수 호출
    public void webSocketUser(String userId, String recordId) {
        Record1Entity record = getRecord(recordId);
        checkUserAccess(userId, record);
    }
}
