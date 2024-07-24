package org.aplikasi.phonebook.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "data")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Phonebook {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "surname")
    private String surname;

    @Column(name = "middlename")
    private String middlename;

    @Column(name = "phone")
    private String phone;

    @Column(name = "work_phone")
    private String workPhone;

}
