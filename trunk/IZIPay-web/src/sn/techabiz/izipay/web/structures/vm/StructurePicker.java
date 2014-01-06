package sn.techabiz.izipay.web.structures.vm;

import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.zhtml.Messagebox;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Tree;
import org.zkoss.zul.Window;

import sn.techabiz.izipay.web.commons.StructureOutils;

public class StructurePicker {

	@Wire("tree")
	Tree tree;
	private Long selected;

	// A remplacer apres par lid de la structure de lutilisaateur cnnecte
	Long currentScope = 1l;

	Component rootComponent;
	private StructureOutils so = new StructureOutils();

	@AfterCompose
	public void init(@ContextParam(ContextType.VIEW) Component view) {

		Selectors.wireComponents(view, this, false);
		so.initTree(tree, currentScope);
	}

	public Long getSelected() {
		return selected;
	}

	public void setSelected(Long selected) {
		this.selected = selected;
	}

	@Command("save")
	public void save(@ContextParam(ContextType.VIEW) Window comp) {
		comp.detach();
	}

}
