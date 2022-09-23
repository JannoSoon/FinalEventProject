package com.example.eventwebapplication.handlers;

import com.example.eventwebapplication.utils.Constants;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

public class AuditAwareHandler implements AuditorAware<String> {
    @Override
    public Optional<String> getCurrentAuditor() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();


        if (authentication != null) {
            return Optional.of(authentication.getName());
        }else {
            return Optional.of(Constants.Audit.DEFAULT_AUDITOR);
        }
    }
}
