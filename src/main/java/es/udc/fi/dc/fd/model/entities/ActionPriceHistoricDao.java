package es.udc.fi.dc.fd.model.entities;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Slice;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ActionPriceHistoricDao extends PagingAndSortingRepository<ActionPriceHistoric, Long> {

	List<ActionPriceHistoric> findActionPriceHistoricByEnterpriseIdOrderByDateAsc(Long id);

}