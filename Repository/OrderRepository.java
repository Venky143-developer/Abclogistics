package com.alpha.ABClogistics.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alpha.ABClogistics.Entity.Order;
@Repository
public interface OrderRepository extends JpaRepository<Order, Integer>{

}
