package tn.esprit.spring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.spring.entities.Notification;

public interface NotificationRepository extends JpaRepository<Notification,Long> {


}
