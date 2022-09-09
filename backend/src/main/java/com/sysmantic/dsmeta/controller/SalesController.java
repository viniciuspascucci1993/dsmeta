package com.sysmantic.dsmeta.controller;

import com.sysmantic.dsmeta.entities.Sale;
import com.sysmantic.dsmeta.services.CallerSmsService;
import com.sysmantic.dsmeta.services.SalesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/sales")
public class SalesController {

    private SalesService salesService;

    private CallerSmsService callerSmsService;

    @Autowired
    public SalesController(SalesService salesService, CallerSmsService callerSmsService) {
        this.salesService = salesService;
        this.callerSmsService = callerSmsService;
    }

    @GetMapping("/find-sales")
    public Page<Sale> findSales(
            @RequestParam(value = "minDate", defaultValue = "") String minDate,
            @RequestParam(value = "maxDate", defaultValue = "") String maxDate,
            Pageable pageable) {
        return salesService.findSales(minDate, maxDate, pageable);
    }

    @GetMapping("/{id}/notification")
    public void notificationBySms(
            @PathVariable Long id
    ) {
        callerSmsService.sendSms(id);
    }
}
