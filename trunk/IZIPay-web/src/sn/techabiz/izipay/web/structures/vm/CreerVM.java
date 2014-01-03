package sn.techabiz.izipay.web.structures.vm;

import java.util.List;

import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Session;
import org.zkoss.zul.Button;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.TreeModel;
import org.zkoss.zul.Window;

import sn.techabiz.izipay.ejb.structures.entities.Structure;
import sn.techabiz.izipay.ejb.structures.entities.TypeStructure;
import sn.techabiz.izipay.ejb.structures.services.StructureServices;
import sn.techabiz.izipay.ejb.structures.services.TypeStructureServices;
import sn.techabiz.izipay.services.JNDIOutils;
import sn.techabiz.izipay.services.RegistreEJB;
import sn.techabiz.izipay.web.commons.VMOutils;

public class CreerVM {

	private TypeStructureServices typeStructureServices = (TypeStructureServices) JNDIOutils
			.chercheEJB(RegistreEJB.TypeStructureFacade);

	private StructureServices structureServices = (StructureServices) JNDIOutils
			.chercheEJB(RegistreEJB.StructureFacade);

	private List<Structure> structures = structureServices.findAll();

	private List<TypeStructure> typeStructures = typeStructureServices
			.findAll();

	private Structure structure = new Structure(), strchoisie;

	private TreeModel<Structure> treemodel;

	Boolean auto = false;

	public List<Structure> getStructures() {
		return structures;
	}

	public void setStructures(List<Structure> structures) {
		this.structures = structures;
	}

	public Structure getStrchoisie() {
		return strchoisie;
	}

	@NotifyChange("treemodel")
	public void setStrchoisie(Structure strchoisie) {

		this.strchoisie = strchoisie;
	}

	public List<TypeStructure> getTypeStructures() {
		return typeStructures;
	}

	public void setTypeStructures(List<TypeStructure> typeStructures) {
		this.typeStructures = typeStructures;
	}

	public Structure getStructure() {
		return structure;
	}

	public void setStructure(Structure structure) {
		this.structure = structure;
	}

	public TreeModel<Structure> getTreemodel() {
		return treemodel;
	}

	public void setTreemodel(TreeModel<Structure> treemodel) {
		this.treemodel = treemodel;
	}

	@Command("save")
	public void doCreate() {

		structure.setInternal(auto);
		Structure parent = new Structure();
		Session session = Executions.getCurrent().getSession();
		parent = (Structure) session.getAttribute("parent");
		structure.setParent(parent);

		if (VMOutils.valider(structure)) {
			structureServices.create(structure);
			String msg = "La structure " + structure.getLibelle()
					+ " fut ajoutée avec succès !";
			VMOutils.rafraichir(msg);
		}

	}

	@Command("edit")
	public void doEdit() {
		if (auto)
			auto = false;
		else
			auto = true;
	}

	@Command("choisirParent")
	public void choisirParent(@BindingParam("bouton") Button b) {
		Window w = (Window) Executions.createComponents(
				"/pages/structures/structure_picker.zul",
				b.getFellow("divCreerStructure"), null);
		w.doModal();
	}
}
