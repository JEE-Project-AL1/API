package com.esgi.jee.apijee.order.infrastructure;
import com.esgi.jee.apijee.order.domain.Order;
import com.esgi.jee.apijee.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByUser(User user);
}
