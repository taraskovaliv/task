package com.kovaliv.task.repos;

import com.kovaliv.task.entities.Case;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CaseRepo extends JpaRepository<Case, Long> {
}
