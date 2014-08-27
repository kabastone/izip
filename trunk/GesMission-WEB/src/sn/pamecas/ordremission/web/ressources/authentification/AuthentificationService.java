package sn.pamecas.ordremission.web.ressources.authentification;

public interface AuthentificationService {
	
	public boolean login(String l, String pwd);

	public void logout();

	public UserInfo<?> getUserInfo();
}
