package com.whenufree.search;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.search.jpa.Search;
import org.hibernate.search.jpa.FullTextEntityManager;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Component;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;

@Transactional
@Component
public class BuildSearchIndex implements ApplicationListener<ApplicationReadyEvent> {
  
    @PersistenceContext
    private EntityManager entityManager;
  
    /**
     * Create an initial Lucene index for the data already present in the
     * database.
     * This method is called when Spring's startup.
     */
    @Override
    public void onApplicationEvent(final ApplicationReadyEvent event) {
	try {
	    FullTextEntityManager fullTextEntityManager =
		Search.getFullTextEntityManager(entityManager);
	    fullTextEntityManager.createIndexer().startAndWait();
	}
	catch (InterruptedException e) {
	    System.out.println(
			       "An error occurred trying to build the serach index: " +
			       e.toString());
	}
	return;
    }
}
