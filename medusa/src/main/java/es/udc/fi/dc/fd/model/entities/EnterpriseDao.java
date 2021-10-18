package es.udc.fi.dc.fd.model.entities;

import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * The Interface EnterpriseDao.
 */
public interface EnterpriseDao extends

		PagingAndSortingRepository<Enterprise, Long> {

	boolean existsByEnterpriseName(String enterpriseName);

}
