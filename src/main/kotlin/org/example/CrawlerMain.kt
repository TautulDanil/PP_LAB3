package org.example

import java.io.File

fun main() {
    val targetUrl = "https://en.wikipedia.org/wiki/Kotlin_(programming_language)" // Poți schimba cu orice URL
    val crawler = WebCrawler(targetUrl)

    println("Se extrag link-urile... vă rugăm așteptați.")
    val rootNode = crawler.buildTree()

    val output = crawler.serializeTree(rootNode)

    File("tree_serialization.txt").writeText(output)

    println("Rezultatul este în tree_serialization.txt")
    println("\nPrevizualizare arbore:\n$output")
}