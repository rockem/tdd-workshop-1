package org.rockem.tdd.csv.control;

import org.rockem.tdd.csv.CsvFind;
import org.rockem.tdd.csv.common.FindResult;
import org.rockem.tdd.csv.repository.CsvsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/csvs")
public class CsvsController {

    private final CsvsRepository csvsRepository;

    @Autowired
    public CsvsController(CsvsRepository csvsRepository) {
        this.csvsRepository = csvsRepository;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> newCSV(@RequestBody String csv) {
        String csvId = csvsRepository.save(csv);
        return ResponseEntity.created(csvURIFor(csvId)).build();
    }

    private URI csvURIFor(String csvId) {
        return ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(csvId).toUri();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String getCSV(@PathVariable String id) {
        return csvsRepository.find(id);
    }

    @RequestMapping(value = "/{id}/find", method = RequestMethod.GET)
    public List<FindResult> findInCSV(@PathVariable String id, @RequestParam String text) {
        return new CsvFind(csvsRepository.find(id)).find(text);
    }

}
