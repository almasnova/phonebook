package org.aplikasi.phonebook.controller;

import org.aplikasi.phonebook.dto.RequestCreatePhonebookDTO;
import org.aplikasi.phonebook.dto.ResponseMain;
import org.aplikasi.phonebook.entity.Phonebook;
import org.aplikasi.phonebook.exception.DataEmptyOrNullException;
import org.aplikasi.phonebook.exception.DataNotFoundException;
import org.aplikasi.phonebook.exception.DataNotValidException;
import org.aplikasi.phonebook.service.PhonebookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/api")
@Validated
public class PhonebookController {

    @Autowired
    private PhonebookService phonebookService;

    @GetMapping("/phonebook/{id}")
    public ResponseEntity<ResponseMain> findById(@PathVariable("id") Long id) {
        HttpStatus status;
        ResponseMain responseMain = new ResponseMain();
        try {
            Phonebook resp = phonebookService.getById(id);
            status = HttpStatus.OK;
            responseMain.setSuccess(resp);
            responseMain.setMessage("Success");
        } catch (DataNotFoundException de) {
            status = HttpStatus.BAD_REQUEST;
            responseMain.setFail(de.getMessage());
        } catch (Exception e) {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            responseMain.setFail(e.getMessage());
        }
        return ResponseEntity.status(status).body(responseMain);
    }

    @GetMapping("/phonebook/list")
    public ResponseEntity<ResponseMain> findList(@RequestParam(value = "search", required = false) String search) {
        HttpStatus status;
        ResponseMain responseMain = new ResponseMain();
        try {
            List<Phonebook> resp = phonebookService.getListPhonebook(search);
            status = HttpStatus.OK;
            responseMain.setSuccess(resp);
            responseMain.setMessage("Success");
        } catch (DataNotFoundException de) {
            status = HttpStatus.BAD_REQUEST;
            responseMain.setFail(de.getMessage());
        } catch (Exception e) {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            responseMain.setFail(e.getMessage());
        }
        return ResponseEntity.status(status).body(responseMain);
    }

    @PostMapping("/phonebook")
    public ResponseEntity<ResponseMain> create(@Valid @RequestBody RequestCreatePhonebookDTO dto) {
        ResponseMain response = new ResponseMain();
        HttpStatus status;
        try {
            phonebookService.createPhonebook(dto);
            status = HttpStatus.OK;
            response.setMessage("Success");
        } catch (DataEmptyOrNullException | DataNotValidException | DataNotFoundException de) {
            status = HttpStatus.BAD_REQUEST;
            response.setFail(de.getMessage());
        } catch (Exception e) {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            response.setFail(e.getMessage());
        }
        return ResponseEntity.status(status).body(response);
    }

    @PutMapping("/phonebook/{id}")
    public ResponseEntity<ResponseMain> update(@PathVariable("id") Long id,
                                               @Valid @RequestBody RequestCreatePhonebookDTO dto) {
        ResponseMain response = new ResponseMain();
        HttpStatus status;
        try {
            phonebookService.updatePhonebook(id, dto);
            status = HttpStatus.OK;
            response.setMessage("Success");
        } catch (DataEmptyOrNullException | DataNotValidException | DataNotFoundException de) {
            de.printStackTrace();
            status = HttpStatus.BAD_REQUEST;
            response.setFail(de.getMessage());
        } catch (Exception e) {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            response.setFail(e.getMessage());
        }
        return ResponseEntity.status(status).body(response);
    }

    @DeleteMapping(value = "/phonebook/{id}")
    public ResponseEntity<ResponseMain> delete(@PathVariable("id") Long id) {
        ResponseMain response = new ResponseMain();
        HttpStatus status;
        try {
            phonebookService.deletePhonebook(id);
            status = HttpStatus.OK;
            response.setMessage("Success");
        }catch (DataEmptyOrNullException | DataNotValidException | DataNotFoundException de) {
            status = HttpStatus.BAD_REQUEST;
            response.setFail(de.getMessage());
        } catch (Exception e) {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            response.setFail(e.getMessage());
        }
        return ResponseEntity.status(status).body(response);
    }

}
