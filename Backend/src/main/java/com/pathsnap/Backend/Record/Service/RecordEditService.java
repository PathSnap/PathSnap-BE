package com.pathsnap.Backend.Record.Service;

import com.pathsnap.Backend.Exception.RecordNotFoundException;
import com.pathsnap.Backend.Record.Dto.Req.RecordEditReqDto;
import com.pathsnap.Backend.Record.Dto.Res.RecordEditResDto;
import com.pathsnap.Backend.Record.Entity.RecordEntity;
import com.pathsnap.Backend.Record.Repository.RecordRepository;
import lombok.Builder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Builder
public class RecordEditService {

    @Autowired
    private RecordRepository recordRepository;

    public RecordEditResDto editRecordName(RecordEditReqDto request){
        RecordEntity recordEdit = recordRepository.findById(request.getRecordId())
                .orElseThrow(()-> new RecordNotFoundException(request.getRecordId()));

        recordEdit.setRecordId(request.getRecordId());
        recordEdit.setRecordName(request.getRecordName());

        recordRepository.save(recordEdit);

        return RecordEditResDto.builder()
                .recordId(request.getRecordId())
                .recordName(request.getRecordName())
                .build();
    }
}
