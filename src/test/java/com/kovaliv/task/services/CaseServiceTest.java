package com.kovaliv.task.services;

import com.kovaliv.task.entities.Case;
import com.kovaliv.task.entities.Orderline;
import com.kovaliv.task.entities.Product;
import com.kovaliv.task.repos.CaseRepo;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CaseServiceTest {

    CaseRepo caseRepo = mock(CaseRepo.class);

    CaseService caseService = new CaseService(caseRepo);

    Product product = new Product(1L, 2.0, 4.0, 1.0, new ArrayList<Orderline>());

    Orderline orderline = new Orderline(1L, 3, product);

    List<Case> cases;

    @Test
    public void testCaseService() {
        cases = new ArrayList<>();
        cases.add(new Case(1L, 4.0, 8.0, 2.0));
        cases.add(new Case(1L, 3.0, 11.0, 1.5));
        cases.add(new Case(2L, 6.0, 4.0, 1.0));
        cases.add(new Case(3L, 1.0, 2.0, 1.0));
        cases.add(new Case(3L, 10.0, 20.0, 10.0));
        when(caseRepo.findAll()).thenReturn(cases);
        Case result = caseService.getCase(orderline);
        assertEquals(new Case(1L, 4.0, 8.0, 2.0), result);
    }
}
