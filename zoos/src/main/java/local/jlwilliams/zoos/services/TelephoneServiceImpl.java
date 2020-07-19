package local.jlwilliams.zoos.services;

import local.jlwilliams.zoos.models.Telephone;
import local.jlwilliams.zoos.repositories.TelephoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "telephoneService")
public class TelephoneServiceImpl implements TelephoneService
{
    @Autowired
    private TelephoneRepository telephonerepos;

    @Override
    public Telephone save(Telephone telephone)
    {
        Telephone newPhone = new Telephone();
        return telephonerepos.save(newPhone);
    }
}
