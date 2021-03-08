package it.academy.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Date;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "T_EMP")
@Entity
@Builder
@JsonIgnoreProperties({ "department" })
public class Employee{

    @Id
    @Column(name = "E_ID")
    @GeneratedValue(generator = "uuid-generator")
    @GenericGenerator(name = "uuid-generator", strategy = "uuid")
    private String id;

    private EmployeeFullName fullName;

    @Column(name = "E_BIRTH_DATE")
    private Date dateOfBirth;

    @Column(name = "E_PHONE")
    private String phoneNumber;

    @Column(name="E_MAIL")
    private String email;

    @Column(name="E_POSITION")
    private String position;

    @Column(name="E_EMPLOYMENT_DATE")
    private Date dateOfEmployment;

    @ManyToOne
    @JoinColumn(name ="D_ID" )
    @ToString.Exclude
    private Department department;


}


