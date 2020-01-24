package com.invest.demo.controllers;

import com.invest.demo.entities.InvestmentData;
import com.invest.demo.services.InvestmentRatioService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class InvestmentController {

    @Resource
    InvestmentRatioService investmentRatioService;

    @RequestMapping("/investments/district")
    public List<InvestmentData> getInvestmentRatios() {
        List<InvestmentData> tmp = investmentRatioService.getInvestmentRatios();
        return tmp;
    }
}
