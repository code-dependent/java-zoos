package local.jlwilliams.zoos.services;

import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AuditingService
        implements AuditorAware<String>
{
    @Override
    public Optional<String> getCurrentAuditor()
    {
        return Optional.of("SYSTEM");
    }
}
