package Managers

import Models.Card
import Models.User

class UserManager private constructor() {

    private var user : User? = null;

    private var cards =  ArrayList<Card>()

    init {
       ++myInstancesCount
        user = User(-1)
    }


    companion object {
        //Debuggable field to check instance count
        var myInstancesCount = 0;
        private val mInstance: UserManager = UserManager()

        @Synchronized
        fun getInstance(): UserManager {
            return mInstance
        }
    }

    public fun initUserManager(user : User){
        this.user = user;
    }

    public fun getUser(): User? {
        return this.user
    }

    public fun addCardList(cardList: ArrayList<Card>){
        this.cards.clear()
        this.cards.addAll(cardList)
    }

    public fun getUserCards() : ArrayList<Card>{
        return cards
    }




}