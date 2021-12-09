package es.udc.fi.dc.fd.model.entities;

import java.util.List;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ActionPriceHistoricDao extends PagingAndSortingRepository<ActionPriceHistoric, Long> {

	List<ActionPriceHistoric> findActionPriceHistoricByEnterpriseIdOrderByDateAsc(Long id);

}