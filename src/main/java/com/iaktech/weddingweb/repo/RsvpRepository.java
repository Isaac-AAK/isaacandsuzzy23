package com.iaktech.weddingweb.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.iaktech.weddingweb.model.Rsvp;

@Repository
public interface RsvpRepository extends JpaRepository<Rsvp, Long> {

	List<Rsvp> findByContact(String contact);
}
