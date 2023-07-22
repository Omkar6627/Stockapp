package com.geekster.StockApp.repository;

import com.geekster.StockApp.model.Stock;
import com.geekster.StockApp.model.StockType;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import jakarta.transaction.Transactional;

import java.util.List;

@Repository
public interface IStockRepo extends CrudRepository<Stock,Long> {
    List<Stock> findByStockTypeOrderByStockPriceDesc(StockType type);

    List<Stock> findByOrderByStockBirthTimeStamp();

    @Modifying
      @Transactional
     @Query(value = "UPDATE stock SET STOCK_PRICE = (STOCK_PRICE + STOCK_PRICE*(:hike)) WHERE STOCK_TYPE = :type" ,nativeQuery = true)
    void updateStocksByType(String type, Float hike);
}
