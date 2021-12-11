package com.pihotel;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.pihotel.config.AuditorWareImpl;
import com.pihotel.entity.RoomTypeEntity;
import com.pihotel.repository.IRoomTypeRepo;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
@Slf4j
public class QuanLyKhachSanApplication {

	public static void main(String[] args) {
		SpringApplication.run(QuanLyKhachSanApplication.class, args);
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public AuditorAware<String> auditorAware(){
		return new AuditorWareImpl();
	}
	
//	@Bean
//	public CommandLineRunner testRun(IRoomTypeRepo roomTypeRepo) {
//		return args -> {
//			RoomTypeEntity roomTypeEntity = roomTypeRepo.findOneByIdRoom("R01");
//			log.info("id: {}; name: {}; price: {}; description: {}; logo: {}", 
//					roomTypeEntity.getId(),
//					roomTypeEntity.getName(), 
//					roomTypeEntity.getPrice(),
//					roomTypeEntity.getDescription(),
//					roomTypeEntity.getLogo());
//		};
//	}
}
