package org.example.bokningssystem.repo;

import org.example.bokningssystem.modell.EmailTemplate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmailTemplateRepo extends JpaRepository<EmailTemplate, Long> {
}