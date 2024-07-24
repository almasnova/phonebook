package org.aplikasi.phonebook.service;

import org.aplikasi.phonebook.dto.RequestCreatePhonebookDTO;
import org.aplikasi.phonebook.entity.Phonebook;
import org.aplikasi.phonebook.exception.DataNotFoundException;
import org.aplikasi.phonebook.repository.PhonebookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class PhonebookServiceImpl implements PhonebookService {

    @Autowired
    private PhonebookRepository phonebookRepository;

    @Override
    public List<Phonebook> getListPhonebook(String search) {
        if (Objects.isNull(search)) search = "";
        return phonebookRepository.findAllSearch(search);
    }

    @Override
    public Phonebook getById(Long id) {
        Optional<Phonebook> byId = phonebookRepository.findById(id);
        if (!byId.isPresent()) throw new DataNotFoundException("Phonebook not found");
        return byId.get();
    }

    @Override
    public void createPhonebook(RequestCreatePhonebookDTO dto) {
        Phonebook book = new Phonebook();
        book.setId(0L);
        book.setSurname(dto.getSurname());
        book.setMiddlename(dto.getMiddlename());
        book.setPhone(dto.getPhone());
        book.setWorkPhone(dto.getWorkPhone());
        phonebookRepository.save(book);
    }

    @Override
    public void updatePhonebook(Long id, RequestCreatePhonebookDTO dto) {
        Phonebook book = getById(id);
        book.setSurname(dto.getSurname());
        book.setMiddlename(dto.getMiddlename());
        book.setPhone(dto.getPhone());
        book.setWorkPhone(dto.getWorkPhone());
        phonebookRepository.save(book);
    }

    @Override
    public void deletePhonebook(Long id) {
        Phonebook book = getById(id);
        phonebookRepository.delete(book);
    }

}
