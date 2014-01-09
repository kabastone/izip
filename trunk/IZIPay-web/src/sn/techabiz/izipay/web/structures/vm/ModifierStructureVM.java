package sn.techabiz.izipay.web.structures.vm;

import java.util.ArrayList;
import java.util.List;

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
import sn.techabiz.izipay.ejb.structures.services.StructureServices;
import sn.techabiz.izipay.ejb.structures.services.TypeStructureServices;
import sn.techabiz.izipay.services.JNDIOutils;
import sn.techabiz.izipay.services.RegistreEJB;

public class ModifierStructureVM {
	
	
	private List<PlageHoraire> horaires = new ArrayList<PlageHoraire>();
	private List<ValeurProprieteStructure> proprieteStructures = new ArrayList<ValeurProprieteStructure>();
	private ProprieteStructure pStructure = new ProprieteStructure();
	private List<StructureParametreComptable> listeParam = new ArrayList<StructureParametreComptable>();
	private ParametreComptable parametreComptable = new ParametreComptable();
	


	private StructureServices structureServices = (StructureServices) JNDIOutils
			.chercheEJB(RegistreEJB.StructureFacade);

	private TypeStructureServices typeStructureServices = (TypeStructureServices) JNDIOutils
			.chercheEJB(RegistreEJB.TypeStructureFacade);

	private List<TypeStructure> typeStructures = typeStructureServices
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

	public List<ValeurProprieteStructure> getProprieteStructures() {
		return proprieteStructures;
	}

	public void setProprieteStructures(
			List<ValeurProprieteStructure> proprieteStructures) {
		this.proprieteStructures = proprieteStructures;
	}

	public ProprieteStructure getpStructure() {
		return pStructure;
	}

	public void setpStructure(ProprieteStructure pStructure) {
		this.pStructure = pStructure;
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
	@NotifyChange("horaires")
	public void ajouterPlage() {
		horaires.add(new PlageHoraire());
	}

	@Command
	@NotifyChange({ "proprieteStructures", "pStructure" })
	public void ajouterPStr() {
		ValeurProprieteStructure valeurProprieteStructure = new ValeurProprieteStructure();

		valeurProprieteStructure.setProprieteStructure(pStructure);

		proprieteStructures.add(valeurProprieteStructure);
		pStructure = new ProprieteStructure();
	}
	
	@Command
	@NotifyChange({ "listeParam", "parametreComptable" })
	public void ajouterStructParam() {
		
		StructureParametreComptable spc = new StructureParametreComptable();
		spc.setParamComptable(parametreComptable);
		listeParam.add(spc);
		parametreComptable = new ParametreComptable();
		
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

	@NotifyChange("structure")
	@GlobalCommand
	public void updateSelected(@BindingParam("selected") Long id) {
		structure = structureServices.find(id);
		
	}
   

	
	public List<StructureParametreComptable> getListeParam() {
		return listeParam;
	}

	public void setListeParam(List<StructureParametreComptable> listeParam) {
		this.listeParam = listeParam;
	}

	public ParametreComptable getParametreComptable() {
		return parametreComptable;
	}

	public void setParametreComptable(ParametreComptable parametreComptable) {
		this.parametreComptable = parametreComptable;
	}

	
}
