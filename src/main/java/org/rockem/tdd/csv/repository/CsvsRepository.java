package org.rockem.tdd.csv.repository;

public interface CsvsRepository {

    String save(String csv);

    String find(String id);
}
