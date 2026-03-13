package org.example

import junit.framework.TestCase.assertTrue
import junit.framework.TestCase.assertFalse
import okhttp3.OkHttpClient
import okhttp3.Request
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.select.Elements
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import org.junit.runners.Parameterized.Parameters
import java.io.File

@RunWith(Parameterized::class)
class Lab3Ex2HttpGetRequestTest(
    private val url: String,
    private val responseNotEmpty: Boolean,
) {
    private val client: OkHttpClient = OkHttpClient()

    companion object {
        @JvmStatic
        @Parameters
        fun data() : Collection<Array<Any>> {
            return listOf(
                arrayOf("https://www.google.com", true),
                arrayOf("https://khttp.readthedocs.io/en/latest/", true),
                arrayOf("https://example.com", true),
            )
        }
    }

    @Test
    fun testHttpGetRequest() {
        try {
            val request: Request = Request.Builder().url(url).build()

            client.newCall(request).execute().use { response ->
                val body = response.body.string()
                println(body)
                if (responseNotEmpty) {
                    assertFalse("GET request to ${url} expected to NOT be empty!", body.isEmpty())
                } else {
                    assertTrue("GET request to ${url} expected to be empty! Actual response: ${body}", body.isEmpty())
                }
            }
        } catch (ex: IllegalArgumentException) {
            println(ex.message)
        }
    }
}

@RunWith(Parameterized::class)
class Lab3Ex2JsoupParseTest(
    private val source: String,
    private val url: String,
    private val baseUri: String,
    private val cssHeadSelector : String,
    private val cssParagraphSelector : String,
    private val cssLinkSelector: String,
) {
    companion object {
        @JvmStatic
        @Parameters
        fun data() : Collection<Array<Any>> {
            var projectPath = System.getProperty("user.dir")

            val client: OkHttpClient = OkHttpClient()
            val request: Request = Request.Builder()
                .url("https://khttp.readthedocs.io/en/latest/")
                .build()
            var htmlContent = "";
            client.newCall(request).execute().use({ response -> htmlContent = response.toString() })

            return listOf(
                arrayOf("url",
                    "https://khttp.readthedocs.io/en/latest/",
                    "", // base url is empty
                    "#khttp-http-without-the-bullshit h1", // head selector
                    "#khttp-http-without-the-bullshit p",  // paragraph selector
                    "#khttp-http-without-the-bullshit > p > a" // link selector
                ),
            )
        }
    }

    @Test
    fun testJsoupParse() {
        var htmlDocument: Document? = null
        htmlDocument = when(source) {
            "url" -> Jsoup.connect(url).get()
            "file" -> Jsoup.parse(File(url), "UTF-8", baseUri)
            "string" -> Jsoup.parse(url)
            else -> throw Exception("Unknown source")
        }
        assertFalse(htmlDocument.title().isEmpty())
        assertFalse(htmlDocument.select(cssHeadSelector).text().isEmpty())
        println(htmlDocument.title())
        println(htmlDocument.select(cssHeadSelector).text())
        val paragraphs: Elements = htmlDocument.select(cssParagraphSelector)
        for (paragraph in paragraphs) {
            assertFalse(paragraph.text().isEmpty())
            println("\t${paragraph.text()}")
        }
        val links = htmlDocument.select(cssLinkSelector)
        println("-".repeat(100))
        for (link in links) {
            assertFalse(link.text().isEmpty());
            println("${link.text()}\n\t${link.absUrl("href")}")
        }
    }
}