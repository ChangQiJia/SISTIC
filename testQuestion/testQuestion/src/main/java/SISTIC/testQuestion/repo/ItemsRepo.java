package SISTIC.testQuestion.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import SISTIC.testQuestion.entity.Items;

@Repository
public interface ItemsRepo extends JpaRepository <Items, Integer>{
		
	Items findByProductName (String productName);
	
	Items findById (int id);
	
	@Query ("SELECT i FROM Items i WHERE i.quantity < (:quant)")
	List<Items> findLessThanQuantity (@Param ("quant") int quantity);
	
	@Query ("SELECT i FROM Items i WHERE i.price < (:price)")
	List<Items> findLessThanPrice (@Param ("price") double price);
	
	@Query ("SELECT i FROM Items i WHERE i.price > (:price)")
	List<Items> findMoreThanPrice (@Param ("price") double price);
	
	@Query ("SELECT i FROM Items i WHERE i.quantity > '0'")
	List<Items> findHasStock ();
}
