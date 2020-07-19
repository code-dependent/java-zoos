package local.jlwilliams.zoos.services;

import local.jlwilliams.zoos.models.Animal;
import local.jlwilliams.zoos.models.Telephone;
import local.jlwilliams.zoos.models.Zoo;
import local.jlwilliams.zoos.models.ZooAnimal;
import local.jlwilliams.zoos.repositories.AnimalRepository;
import local.jlwilliams.zoos.repositories.TelephoneRepository;
import local.jlwilliams.zoos.repositories.ZooRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Service(value = "zooService")
public class ZooServiceImpl implements ZooService
{
    @Autowired
    private ZooRepository zoorepos;
    @Autowired
    private AnimalService animalService;

    @Override
    public Zoo findZooById( long id)
    {

        return zoorepos.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("Zoo ID " + id + " Not Found"));
    }

    @Transactional
    @Override
    public Zoo save(Zoo zoo)
    {
        Zoo newzoo = new Zoo();
        if(zoo.getZooid() != 0){
            zoorepos.findById(zoo.getZooid())
                    .orElseThrow(()-> new EntityNotFoundException("Zoo ID " + zoo.getZooid() + " Not Found..."));

            newzoo.setZooid(zoo.getZooid());
        }

        newzoo.setZooname(zoo.getZooname());

        newzoo.getTelephones().clear();
        for(Telephone tp:zoo.getTelephones()){

            tp.setZoo(newzoo);
            newzoo.addTelephone(tp);
        }

        newzoo.getAnimals().clear();
        for(ZooAnimal za:zoo.getAnimals()){

            Animal an = animalService.findAnimalById(za.getAnimal().getAnimalid());
            newzoo.getAnimals().add(new ZooAnimal(newzoo,an));
        }

        return zoorepos.save(newzoo);
    }

    @Override
    public List<Zoo> listAllZoos()
    {
        List<Zoo> rtn = new ArrayList<>();
        zoorepos.findAll()
        .iterator()
        .forEachRemaining(rtn::add);
        return rtn;
    }

    @Override
    public Zoo update(Zoo zoo,
                      long id)
    {


        Zoo currentZoo = zoorepos.findById(id)
                    .orElseThrow(()-> new EntityNotFoundException("Zoo ID " + zoo.getZooid() + " Not Found..."));

        if(zoo.getZooname() != null)
        {
            currentZoo.setZooname(zoo.getZooname());
        }

        if(zoo.getTelephones().size() > 0)
        {
            currentZoo.getTelephones()
                    .clear();
            for (Telephone tp : zoo.getTelephones())
            {
                tp.setZoo(currentZoo);
                currentZoo.addTelephone(tp);
            }
        }

        if(zoo.getAnimals().size() > 0)
        {
            currentZoo.getAnimals()
                    .clear();
            for (ZooAnimal za : zoo.getAnimals())
            {
                ZooAnimal nza = new ZooAnimal(currentZoo,za.getAnimal());
                currentZoo.getAnimals().add(nza);
            }
        }

        return zoorepos.save(currentZoo);
    }
}
