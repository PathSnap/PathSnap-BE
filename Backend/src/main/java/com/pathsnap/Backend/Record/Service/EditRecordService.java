package com.pathsnap.Backend.Record.Service;

import com.pathsnap.Backend.Record.Component.CheckRecord;
import com.pathsnap.Backend.Record.Component.EditRecordName;
import com.pathsnap.Backend.Record.Dto.Req.RecordEditReqDto;
import com.pathsnap.Backend.Record.Dto.Res.RecordEditResDto;
import com.pathsnap.Backend.Record.Entity.Record1Entity;
import com.pathsnap.Backend.Record.Repository.RecordRepository;
import lombok.Builder;
import org.springframework.stereotype.Service;

@Service
@Builder
public class EditRecordService {

    private final CheckRecord checkRecord;
    private final EditRecordName editRecordName;
    private final RecordRepository recordRepository;

    public RecordEditResDto editRecordName(RecordEditReqDto request){

        //recordId 있는지 확인
        Record1Entity recordEdit = checkRecord.exec(request.getRecordId());

        //recordName 수정
        recordEdit = editRecordName.exec(recordEdit, request.getRecordName());

        //Edited record 저장
        recordRepository.save(recordEdit);

        //Edited recordName 반환
        return RecordEditResDto.builder()
                .recordId(request.getRecordId())
                .recordName(request.getRecordName())
                .build();
    }
}
