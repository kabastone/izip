package sn.pamecas.ordremission.web.ressources.authentification;

import java.io.Serializable;

import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;

import sn.pamecas.ordremission.ejb.entites.Utilisateur;
import sn.pamecas.ordremission.ejb.outils.EJBRegistry;
import sn.pamecas.ordremission.ejb.outils.JNDIUtils;
import sn.pamecas.ordremission.ejb.session.UtilisateurFacade;

public class AuthentificationServiceImpl implements AuthentificationService,
		Serializable {

	/**
	 * 
	 */
	private UtilisateurFacade uf = (UtilisateurFacade) JNDIUtils
			.lookUpEJB(EJBRegistry.UtilisateurFacade);
	private static final long serialVersionUID = -217067284487390480L;

	@Override
	public boolean login(String l, String pwd) {
		Utilisateur utilisateur = uf.findByLogin(l);

		if (utilisateur == null) {
			// Cas pour un utilisateur Agence

			return false;
		} else {
			if (!utilisateur.getPassword().equals(pwd)) {
				return false;
			} else {
				UserInfo<Utilisateur> userInfo = new UserInfo<Utilisateur>(
						utilisateur.getId(), utilisateur.getLogin(),
						utilisateur.getGroupes(), utilisateur.getEntite(),
						utilisateur.getFonction(), utilisateur.getStatut(),
						utilisateur.getEmail());

				// userInfo.setEntite(ub);
				// userInfo.setPaysActuel(ub.getBanque().getPays());

				Session session = Sessions.getCurrent();
				session.setAttribute("userInfo", userInfo);
				return true;
			}
		}

	}

	@Override
	public void logout() {
		Session session = Sessions.getCurrent();
		session.removeAttribute("userInfo");
	}

	@Override
	public UserInfo<?> getUserInfo() {
		Session session = Sessions.getCurrent();
		UserInfo<?> userInfo = (UserInfo<?>) session.getAttribute("userInfo");
		if (userInfo == null) {
			userInfo = new UserInfo<Utilisateur>();
			session.setAttribute("userInfo`", userInfo);
		}
		return userInfo;
	}

}
