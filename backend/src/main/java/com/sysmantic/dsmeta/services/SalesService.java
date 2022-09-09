package com.sysmantic.dsmeta.services;

import com.sysmantic.dsmeta.entities.Sale;
import com.sysmantic.dsmeta.repositories.SalesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SalesService {

    private SalesRepository salesRepository;

    @Autowired
    public SalesService(SalesRepository salesRepository) {
        this.salesRepository = salesRepository;
    }

    public List<Sale> findSales() {
        return salesRepository.findAll();
    }
}
