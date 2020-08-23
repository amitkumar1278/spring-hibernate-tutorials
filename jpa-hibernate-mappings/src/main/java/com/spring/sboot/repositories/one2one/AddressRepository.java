package com.spring.sboot.repositories.one2one;

import org.springframework.data.repository.CrudRepository;

import com.spring.sboot.domains.one2one.Address;

public interface AddressRepository extends CrudRepository<Address, Long> {

}
