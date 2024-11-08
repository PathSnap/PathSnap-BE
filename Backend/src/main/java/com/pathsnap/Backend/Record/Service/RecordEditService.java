package com.pathsnap.Backend.Record.Service;

import com.pathsnap.Backend.Record.Component.CheckRecord;
import com.pathsnap.Backend.Record.Dto.Req.RecordEditReqDto;
import com.pathsnap.Backend.Record.Dto.Res.RecordEditResDto;
import com.pathsnap.Backend.Record.Entity.RecordEntity;
import com.pathsnap.Backend.Record.Repository.RecordRepository;
import lombok.Builder;
import org.springframework.stereotype.Service;

@Service
@Builder
public class RecordEditService {

    private final CheckRecord recordCheck;
    private final RecordRepository recordRepository;

    public RecordEditResDto editRecordName(RecordEditReqDto request){

        //기록ID 있는지 확인
        RecordEntity recordEdit = recordCheck.exec(request.getRecordId());

        //기록이름 수정 저장
        recordEdit.setRecordId(request.getRecordId());
        recordEdit.setRecordName(request.getRecordName());
        recordRepository.save(recordEdit);

        //수정된 기록이름 반환
        return RecordEditResDto.builder()
                .recordId(request.getRecordId())
                .recordName(request.getRecordName())
                .build();
    }
}
