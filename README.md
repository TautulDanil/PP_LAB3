# pp-lab3 

Proiect Kotlin cu Maven care include un web crawler, un parser RSS și un procesor de fișiere text (ebook).

## Funcționalități

###  Web Crawler (`CrawlerMain.kt` + `WebCrawler.kt`)
- Pornește de la un URL și construiește un arbore de link-uri pe **2 niveluri**
- Filtrează doar link-urile din același domeniu
- Limitează la **5 link-uri per nod** (pentru performanță)
- Serializează arborele și îl salvează în `tree_serialization.txt`

###  RSS Parser (`RssParser.kt`)
- Parsează un feed RSS (implicit CNN) folosind jsoup cu XML parser
- Extrage titlu, link, descriere și dată pentru fiecare articol
- Afișează titlul și link-ul fiecărui item în consolă

###  Ebook Processor (`EbookProcessor.kt`)
- Elimină numerele de pagină izolate
- Curăță spațiile și liniile goale multiple
- Corectează caracterele românești greșite (`ş` → `ș`, `ţ` → `ț`)
- Salvează rezultatul într-un fișier nou

### RssModel.kt
- Continte clasele de date pentru RssModel.kt

