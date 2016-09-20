package org.rockem.tdd.csv.control;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/csvs")
public class CsvsController {

    private Map<String, String> csvs = new HashMap<>();

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> newCSV(@RequestBody String csv) {
        String csvId = generateRandomHash();
        csvs.put(csvId, csv);
        return ResponseEntity.created(csvURIFor(csvId)).build();
    }

    private String generateRandomHash() {
        return UUID.randomUUID().toString();
    }

    private URI csvURIFor(String csvId) {
        return ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(csvId).toUri();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String getCSV(@PathVariable String id) {
        return csvs.get(id);
    }

}
