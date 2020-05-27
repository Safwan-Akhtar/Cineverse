package com.qa.cineverse.repo;

import com.qa.cineverse.domain.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sun.tools.jps.Jps;

@Repository
public interface OrdersRepo extends JpaRepository<Orders, Long> {
}
