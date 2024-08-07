package com.example.java_project.model;


public class JwtResponse {

    private String username;

    private String jwtToken;

    public JwtResponse() {
    }

    public JwtResponse(String username, String jwtToken) {
        this.username = username;
        this.jwtToken = jwtToken;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getJwtToken() {
        return jwtToken;
    }

    public void setJwtToken(String jwtToken) {
        this.jwtToken = jwtToken;
    }

    @Override
    public String toString() {
        return "JwtResponse [username=" + username + ", jwtToken=" + jwtToken + "]";
    }

    private JwtResponse(Builder builder) {
        this.username = builder.username;
        this.jwtToken = builder.jwtToken;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String username;
        private String jwtToken;

        public Builder username(String username) {
            this.username = username;
            return this;
        }

        public Builder jwtToken(String jwtToken) {
            this.jwtToken = jwtToken;
            return this;
        }

        public JwtResponse build() {
            return new JwtResponse(this);
        }
    }


}