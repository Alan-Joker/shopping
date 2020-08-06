package com.example.shopping.service;

import com.example.shopping.Entity.Address;

import java.util.List;

public interface AddressService {
    List<Address> findAllById(Integer userId);

    void insert(Address address);

    void update(Address address);

    void delete(Address address);

    Address findAllByAid(Integer addressid);
}
