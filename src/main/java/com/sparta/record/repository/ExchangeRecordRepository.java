package com.sparta.record.repository;

import com.sparta.record.entity.ExchangeRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public interface ExchangeRecordRepository extends JpaRepository<ExchangeRecord, Long> {
    default ExchangeRecord findByIdOrElseThrow(long id) {
        return findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "기록을 찾을 수 없습니다")
        );
    }


}
