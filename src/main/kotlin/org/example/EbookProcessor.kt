package org.example

import java.io.File

class EbookProcessor(private val filePath: String) {

    fun process(): String {
        var content = File(filePath).readText()
        content = content.replace(Regex("\\s{2,}\\d+\\s{2,}"), " ")

        content = content.replace(Regex("\\n{2,}"), "\n")

        content = content.replace(Regex("[ ]{2,}"), " ")

        val mapping = mapOf("ş" to "ș", "Ş" to "Ș", "ţ" to "ț", "Ţ" to "Ț")
        mapping.forEach { (old, new) ->
            content = content.replace(old, new)
        }

        return content.trim()
    }

    fun save(content: String, outputPath: String) {
        File(outputPath).writeText(content)
        println("Fișier procesat salvat în: $outputPath")
    }
}

fun main() {
    // Creează un fișier de test dacă nu există
    val testFile = File("ebook_test.txt")
    testFile.writeText("""
        Capitolul 1       
        
        Acesta este un   text    cu multe spații.
        
                  45          
        
        Urmează o altă pagină: şarpe, ţară.
    """.trimIndent())

    val processor = EbookProcessor("ebook_test.txt")
    val result = processor.process()

    println("Rezultat procesat:\n")
    println(result)
    processor.save(result, "ebook_clean.txt")
}