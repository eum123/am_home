package com.am.homework.admin.service;

import org.springframework.stereotype.Service;

import com.am.homework.admin.ExternalCommand;
import com.am.homework.common.ExternalInvokeException;

@Service
public class ExternalService {
	/**
	 * category cache 동기화. 
	 * @param categoryNo
	 * @throws Exception
	 */
	public void syncCategory(ExternalCommand command, Integer categoryNo) throws ExternalInvokeException {
		//TODO : HTTP 호출을 통해 category cache값을 업데이트 한다.
	}
	
	/**
	 * product cache 동기화.
	 * @param productNo
	 * @throws Exception
	 */
	public void syncProduct(ExternalCommand command, Long productNo) throws ExternalInvokeException {
		//TODO : HTTP 호출을 통해 product cache값을 업데이트 한다.
	}
}
