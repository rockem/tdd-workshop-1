package org.rockem.tdd.csv.control;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/csvs")
public class CsvsController {

    private String csv;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> newCSV(@RequestBody String csv) {
        this.csv = csv;
        return ResponseEntity.created(csvURI()).build();
    }

    private URI csvURI() {
        return ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();
    }

    @RequestMapping(method = RequestMethod.GET)
    public String getCSV() {
        return csv;
    }

}
