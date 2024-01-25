package com.freeuni.coursewhisperer.service;

import com.freeuni.coursewhisperer.model.db.Prerequisite;
import com.freeuni.coursewhisperer.repository.PrerequisiteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PrerequisiteService {

    private final PrerequisiteRepository prerequisiteRepository;

    @Autowired
    public PrerequisiteService(PrerequisiteRepository prerequisiteRepository) {
        this.prerequisiteRepository = prerequisiteRepository;
    }

    public List<Prerequisite> getAllPrerequisites() {
        return prerequisiteRepository.findAll();
    }

    public Prerequisite getPrerequisiteById(Long id) {
        return prerequisiteRepository.findById(id).orElse(null);
    }

    public Prerequisite createPrerequisite(Prerequisite prerequisite) {
        return prerequisiteRepository.save(prerequisite);
    }

    public Prerequisite updatePrerequisite(Long id, Prerequisite prerequisite) {
        if (prerequisiteRepository.existsById(id)) {
            prerequisite.setId(id);
            return prerequisiteRepository.save(prerequisite);
        }
        return null;
    }

    public void deletePrerequisite(Long id) {
        prerequisiteRepository.deleteById(id);
    }
}
