package com.pihotel.config;

import java.util.Optional;

import org.springframework.data.domain.AuditorAware;

import com.pihotel.constant.SystemConstant;

/*
*Cấu hình đối tượng nào đã và đang cập nhập dữ liệu với auditing
**/

//@Transactional(propagation = Propagation.REQUIRES_NEW)
public class AuditorWareImpl implements AuditorAware<String>{

	@Override
	public Optional<String> getCurrentAuditor() {
		// TODO Auto-generated method stub
//		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		return Optional.of(SystemConstant.EMAIL_AUDITING);
	}

}
