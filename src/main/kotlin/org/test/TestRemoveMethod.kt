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
@Fork(value = 1, jvmArgs = ["-Xms2G", "-Xmx2G"])
@Warmup(iterations = 5)
@Measurement(iterations = 10)

open class TestRemoveMethod {

    private val treeSet = TreeSet<Int>()
    private val hashSet = HashSet<Int>()
    private val binarySearchTreeSet = KtBinarySearchTree<Int>()
    private var removedElement = 0

    @Param("10", "14", "17", "20", "23")
    private var pow: Int = 5

    private val max = (2.0.pow(pow)).toInt()

    @Benchmark
    fun treeSetTest(bh: Blackhole) {
        val result = treeSet.remove(removedElement)
        bh.consume(result)
    }

    @Benchmark
    fun hashSetTest(bh: Blackhole) {
        val result = hashSet.remove(removedElement)
        bh.consume(result)
    }

    @Benchmark
    fun binarySearchTreeSetTest(bh: Blackhole) {
        val result = binarySearchTreeSet.remove(removedElement)
        bh.consume(result)
    }

    /**
    // упорядоченные данные
    // данные подаются так что дерево заполняется равномерно
    fun setupSets() {
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
    }
     */

    @Setup
    // случайные данные
    fun randomSetupSets() {
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
}