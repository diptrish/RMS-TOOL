package com.diptrish.qr.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.diptrish.qr.dto.QRCodeDto;
import com.diptrish.qr.util.AscUtil;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class QrCodeGenerationService {
	
	@Autowired
	private AscUtil AscUtil;
	
	public String createQr(QRCodeDto inputData) {
		
		String orgText = new StringBuffer(inputData.getName()).append("#-#")
				.append(inputData.getCode()).append("#-#")
				.append(inputData.getTableNo()).append("#-#")
				.toString();
		
		return orgText;
	}

}
