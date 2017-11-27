package com.jwtfun.service.jwt;

import com.jwtfun.configuration.security.model.User;
import com.jwtfun.model.jwt.JwtUser;

/**
 * Factory class for creating JwtUser's.
 */
public final class JwtUserFactory {

  private JwtUserFactory() {
  }

  public static JwtUser create(final User user) {
    return new JwtUser(user);
  }
}
