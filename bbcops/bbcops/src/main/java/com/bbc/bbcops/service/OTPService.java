package com.bbc.bbcops.service;

import org.springframework.stereotype.Service;

import com.bbc.bbcops.dao.OTPDao;

@Service
public class OTPService {

	private OTPDao otpDao;

	public OTPService(OTPDao otpDao) {
		super();
		this.otpDao = otpDao;
	}
	
	public Boolean checkOTP(Long customerId,String otp) {
		String getOtp = otpDao.checkOTP(customerId);
		if(otp.equals(getOtp)) {
			return true;
		}
		else {
			return false;
		}
		
	}
}
