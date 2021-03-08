package it.academy.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "T_DEP")
@Builder
public class Department {

    @Id
    @Column(name = "D_ID")
    @GeneratedValue(generator = "uuid-generator")
    @GenericGenerator(name = "uuid-generator", strategy = "uuid")
    private String id;

    @Column(name = "D_NAME")
    private String name;

    @Column(name = "D_PHONE")
    private String phoneNumber;

    @Column(name = "D_FORMATION_DATE")
    private Date dateOfFormation;
    @Column(name = "D_DESC")
    private String description;


    @OneToMany (mappedBy = "department", cascade = CascadeType.PERSIST)

    private List<Employee> employeeList;


}
