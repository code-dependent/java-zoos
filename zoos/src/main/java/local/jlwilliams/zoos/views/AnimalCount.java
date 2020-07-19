package local.jlwilliams.zoos.views;

import org.springframework.data.jpa.repository.Query;

public interface AnimalCount
{
//animaltype as anty, a.animalid as id, COUNT(zooid) as countzoos

            String getAnimal();
            long getAnimalid();
            int getCountzoos();
}
