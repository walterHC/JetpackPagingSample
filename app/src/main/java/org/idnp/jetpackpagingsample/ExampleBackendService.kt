package org.idnp.jetpackpagingsample

class ExampleBackendService {
    fun searchUsers(nextPageNumber: Int): List<User> {
        val users = arrayListOf<User>()
        var user: User

        for (i in 2001..2100) {
            user = User(cui = i, firstName = "FirstName " + i, lastName = "SecondName " + i);
            users.add(user)
        }

        return users
    }
}

data class ResponseUser(val users: List<User>, val nextPageNumber: Int)