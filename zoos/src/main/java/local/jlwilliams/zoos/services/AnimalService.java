package local.jlwilliams.zoos.services;

import local.jlwilliams.zoos.models.Animal;
import local.jlwilliams.zoos.views.AnimalCount;

import java.util.List;

public interface AnimalService
{
    Animal save(Animal animal);

    List<AnimalCount> listAnimalCount();

    Animal findAnimalById(long id);
}
