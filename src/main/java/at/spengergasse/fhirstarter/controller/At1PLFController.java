package at.spengergasse.fhirstarter.controller;

import at.spengergasse.fhirstarter.entity.Patient;
import at.spengergasse.fhirstarter.model.At1PLF;
import at.spengergasse.fhirstarter.repository.At1PLFRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class At1PLFController {

    @Autowired
    At1PLFRepository at1PLFRepository;

    @GetMapping("/getall")
    public Iterable<At1PLF> getAllAt1PLFs(){

        return at1PLFRepository.findAll();
    }

    @PostMapping("/save")
    public void save(@RequestBody At1PLF at1PLF){
        at1PLFRepository.save(at1PLF);
    }

    @DeleteMapping("/delete")
    public void delete(@RequestBody At1PLF at1PLF){
        at1PLFRepository.delete(at1PLF);
    }

}
