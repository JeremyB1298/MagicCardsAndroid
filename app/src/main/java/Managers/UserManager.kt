package Managers

import Models.Card
import Models.User

object UserManager {

    var user: User? = null
    var listCards: ArrayList<Card>? = null

    fun initialize() {
        this.user = User()
        this.listCards = ArrayList()
    }

    fun getCurrentUser(): User? {
        return user
    }

    fun getUserCards(): ArrayList<Card>? {
        return listCards
    }
}