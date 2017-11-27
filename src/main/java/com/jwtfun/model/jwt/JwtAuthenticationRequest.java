package com.jwtfun.model.jwt;


import java.io.Serializable;

/**
 * Model for binding a isUserCredentialsValid request to.
 */
public class JwtAuthenticationRequest implements Serializable {
  private static final long serialVersionUID = -8445943548965154778L;
  private String username;
  private String password;

  public JwtAuthenticationRequest(final String username, final String password) {
    this.username = username;
    this.password = password;
  }

  public JwtAuthenticationRequest() {
  }

  public String getUsername() {
    return this.username;
  }

  public void setUsername(final String username) {
    this.username = username;
  }

  public String getPassword() {
    return this.password;
  }

  public void setPassword(final String password) {
    this.password = password;
  }

  @Override
  public String toString() {
    return "JwtAuthenticationRequest{"
        +
        "username='" + username + '\''
        +
        ", password='" + password + '\''
        +
        '}';
  }
}

