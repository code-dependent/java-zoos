package local.jlwilliams.zoos.repositories;

import local.jlwilliams.zoos.models.Animal;
import local.jlwilliams.zoos.views.AnimalCount;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AnimalRepository extends CrudRepository<Animal, Long>
{

    @Query(value = "SELECT a.animaltype as animal, a.animalid as animalid, COUNT(za.zooid) as countzoos " +
            "FROM animals a LEFT JOIN zooanimals za " +
            "ON a.animalid = za.animalid " +
            "GROUP BY animalid, animal " +
            "ORDER BY animal", nativeQuery = true)
    List<AnimalCount> findAnimalCount();
}
