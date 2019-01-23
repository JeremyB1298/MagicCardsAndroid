package Managers

import Models.User

class UserManager private constructor() {

    private var user : User? = null;

    private val mCache: MutableMap<String, String> = mutableMapOf()


    init {
       ++myInstancesCount
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



}