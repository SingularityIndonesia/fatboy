package com.singularityindonesia.core.session

import com.singularityindonesia.core.user.User

class Session(
    private val user: User,
) {
    var authenticationToken: Token?
        get() {
            TODO()
        }
        private set(value) {
            TODO()
        }
}