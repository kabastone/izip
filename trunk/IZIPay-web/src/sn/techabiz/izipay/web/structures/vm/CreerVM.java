package sn.techabiz.izipay.web.structures.vm;

import java.util.List;

import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.GlobalCommand;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zul.Messagebox;
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

	private Long parentID;
	private Long currentscope = 1l;
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

	@Command("save")
	public void doCreate() {
		new Structure();
		structureServices.find(currentscope);

		structure.setInternal(auto);
		Structure parent = new Structure();
		if (parentID != null) {
			parent = structureServices.find(parentID);
			if (parent.getType().getRang() >= structure.getType().getRang()) {
				Messagebox.show(" parent structure incorrect");

			} else if (parent.getType().getRang() == structure.getType()
					.getRang() - 1) {
				// structure.setParent(parent);
				structure.setVirtual(false);
				if (VMOutils.valider(structure)) {
					structureServices.create(structure);
					String msg = structure.getLibelle() + " créée";
					VMOutils.rafraichir(msg);
				}

			} else if (structure.getType().getCode().equalsIgnoreCase("AGENCE")
					&& parent.getType().getCode().equalsIgnoreCase("OPERATEUR")) {

				Structure distributeur = new Structure();
				distributeur.setLibelle("Dist virt");
				distributeur.setVirtual(true);
				distributeur.setType(structure.getType().getParent());
				distributeur.setInternal(false);
				distributeur.setParent(parent);
				

				structure.setVirtual(false);
				
				if (VMOutils.valider(structure)
						&& VMOutils.valider(distributeur)) {					
					structure.setParent(distributeur);
					structureServices.edit(structure);
					String msg = structure.getLibelle() + " créée";
					VMOutils.rafraichir(msg);
				}
			} else {
				Messagebox.show("parent structure incorrect");
			}

		} else {
			Messagebox.show("Veuillez choisir un parent");
		}

	}

	@Command("edit")
	public void doEdit() {
		if (auto)
			auto = false;
		else
			auto = true;
	}

	@Command
	public void open() {

		Window w = (Window) Executions.createComponents(
				"/pages/structures/structure_picker.zul", null, null);
		w.doHighlighted();
	}

	@GlobalCommand("dlgClose")
	@NotifyChange("parentID")
	public void dlgClose(@BindingParam("parentID") Long parent) {
		parentID = parent;

		structure.setParent(structureServices.find(parent));

	}
}
