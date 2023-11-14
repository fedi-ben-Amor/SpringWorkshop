package tn.esprit.spring.Services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.spring.DAO.Entities.Bloc;
import tn.esprit.spring.DAO.Entities.Chamber;
import tn.esprit.spring.DAO.Entities.TypeChamber;
import tn.esprit.spring.DAO.Repositories.BlocRepository;
import tn.esprit.spring.DAO.Repositories.ChamberRepository;

import java.util.List;

@AllArgsConstructor
@Service
public class ChamberService implements IChamberService{
    ChamberRepository chamberRepository;

    @Override
    public Chamber findByNumerochamberAndTypeC(long numero, TypeChamber type) {
        return chamberRepository.findByNumerochamberAndTypeC(numero , type);
    }

    @Override
    public Chamber addChamber(Chamber c) {
        return chamberRepository.save(c) ;
    }

    @Override
    public List<Chamber> addAllChambers(List<Chamber> ls) {
        return chamberRepository.saveAll(ls);
    }

    @Override
    public Chamber editChamber(Chamber c) {
        return chamberRepository.save(c);
    }

    @Override
    public List<Chamber> findAll() {
        return chamberRepository.findAll();
    }

    @Override
    public Chamber findById(long id) {
        return chamberRepository.findById(id).orElse(Chamber.builder().idChamber(0).numerochamber(0).build());
    }

    @Override
    public void deleteByID(long id) {
        chamberRepository.deleteById(id);

    }

    @Override
    public void delete(Chamber c) {
        chamberRepository.delete(c);

    }
    BlocRepository blocRepository;
    @Override
    public List<Chamber> getChambresParNomBloc(String nomBloc) {
        Bloc b = blocRepository.getBlocByNomBloc(nomBloc);
        return chamberRepository.findByBloc(b) ;
    }

    @Override
    public List<Chamber> getChambresNonReserveParNomFoyerEtTypeChambre(String nomFoyer, TypeChamber type) {
        return chamberRepository.findChamberByBlocFoyerNomFoyerAndTypeCAndReservations_Empty(nomFoyer,type);
    }

    @Override
    public long nbChambreParTypeEtBloc(TypeChamber type, long idBloc) {
        Bloc b = blocRepository.findById(idBloc).get();
        int c = chamberRepository.countChamberByTypeCAndBloc_IdBloc(type , idBloc);
        return c;
    }
}
