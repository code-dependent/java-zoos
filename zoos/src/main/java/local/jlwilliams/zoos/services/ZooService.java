package local.jlwilliams.zoos.services;

import local.jlwilliams.zoos.models.Zoo;

import java.util.List;

public interface ZooService
{
    Zoo save(Zoo zoo);

    List<Zoo> listAllZoos();

    Zoo findZooById(long id);

    Zoo update(Zoo zoo, long id);
}
