package com.face.po;

public class UserinfoCustom extends Userinfo {
	 	private Long id;
	    private String faceimg;
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public String getFaceimg() {
			return faceimg;
		}
		public void setFaceimg(String faceimg) {
			this.faceimg = faceimg;
		}
		public UserinfoCustom() {
			super();
			// TODO Auto-generated constructor stub
		}
		public UserinfoCustom(Long id, String faceimg,String username,String password,String nickname,String gender) {
			super();
			this.id = id;
			this.faceimg = faceimg;
			setUsername(username);
			setPassword(password);
			setNickname(nickname);
			setGender(gender);
		}
		
		
}
