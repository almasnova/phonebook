package org.aplikasi.phonebook.service;

import org.aplikasi.phonebook.dto.RequestCreatePhonebookDTO;
import org.aplikasi.phonebook.entity.Phonebook;

import java.util.List;

public interface PhonebookService {
    
    Phonebook getById(Long id);

    List<Phonebook> getListPhonebook(String search);

    void createPhonebook(RequestCreatePhonebookDTO dto);

    void updatePhonebook(Long id, RequestCreatePhonebookDTO dto);

    void deletePhonebook(Long id);
}
