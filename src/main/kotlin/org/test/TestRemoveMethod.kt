package org.test

import org.myClass.KtBinarySearchTree
import org.openjdk.jmh.annotations.*
import org.openjdk.jmh.infra.Blackhole
import java.util.*
import java.util.concurrent.TimeUnit
import kotlin.math.pow
import kotlin.random.Random

@State(Scope.Benchmark)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Fork(1)

open class TestRemoveMethod {

    private val treeSet = TreeSet<Int>()
    private val hashSet = HashSet<Int>()
    private val binarySearchTreeSet = KtBinarySearchTree<Int>()
    private var removedElement = 0

    @Param("2", "4", "6", "8", "10", "14", "18", "22", "24")
    private var pow: Int = 0

    private var max = 0

    @Benchmark
    @Warmup(iterations = 5, time = 1)
    @Measurement(iterations = 7, time = 1)
    fun binarySearchTreeSetTest(bh: Blackhole) {
        val result = binarySearchTreeSet.remove(removedElement)
        bh.consume(result)
    }

    @Benchmark
    @Warmup(iterations = 3, time = 1)
    @Measurement(iterations = 7, time = 1)
    fun treeSetTest(bh: Blackhole) {
        val result = treeSet.remove(removedElement)
        bh.consume(result)
    }

    @Benchmark
    @Warmup(iterations = 3, time = 1)
    @Measurement(iterations = 4, time = 1)
    fun hashSetTest(bh: Blackhole) {
        val result = hashSet.remove(removedElement)
        bh.consume(result)
    }


    /**
     *
    // упорядоченные данные
    // равномерно заполненное дерево
    removedElement = Random.nextInt(1, max)
    var current = max / 2
    binarySearchTreeSet.add(current)
    treeSet.add(current)
    hashSet.add(current)
    var nodeNumber = 2
    for (i in 1 until max) {
    var newNode = current / 2
    if (newNode == 0) break
    for (j in 1..nodeNumber) {
    binarySearchTreeSet.add(newNode)
    treeSet.add(newNode)
    hashSet.add(newNode)
    newNode += current
    }
    current /= 2
    nodeNumber *= 2
    }


    // упорядоченные данные
    // все элементы в правом поддереве
    val random = Random
    removedElement = random.nextInt(1, max)
    binarySearchTreeSet.add(removedElement)
    treeSet.add(removedElement)
    hashSet.add(removedElement)

    for (i in 1..max){
    binarySearchTreeSet.add(i)
    treeSet.add(i)
    hashSet.add(i)
    }

    // упорядоченные данные
    // все элементы в левом поддереве
    val random = Random
    removedElement = random.nextInt(1, max)
    binarySearchTreeSet.add(removedElement)
    treeSet.add(removedElement)
    hashSet.add(removedElement)

    for (i in max.downTo(1)){
    binarySearchTreeSet.add(i)
    treeSet.add(i)
    hashSet.add(i)
    }

    // случайные данные
    val random = Random
    removedElement = random.nextInt(1, max)
    binarySearchTreeSet.add(removedElement)
    treeSet.add(removedElement)
    hashSet.add(removedElement)

    for (i in 1..max){
    val newElement = random.nextInt(1, max)
    binarySearchTreeSet.add(newElement)
    treeSet.add(newElement)
    hashSet.add(newElement)
    }

     */

    @Setup
    fun setupData() {
        // случайные данные
        max = (2.0.pow(pow)).toInt()
        val random = Random
        removedElement = random.nextInt(1, max)
        binarySearchTreeSet.add(removedElement)
        treeSet.add(removedElement)
        hashSet.add(removedElement)

        for (i in 1..max){
            val newElement = random.nextInt(1, max)
            binarySearchTreeSet.add(newElement)
            treeSet.add(newElement)
            hashSet.add(newElement)
        }
    }

    @TearDown
    fun clearData() {
        treeSet.clear()
        hashSet.clear()
        binarySearchTreeSet.clear()
    }
}