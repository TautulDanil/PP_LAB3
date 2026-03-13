package org.example

import org.jsoup.Jsoup
import org.jsoup.parser.Parser

fun main() {
    val url = "http://rss.cnn.com/rss/edition.rss"

    try {
        val doc = Jsoup.connect(url)
            .parser(Parser.xmlParser())
            .get()

        val channel = doc.selectFirst("channel")
        val feedTitle = channel?.selectFirst("title")?.text() ?: ""
        val feedLink = channel?.selectFirst("link")?.text() ?: ""
        val feedDesc = channel?.selectFirst("description")?.text() ?: ""

        val items = doc.select("item").map { item ->
            RssItem(
                title = item.selectFirst("title")?.text() ?: "Fără titlu",
                link = item.selectFirst("link")?.text() ?: "Fără link",
                description = item.selectFirst("description")?.text() ?: "",
                pubDate = item.selectFirst("pubDate")?.text() ?: ""
            )
        }

        val myFeed = RssFeed(feedTitle, feedLink, feedDesc, items)

        myFeed.items.forEach {
            println("Titlu: ${it.title}")
            println("Link:  ${it.link}")
            println(" ".repeat(30))
        }

    } catch (e: Exception) {
        println("Eroare la procesarea RSS: ${e.message}")
    }
}