package com.emmanuelmacaulay.Phone.Book.api.repository

import com.emmanuelmacaulay.Phone.Book.api.model.Contact
import org.springframework.data.repository.CrudRepository

/**
 * Repository class for <code>Contact</code> domain objects
 *
 * @author Emmanuel Macaulay
 */
interface ContactRepository: CrudRepository<Contact, Int> {

    fun existsByPhoneNumber(phoneNumber : String) : Boolean
}