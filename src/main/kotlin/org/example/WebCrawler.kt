package org.example

import org.jsoup.Jsoup
import java.io.File
import java.net.URL

class WebCrawler(val startUrl: String) {
    private val domain = URL(startUrl).host

    fun buildTree(): Node {
        val root = Node(startUrl)
        val level1Links = getLinks(startUrl)

        for (link in level1Links) {
            val childNode = Node(link)
            root.children.add(childNode)

            val level2Links = getLinks(link)
            level2Links.forEach { childNode.children.add(Node(it)) }
        }
        return root
    }

    private fun getLinks(url: String): List<String> {
        return try {
            Jsoup.connect(url).timeout(5000).get()
                .select("a[href]")
                .map { it.attr("abs:href") }
                .filter { it.contains(domain) } // Doar același domeniu
                .distinct()
                .take(5) // Limităm pentru testare rapidă
        } catch (e: Exception) {
            emptyList()
        }
    }

    fun serializeTree(node: Node, sb: StringBuilder = StringBuilder()): String {
        if (node.children.isNotEmpty()) {
            sb.append("${node.url}: ${node.children.joinToString(", ") { it.url }}\n")
            node.children.forEach { serializeTree(it, sb) }
        }
        return sb.toString()
    }
}