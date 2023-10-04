package com.diptrish.qr.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class PropertyConstants {

	@Value("${aes.key}")
	private String ascKey;
}
