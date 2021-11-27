package pl.pawit.NYT.Articles.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import pl.pawit.NYT.Articles.model.SectionRepository;
import pl.pawit.NYT.Articles.model.Section;

import java.net.URI;
import java.util.List;

@RestController
public class SectionController {

    private SectionRepository sectionRepository;

    public SectionController(SectionRepository sectionRepository) {
        this.sectionRepository = sectionRepository;
    }

    @PostMapping("/section")
    ResponseEntity<Section> createSection(@RequestBody Section section) {
        Section result = sectionRepository.save(section);
        return ResponseEntity.created(URI.create("/" + result.getId())).body(result);
    }

    @GetMapping("/section/{id}")
    ResponseEntity<Section> readById(@PathVariable int id) {
        return sectionRepository.findById(id)
                .map(section -> ResponseEntity.ok(section))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/section")
    ResponseEntity<List<Section>> readAll() {
        return ResponseEntity.ok(sectionRepository.findAll());
    }

    @Transactional
    @PatchMapping("/section/{id}")
     ResponseEntity<?> update(@PathVariable int id, @RequestBody Section toUpdate) {
        if (!sectionRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        sectionRepository.findById(id)
            .ifPresent(section1 -> section1.toUpdate(toUpdate));


        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/section/{id}")
    ResponseEntity<?> delete(@PathVariable int id){

        if (!sectionRepository.existsById(id)){
            return ResponseEntity.notFound().build();
        }
        sectionRepository.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body("Category has been removed");
    }
}
