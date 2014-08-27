package sn.pamecas.ordremission.web.demande.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Hlayout;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.ListitemRenderer;

import sn.pamecas.ordremission.ejb.entites.ValeurRessource;
import sn.pamecas.ordremission.ejb.outils.EJBRegistry;
import sn.pamecas.ordremission.ejb.outils.JNDIUtils;
import sn.pamecas.ordremission.ejb.session.ValeurRessourceFacade;
import sn.pamecas.ordremission.web.ressources.authentification.UserInfo;

public class FraisMissionControl extends SelectorComposer<Component> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Wire
	private Hlayout lstFraisMission;

	private ListModelList<ValeurRessource> vaModel = new ListModelList<ValeurRessource>();
	private List<ValeurRessource> listValeurRessource = new ArrayList<ValeurRessource>();

	@Override
	public void doAfterCompose(Component comp) throws Exception {
		// TODO Auto-generated method stub
		super.doAfterCompose(comp);
		Session session = Sessions.getCurrent();
		UserInfo<?> uf = (UserInfo<?>) session.getAttribute("userInfo");
	   
		Map<String, Object> params = (Map<String, Object>) Executions.getCurrent().getArg();
		Date dateDeb = (Date) params.get("dateDeb");
		Date dateFin = (Date)params.get("dateFin");
		ValeurRessourceFacade vf = (ValeurRessourceFacade) JNDIUtils.lookUpEJB(EJBRegistry.ValeurRessourceFacade);
		vaModel = new ListModelList<ValeurRessource>(uf.getStatut().getPerdiem().getValeurRessources());
		for(int i = dateDeb.getDay(); i < dateFin.getDay(); i++){
			Listbox lstbx = new Listbox();
			lstbx.setModel(vaModel);
			lstbx.setCheckmark(true);
			lstbx.setMultiple(true);
			lstbx.setItemRenderer(new ListitemRenderer<ValeurRessource>() {

				@Override
				public void render(Listitem item, ValeurRessource data,
						int index) throws Exception {
				Listcell cell = new Listcell();
				cell.setParent(item);
				Listcell cellCode = new Listcell(data.getCode());
				cellCode.setParent(item);
				
					
				}
			});
			
			lstbx.addEventListener("onChange", new EventListener<Event>() {

				@Override
				public void onEvent(Event event) throws Exception {
					
					listValeurRessource.addAll(vaModel.getSelection());
				}
			});
			lstbx.setParent(lstFraisMission);
		}
		
	}
	@Listen("onClick = #btValider")
	public void save(){
		Events.postEvent("onListeMission", getSelf().getParent(), listValeurRessource);
		getSelf().detach();
	}
}
