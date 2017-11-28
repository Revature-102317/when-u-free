package com.whenufree.search;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.lucene.search.Query;

import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.FullTextQuery;
import org.hibernate.search.jpa.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
    
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Repository;

import com.whenufree.model.User;
import com.whenufree.model.FriendGroup;

@Repository
@Transactional
public class UserGroupSearch{

    @PersistenceContext
    private EntityManager entityManager;

    public List search(String text, String type){
	// get the full text entity manager
	FullTextEntityManager fullTextEntityManager =
	    Search.getFullTextEntityManager(entityManager);
    
	// create the query using Hibernate Search query DSL
	QueryBuilder queryBuilder = 
	    fullTextEntityManager.getSearchFactory()
	    .buildQueryBuilder().forEntity(User.class).get();
    
	// a very basic query by keywords
	Query query =
	    queryBuilder
	    .keyword()
	    .onFields("firstname", "lastname")
	    .matching(text)
	    .createQuery();

	// wrap Lucene query in an Hibernate Query object
	FullTextQuery jpaQuery = null;
	switch(type){
	case "all":
	    jpaQuery = fullTextEntityManager.createFullTextQuery(query, User.class, FriendGroup.class);
	    break;
	case "user":
	    jpaQuery = fullTextEntityManager.createFullTextQuery(query, FriendGroup.class);
	    break;
	case "group":
	    jpaQuery = fullTextEntityManager.createFullTextQuery(query, FriendGroup.class);
	    break;
	}
	// execute search and return results (sorted by relevance as default)
	@SuppressWarnings("unchecked")
	    List results = jpaQuery.getResultList();
    
	return results;
    }

    
}
