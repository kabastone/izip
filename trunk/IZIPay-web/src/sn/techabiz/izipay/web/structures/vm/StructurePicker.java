package sn.techabiz.izipay.web.structures.vm;

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

public class StructurePicker {

	@Wire("tree")
	Tree tree;
	private Long selected;
	private StructureServices structureServices = (StructureServices) JNDIOutils
			.chercheEJB(RegistreEJB.StructureFacade);

	// A remplacer apres par lid de la structure de lutilisaateur cnnecte
	Long currentScope = 1l;

	@AfterCompose
	public void init(@ContextParam(ContextType.VIEW) Component view) {
		Selectors.wireComponents(view, this, false);

		Structure root = new Structure();
		root = structureServices.find(7l);

		Treechildren treechildren = new Treechildren();
		treechildren.setParent(tree);

		final Treeitem treeitem = new Treeitem(root.getLibelle(), root.getId());
		treeitem.setOpen(false);
		treeitem.setParent(treechildren);

		if (root.getParent() != null) {

			if (root.getParent().getVirtual().FALSE) {
				Treechildren treechildren2 = new Treechildren();
				treechildren2.setParent(treeitem);
				treeitem.addEventListener(Events.ON_OPEN,
						createNode(root.getParent(), treechildren2));

			} else {
				Treechildren treechildren2 = new Treechildren();
				treechildren2.setParent(treeitem);
				treeitem.addEventListener(Events.ON_OPEN,
						createNode(root.getParent().getParent(), treechildren2));
			}
		}

	}

	public EventListener<Event> createNode(final Structure structure,
			final Treechildren item) {

		EventListener<Event> evt = new EventListener<Event>() {

			@Override
			public void onEvent(Event arg0) throws Exception {
				if (item.getChildren().isEmpty()) {
					final Treeitem ti = new Treeitem(structure.getLibelle(),
							structure.getId());
					ti.setOpen(false);
					ti.setParent(item);

					if (structure.getParent() != null) {
						final Treechildren tc = new Treechildren();
						tc.setParent(ti);
						ti.addEventListener(Events.ON_OPEN,
								createNode(structure.getParent(), tc));
					}
				}
			}

		};
		return evt;

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
		Session session = Executions.getCurrent().getSession();
		session.setAttribute("parent", parent);
		Messagebox.show(parent.getLibelle() + " sélectionné");

	}

}
