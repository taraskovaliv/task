package com.kovaliv.task.repos;

import com.kovaliv.task.entities.Orderline;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderlineRepo extends JpaRepository<Orderline, Long> {
}
