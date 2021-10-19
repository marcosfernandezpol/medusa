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

	public Optional<List<OrderLine>> findByOrderTypeAndOwnerAndEnterpriseAndAvaliableOrderByRequestDateDesc(
			OrderType orderType, User owner, Enterprise enterprise, Boolean avaliable);

	public Optional<List<OrderLine>> findByOrderTypeAndOwnerAndEnterpriseOrderByRequestDateDesc(OrderType orderType,
			User Owner, Enterprise enterprise);

	public Slice<OrderLine> findByOwnerAndOrderTypeAndAvaliableOrderByRequestDateDesc(User owner, OrderType orderType,
			Boolean avaliable);

	public Optional<Slice<OrderLine>> findByOwner(User owner, Pageable pageable);

}
