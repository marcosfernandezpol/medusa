package es.udc.fi.dc.fd.rest.dtos;

import java.util.List;
import java.util.stream.Collectors;

import es.udc.fi.dc.fd.model.entities.OrderLine;

public class OrderLineConversor {

	private OrderLineConversor() {
	}

	public final static OrderLineDto toOrderLineDto(OrderLine order) {
		return new OrderLineDto(order.getId(), order.getRequestDate(), order.getOrderType(), order.getOwner().getId(),
				order.getPrice(), order.getNumber(), order.getEnterprise().getId());
	}

	public final static List<OrderLineDto> toOrderLineDtos(List<OrderLine> orders) {
		return orders.stream().map(order -> toOrderLineDto(order)).collect(Collectors.toList());
	}

}
