package com.whenufree.search;

import java.util.List;
import java.util.ArrayList;
import java.io.IOException;
import java.io.StringReader;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.lucene.search.Query;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.BooleanQuery; 
import org.apache.lucene.search.BooleanClause;
import org.apache.lucene.search.BooleanClause.Occur;
import org.apache.lucene.search.RegexpQuery;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;

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
	//	QueryBuilder queryBuilder = 
	//  fullTextEntityManager.getSearchFactory()
	//  .buildQueryBuilder().forEntity(User.class).get();
    
	// a very basic query by keywords

	/*Query query =
	    queryBuilder
	    .keyword() 
	    .onFields("firstname", "lastname")
	    .matching(text)
	    .createQuery();
	*/
	//System.out.println(query.getClass());

	List<String> terms = tokenizeString(new StandardAnalyzer(), text);
	String[] fields = {"firstname", "lastname", "name", "email"};
	
	BooleanQuery.Builder builder = new BooleanQuery.Builder();

	for(String t: terms){
	    String lowert = t.toLowerCase() + ".*";
	    for(String f: fields){
		builder.add(new RegexpQuery(new Term(f, lowert)), Occur.SHOULD);
	    }
	}
	
	Query query =  builder.build();
	
	// wrap Lucene query in an Hibernate Query object
	FullTextQuery jpaQuery = null;
	switch(type){
	case "all":
	    jpaQuery = fullTextEntityManager.createFullTextQuery(query, User.class, FriendGroup.class);
	    break;
	case "user":
	    jpaQuery = fullTextEntityManager.createFullTextQuery(query, User.class);
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


    public static List<String> tokenizeString(Analyzer analyzer, String string) {
	List<String> result = new ArrayList<String>();
	try {
	    TokenStream stream  = analyzer.tokenStream(null, new StringReader(string));
	    stream.reset();
	    while (stream.incrementToken()) {
		result.add(stream.getAttribute(CharTermAttribute.class).toString());
	    }
	} catch (IOException e) {
	    // not thrown b/c we're using a string reader...
	    throw new RuntimeException("This should never happen, we're using a string reader");
	}
	return result;
    }
    
}
