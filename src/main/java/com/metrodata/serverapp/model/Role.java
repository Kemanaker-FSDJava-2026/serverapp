package com.metrodata.serverapp.model;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_role")
public class Role {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(unique = true, nullable = false)
  private String name;

  @JsonIgnore
  @ManyToMany(mappedBy = "roles")
  private Set<User> users;

  @ManyToMany
  @JoinTable(
      name = "tr_role_privilege", 
      joinColumns = @JoinColumn(name = "role_id"), 
      inverseJoinColumns = @JoinColumn(name = "privilege_id"))
  private Set<Privilege> privileges;
}
