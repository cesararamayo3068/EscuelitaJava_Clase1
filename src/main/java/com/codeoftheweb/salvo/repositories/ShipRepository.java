package com.codeoftheweb.salvo.repositories;

import com.codeoftheweb.salvo.models.Ship;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShipRepository   extends JpaRepository<Ship, Long> {

}
