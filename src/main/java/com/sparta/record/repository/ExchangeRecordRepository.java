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
    // 기록 식별자로 기록을 조회 없을경우 예외처리
    default ExchangeRecord findByIdOrElseThrow(long id) {
        return findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "기록을 찾을 수 없습니다")
        );
    }
    // 유저의 거래횟수 및 총액 조회
    @Query(value = "select count(*), sum(r.beforeAmount) " +
            "from ExchangeRecord r where r.user.id = :userId group by r.user")
    List<Object[]> findAmountByUserId (@Param("userId")Long userId);
    // 모든 화폐의 거래횟수 및 총액 조회 취소된 거래는 제외
    @Query("SELECT new com.sparta.record.dto.find_currency_data.CurrencyVolumeData(r.currency.currencyName, COUNT(*), SUM(r.beforeAmount)) " +
            "FROM ExchangeRecord r where r.isCancelled = false GROUP BY r.currency")
    Page<CurrencyVolumeData> findAmountByCurrency(Pageable pageable);

}
