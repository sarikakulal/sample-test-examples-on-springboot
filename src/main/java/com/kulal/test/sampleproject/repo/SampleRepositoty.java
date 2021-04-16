package com.kulal.test.sampleproject.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kulal.test.sampleproject.model.Documents;
@Repository
public interface SampleRepositoty extends JpaRepository<Documents, Long> {

}
