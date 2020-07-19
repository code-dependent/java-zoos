package local.jlwilliams.zoos.repositories;

import local.jlwilliams.zoos.models.Zoo;
import org.springframework.data.repository.CrudRepository;

public interface ZooRepository extends CrudRepository<Zoo, Long>
{
}
