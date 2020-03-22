# Repozytorium startowe dla listy nr 5/2020
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)
[![CircleCI](https://circleci.com/gh/pwr-piisw/lista5_2020.svg?style=svg)](https://circleci.com/gh/pwr-piisw/lista5_2020)

## Jak używać?
Projekt budujemy Mavenem, z użyciem komendy `mvnw`. Klasycznie:
```bash
mvnw clean install
```
spowoduje całkowite zbudowanie projektu oraz jego instalacja w lokalnym repozytorium Mavena.

### Spring boot
Uruchomienie aplikacji można zrobić z poziomu IDE (klasa `BookstoreApplication`) lub z poziomiu CLI:
```bash
mvnw spring-boot:run
```
Więcej informacji na temat tego co oferuje plugin SpringBoot dla Mavena można uzyskać w ten sposób:
```bash
mvnw help:describe -DgroupId=org.springframework.boot -DartifactId=spring-boot-maven-plugin
```

### Spotless
W projekcie zintegrowano narzędzie Spotless, które przy każdym buildzie sprawdza formatowanie kodu, importy, nagłówki licencyjne itp. W przypadku błędów i niezgodności w tym obszarze build nie przejdzie. Spotless umożliwia szybką naprawę kodu przy użyciu następującej komendy:
```bash
mvnw spotless:apply
```
Aby dokonać szybkiego sprawdzenia zgodności bez uruchomiania całego builda należy wykonać:
```bash
mvnw spotless:check
```

### Lombok
W projekcie dostępne jest rozszerzenie Javy pt. Lombok. Lombok działa automatycznie w trakcie buildu, konieczne jest jednak włączenie odpowiedniego plugina na poziomie IDE (np. w IntelliJ należy zainstalować i aktywować plugin oraz włączyć opcję "enable annotation processing" w zakładce Compiler ustawień).

## Backend
Backend zaimplentowano w Javie z użyciem modułu Spring WebFlux - jest to reaktywna biblioteka web servera. Poniżej opisano zaimplementowane endpointy. W przypadku uruchomienia backendu na lokalnym komputerze dostęp do endpointów możliwy jest za pomocą przeglądarki internetowej (np. `http://localhost:8080/books` zwraca listę książek w formacie JSON). Zaleca się stosowanie specjalistycznych narzędzi do pracy z endpointami (np. PostMan).

### Find all books
`GET /books` - zwraca listę wszystkich książek dostępnych w systemie

### Find book by id        
`GET /books/{bookId}` - zwraca szczegóły książki o zadanym ID

### Insert or update book
`POST /books` - dodaje nową książkę lub aktualizuje istniejącą

W treści żądania należy przekazać obiekt JSON opisujący książkę, tj
```json
{
    "title": "Fiasco",
    "author": "Stanislaw Lem"
}
```
w przypadku tworzenia nowej książki (bez `id`), lub
```json
{
    "id": "1cf14edf-353f-41cb-a1fc-16b5e53993bf",
    "title": "Fiasco",
    "author": "Stanislaw Lem"
}
```
w przypadku aktualizacji istniejącej książki (z `id`).
        
### Delete book        
`DELETE /books/{bookId}` - usuwa istniejącą książkę na podstawie ID
        
### Find review by id        
`GET /reviews/{reviewId}` - zwraca recenzję o zadanym ID
        
### Find all reviews for a book        
`GET /books/{bookId}/reviews` - zwraca wszystkie recenzje dla książki o zadanym ID
        
### Create or update a review        
`POST /reviews` - tworzy lub aktualizuje recenzję

W treści żądania należy przekazać obiekt JSON opisujący recenzję, tj
```json
{
    "book": "1cf14edf-353f-41cb-a1fc-16b5e53993bf",
    "author": "James Gosling",
    "rate": 2,
    "title": "Boo",
    "content": "Some text3"
}
```
w przypadku tworzenia nowej recenzji (bez `id`), lub
```json
{
    "id": "b1167a56-9f4f-4b9a-a2b2-5bb207fe413b",
    "book": "1cf14edf-353f-41cb-a1fc-16b5e53993bf",
    "author": "James Gosling",
    "rate": 2,
    "title": "Boo",
    "content": "Some text3"
}
```
w przypadku aktualizacji istniejącej recenzji (z `id`).

