package tn.esprit.spring.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.spring.entities.Piste;
import tn.esprit.spring.repositories.IPisteRepository;

import java.util.List;
@AllArgsConstructor
@Service
public class PisteServicesImpl implements  IPisteServices{

    private IPisteRepository pisteRepository;

    @Override
    public List<Piste> retrieveAllPistes() {
        return pisteRepository.findAll();
    }

    @Override
    public Piste addPiste(Piste piste) {
        return pisteRepository.save(piste);
    }
    @Override
    public Piste updatePiste(Long numPiste, Piste piste) {
       Piste pisteselect =   pisteRepository.findById(numPiste).orElse(null);
        pisteselect.setNamePiste(piste.getNamePiste());
        pisteselect.setLength(piste.getLength());
        pisteselect.setColor(piste.getColor());
        pisteselect.setSlope(piste.getSlope());
       return  pisteRepository.save(pisteselect); }

    @Override
    public void removePiste(Long numPiste) { pisteRepository.deleteById(numPiste); }

    @Override
    public Piste retrievePiste(Long numPiste) {
        return pisteRepository.findById(numPiste).orElse(null);
    }
}
