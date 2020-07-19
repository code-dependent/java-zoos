package local.jlwilliams.zoos.services;

import local.jlwilliams.zoos.models.Animal;
import local.jlwilliams.zoos.models.ZooAnimal;
import local.jlwilliams.zoos.repositories.AnimalRepository;
import local.jlwilliams.zoos.views.AnimalCount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service(value = "animalService")
public class AnimalServiceImpl implements AnimalService
{

    @Autowired
    private AnimalRepository animalrepos;

    @Override
    public Animal findAnimalById(long id)
    {

       return animalrepos.findById(id)
               .orElseThrow(()-> new
                       EntityNotFoundException("Animal ID "+id+" Not Found..."));
    }

    @Override
    public Animal save(Animal animal)
    {
        Animal newAnimal = new Animal();
        if (animal.getAnimalid() != 0)
        {
            animalrepos.findById(animal.getAnimalid())
                    .orElseThrow(() -> new EntityNotFoundException("Animal ID " + animal.getAnimalid() + " Not Found..."));
            newAnimal.setAnimalid(animal.getAnimalid());
        }
        newAnimal.setAnimaltype(animal.getAnimaltype());
        newAnimal.setIncomingzoo(animal.getIncomingzoo());

        newAnimal.getZoos().clear();
        for(ZooAnimal za: animal.getZoos()){
            ZooAnimal nza = new ZooAnimal(za.getZoo(),za.getAnimal());

            newAnimal.getZoos().add(nza);
        }

        return animalrepos.save(newAnimal);
    }

    @Override
    public List<AnimalCount> listAnimalCount()
    {
        return animalrepos.findAnimalCount();
    }
}
