package it.academy.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;



@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "T_EMP")
@Entity
@Builder
public class Employee{

    @Id
    @Column(name = "E_ID")
    @GeneratedValue(generator = "uuid-generator")
    @GenericGenerator(name = "uuid-generator", strategy = "uuid")
    String id;

    EmployeeFullName fullName;

    @Column(name = "E_BIRTH_DATE")
    String dateOfBirth;

    @Column(name = "E_PHONE")
    String phoneNumber;

    @Column(name="E_MAIL")
    String email;

    @Column(name="E_POSITION")
    String position;

    @Column(name="E_EMPLOYMENT_DATE")
    String dateOfEmployment;

    @ManyToOne
    @JoinColumn(name ="D_ID" )
    Department department;

    @Override
    public String toString() {
        return "Employee{" +
                "id='" + id + '\'' +
                ", fullName=" + fullName +
                ", dateOfBirth='" + dateOfBirth + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", position='" + position + '\'' +
                ", dateOfEmployment='" + dateOfEmployment + '\'' +
                '}';
    }
}


