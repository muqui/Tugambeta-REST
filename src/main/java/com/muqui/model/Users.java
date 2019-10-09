/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.muqui.model;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author hola
 */
@Entity
@Table(name = "users")
public class Users {

    private String username;

    private String password;

    private boolean  enabled;

    private Set<UserRoles> userRoleses = new HashSet<UserRoles>(0);

    public Users() {
    }

    public Users(String username, String password, boolean enabled) {
        this.username = username;
        this.password = password;
        this.enabled = enabled;
    }

    @Id
    @NotNull
    @Column(name = "username", unique = true, nullable = false, length = 45)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

     @Column(name = "password", length = 60)
    @NotNull
    public String getPassword() {
        return password;
    }

    
    public void setPassword(String password) {
        this.password = password;
    }

   @NotNull
    @Column(name = "enabled", nullable = false)
    public boolean isEnabled() {
        return this.enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "users", cascade = CascadeType.REMOVE)
    public Set<UserRoles> getUserRoleses() {
        return userRoleses;
    }

    public void setUserRoleses(Set<UserRoles> userRoleses) {
        this.userRoleses = userRoleses;
    }

}
