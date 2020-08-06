package com.example.shopping.service.impl;

import com.example.shopping.Entity.Address;
import com.example.shopping.dao.AddressMapper;
import com.example.shopping.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AddressServiceImpl implements AddressService {

    @Autowired
    private AddressMapper addressMapper;

    @Override
    public List<Address> findAllById(Integer userId) {
       return addressMapper.findAllById(userId);
    }

    @Override
    public void insert(Address address) {
        addressMapper.insert(address);
    }

    @Override
    public void update(Address address) {
        addressMapper.updateByPrimaryKeySelective(address);
    }

    @Override
    public void delete(Address address) {
        addressMapper.deleteByPrimaryKey(address.getAddressid());
    }

    @Override
    public Address findAllByAid(Integer addressid) {
        return addressMapper.findAllByAid(addressid);
    }
}
