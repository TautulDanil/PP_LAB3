# pp-lab3 — Web Crawler & Tools (Kotlin)

Proiect Kotlin cu Maven care include un web crawler, un parser RSS și un procesor de fișiere text (ebook).

---

## Structura proiectului

```
src/main/kotlin/org/example/
├── CrawlerMain.kt       # Entry point pentru web crawler
├── WebCrawler.kt        # Logica de crawling și serializare arbore
├── WebNodes.kt          # Model de date pentru noduri (arbore URL-uri)
├── RssParser.kt         # Parser RSS (entry point separat)
├── RssModel.kt          # Modele de date RSS (RssItem, RssFeed)
└── EbookProcessor.kt    # Procesor și curățare fișiere text
```

---

## Funcționalități

### 🕷️ Web Crawler (`CrawlerMain.kt` + `WebCrawler.kt`)
- Pornește de la un URL și construiește un arbore de link-uri pe **2 niveluri**
- Filtrează doar link-urile din același domeniu
- Limitează la **5 link-uri per nod** (pentru performanță)
- Serializează arborele și îl salvează în `tree_serialization.txt`

### 📰 RSS Parser (`RssParser.kt`)
- Parsează un feed RSS (implicit CNN) folosind jsoup cu XML parser
- Extrage titlu, link, descriere și dată pentru fiecare articol
- Afișează titlul și link-ul fiecărui item în consolă

### 📖 Ebook Processor (`EbookProcessor.kt`)
- Elimină numerele de pagină izolate
- Curăță spațiile și liniile goale multiple
- Corectează caracterele românești greșite (`ş` → `ș`, `ţ` → `ț`)
- Salvează rezultatul într-un fișier nou

---

## Tehnologii

| Tehnologie | Versiune |
|---|---|
| Kotlin | 2.3.0 |
| Maven | - |
| jsoup | 1.22.1 |
| OkHttp | 5.1.0 |
| JUnit | 4.13.1 |

---

## Cerințe

- **JDK 11+**
- **Maven 3.6+**

---

## Instalare și rulare

### 1. Clonează repository-ul
```bash
git clone <url-repo>
cd pp-lab3
```

### 2. Compilează proiectul
```bash
mvn compile
```

### 3. Rulează Web Crawler-ul
```bash
mvn exec:java -Dexec.mainClass="org.example.CrawlerMainKt"
```
Rezultatul va fi salvat în `tree_serialization.txt`.

### 4. Rulează RSS Parser-ul
```bash
mvn exec:java -Dexec.mainClass="org.example.RssParserKt"
```

### 5. Rulează Ebook Processor-ul
```bash
mvn exec:java -Dexec.mainClass="org.example.EbookProcessorKt"
```
Rezultatul va fi salvat în `ebook_clean.txt`.

---

## Output-uri generate

| Fișier | Descriere |
|---|---|
| `tree_serialization.txt` | Arborele de URL-uri serializat |
| `ebook_clean.txt` | Textul procesat și curățat |

---

## Exemplu output Web Crawler

```
https://en.wikipedia.org/wiki/Kotlin_(programming_language): https://en.wikipedia.org/wiki/JVM, ...
https://en.wikipedia.org/wiki/JVM: https://en.wikipedia.org/wiki/Java, ...
```
