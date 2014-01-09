package sn.techabiz.izipay.web.structures.vm;

import java.util.ArrayList;
import java.util.List;

import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.DependsOn;
import org.zkoss.bind.annotation.GlobalCommand;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zul.Window;

import sn.techabiz.izipay.ejb.structures.entities.ParametreComptable;
import sn.techabiz.izipay.ejb.structures.entities.PlageHoraire;
import sn.techabiz.izipay.ejb.structures.entities.ProprieteStructure;
import sn.techabiz.izipay.ejb.structures.entities.Structure;
import sn.techabiz.izipay.ejb.structures.entities.StructureParametreComptable;
import sn.techabiz.izipay.ejb.structures.entities.TypeStructure;
import sn.techabiz.izipay.ejb.structures.entities.ValeurProprieteStructure;
import sn.techabiz.izipay.ejb.structures.services.ParametreComptableServices;
import sn.techabiz.izipay.ejb.structures.services.PlageHoraireServices;
import sn.techabiz.izipay.ejb.structures.services.ProprieteStructureServices;
import sn.techabiz.izipay.ejb.structures.services.StructParamComptableServices;
import sn.techabiz.izipay.ejb.structures.services.StructureServices;
import sn.techabiz.izipay.ejb.structures.services.TypeStructureServices;
import sn.techabiz.izipay.ejb.structures.services.ValeurProprieteStructureServices;
import sn.techabiz.izipay.services.JNDIOutils;
import sn.techabiz.izipay.services.RegistreEJB;

public class ModifierStructureVM {

	private StructureServices structureServices = (StructureServices) JNDIOutils
			.chercheEJB(RegistreEJB.StructureFacade);

	private TypeStructureServices typeStructureServices = (TypeStructureServices) JNDIOutils
			.chercheEJB(RegistreEJB.TypeStructureFacade);

	private ValeurProprieteStructureServices valeurServices = (ValeurProprieteStructureServices) JNDIOutils
			.chercheEJB(RegistreEJB.ValeurProprieteStructureFacade);

	private ProprieteStructureServices proServices = (ProprieteStructureServices) JNDIOutils
			.chercheEJB(RegistreEJB.ProprieteStructureFacade);

	private ParametreComptableServices comptableServices = (ParametreComptableServices) JNDIOutils
			.chercheEJB(RegistreEJB.ParametreComptableFacade);

	private StructParamComptableServices paramServices = (StructParamComptableServices) JNDIOutils
			.chercheEJB(RegistreEJB.StructParamComptableFacade);

	private PlageHoraireServices plageHoraireServices = (PlageHoraireServices) JNDIOutils
			.chercheEJB(RegistreEJB.PlageHoraireFacade);

	private List<TypeStructure> typeStructures = typeStructureServices
			.findAll();

	private List<PlageHoraire> horaires = new ArrayList<PlageHoraire>();
	private List<ValeurProprieteStructure> valeurProprieteStructures = new ArrayList<ValeurProprieteStructure>();
	private List<ProprieteStructure> proprieteStructures = proServices
			.findAll();
	private List<StructureParametreComptable> listeParam = new ArrayList<StructureParametreComptable>();
	private List<ParametreComptable> parametreComptables = comptableServices
			.findAll();

	private Long selected;

	private Structure structure;
	private boolean auto;

	public List<PlageHoraire> getHoraires() {
		return horaires;
	}

	public void setHoraires(List<PlageHoraire> horaires) {
		this.horaires = horaires;
	}

	public List<TypeStructure> getTypeStructures() {
		return typeStructures;
	}

	public List<ValeurProprieteStructure> getValeurProprieteStructures() {
		return valeurProprieteStructures;
	}

	public List<ProprieteStructure> getProprieteStructures() {
		return proprieteStructures;
	}

	public List<StructureParametreComptable> getListeParam() {
		return listeParam;
	}

	public List<ParametreComptable> getParametreComptables() {
		return parametreComptables;
	}

	public Long getSelected() {
		return selected;
	}

	public void setTypeStructures(List<TypeStructure> typeStructures) {
		this.typeStructures = typeStructures;
	}

	public void setValeurProprieteStructures(
			List<ValeurProprieteStructure> valeurProprieteStructures) {
		this.valeurProprieteStructures = valeurProprieteStructures;
	}

	public void setProprieteStructures(
			List<ProprieteStructure> proprieteStructures) {
		this.proprieteStructures = proprieteStructures;
	}

	public void setListeParam(List<StructureParametreComptable> listeParam) {
		this.listeParam = listeParam;
	}

	public void setParametreComptables(
			List<ParametreComptable> parametreComptables) {
		this.parametreComptables = parametreComptables;
	}

	public void setSelected(Long selected) {
		this.selected = selected;
	}

	public void setStructure(Structure structure) {
		this.structure = structure;
	}

	@DependsOn("selected")
	@NotifyChange({ "horaires", "valeurProprieteStructures", "listeParam" })
	public Structure getStructure() {
		if (selected != null) {
			structure = structureServices.find(selected);

			horaires = plageHoraireServices.getPlaceHoraire(structure);
			valeurProprieteStructures = valeurServices
					.getValeurProprieteStructures(structure);
			listeParam = paramServices.findByStructure(structure);
		}
		return structure;
	}

	@Command
	@NotifyChange("horaires")
	public void ajouterPlage() {
		horaires.add(new PlageHoraire());
	}

	@Command
	@NotifyChange("valeurProprieteStructures")
	public void ajouterPStr() {
		valeurProprieteStructures.add(new ValeurProprieteStructure());
	}

	@Command
	@NotifyChange({ "listeParam", "parametreComptable" })
	public void ajouterStructParam() {
		listeParam.add(new StructureParametreComptable());
	}

	@Command
	public void edit() {
		if (auto)
			auto = false;
		else
			auto = true;
	}

	@Command
	public void open() {
		Window w = (Window) Executions.createComponents(
				"/pages/structures/structure_picker.zul", null, null);
		w.doModal();
	}

	@NotifyChange("structure")
	@GlobalCommand
	public void dlgClose(@BindingParam("parentID") Long parent) {
		structure.setParent(structureServices.find(parent));
	}

	@NotifyChange({ "structure", "horaires", "valeurProprieteStructures",
			"listeParam" })
	@GlobalCommand
	public void updateSelected(@BindingParam("selected") Long id) {
		structure = structureServices.find(id);

		horaires = plageHoraireServices.getPlaceHoraire(structure);
		valeurProprieteStructures = valeurServices
				.getValeurProprieteStructures(structure);
		listeParam = paramServices.findByStructure(structure);
	}

	@Command
	public void editStructure() {
		if (horaires.size() > 0) {
			for (PlageHoraire ph : horaires) {
				if (ph.getId() == null) {
					ph.setAgence(structure);
					plageHoraireServices.create(ph);
				} else {
					plageHoraireServices.edit(ph);
				}
			}
		}

		if (listeParam.size() > 0) {
			for (StructureParametreComptable spc : listeParam) {
				if (spc.getId() == null) {
					spc.setStructure(structure);
					paramServices.create(spc);
				} else {
					paramServices.edit(spc);
				}
			}
		}
	}
}
