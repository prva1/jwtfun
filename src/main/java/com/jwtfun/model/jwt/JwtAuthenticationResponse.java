package com.jwtfun.model.jwt;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Model class for validatePasswordUser response.
 */
public class JwtAuthenticationResponse implements Serializable {
  private static final long serialVersionUID = 1250166508152483573L;

  @JsonProperty("access_token")
  private final String token;

  public JwtAuthenticationResponse(final String token) {
    this.token = token;
  }

  public String getToken() {
    return this.token;
  }
}
