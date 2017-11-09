package com.ambient.model;

public class LoginCredential {

	
		protected String login;
		
		protected String password;
		protected int level;
		
		public int getNivel() {
			return level;
		}

		public void setNivel(int level) {
			this.level = level;
		}

		public String getLogin() {
			return login;
		}

		public void setLogin(String login) {
			this.login = login;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}
}
