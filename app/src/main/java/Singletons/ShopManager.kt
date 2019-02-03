package Singletons

object ShopManager {
    var counter = 0
        private set

    init {
        counter = -1
    }

    @Synchronized fun test(num:Int){
        counter = num
    }

}