package io.github.cepr0.demo.commons.event;

import lombok.Getter;
import lombok.Value;

import java.time.LocalDateTime;
import java.util.UUID;

import static io.github.cepr0.demo.commons.event.OrderFailed.Reason.PRODUCT_ENDED;
import static io.github.cepr0.demo.commons.event.OrderFailed.Reason.PRODUCT_NOT_FOUND;

@Value
public class OrderFailed implements Event {
	private UUID orderId;
	private LocalDateTime failedAt;
	private int productId;
	private Reason reason;

	public static OrderFailed of(UUID orderId, int productId, Reason reason) {
		return new OrderFailed(orderId, LocalDateTime.now(), productId, reason);
	}

	public static OrderFailed productNotFound(UUID orderId, int productId) {
		return new OrderFailed(orderId, LocalDateTime.now(), productId, PRODUCT_NOT_FOUND);
	}

	public static OrderFailed productEnded(UUID orderId, int productId) {
		return new OrderFailed(orderId, LocalDateTime.now(), productId, PRODUCT_ENDED);
	}

	public enum Reason {
		PRODUCT_NOT_FOUND("Product not found"),
		PRODUCT_ENDED("Product ended");

		@Getter private final String message;

		Reason(String message) {
			this.message = message;
		}
	}
}
