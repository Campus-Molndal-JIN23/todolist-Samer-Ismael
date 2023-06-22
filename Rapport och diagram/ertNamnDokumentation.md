# Samer Ismael

## Egna reflektioner
En väldigt rolig uppgift som gick väldigt bra.
Mycket tydligt förklarat och man behövde inte ställa frågor eller undra om något

## Projektet
Todo-lista som kan ta användare som har Todo listor och varje lista har taskes som går att ändra och ta bort

### Beskrivning av projektet
En todo-lista för att skapa händelser åt en användare.
Kan ta fler användare och todo listor för användaren som innehåller tasks.

### Vad du har gjort
Jag har skapat en användarklass som har en lista med todos, där varje todo i sin tur har en lista med tasks.
På detta sätt blir det enkelt för användaren att skapa hur många listor som helst för olika ändamål.
I databasen registreras enbart en användare.
Alla ändringar som görs i programmet uppdateras direkt i användaren, vilket underlättar sparning och sökning av information i databasen.

## Planering
Planeringen har utförts i Asana. Jag började med att skriva klasserna som behövs för att göra ett program som gör som uppgiften säger. 
Sen kom några andra ider som hamnade i backlogen för att tanken var att göra ett program som funkar först och sen lägga till mer funktioner.
Sen började jag ta ett uppgift i taget från backlogen och skapa klasser som returnerar null för att kunna ska tester och sen fylla på metoderna

### Lösningsförslag innan uppgiften påbörjas
Att skapa en SQL:ite database som har 3 tabeller. User Todo Task.
Att skapa en MongoDB med två document en som sparar användare och en för alla todos och tasks.
Att skapa MongoDB med ett document som innehåller User och inne i User så kan man ha listorna för todos. 
Varje gång man gör en ändring så uppdateras hela User.

#### Skisser (exempelvis)
För projektplanering och -organisation använde jag mig av Asana.
Asana är ett kraftfullt verktyg för projektledning som möjliggjorde att jag kunde skapa nots med uppgifter.

#### Hur du tänker försöka lösa uppgiften (exempelvis)
På samma sätt som SMHI API funkar.
En användare har en lista med todos som i sin tur har en lista med tasks.
Det är en bra metod tycker jag för att man behöver inte många metoder i database klassen.
Man göt CRUDL för User och så uppdaterar man den när man vill.
En Scanner Utility klass för att inte behöva använda Scanner många gånger i appen och då blir det svårt att köra tester.
En menu klass som kör hela programmet med så med så tydliga metoder det går,
eftersom det ska vara mycket kod i menyn och man måste kunna förstår koden där

Jag tänkte att Todo listan är en privat och olika användare ska inte kunna se andras tasks. 
Det var därför jag valde att lista och visa Todos och tasks passerad på användaren som har loggat in. 
Inte att man kan se vad som helst i databasen.
Jag har lagt en funktion som heter account settings som inte ska vara med i verkligheten om jag ska skapa en sån app, 
men den finns nu för att läraren ska kunna testa och se vad som finns i databasen

#### Pseudokod (exempelvis)
Skapa ScannerUtil.
Skapa Todo-klass.
Skapa Task-klass.
Skapa MongoDB-klass.
Skapa MongoDBFacade.
Skapa Meny-klass.
Skapa User-klass.

#### Diagram (exempelvis)
Bifogar ett klassdiagram.
En Database klass och en database fasade för enklare hantering av metoderna.
En User klass som är kopplad till databasen.
En ToDo klass som är kopplad till User via en lista i User klassen.
En Task klass som är kopplad till ToDo klassen via en lista i ToDo klassen.
Em meny som tar info av alla andra klasser.
En User kan har många todos och en todo kan ha många tasks, men en todo eller en task kan ha bara en User.

### Jira/Trello/Github Project och projekthantering enligt Scrum/Kanban
Bifogar en bild på detta.
Så ser det ut i min Asana, där jag har 4 olika sektioner.
ToDo, Doing, Check, Done
Skapa ScannerUtil.
Skapa Todo-klass.
Skapa Task-klass.
Skapa MongoDB-klass.
Skapa MongoDBFacade.
Skapa Meny-klass.
Skapa User-klass.

## Arbetet och dess genomförande
Projektet handlade om att skapa en todo-lista där användare kan hantera uppgifter.
Jag implementerade en User-klass med inbäddade Todos och Tasks. Data sparades i en databas för enkel åtkomst.
Utmaningar inkluderade tester och att lösa problem med Github Actions. Sammantaget var det en lärorik erfarenhet.

### Vad som har varit svårt
Att göra tester på databasen var lite svårt men med lite sökning så gick det bra.
Att göra testerna via CI GitHub Action var lite svårt.

### Beskriv olika lösningar du har implementerat
Jag skapade en Scanner som en utility-klass för att slippa skapa flera Scanner-instanser i programmet.
Att spara användardata i databasen och uppdatera den vid varje ändring gjorde det enkelt att hitta information i databasen.

### Beskriv något som var besvärligt att få till
Github Actions var lite besvärligt, men det är då man lär sig nya saker.

### Beskriv om du var tvungen att ändra lösning och varför
Det blev mycket ändringar i Meny-klassen på grund av mycket kod. Först hade jag stora metoder,
men sedan ändrade jag till olika små privata metoder.

## Reflektion & Slutsatser
Jag tycker om uppgiften. Den var rolig och kan lösas på många olika sätt.
Man behövde sitta och planera innan man börjar koda, för att se till att den kommer bli enkelt och funkar som den ska.

### Vad som gick bra
Alla tester och tester med Github Actions gick bra.
Programmet fungerar som det ska och är inte lätt att få att krascha.

### Vad som gick mindre bra
Github Actions hittade inte testerna, men med lite sökande och hjälp av bästa Marcus så fungerar allt som det ska.

### Vad har du lärt dig
Att Github Actions underlättar mycket i arbetet när man får det att fungera.
Det ger direkt feedback vid problem, vilket gör att man kan åtgärda dem direkt när de uppstår.

### Vad du skulle ha gjort annorlunda om du gjorde om projektet
Jag tycker att idén är mycket bra och jag skulle inte ändra den, men själva koden kan definitivt förbättras.

### Vilka möjligheter ser du med de kunskaper du har fått under kursen?
Jag har lärt mig att skapa tester för API, Database och använda GitHub Actions samt göra komplett från början till slut.

### Motivera varför du valt en specifik lösning.
Jag valde att använda ScannerUtil för att det blir enklare att göra tester och man slipper använda new.Scanner många gånger i programmet.

### Lämna förslag på förbättringar av din kod.
Om jag skulle göra projektet en gång till så ska jag skapa två till klasser för att hantera Meny som ska visas till användare
tex.(Options.java) och en klass för att hantera funktionen som hanterar frågorna tex.(OptionHandler.java)

### Lämna exempel på lösningar du valde att **inte** implementera
Det var kanske bättre att använda SQL:ite men jag valde att inte använda den, för att appen är liten och ska kunna fungera snabbt på alla enheter,
och den behöver inte vara komplicerad med SQL kopplingarna

### Lämna förslag på förbättringar av din UI/UX design eller reflektera över den.
Det hade varit enklare med två andra klasser som hanterar Meny funktionerna. 
En klass som visar frågor och en klass som kör koden enligt frågan.
