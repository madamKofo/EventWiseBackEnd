/*
package com.EventWise.EventWiseBackend.configure;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

import static com.EventWise.EventWiseBackend.configure.Constants.Audit.DEFAULT_AUDITOR;

public class AuditAwareHandler implements AuditorAware<String> {
    @Override
    public Optional<String> getCurrentAuditor() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication != null ? Optional.of(authentication.getName()) : Optional.of(DEFAULT_AUDITOR);
    }
}
*/
