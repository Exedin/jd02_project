package it.academy.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "T_DEP")
public class Department {

    @Id
    @Column(name = "D_ID")
    @GeneratedValue(generator = "uuid-generator")
    @GenericGenerator(name = "uuid-generator", strategy = "uuid")
    String id;

    @Column(name = "D_NAME")
    String name;

    @Column(name = "D_PHONE")
    String phoneNumber;

    @Column(name = "D_FORMATION_DATE")
    String dateOfFormation;
    @Column(name = "D_DESC")
    String description;


    @OneToMany (mappedBy = "department", fetch = FetchType.EAGER)
    Set<Employee> employeeList;

}
