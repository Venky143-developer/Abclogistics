package com.alpha.ABClogistics.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alpha.ABClogistics.Entity.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer>{

}
