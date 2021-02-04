package it.academy.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class EmployeeFullName {

    @Column(name = "E_NAME")
    String name;
    @Column(name = "E_SURNAME")
    String Surname;
    @Column(name = "E_MIDDLE_NAME")
    String middleName;

}
