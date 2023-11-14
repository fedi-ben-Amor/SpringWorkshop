package tn.esprit.spring.Services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.spring.DAO.Entities.Chamber;
import tn.esprit.spring.DAO.Entities.Etudiant;
import tn.esprit.spring.DAO.Entities.Reservation;
import tn.esprit.spring.DAO.Repositories.ChamberRepository;
import tn.esprit.spring.DAO.Repositories.EtudiantRepository;
import tn.esprit.spring.DAO.Repositories.ReservationRepository;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@Service
public class ReservationService implements IReservationService {
    ReservationRepository reservationRepository ;

    @Override
    public Reservation addReservation(Reservation r) {
        return reservationRepository.save(r);
    }

    @Override
    public List<Reservation> addAllReservation(List<Reservation> ls) {
        return reservationRepository.saveAll(ls);
    }

    @Override
    public Reservation editReservation(Reservation r) {
        return reservationRepository.save(r);
    }

    @Override
    public List<Reservation> findAllReservations() {
        return reservationRepository.findAll();
    }

    @Override
    public Reservation findByIdReservation(String id) {
        return reservationRepository.findById(id).orElse(Reservation.builder().build());
    }



    ChamberRepository chamberRepository ;
    EtudiantRepository etudiantRepository ;
    @Override
    public Reservation ajouterReservationEtAssignerAChambreEtAEtudiant(int numChambre, long cin) {
        Chamber c = chamberRepository.findByNumerochamber(numChambre);
        Etudiant e = etudiantRepository.findByCin(cin);
        Set<Etudiant> etud = new HashSet<>( ) ;
        etud.add(e);
        Reservation r = new Reservation();
        r.setIdReservation(e.getNomEt()+"RES");
        r.setAnneeReservation(new Date());
        r.setEstValide(false);
        r.setEtudiants(etud);
        Set<Reservation> reservationsList = new HashSet<>();
        reservationsList.add(r);
        c.setReservations(reservationsList);
        e.setResservations(reservationsList);
        reservationRepository.save(r);
        etudiantRepository.save(e);
        chamberRepository.save(c);
        return r;
    }

    @Override
    public Reservation annulerReservation(long cinEtudiant) {
        Etudiant e = etudiantRepository.findByCin(cinEtudiant);

        return null;
    }

    @Override
    public void deleteById(String id) {
        reservationRepository.deleteById(id);
    }

    @Override
    public void deleteReservation(Reservation r) {
        reservationRepository.delete(r);

    }
}
