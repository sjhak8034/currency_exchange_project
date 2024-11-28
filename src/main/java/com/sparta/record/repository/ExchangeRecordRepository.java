package com.sparta.record.repository;

import com.sparta.record.dto.find_currency_data.CurrencyVolumeData;
import com.sparta.record.entity.ExchangeRecord;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

public interface ExchangeRecordRepository extends JpaRepository<ExchangeRecord, Long> {
    default ExchangeRecord findByIdOrElseThrow(long id) {
        return findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "기록을 찾을 수 없습니다")
        );
    }

    @Query(value = "select count(*), sum(r.beforeAmount) " +
            "from ExchangeRecord r where r.user.id = :userId group by r.user")
    List<Object[]> findAmountByUserId (@Param("userId")Long userId);

    @Query("SELECT new com.sparta.record.dto.find_currency_data.CurrencyVolumeData(r.currency.currencyName, COUNT(*), SUM(r.beforeAmount)) " +
            "FROM ExchangeRecord r where r.isCancelled = false GROUP BY r.currency")
    Page<CurrencyVolumeData> findAmountByCurrency(Pageable pageable);

}
