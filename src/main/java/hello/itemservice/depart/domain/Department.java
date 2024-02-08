package hello.itemservice.depart.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Setter @Getter
public class Department {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    public String name;



}
