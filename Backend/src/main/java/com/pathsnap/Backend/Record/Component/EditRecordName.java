package com.pathsnap.Backend.Record.Component;

import com.pathsnap.Backend.Record.Entity.RecordEntity;
import org.springframework.stereotype.Component;

@Component
public class EditRecordName {
    public RecordEntity exec(RecordEntity record, String newRecordName) {

        //recordName 수정
        record.setRecordName(newRecordName);
        return record;
    }
}
