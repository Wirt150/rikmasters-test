package com.example.rikmasters.calculation.repository;

import com.example.rikmasters.calculation.entity.Detail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DetailRepository extends JpaRepository<Detail, Long> {
    @Modifying(clearAutomatically = true)
    @Query(value = "" +
            "UPDATE details " +
            "SET car_id = ?1 " +
            "WHERE id =  ?2 ", nativeQuery = true)
    void addDetailCarId(Long carId, Long detailsId);

    @Modifying(clearAutomatically = true)
    @Query(value = "" +
            "UPDATE details " +
            "SET car_id = NULL " +
            "WHERE id =  ?1 ", nativeQuery = true)
    void deletedDetailCarId(Long detailsId);
}
