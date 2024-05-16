package lv.venta.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lv.venta.config.MyUserDetails;
import lv.venta.model.MyUser;
import lv.venta.repo.IUserRepo;

@Service
public class MyUserDetailServiceImpl implements UserDetailsService{
	@Autowired
	private IUserRepo userRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		
		MyUser user = userRepo.findByUsername(username);
		
		if(user == null) throw new UsernameNotFoundException(username + "not found");
		
		MyUserDetails userDetails = new MyUserDetails(user);
		return userDetails;
	}

}
