package com.emmanuelmacaulay.Phone.Book.api.controller

import com.emmanuelmacaulay.Phone.Book.api.exception.BadRequestException
import com.emmanuelmacaulay.Phone.Book.api.exception.ResourceNotFoundException
import com.emmanuelmacaulay.Phone.Book.api.model.Contact
import com.emmanuelmacaulay.Phone.Book.api.repository.ContactRepository
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

@Validated
@RequestMapping("/api")
@RestController
class ContactController(val contactRepository: ContactRepository) {

    @GetMapping("/contacts")
    fun getContacts(): ResponseEntity<MutableIterable<Contact>> {
        return ResponseEntity.ok(contactRepository.findAll())
    }

    @GetMapping("/contacts/{id}")
    fun getContact(@PathVariable id: Int): ResponseEntity<Contact> {
        return ResponseEntity.ok(contactRepository.findById(id).orElseThrow { ResourceNotFoundException("Contact with id $id not found") } )
    }

    @PostMapping("/contacts")
    fun createContact(@Valid @RequestBody contact: Contact): ResponseEntity<Contact> {
        if (contactRepository.existsByPhoneNumber(contact.phoneNumber)) {
            throw BadRequestException("Contact with phone number ${contact.phoneNumber} already exists")
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(contactRepository.save(contact))
    }

    @DeleteMapping("/contacts/{id}")
    fun deleteContact(@PathVariable id: Int) : ResponseEntity<Any> {
        val existingContact = contactRepository.findById(id).orElseThrow { ResourceNotFoundException("Contact with id $id not found") }
        contactRepository.delete(existingContact)
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build()
    }

    @PutMapping("/contacts/{id}")
    fun updateContact(@PathVariable id: Int, @Valid @RequestBody contact: Contact): ResponseEntity<Contact> {
        val existingContact = contactRepository.findById(id).orElseThrow { ResourceNotFoundException("Contact with id $id not found") }
        existingContact.name = contact.name
        existingContact.phoneNumber = contact.phoneNumber
        existingContact.address = contact.address
        return ResponseEntity.ok(contactRepository.save(existingContact))
    }
}