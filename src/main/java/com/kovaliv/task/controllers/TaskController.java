package com.kovaliv.task.controllers;

import com.kovaliv.task.entities.Case;
import com.kovaliv.task.entities.Orderline;
import com.kovaliv.task.services.CaseService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/")
@AllArgsConstructor
public class TaskController {
    private final CaseService caseService;

    @PostMapping
    public ResponseEntity<Case> getCase(@RequestBody List<Orderline> orderlines) {
        return ResponseEntity.status(HttpStatus.OK).body(caseService.getCase(orderlines));
    }
}
