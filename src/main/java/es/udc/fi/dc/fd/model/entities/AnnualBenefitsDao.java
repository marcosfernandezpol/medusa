package es.udc.fi.dc.fd.model.entities;

import org.springframework.data.domain.Slice;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface AnnualBenefitsDao extends PagingAndSortingRepository<AnnualBenefits, Long> {

	Slice<AnnualBenefits> findAnnualBenefitsByEnterpriseIdOrderByYearAsc(Long id);

}
