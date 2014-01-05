package sn.techabiz.izipay.web.structures.vm;

import java.util.List;

import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Tree;
import org.zkoss.zul.Treechildren;
import org.zkoss.zul.Treeitem;

import sn.techabiz.izipay.ejb.structures.entities.Structure;
import sn.techabiz.izipay.ejb.structures.services.StructureServices;
import sn.techabiz.izipay.services.JNDIOutils;
import sn.techabiz.izipay.services.RegistreEJB;

public class StructurePicker implements EventListener<Event> {

	@Wire("tree")
	Tree tree;
	private Long selected;
	private StructureServices structureServices = (StructureServices) JNDIOutils
			.chercheEJB(RegistreEJB.StructureFacade);

	// A remplacer apres par lid de la structure de lutilisaateur cnnecte
	Long currentScope = 1l;
	
	Component rootComponent;

	@AfterCompose
	public void init(@ContextParam(ContextType.VIEW) Component view) {
		
		
		Selectors.wireComponents(view, this, false);

		Structure root = new Structure();
		root = structureServices.find(currentScope);

		Treechildren treechildren = new Treechildren();
		treechildren.setParent(tree);

		final Treeitem treeitem = new Treeitem(root.getLibelle(), root.getId());
		treeitem.setOpen(false);
		treeitem.setParent(treechildren);

		treeitem.appendChild(new Treechildren());
		treeitem.addEventListener(Events.ON_OPEN, this);

	}

	public Long getSelected() {
		return selected;
	}

	public void setSelected(Long selected) {
		this.selected = selected;
	}

	@Command("save")
	public void valider() {
		Structure parent = new Structure();
		parent = structureServices.find(selected);
		
		Messagebox.show(parent.getLibelle() + " sélectionné");

	}

	@Override
	public void onEvent(Event event) throws Exception {
		Treeitem treeitem = (Treeitem) event.getTarget(), ti;

		List<Structure> lst_str = structureServices
				.findByParent(structureServices.find((Long) treeitem.getValue())), lst_vir;

		Treechildren tc = treeitem.getTreechildren();
		
		tc.getChildren().clear();

		for (Structure t : lst_str) {
			if (t.getVirtual()) {
				lst_vir = structureServices.findByParent(structureServices
						.find(t.getId()));
				for (Structure tv : lst_vir) {
					ti = new Treeitem();
					ti.appendChild(new Treechildren());
					ti.setValue(tv.getId());
					ti.setLabel(tv.getLibelle());
					tc.appendChild(ti);
					ti.addEventListener(Events.ON_OPEN, this);
					ti.setOpen(false);
				}
			} else {
				ti = new Treeitem();
				ti.appendChild(new Treechildren());
				ti.setValue(t.getId());
				ti.setLabel(t.getLibelle());
				tc.appendChild(ti);
				ti.addEventListener(Events.ON_OPEN, this);
				ti.setOpen(false);
			}
		}

		event.getTarget().appendChild(tc);
	}
}
