package com.store.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "PERMISSIONS")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PermissionE {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PERMISSION_ID")
	private long permissionId;
	
	@Column(name = "PERMISSION_NAME", unique = true)
	private String permissionName;
	
	@ManyToMany(mappedBy = "permissions")
	private Set<RoleE> roles = new HashSet<>();
} 
