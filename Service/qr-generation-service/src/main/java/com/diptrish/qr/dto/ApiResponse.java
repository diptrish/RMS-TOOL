package com.diptrish.qr.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ApiResponse {
	private String message;
	private Integer status;
}
