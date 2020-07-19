package local.jlwilliams.zoos.controllers;

import local.jlwilliams.zoos.services.TelephoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/")
public class TelephoneController
{
    @Autowired
    private TelephoneService telephoneService;




}
