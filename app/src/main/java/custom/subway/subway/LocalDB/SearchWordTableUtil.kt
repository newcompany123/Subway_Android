package custom.subway.subway.LocalDB

import io.realm.Realm


// 중복 저장 방지 부터 해야됨
fun saveRecentSerachWordToDB(word: String) {
    val realm = Realm.getDefaultInstance()
    deleteRecentSearchWordFromDB(word)
    realm.executeTransaction { realm ->
        with(realm.createObject(SearchWordTable::class.java)) {
            this.user_id = 1
            this.search_word = word
        }
    }
}

fun deleteRecentSearchWordFromDB(word: String) {
    val realm = Realm.getDefaultInstance()
    realm.executeTransaction {
        val base = realm.where(SearchWordTable::class.java)
        base.equalTo("search_word", word).findAll()?.let {
            it.deleteAllFromRealm()
        }
    }
}


fun deleteAllRecentSearchWordFromDB() {
    val realm = Realm.getDefaultInstance()
    realm.executeTransaction {
        val base = realm.where(SearchWordTable::class.java)
        base.findAll().deleteAllFromRealm()
    }
}