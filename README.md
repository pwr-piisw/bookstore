# Repozytorium startowe dla listy nr 5/2020
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)
[![CircleCI](https://circleci.com/gh/pwr-piisw/bookstore.svg?style=svg)](https://circleci.com/gh/pwr-piisw/bookstore)

## Jak używać?
Projekt budujemy Mavenem, z użyciem komendy `mvnw`. Klasycznie:
```bash
mvnw clean install
```
spowoduje całkowite zbudowanie projektu oraz jego instalacja w lokalnym repozytorium Mavena.

### Spring boot
Uruchomienie aplikacji można zrobić z poziomu IDE (klasa `BookstoreApplication`) lub z poziomiu CLI:
```bash
mvnw spring-boot:run -pl backend
```
Więcej informacji na temat tego co oferuje plugin SpringBoot dla Mavena można uzyskać w ten sposób:
```bash
mvnw help:describe -DgroupId=org.springframework.boot -DartifactId=spring-boot-maven-plugin -pl backend
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
`GET /api/books` - zwraca listę wszystkich książek dostępnych w systemie

### Find book by id        
`GET /api/books/{bookId}` - zwraca szczegóły książki o zadanym ID

### Insert or update book
`POST /api/books` - dodaje nową książkę lub aktualizuje istniejącą

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
`DELETE /api/books/{bookId}` - usuwa istniejącą książkę na podstawie ID
        
### Find review by id        
`GET /api/reviews/{reviewId}` - zwraca recenzję o zadanym ID
        
### Find all reviews for a book        
`GET /api/books/{bookId}/reviews` - zwraca wszystkie recenzje dla książki o zadanym ID
        
### Create or update a review        
`POST /api/reviews` - tworzy lub aktualizuje recenzję

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

## Frontend
Kod frontendu dostępny jest w module `frontend` i jest napisany w Angular 9. Praca z frontendem możliwa jest w całości przy użyciu Mavena, ale wygodniej będzie zainstalować kilka dodatkowych narzędzi.

### Instalacja narzędzi
Do pracy w trybie developerskim niezbędny jest `nodeJS` w wersji 12.16.x. Do zarządzania wersjam środowiska `nodeJS` dobrze jest użyć `nvm`:

https://github.com/coreybutler/nvm-windows/releases

Po zainstalowaniu `nvm` dostępna jest komenda CLI, przy pomocy której możemy zainstalować odpowiednią wersję `nodeJS`:

```
nvm list available
nvm install 12.16.1
nvm use 12.16.1
```

Po zainstalowaniu środowiska `nodeJS` dostępne będzie także narzędzie `npm`, którego użyjemy do zainstalowania Angular CLI:

```
npm install -g @angular/cli
```

### Uruchomienie frontendu w trybie develperskim
Tryb developerski pozwala na szybki restart frondendu w celu natychmiastowej weryfikacji napisanego kodu. W celu skorzystania z tego trybu pracy należy najpierw uruchomić backend przy użyciu np. IDE lub z poziomu linii poleceń:
```
mvnw spring-boot:run -pl backend
```

Następnie należy z poziomu linii poleceń wejść do katalogu `frontend` i uruchomić komendę:
```
ng serve
```

Frontend w trybie developerskim dostępny będzie pod adresem: http:\\localhost:4200

### Uruchomienie frontendu w trybie produkcyjnym
Frontend w trybie produkcyjnym może być uruchomiony z poziomu linii poleceń:
```
mvnw install
mvnw spring-boot:run -pl backend
```

W tym trybie frontend dostępny będzie pod adresem: http:\\localhost:8080\ui
