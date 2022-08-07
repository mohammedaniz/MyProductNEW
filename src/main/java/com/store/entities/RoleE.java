package com.store.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.ManyToAny;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "ROLES")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RoleE {
	
	

	public RoleE(long roleId, String roleName) {
		super();
		this.roleId = roleId;
		this.roleName = roleName;
	}


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ROLE_ID")
	private long roleId;
	
	@Column(name = "ROLE_NAME")
	private String roleName;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "ROLE_PERMISSION",
		joinColumns = {@JoinColumn(name = "ROLE_ID")},
		inverseJoinColumns = {@JoinColumn(name = "PERMISSION_ID")})
	private Set<PermissionE> permissions = new HashSet<>();
}
