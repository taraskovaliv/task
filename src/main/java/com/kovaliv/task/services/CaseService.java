package com.kovaliv.task.services;

import com.kovaliv.task.entities.Case;
import com.kovaliv.task.entities.Orderline;
import com.kovaliv.task.repos.CaseRepo;
import com.kovaliv.task.repos.OrderlineRepo;
import com.kovaliv.task.repos.ProductRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CaseService {
    private final CaseRepo caseRepo;
    private final ProductRepo productRepo;
    private final OrderlineRepo orderlineRepo;

    public Case getCase(List<Orderline> orderlines) {

        return null;
    }
}
