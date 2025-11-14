package com.bitmax.ojt.repository;

import com.bitmax.ojt.entitiy.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board,Long> {
}
