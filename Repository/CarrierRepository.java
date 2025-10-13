package com.alpha.ABClogistics.Repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alpha.ABClogistics.Entity.Carrier;


@Repository
public interface CarrierRepository extends JpaRepository<Carrier, Integer>{

}
