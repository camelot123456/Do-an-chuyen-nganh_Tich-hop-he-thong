package com.pihotel.service.impl;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
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

import com.pihotel.constant.SystemConstant;
import com.pihotel.entity.AccountEntity;
import com.pihotel.entity.RoleEntity;
import com.pihotel.entity.enums.EAuthenticationProvider;
import com.pihotel.repository.IAccountRepo;
import com.pihotel.repository.IRoleRepo;
import com.pihotel.service.IAccountServ;
import com.pihotel.service.IJavaSenderService;

import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.utility.RandomString;

@Service
@Slf4j
public class AccountServ implements IAccountServ, UserDetailsService {

	@Autowired
	private IAccountRepo accountRepo;
	
	@Autowired
	private IRoleRepo roleRepo;

	@Autowired
	private IJavaSenderService senderService;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

//	---------------------------------------SELECT---------------------------------------
	
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
		account.getRoles().forEach(role -> authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getCode())));

		UserDetails userDetails = new User(account.getUsername(), account.getPassword(), authorities);
		return userDetails;
	}
	
	@Override
	public List<AccountEntity> findAll() {
		// TODO Auto-generated method stub
		return accountRepo.findAll();
	}
	
	@Override
	public AccountEntity findOneById(String id) {
		// TODO Auto-generated method stub
		return accountRepo.findOneById(id);
	}
	
	@Override
	public Page<AccountEntity> findAll(int numPage, String sortField, String sortDir, String keyword) {
		// TODO Auto-generated method stub
		
		Sort sort = Sort.by(sortField);
		sort = sortDir.equals("asc") ? sort.ascending() : sort.descending();
		PageRequest pageable = PageRequest.of(numPage - 1, 10, sort);
		if (keyword != null) {
			return accountRepo.search(keyword, pageable);
		}
		return accountRepo.findAll(pageable);
	}
	
	@Override
	public AccountEntity findOneByUsername(String username) {
		// TODO Auto-generated method stub
		return accountRepo.findByUsername(username);
	}
	
//	---------------------------------------INSERT---------------------------------------
	
	@Override
	public AccountEntity save(AccountEntity account) {
		// TODO Auto-generated method stub
		account.setId(RandomString.make(12));
		account.setPassword(passwordEncoder.encode(account.getPassword()));
		account.setCreateAt(new Date());
		return accountRepo.save(account);
	}
	
	@Override
	public void saveOneNewAccountByOAuth2(String id, String name, String email, String avatar,
			EAuthenticationProvider provider) {
		// TODO Auto-generated method stub
		AccountEntity account = AccountEntity.builder()
				.email(email)
				.name(name)
				.avatar(avatar)
				.authProvider(provider)
				.build();
		account.setEnabled(email == null ? false : true);
		account.setId(id);
		account.setCreateAt(new Date());
		accountRepo.save(account);
	}
	
	@Override
	public void register(AccountEntity account, String siteURL)
			throws MessagingException, UnsupportedEncodingException {
		// TODO Auto-generated method stub
		account.setEnabled(false);
		account.setId(RandomString.make(12));
		account.setVerificationCode(RandomString.make(64));
		account.setAuthProvider(EAuthenticationProvider.LOCAL);
		account.setCreateAt(new Date());
		if (account.getAvatar() == null) {
			account.setAvatar(SystemConstant.AVATAR_ACCOUNT_DEFAULT_LINK);
		}
		save(account);

		senderService.sendVerificationEmail(account, siteURL);
	}
	
	@Override
	public void addRoleToAccount(String idAccount, List<RoleEntity> roles) {
		// TODO Auto-generated method stub
		AccountEntity account = accountRepo.findOneById(idAccount);
		account.setRoles(roles);
		account.setCreateAt(new Date());
		accountRepo.save(account);
	}
	
	@Override
	public void saveWithFile(AccountEntity account) {
		// TODO Auto-generated method stub
		List<RoleEntity> roleArr = new ArrayList<RoleEntity>();
		account.getRoles().forEach(role -> {
			roleArr.add(roleRepo.findOneById(role.getId()));
		});
		account.setRoles(roleArr);
		account.setCreateAt(new Date());
		accountRepo.save(account);
	}
	
	@Override
	public AccountEntity saveCustomer(AccountEntity customer) {
		// TODO Auto-generated method stub
		if (!accountRepo.existsById(customer.getId())) {
			customer.setCreateAt(new Date());
			return accountRepo.save(customer);
		}
		return null;
	}
	
//	---------------------------------------UPDATE---------------------------------------
	
	@Override
	public AccountEntity update(AccountEntity account) {
		// TODO Auto-generated method stub
		if (accountRepo.existsById(account.getId())) {
			account.setPassword(passwordEncoder.encode(account.getPassword()));
			account.setModifiedAt(new Date());
			return accountRepo.save(account);
		} else
			return null;
	}
	
	@Override
	public void updateOneAccountByOAuth2(AccountEntity account, String name, String email, String avatar,
			EAuthenticationProvider provider) {
		// TODO Auto-generated method stub
		account.setName(name);
		account.setEmail(email);
		account.setAvatar(avatar);
		account.setAuthProvider(provider);
		account.setModifiedAt(new Date());
		accountRepo.save(account);
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
			account.setModifiedAt(new Date());
			accountRepo.save(account);

			return true;
		}
	}
	
	@Override
	public int updateCustom(String id, String name, String email, String address,
			String phoneNum, Date birthday, Boolean gender, List<RoleEntity> roles) {
		// TODO Auto-generated method stub
		AccountEntity account = accountRepo.findOneById(id);
		account.setId(id);
		account.setName(name);
		account.setEmail(email);
		account.setAddress(address);
		account.setPhoneNum(phoneNum);
		account.setBirthday(birthday);
		account.setGender(gender);
		account.setModifiedAt(new Date());
		List<RoleEntity> roleArr = new ArrayList<RoleEntity>();
		roles.forEach(role -> {
			roleArr.add(roleRepo.findOneById(role.getId()));
		});
		account.setRoles(roleArr);
		accountRepo.save(account);
		return 1;
	}
	
	@Override
	public AccountEntity updateCustomer(String id, String name, String email, String address,
			String phoneNum, Date birthday, Boolean gender) {
		// TODO Auto-generated method stub
		AccountEntity customer = accountRepo.findOneById(id);
		customer.setId(id);
		customer.setName(name);
		customer.setEmail(email);
		customer.setAddress(address);
		customer.setPhoneNum(phoneNum);
		customer.setBirthday(birthday);
		customer.setGender(gender);
		customer.setModifiedAt(new Date());
		if (accountRepo.existsById(customer.getId())) {
			customer.setModifiedAt(new Date());
			return accountRepo.save(customer);
		}
		return null;
	}
	
//	---------------------------------------DELETE---------------------------------------
	
	@Override
	public void delete(String[] ids) {
		// TODO Auto-generated method stub
		for (String id : ids) {
			accountRepo.deleteById(id);
		}
	}
	
	@Override
	public void deleteById(String id) {
		// TODO Auto-generated method stub
		accountRepo.deleteById(id);
	}

}