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

		structure.setInternal(auto);
		Structure parent = new Structure();
		if (parentID != null) {
			if (!structure.getType().getCode().equalsIgnoreCase("RESEAU")) {
				parent = structureServices.find(parentID);
				if (!parent
						.getType()
						.getCode()
						.equalsIgnoreCase(
								structure.getType().getParent().getCode())) {
					if (structure.getType().getCode()
							.equalsIgnoreCase("AGENCE")
							&& parent.getType().getCode()
									.equalsIgnoreCase("OPERATEUR")) {

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
							String msg = structure.getLibelle() + " cr��e";
							VMOutils.rafraichir(msg);
						}
					} else
						Messagebox.show(" parent structure incorrect");

				} else {
					structure.setParent(parent);
					structure.setVirtual(false);
					if (VMOutils.valider(structure)) {
						structureServices.create(structure);
						String msg = structure.getLibelle() + " cr��e";
						VMOutils.rafraichir(msg);
					}

				}
			}
			else{
				Messagebox.show(" parent structure incorrect");
			}

		} else {
			if (structure.getType().getCode().equalsIgnoreCase("RESEAU")) {
				structure.setVirtual(false);
				if (VMOutils.valider(structure)) {
					structureServices.create(structure);
					String msg = structure.getLibelle() + " cr��e";
					VMOutils.rafraichir(msg);
				}

			} else {
				Messagebox.show("Veuillez choisir un parent");
			}
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
	@NotifyChange({ "parentID", "structure" })
	public void dlgClose(@BindingParam("parentID") Long parentID) {

		this.parentID = parentID;

	}
}
