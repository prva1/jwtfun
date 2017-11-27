package com.jwtfun.service.jwt;

import static org.apache.commons.lang3.time.DateUtils.parseDate;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.mobile.device.Device;
import org.springframework.security.core.userdetails.UserDetails;

import com.jwtfun.model.jwt.JwtUser;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JwtTokenUtil implements Serializable {

	private static final long serialVersionUID = -4937331696612986013L;
	private static final String SECRET = "c2VjcmV0"; // -- secret
	private static final Long EXPIRATION = 60000L;
    // 900000
	public JwtTokenUtil() {
	}

	public Claims getClaimsFromToken(final String token) {
		System.out.println("getClaimsFromToken started - token: " + token);
		Claims claims = null;
		try {
			claims = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody();

			System.out.println("getClaimsFromToken ended successfully");
			return claims;
		} catch (Exception e) {
			System.out.println("getClaimsFromToken - Message: " + e.getMessage() + "; Cause: " + e.getCause());
			return claims;
		}
	}

	private Date generateExpirationDate() {
		return new Date(System.currentTimeMillis() + EXPIRATION);
	}

	private String generateAudience(final Device device) {
		String audience = "unknown";

		if (device.isNormal()) {
			audience = "web";
		} else if (device.isTablet()) {
			audience = "tablet";
		} else if (device.isMobile()) {
			audience = "mobile";
		}
		return audience;
	}

	public String generateToken(final UserDetails userDetails, final Device device) {
		Map<String, Object> claims = new HashMap<String, Object>();

		claims.put("iss", "pedro.villalba.a@gmail.com");

		claims.put("created", new Date());

		claims.put("sub", userDetails.getUsername());

		claims.put("scope", userDetails.getAuthorities());

		claims.put("audience", generateAudience(device));

		Map<String, Object> header = new HashMap<String, Object>();

		header.put("typ", "JWT");

		header.put("alg", "HS256");

		return generateToken(claims, header);
	}

	public String generateToken(final Map<String, Object> claims, final Map<String, Object> header) {
		return Jwts.builder().setHeader(header).setClaims(claims).setExpiration(generateExpirationDate())
				.signWith(SignatureAlgorithm.HS256, SECRET).compact();
	}

	public Boolean validateToken(final String token, final UserDetails userDetails) {
		JwtUser user = (JwtUser) userDetails;
		final String username = getUsernameFromToken(token);
		return username.equals(user.getUsername()) && !isTokenExpired(token);
	}

	public Boolean validateToken(final String token) {
		return isTokenExpired(token);
	}

	public Boolean isTokenExpired(final String token) {
		Date expirationDate = getExpirationDateFromToken(token);
		if (expirationDate == null) {
			return false;
		}
		return expirationDate.before(new Date());
	}

	public Date getExpirationDateFromToken(final String token) {
		System.out.println("getExpirationDateFromToken started - token: " + token);
		Claims claims;
		try {
			claims = getClaimsFromToken(token);
			if (claims == null) {
				return null;
			}
			System.out.println("getExpirationDateFromToken ended successfully");
			return claims.getExpiration();
		} catch (Exception e) {
			System.out.println("getExpirationDateFromToken - Message: " + e.getMessage() + "; Cause: " + e.getCause());
			return null;
		}
	}

	public String getUsernameFromToken(final String token) {
		System.out.println("getUsernameFromToken started - token: " + token);
		try {
			String userName = getClaimsFromToken(token).getSubject();
			System.out.println("getUsernameFromToken ended successfully");
			return userName;
		} catch (Exception e) {
			System.out.println("getUsernameFromToken - Message: " + e.getMessage() + "; Cause: " + e.getCause());
			return null;
		}
	}

	public List<String> getScopeFromToken(final String token) {
		System.out.println("getScopeFromToken started - token: " + token);
		try {
			List permissionsList = (ArrayList) Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody()
					.get("scope");
			System.out.println("getScopeFromToken ended successfully");
			return permissionsList;
		} catch (Exception e) {
			System.out.println("getScopeFromToken - Message: " + e.getMessage() + "; Cause: " + e.getCause());
			return Collections.emptyList();
		}
	}

	public Date getCreatedDateFromToken(final String token) {
		System.out.println("getCreatedDateFromToken started - token: " + token);
		try {
			final Claims claims = getClaimsFromToken(token);
			return parseDate((String) claims.get("created"), "yyyy-MM-dd'T'HH:mm:ss");

		} catch (Exception e) {
			System.out.println("getCreatedDateFromToken - Message: " + e.getMessage() + "; Cause: " + e.getCause());
			return null;
		}
	}

	public List retrieveGroups(final String jwtToken) {
		System.out.println("retrieveGroups started - jwtToken: " + jwtToken);
		if (!StringUtils.isBlank(jwtToken) && StringUtils.countMatches(jwtToken, ".") == 2) {
			if (!this.validateToken(jwtToken).booleanValue()) {
				List permissionList = this.getScopeFromToken(jwtToken);
				System.out.println("retrieveGroups ended successfully - Permissions retrieved");
				return permissionList;
			}
		} else {
			System.out.println("Token is: Null - Empty / Bad structure / Expired");
		}

		System.out.println("retrieveGroups ended unsuccessfully - Permissions can not be retrieved");

		System.out.println("retrieveGroups ended unsuccessfully");
		return Collections.emptyList();
	}

}