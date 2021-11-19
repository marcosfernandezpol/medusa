package es.udc.fi.dc.fd.rest.dtos;

import java.util.List;
import java.util.stream.Collectors;

import es.udc.fi.dc.fd.model.entities.OrderLine;

public class OrderLineConversor {

	private OrderLineConversor() {
	}

	public final static OrderLineDto toOrderLineDto(OrderLine order) {
		if (order.getOwner() == null) {
			return new OrderLineDto(order.getId(), order.getRequestDate(), order.getOrderType(), null, order.getPrice(),
					order.getNumber(), order.getEnterprise().getId(), order.getDeadline(), order.getOrderLineType());
		} else {
			return new OrderLineDto(order.getId(), order.getRequestDate(), order.getOrderType(),
					order.getOwner().getId(), order.getPrice(), order.getNumber(), order.getEnterprise().getId(),
					order.getDeadline(), order.getOrderLineType());
		}

	}

	public final static List<OrderLineDto> toOrderLineDtos(List<OrderLine> orders) {
		return orders.stream().map(order -> toOrderLineDto(order)).collect(Collectors.toList());
	}

}
