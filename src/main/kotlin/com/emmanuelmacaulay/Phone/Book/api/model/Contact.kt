package com.emmanuelmacaulay.Phone.Book.api.model

import jakarta.persistence.*
import jakarta.validation.constraints.NotBlank

/**
 * Simple JavaBean domain object representing an owner.
 *
 * @author Emmanuel Macaulay
 */
@Entity
@Table(name = "contacts")
data class Contact(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) var id: Int?, @Column(name = "name")
    @NotBlank var name: String, @Column(name = "phone_number")
    @NotBlank var phoneNumber: String, @Column(name = "address")
    @NotBlank var address: String
) {

}