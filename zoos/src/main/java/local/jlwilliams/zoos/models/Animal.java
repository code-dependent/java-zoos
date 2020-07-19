package local.jlwilliams.zoos.models;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "animals")
public class Animal extends Auditable
{

//    animalid - long primary key
//    animaltype - String - the type of animal such as lion or llama
//    incomingzoo - String - the name of the zoo when the animal came from. The field can be left blank or null if the animal does not come from another zoo.

    private String animaltype;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long animalid;
    private String incomingzoo;

    @OneToMany(mappedBy = "animal",cascade = CascadeType.ALL)
    @JsonIgnoreProperties(value="animal", allowSetters = true)
    private Set<ZooAnimal> zoos = new HashSet<>();

    public Animal(String animaltype,
                  String incomingzoo)
    {
        this.animaltype = animaltype;
        this.incomingzoo = incomingzoo;
    }



    public Animal()
    {
    }

    public long getAnimalid()
    {
        return animalid;
    }

    public void setAnimalid(long animalid)
    {
        this.animalid = animalid;
    }

    public String getAnimaltype()
    {
        return animaltype;
    }

    public void setAnimaltype(String animaltype)
    {
        this.animaltype = animaltype;
    }

    public String getIncomingzoo()
    {
        return incomingzoo;
    }

    public void setIncomingzoo(String incomingzoo)
    {
        this.incomingzoo = incomingzoo;
    }

    public Set<ZooAnimal> getZoos()
    {
        return zoos;
    }

    public void setZoos(Set<ZooAnimal> zoos)
    {
        this.zoos = zoos;
    }


}
