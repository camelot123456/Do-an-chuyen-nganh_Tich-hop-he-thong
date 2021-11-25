package com.pihotel.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.pihotel.entity.AccountEntity;
import com.pihotel.repository.IAccountRepo;
import com.pihotel.service.IAccountServ;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AccountServ implements IAccountServ, UserDetailsService{

	@Autowired
	private IAccountRepo accountRepo;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		AccountEntity account = accountRepo.findByUsername(username);
		if (account == null) {
			log.warn("Username {} is not exist in database", username);
			throw new UsernameNotFoundException("Username not found!!");
		}
		
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		account.getRoles().forEach(role -> authorities.add(new SimpleGrantedAuthority("ROLE_"+role.getCode())));
		
		UserDetails userDetails = new User(account.getUsername(), account.getPassword(), authorities);
		return userDetails;
	}

	@Override
	public List<AccountEntity> findAll() {
		// TODO Auto-generated method stub
		return accountRepo.findAll();
	}

	@Override
	public AccountEntity save(AccountEntity account) {
		// TODO Auto-generated method stub
		if (!accountRepo.existsById(account.getId())) {
			account.setPassword(passwordEncoder.encode(account.getPassword()));
			return accountRepo.save(account);
		}
		else return null;
	}

	@Override
	public AccountEntity update(AccountEntity account) {
		// TODO Auto-generated method stub
		if (accountRepo.existsById(account.getId())) {
			account.setPassword(passwordEncoder.encode(account.getPassword()));
			return accountRepo.save(account);
		}
		else return null;
	}

	@Override
	public void delete(String[] ids) {
		// TODO Auto-generated method stub
		for (String id : ids) {
			accountRepo.deleteById(id);
		}
	}

}
