package com.ftn.ZgradeProjekat.repository;

import com.ftn.ZgradeProjekat.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by djuro on 11/30/2017.
 */
public interface CommentRepository extends JpaRepository<Comment,Long>
{
}
