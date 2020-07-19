package local.jlwilliams.zoos.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "zoos")
public class Zoo extends Auditable
{
//    zooid - long primary key
//    zooname - String Name of the Zoo
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long zooid;
    private String zooname;

    @OneToMany(mappedBy = "zoo", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties(value="zoo", allowSetters = true)
    private Set<Telephone> telephones = new HashSet<>();


    @OneToMany(mappedBy = "zoo",cascade = CascadeType.ALL)
    @JsonIgnoreProperties(value="zoo", allowSetters = true)
    private Set<ZooAnimal> animals = new HashSet<>();


    public Zoo()
    {
    }

    public Zoo(String zooname)
    {
        this.zooname = zooname;
    }

    public long getZooid()
    {
        return zooid;
    }

    public void setZooid(long zooid)
    {
        this.zooid = zooid;
    }

    public String getZooname()
    {
        return zooname;
    }

    public void setZooname(String zooname)
    {
        this.zooname = zooname;
    }

    public Set<Telephone> getTelephones()
    {
        return telephones;
    }

    public void setTelephones(Set<Telephone> telephones)
    {
        this.telephones = telephones;
    }

    public Set<ZooAnimal> getAnimals()
    {
        return animals;
    }

    public void setAnimals(Set<ZooAnimal> animals)
    {
        this.animals = animals;
    }

    public void addZooAnimal(ZooAnimal za){
        za.setZoo(this);
        this.animals.add(za);
    }

    public void addTelephone(Telephone tp){
        tp.setZoo(this);
        this.telephones.add(tp);
    }


}
