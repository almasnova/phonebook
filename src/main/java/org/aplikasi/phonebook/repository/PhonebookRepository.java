
package org.aplikasi.phonebook.repository;

import org.aplikasi.phonebook.entity.Phonebook;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PhonebookRepository extends JpaRepository<Phonebook, Long> {

    @Query("SELECT p FROM Phonebook p " +
            "WHERE (LOWER(CONCAT(p.surname)) like LOWER(CONCAT('%', :search,'%')) " +
            "OR LOWER(CONCAT(p.middlename)) like LOWER(CONCAT('%', :search,'%')))")
    List<Phonebook> findAllSearch(@Param("search") String search);

    @Query(value = "SELECT p FROM Phonebook p WHERE id = :id")
    Optional<Phonebook> findById(@Param("id") long id);

}
