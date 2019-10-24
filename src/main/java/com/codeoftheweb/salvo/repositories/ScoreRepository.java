package com.codeoftheweb.salvo.repositories;

import com.codeoftheweb.salvo.models.Score;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScoreRepository  extends JpaRepository<Score, Long> {
}
