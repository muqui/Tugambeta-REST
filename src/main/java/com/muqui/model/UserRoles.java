/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.muqui.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
/**
 *
 * @author hola
 */
@Entity
@Table(name = "user_roles")
public class UserRoles {

    
     private Integer userRoleId;
     private Users users;
     private String role;

    public UserRoles() {
    }

    public UserRoles(Users users, String role) {
       this.users = users;
       this.role = role;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="user_role_id", unique=true, nullable=false)
    public Integer getUserRoleId() {
        return this.userRoleId;
    }
    
    public void setUserRoleId(Integer userRoleId) {
        this.userRoleId = userRoleId;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="username", nullable=false)
    public Users getUsers() {
        return this.users;
    }
    
    public void setUsers(Users users) {
        this.users = users;
    }

    
    @Column(name="role", nullable=false, length=45)
    public String getRole() {
        return this.role;
    }
    
    public void setRole(String role) {
        this.role = role;
    }


}
