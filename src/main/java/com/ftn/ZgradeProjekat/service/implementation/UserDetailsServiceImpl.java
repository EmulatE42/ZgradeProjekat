package com.ftn.ZgradeProjekat.service.implementation;

import com.ftn.ZgradeProjekat.domain.User;
import com.ftn.ZgradeProjekat.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

  @Autowired
  private UserRepository userRepository;

  @Override
  //@Transactional
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
      System.out.println("1111111");
    User user = userRepository.findByUsername(username);
      System.out.println("2222222");

    if (user == null) {
      throw new UsernameNotFoundException(String.format("No user found with username '%s'.", username));
    } else {
    	/*List<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();
    	for (UserAuthority ua: user.getUserAuthorities()) {
    		grantedAuthorities.add(new SimpleGrantedAuthority(ua.getAuthority().getName()));
    	}*/
    	
    	//Java 1.8 way
        System.out.println("3333333");
        System.out.println(username);
        System.out.println(user.getFirstUserAuthority());
        System.out.println(user.getPassword());
        System.out.println(user.getUserAuthorities());
    	List<GrantedAuthority> grantedAuthorities = user.getUserAuthorities().stream()
                .map(authority -> new SimpleGrantedAuthority(authority.getAuthority().getName()))
                .collect(Collectors.toList());
        System.out.println("444444444444");
    	return new org.springframework.security.core.userdetails.User(
    		  user.getUsername(),
    		  user.getPassword(),
    		  grantedAuthorities);
    }
  }

}
