package com.kulal.test.sampleproject.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kulal.test.sampleproject.exception.SampleException;
import com.kulal.test.sampleproject.model.Documents;
import com.kulal.test.sampleproject.repo.SampleRepositoty;

@RestController
@RequestMapping("/api")
public class SampleController {
	@Autowired
	SampleRepositoty sampleRepositoty;
	@GetMapping("/samples")
	public List<Documents> getAllNotes() {
	    return sampleRepositoty.findAll();
	}
	
	@PostMapping("/samples")
	public Documents createSample(@Valid @RequestBody Documents documents) {
	    return sampleRepositoty.save(documents);
	}
	
	@GetMapping("/samples/{id}")
	public Documents getNoteById(@PathVariable(value = "id") Long docId) {
	    return sampleRepositoty.findById(docId).orElseThrow(() -> new SampleException("Docs", "id", docId));
	}

	
	@PutMapping("/samples/{id}")
	public Documents updateNote(@PathVariable(value = "id") Long docId,
	                                        @Valid @RequestBody Documents docDetails) {

	    Documents docs = sampleRepositoty.findById(docId)
	            .orElseThrow(() -> new SampleException("Docs", "id", docId));

	    docs.setTitle(docDetails.getTitle());
	    docs.setContent(docDetails.getContent());

	   Documents updatedNote = sampleRepositoty.save(docs);
	    return updatedNote;
	}
	
	@DeleteMapping("/samples/{id}")
	public ResponseEntity<?> deleteNote(@PathVariable(value = "id") Long docId) {
	    Documents docs = sampleRepositoty.findById(docId)
	            .orElseThrow(() -> new SampleException("Docs", "id", docId));

	    sampleRepositoty.delete(docs);

	    return ResponseEntity.ok().build();
	}
}
