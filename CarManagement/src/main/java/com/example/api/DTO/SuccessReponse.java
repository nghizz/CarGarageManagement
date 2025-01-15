package com.example.api.DTO;

public class SuccessReponse {
	private String status;
    private String message;
    private String username;
    private String role;
    private String token;
    
    public SuccessReponse(String status, String message, String username, String role, String token) {
        this.status = status;
        this.message = message;
        this.username = username;
        this.role = role;
        this.token = token;
    }

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
    
}
