package sn.techabiz.izipay.web.structures.vm;

import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.DependsOn;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Tree;

import sn.techabiz.izipay.ejb.structures.services.StructureServices;
import sn.techabiz.izipay.services.JNDIOutils;
import sn.techabiz.izipay.services.RegistreEJB;
import sn.techabiz.izipay.web.commons.StructureOutils;

public class ListerVM {
	@Wire("tree")
	Tree tree;

	private StructureServices structureServices = (StructureServices) JNDIOutils
			.chercheEJB(RegistreEJB.StructureFacade);

	private Long currentScope = 1L, selected;

	private StructureOutils so = new StructureOutils();
	private String url = "/pages/structures/str/guichet.zul";

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

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Command
	@DependsOn("selected")
	@NotifyChange("url")
	public void changeUrl() {
		if (selected != null) {
			url = "/pages/structures/str/"
					+ structureServices.find(selected).getType().getCode()
							.toLowerCase() + ".zul";
		}
	}
}
