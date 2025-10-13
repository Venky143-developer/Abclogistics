package com.alpha.ABClogistics.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alpha.ABClogistics.Entity.Driver;
import com.alpha.ABClogistics.Entity.Truck;
@Repository
public interface DriverRepository extends JpaRepository<Driver, Integer>{

}
