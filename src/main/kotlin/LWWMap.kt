class LWWMap<T> {
    private val _addedMap = HashMap<T,Long>()
    fun getMap() = _addedMap

    fun add(item:T){
        if(_addedMap.containsKey(item)) {
            _addedMap[item]?.let { t ->
                val currentTimeStamp = System.currentTimeMillis()
                if(t <= currentTimeStamp) {
                    _addedMap[item] = currentTimeStamp
                }
            }
        } else {
            _addedMap[item] = System.currentTimeMillis()
        }
    }

    fun remove(item:T){
        if (_addedMap.containsKey(item)) {
            _addedMap.remove(item)
        }
    }

    fun merge(map:LWWMap<T>){
        map.getMap().forEach { e ->
            _addedMap.merge( e.key, e.value){ v1, v2 ->
                if(v1 < v2) {
                    v1
                } else {
                    v2
                }
            }
        }
    }
}


