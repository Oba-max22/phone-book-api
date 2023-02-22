package com.emmanuelmacaulay.Phone.Book.api.exception

/**
 * Exception thrown when a resource is not found.
 *
 * @author Emmanuel Macaulay
 */

class ResourceNotFoundException(message: String? = null, cause: Throwable? = null) : Exception(message, cause)
