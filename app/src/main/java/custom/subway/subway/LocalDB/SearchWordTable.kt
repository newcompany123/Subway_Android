package custom.subway.subway.LocalDB

import io.realm.RealmObject

open class SearchWordTable : RealmObject() {
    var user_id: Int? = null
    var search_word: String? = null
}