package at.spengergasse.fhirstarter.controller;

import at.spengergasse.fhirstarter.entity.AttachmentPLF;
import at.spengergasse.fhirstarter.repository.PlfRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/attachments")
public class AttachmentPLFController {

    @Autowired
    private PlfRepository repository;

    @GetMapping
    public List<AttachmentPLF> getAllAttachments() {
        return new ArrayList<>((Collection) repository.findAll());
    }

    @PostMapping
    public AttachmentPLF createAttachment(@RequestBody AttachmentPLF attachment) {
        return repository.save(attachment);
    }

    @GetMapping("/{id}")
    public AttachmentPLF getAttachmentById(@PathVariable String id) {
        return repository.findById(id).orElse(null);
    }

    @PutMapping("/{id}")
    public AttachmentPLF updateAttachment(@PathVariable String id, @RequestBody AttachmentPLF updatedAttachment) {
        return repository.findById(id)
                .map(attachment -> {
                    attachment.setSize(updatedAttachment.getSize());
                    attachment.setTitle(updatedAttachment.getTitle());
                    attachment.setCreation(updatedAttachment.getCreation());
                    return repository.save(attachment);
                }).orElseGet(() -> {
                    updatedAttachment.setId(id);
                    return repository.save(updatedAttachment);
                });
    }

    @DeleteMapping("/{id}")
    public void deleteAttachment(@PathVariable String id) {
        repository.deleteById(id);
    }
}
