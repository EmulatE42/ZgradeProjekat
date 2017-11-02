package com.ftn.ZgradeProjekat.domain;

import javax.persistence.*;

/**
 * Mapping table between User and Authority
 * @author Goran
 *
 */
@Entity
public class UserAuthority {
	@Id
	@GeneratedValue
	private Long id;
	
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	private Korisnik user;
	
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	private Authority authority;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Korisnik getUser() {
		return user;
	}

	public void setUser(Korisnik user) {
		this.user = user;
	}

	public Authority getAuthority() {
		return authority;
	}

	public void setAuthority(Authority authority) {
		this.authority = authority;
	}
}
