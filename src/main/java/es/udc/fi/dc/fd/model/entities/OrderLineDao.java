package es.udc.fi.dc.fd.model.entities;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.repository.PagingAndSortingRepository;

import es.udc.fi.dc.fd.model.entities.OrderLine.OrderType;

public interface OrderLineDao extends PagingAndSortingRepository<OrderLine, Long> {

	public Optional<Slice<OrderLine>> findByOrderTypeAndEnterpriseAndAvaliableOrderByRequestDateDesc(
			OrderType orderType, Enterprise enterprise, Boolean avaliable, Pageable pageable);

	public Optional<List<OrderLine>> findByOrderTypeAndEnterpriseAndAvaliableOrderByRequestDateDesc(OrderType orderType,
			Enterprise enterprise, Boolean avaliable);
	
	public Optional<List<OrderLine>> findByOrderTypeAndEnterpriseAndAvaliableOrderByRequestDateAsc(OrderType orderType,
			Enterprise enterprise, Boolean avaliable);
	
	public Optional<List<OrderLine>> findByOrderTypeAndEnterpriseAndAvaliableOrderByPriceAsc(OrderType orderType,
			Enterprise enterprise, Boolean avaliable);
	
	public Optional<List<OrderLine>> findByOrderTypeAndEnterpriseAndAvaliableOrderByPriceAscRequestDateAsc(OrderType orderType,
			Enterprise enterprise, Boolean avaliable);
	
	public Optional<List<OrderLine>> findByOrderTypeAndOwnerAndEnterpriseAndAvaliableOrderByRequestDateDesc(
			OrderType orderType, User owner, Enterprise enterprise, Boolean avaliable);

	public Optional<List<OrderLine>> findByOrderTypeAndOwnerAndEnterpriseOrderByRequestDateDesc(OrderType orderType,User owner, Enterprise enterprise);

	public Optional<List<OrderLine>> findByOwnerAndOrderTypeAndAvaliableOrderByRequestDateDesc(User owner, OrderType orderType,Boolean avaliable);

	public Optional<Slice<OrderLine>> findByOwner(User owner, Pageable pageable);
	
	public Optional<List<OrderLine>> findByOwnerAndAvaliableOrderByRequestDateDesc(User owner, Boolean avaliable);
	
	public Optional<List<OrderLine>> findByOrderTypeAndOwnerAndEnterpriseAndAvaliableAndCancelledOrderByRequestDateDesc(OrderType orderType,User owner,
		Enterprise enterprise, Boolean avaliable, Boolean cancelled);
	
	public Optional<List<OrderLine>> findByOrderTypeAndOwnerAndEnterpriseAndCancelledOrderByRequestDateDesc(OrderType orderType, User owner,
			Enterprise enterprise, Boolean cancelled);

}
