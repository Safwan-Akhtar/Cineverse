package com.qa.cineverse.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.persistence.EntityNotFoundException;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "This order does not exist")
public class OrdersNotFoundException extends EntityNotFoundException {
}
