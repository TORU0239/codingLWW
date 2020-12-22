
fun main(args: Array<String>) {
    println("Hello World! This is the test cases.")
    testLWW()
}

fun testLWW() {
    val map  = LWWMap<String>()
    println("current timestamp: ${System.currentTimeMillis()}")
    map.add("1")
    map.add("2")
    map.add("3")

    Thread.sleep(100)
    map.add("2")
    map.remove("3")

    println("current timestamp: ${System.currentTimeMillis()}")
    println("checking map")
    map.getMap().forEach {
        println("${it.key} / ${it.value}")
    }

    Thread.sleep(1000)
    val map2  = LWWMap<String>()
    map2.add("3")
    map2.add("4")
    map2.add("5")

    Thread.sleep(100)
    map2.merge(map)
    println("current timestamp: ${System.currentTimeMillis()}")
    println("checking map2")
    map2.getMap().forEach {
        println("${it.key} / ${it.value}")
    }

    // edge case
    map.remove("3")
    map.remove("6")

    Thread.sleep(100)
    map.add("1")

    println("checking map")
    map.getMap().forEach {
        println("${it.key} / ${it.value}")
    }
}