package com.pihotel.service.impl;

import org.springframework.data.domain.Pageable;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.pihotel.entity.AccountEntity;
import com.pihotel.entity.enums.EAuthenticationProvider;
import com.pihotel.repository.IAccountRepo;
import com.pihotel.service.IAccountServ;
import com.pihotel.service.IJavaSenderService;

import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.utility.RandomString;

@Service
@Slf4j
public class AccountServ implements IAccountServ, UserDetailsService{

	@Autowired
	private IAccountRepo accountRepo;
	
	@Autowired
	private IJavaSenderService senderService;
	
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
		
		if (account.getEnabled() == false) {
			return null;
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

	@Override
	public AccountEntity findOneById(String id) {
		// TODO Auto-generated method stub
		return accountRepo.findOneById(id);
	}

	@Override
	public void saveOneNewAccountByOAuth2(String id, String name, String email, String avatar,
			EAuthenticationProvider provider) {
		// TODO Auto-generated method stub
		AccountEntity account = AccountEntity.builder()
				.username(String.valueOf(System.currentTimeMillis()))
				.password(String.valueOf(System.currentTimeMillis()))
				.email(email)
				.name(name)
				.avatar(avatar)
				.authProvider(provider)
				.build();
		account.setEnabled(email == null ? false : true);
		account.setId(id);
		accountRepo.save(account);
	}

	@Override
	public void updateOneAccountByOAuth2(AccountEntity account, String name, String email, String avatar,
			EAuthenticationProvider provider) {
		// TODO Auto-generated method stub
		account.setName(name);
		account.setEmail(email);
		account.setAvatar(avatar);
		account.setAuthProvider(provider);
		accountRepo.save(account);
	}

	@Override
	public void register(AccountEntity account, String siteURL)
			throws MessagingException, UnsupportedEncodingException {
		// TODO Auto-generated method stub
		String verifyCode = RandomString.make(64);
		
		account.setEnabled(false);
		account.setId(String.valueOf(System.currentTimeMillis()));
		account.setVerificationCode(verifyCode);
		account.setAuthProvider(EAuthenticationProvider.LOCAL);
		
		save(account);
		
		senderService.sendVerificationEmail(account, siteURL);
	}

	@Override
	public Boolean verify(String verificationCode) {
		// TODO Auto-generated method stub
		AccountEntity account = accountRepo.findByVerificationCode(verificationCode);
		if (account == null || account.getEnabled() == true) {
			return false;
		} else {
			account.setVerificationCode(null);
			account.setEnabled(true);
			
			accountRepo.save(account);
			
			return true;
		}
	}

	@Override
	public Page<AccountEntity> findAll(int numPage, String sortField, String sortDir, String keyword) {
		// TODO Auto-generated method stub
		Sort sort = Sort.by(sortField);
		sort = sortDir.equals("asc") ? sort.ascending() : sort.descending();
		Pageable pageable = PageRequest.of(numPage - 1, 10, sort);
		if (keyword == "") {
			return accountRepo.findAll(pageable);
		}
		return accountRepo.search(keyword, pageable);
	}

}
