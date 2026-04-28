package com.ceej.common.use_case

import java.util.regex.Pattern

class ValidateEmail {
    private val EMAIL_PATTERN = Pattern.compile(
        "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                "\\@" +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                "(" +
                "\\." +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                ")+"
    )

    fun execute(email: String): Boolean {
        return email.isNotEmpty() && EMAIL_PATTERN.matcher(email).matches()
    }
}
