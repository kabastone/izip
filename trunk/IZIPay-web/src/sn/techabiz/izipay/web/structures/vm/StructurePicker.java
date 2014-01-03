package sn.techabiz.izipay.web.structures.vm;

import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Tree;
import org.zkoss.zul.Treechildren;
import org.zkoss.zul.Treeitem;

import sn.techabiz.izipay.ejb.structures.entities.Structure;

public class StructurePicker {

@Wire("tree")
Tree tree;

//A remplacer apres par lid de la structure de lutilisaateur cnnecte
Long currentScope = 1l;
	
@AfterCompose
public void init(@ContextParam(ContextType.VIEW)Component view)
{
	Selectors.wireComponents(view, this, false);
	
	Structure root = new Structure();
	root.setId(1l);
	root.setLibelle("Reseau");
	
	Treechildren treechildren = new Treechildren();
	treechildren.setParent(tree);
	
	Treeitem treeitem = new Treeitem(root.getLibelle(), root.getId());
	treeitem.setOpen(false);
	treeitem.setParent(treechildren);
	treeitem.addEventListener(Events.ON_OPEN, new EventListener<Event>() {

		@Override
		public void onEvent(Event event) throws Exception {
			// TODO Auto-generated method stub
			
		}
	});
	new Treechildren().setParent(treeitem);
}



}
