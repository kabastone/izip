package sn.techabiz.izipay.web.structures.vm;

import java.util.List;

import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.DependsOn;
import org.zkoss.bind.annotation.GlobalCommand;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Tree;
import org.zkoss.zul.Window;

import sn.techabiz.izipay.ejb.structures.entities.Structure;
import sn.techabiz.izipay.ejb.structures.entities.TypeStructure;
import sn.techabiz.izipay.ejb.structures.services.StructureServices;
import sn.techabiz.izipay.ejb.structures.services.TypeStructureServices;
import sn.techabiz.izipay.services.JNDIOutils;
import sn.techabiz.izipay.services.RegistreEJB;
import sn.techabiz.izipay.web.commons.StructureOutils;

public class ListerVM {
	@Wire("tree")
	Tree tree;

	private StructureServices structureServices = (StructureServices) JNDIOutils
			.chercheEJB(RegistreEJB.StructureFacade);

	private TypeStructureServices typeStructureServices = (TypeStructureServices) JNDIOutils
			.chercheEJB(RegistreEJB.TypeStructureFacade);

	private Long currentScope = 1L, selected;

	private StructureOutils so = new StructureOutils();

	private Structure structure = new Structure();

	private List<TypeStructure> typeStructures = typeStructureServices
			.findAll();

	@AfterCompose
	public void init(@ContextParam(ContextType.VIEW) Component view) {
		Selectors.wireComponents(view, this, false);
		so.initTree(tree, currentScope);
	}

	@DependsOn("selected")
	public Structure getStructure() {
		if (selected != null)
			structure = structureServices.find(selected);
		return structure;
	}

	public void setStructure(Structure structure) {
		this.structure = structure;
	}

	public List<TypeStructure> getTypeStructures() {
		return typeStructures;
	}

	public void setTypeStructures(List<TypeStructure> typeStructures) {
		this.typeStructures = typeStructures;
	}

	public Long getSelected() {
		return selected;
	}

	public void setSelected(Long selected) {
		this.selected = selected;
	}

	@Command
	public void open() {

		Window w = (Window) Executions.createComponents(
				"/pages/structures/structure_picker.zul", null, null);
		w.doModal();
	}

	@GlobalCommand("dlgClose")
	@NotifyChange("structure")
	public void dlgClose(@BindingParam("parentID") Long parent) {
		
		structure.setParent(structureServices.find(parent));
		Messagebox.show(structure.getParent().getLibelle());
	}

}
