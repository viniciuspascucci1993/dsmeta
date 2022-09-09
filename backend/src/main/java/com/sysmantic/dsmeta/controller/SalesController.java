package com.sysmantic.dsmeta.controller;

import com.sysmantic.dsmeta.entities.Sale;
import com.sysmantic.dsmeta.services.SalesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/sales")
public class SalesController {

    private SalesService salesService;

    @Autowired
    public SalesController(SalesService salesService) {
        this.salesService = salesService;
    }

    @GetMapping("/find-sales")
    public ResponseEntity<List<Sale>> findSales() {
        return ResponseEntity.ok(salesService.findSales());
    }
}
