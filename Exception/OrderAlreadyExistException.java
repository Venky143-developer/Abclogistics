package com.alpha.ABClogistics.Exception;

public class OrderAlreadyExistException extends RuntimeException {
	public OrderAlreadyExistException(String message) {
		super(message);
	}

}
