package sn.pamecas.ordremission.web.components;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.ext.AfterCompose;
import org.zkoss.zul.Toolbarbutton;

public class ActionButton extends Toolbarbutton implements AfterCompose {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String action;
	private String hideMode;

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getHideMode() {
		return hideMode;
	}

	public void setHideMode(String hideMode) {
		this.hideMode = hideMode;
	}

	@Override
	public void afterCompose() {
		
		this.addEventListener(Events.ON_CLICK, new EventListener<Event>() {

			@Override
			public void onEvent(Event event) throws Exception {
				Events.postEvent("onClickActionButton",
						(Component) getSpaceOwner(), action);

			}

		});
	}

}
