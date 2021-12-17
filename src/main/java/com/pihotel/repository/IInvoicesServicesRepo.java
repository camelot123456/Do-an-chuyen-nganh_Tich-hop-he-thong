package com.pihotel.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.pihotel.entity.InvoiceServiceEntity;

@Repository
@Transactional
public interface IInvoicesServicesRepo extends JpaRepository<InvoiceServiceEntity, String> {

	public InvoiceServiceEntity findOneById(String id);
	
	@Query(value = "select iss.* "
			+ "from invoice i inner join invoice_service iss "
			+ "on i.id = iss.id_invoice "
			+ "where i.id = ?1 ",
			nativeQuery = true)
	public List<InvoiceServiceEntity> findAllByIdInvoice(String idInvoice);
}
