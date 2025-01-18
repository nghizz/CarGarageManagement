package com.example.api.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtTokenProvider {

	private static final String SECRET_KEY = "JpjOz0RQbc7WjN+DQ6sr5PpeU/OfyLhA+r0YLWXa+MqYNg2tt8CExh1XECLvgDPum7bmm9s1TzM2kE7BcM2xKw==";

	// Thời gian hết hạn token (30 phút)
	private static final long EXPIRATION_TIME = 1800000; // 30 phút (30 * 60 * 1000)

	// Tạo JWT Token
	public String generateToken(UserDetails userDetails) {
		return Jwts.builder().setSubject(userDetails.getUsername()).setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
				.signWith(SignatureAlgorithm.HS512, SECRET_KEY).compact();
	}

	// Lấy username từ token
	public String getUsernameFromToken(String token) {
		try {
			// Loại bỏ các ký tự không hợp lệ trong token
			token = token.replaceAll("\\s+", ""); // Loại bỏ khoảng trắng trong token

			return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody().getSubject();
		} catch (Exception e) {
			System.out.println("Lỗi khi giải mã token: " + e.getMessage());
		}
		return null; // Trả về null nếu không lấy được username
	}

	public boolean validateToken(String token) {
	    try {
	        // Loại bỏ các dấu cách và các ký tự không hợp lệ
	        token = token.replaceAll("\\s+", ""); // Loại bỏ tất cả các khoảng trắng

	        // Kiểm tra cấu trúc của token
	        if (token.split("\\.").length != 3) {
	            System.out.println("Token không đúng định dạng JWT (header.payload.signature)");
	            return false;
	        }

	        // Giải mã và kiểm tra token
	        Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token);
	        return true;
	    } catch (io.jsonwebtoken.ExpiredJwtException e) {
	        System.out.println("Token đã hết hạn: " + e.getMessage());
	    } catch (io.jsonwebtoken.SignatureException e) {
	        System.out.println("Chữ ký token không hợp lệ: " + e.getMessage());
	    } catch (io.jsonwebtoken.MalformedJwtException e) {
	        System.out.println("Token không hợp lệ: " + e.getMessage());
	    } catch (io.jsonwebtoken.UnsupportedJwtException e) {
	        System.out.println("Token không được hỗ trợ: " + e.getMessage());
	    } catch (IllegalArgumentException e) {
	        System.out.println("Token rỗng hoặc không hợp lệ: " + e.getMessage());
	    } catch (Exception e) {
	        System.out.println("Lỗi không xác định: " + e.getMessage());
	    }
	    return false;
	}


}
