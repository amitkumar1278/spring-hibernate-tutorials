package com.ag.sjh.specifications.repositories.specs;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.ag.sjh.specifications.domain.Movie;

public class MovieSpecification implements Specification<Movie> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<SearchCriteria> list;
	
	

	public MovieSpecification() {
		super();
		this.list = new ArrayList<>();
	}

    public void add(SearchCriteria criteria) {
        list.add(criteria);
    }

	@Override
	public Predicate toPredicate(Root<Movie> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
		
        /**
         * create a new predicate list
         */
        List<Predicate> predicates = new ArrayList<>();
        
        /**
         * Add Criteria to predicates
         */
        for(SearchCriteria criteria: list) {

        	String key = criteria.getKey();
        	String value = criteria.getValue().toString();
        	String valueLowerCase = value.toLowerCase();
        			
        	if(criteria.getOperation().equals(SearchOperation.GREATER_THAN)) {
        		predicates.add(criteriaBuilder.greaterThan(root.get(key), value));
        	
        	}else if(criteria.getOperation().equals(SearchOperation.LESS_THAN)) {
        		predicates.add(criteriaBuilder.lessThan(root.get(key), value));
        		
        	}else if(criteria.getOperation().equals(SearchOperation.GREATER_THAN_EQUAL)) {
        		predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get(key), value));
        		
        	}else if(criteria.getOperation().equals(SearchOperation.LESS_THAN_EQUAL)) {
        		predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get(key), value));
        		
        	}else if(criteria.getOperation().equals(SearchOperation.NOT_EQUAL)) {
        		predicates.add(criteriaBuilder.notEqual(root.get(key), value));
        		
        	}else if(criteria.getOperation().equals(SearchOperation.EQUAL)) {
        		predicates.add(criteriaBuilder.equal(root.get(key), value));
        		
        	}else if(criteria.getOperation().equals(SearchOperation.MATCH)) {
        		predicates.add(criteriaBuilder.like
        				(criteriaBuilder.lower(root.get(key)), "%"+ valueLowerCase +"%"));
        		
        	}else if(criteria.getOperation().equals(SearchOperation.MATCH_END)) {
        		predicates.add(criteriaBuilder.like
        				(criteriaBuilder.lower(root.get(key)), valueLowerCase +"%"));
        		
        	}else if(criteria.getOperation().equals(SearchOperation.MATCH_START)) {
        		predicates.add(criteriaBuilder.like
        				(criteriaBuilder.lower(root.get(key)), "%"+ valueLowerCase ));
        		
        	}else if(criteria.getOperation().equals(SearchOperation.IN)) {
        		predicates.add(criteriaBuilder.in(root.get(key)).value(value));
        		
        	}else if(criteria.getOperation().equals(SearchOperation.NOT_IN)) {
        		predicates.add(criteriaBuilder.not(root.get(key)).in(value));
        		
        	}
        }
        
		return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
	}

}
