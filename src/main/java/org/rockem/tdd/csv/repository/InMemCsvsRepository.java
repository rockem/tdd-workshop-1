package org.rockem.tdd.csv.repository;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class InMemCsvsRepository implements CsvsRepository {

    private Map<String, String> csvs = new HashMap<>();

    @Override
    public String save(String csv) {
        String csvId = generateRandomHash();
        csvs.put(csvId, csv);
        return csvId;
    }

    private String generateRandomHash() {
        return UUID.randomUUID().toString();
    }

    @Override
    public String find(String id) {
        return csvs.get(id);
    }
}
