package com.pathsnap.Backend.Record.Component;

import com.pathsnap.Backend.Record.Entity.Record1Entity;
import org.springframework.stereotype.Component;

@Component
public class EditRecordName {
    public Record1Entity exec(Record1Entity record, String newRecordName) {

        //recordName 수정
        record.setRecordName(newRecordName);
        return record;
    }
}
