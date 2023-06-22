# Samer Ismael

## Egna reflektioner
En väldigt rolig uppgift som gick väldigt bra.

## Projektet
Todo-lista

### Beskrivning av projektet
En todo-lista för att skapa händelser åt en användare.

### Vad du har gjort
Jag har skapat en användarklass som har en lista med todos, där varje todo i sin tur har en lista med tasks. På detta sätt blir det enkelt för användaren att skapa hur många listor som helst för olika ändamål. I databasen registreras enbart en användare. Alla ändringar som görs i programmet uppdateras direkt i användaren, vilket underlättar sparning och sökning av information i databasen.

## Planering
Planeringen har utförts i Asana.

### Lösningsförslag innan uppgiften påbörjas
Jag kan inte svara på den frågan eftersom jag redan har slutfört uppgiften.

#### Skisser (exempelvis)
För projektplanering och -organisation använde jag mig av Asana. Asana är ett kraftfullt verktyg för projektledning som möjliggjorde att jag kunde skapa listor med uppgifter.

#### Hur du tänker försöka lösa uppgiften (exempelvis)
På samma sätt som SMHI API funkar. En användare har en lista med todos som i sin tur har en lista med tasks. Det är en bra metod för att hantera stora mängder data.

#### Pseudokod (exempelvis)
Skapa Todo-klass.
Skapa Task-klass.
Skapa MongoDB-klass.
Skapa MongoDBFacade.
Skapa ScannerUtil.
Skapa Meny-klass.
Skapa User-klass.

#### Diagram (exempelvis)
Bifogar ett klassdiagram.

### Jira/Trello/Github Project och projekthantering enligt Scrum/Kanban
Bifogar en bild på detta.

## Arbetet och dess genomförande
Projektet handlade om att skapa en todo-lista där användare kan hantera uppgifter. Jag implementerade en User-klass med inbäddade Todos och Tasks. Data sparades i en databas för enkel åtkomst. Utmaningar inkluderade tester och att lösa problem med Github Actions. Sammantaget var det en lärorik erfarenhet.

### Vad som har varit svårt
Att göra tester på databasen.

### Beskriv olika lösningar du har implementerat
Jag skapade en Scanner som en utility-klass för att slippa skapa flera Scanner-instanser i programmet. Att spara användardata i databasen och uppdatera den vid varje ändring gjorde det enkelt att hitta information i databasen.

### Beskriv något som var besvärligt att få till
Github Actions var lite besvärligt, men det är då man lär sig nya saker.

### Beskriv om du var tvungen att ändra lösning och varför
Det blev mycket ändringar i Meny-klassen på grund av mycket kod. Först hade jag stora metoder, men sedan ändrade jag till olika små privata metoder.

## Reflektion & Slutsatser
Jag tycker om sådana här uppgifter. Den var rolig och kan lösas på många olika sätt.

### Vad som gick bra
Alla tester och tester med Github Actions gick bra. Programmet fungerar som det ska och är inte lätt att få att krascha.

### Vad som gick mindre bra
Github Actions hittade inte testerna, men med lite sökande fungerar allt som det ska.

### Vad har du lärt dig
Att Github Actions underlättar mycket i arbetet när man får det att fungera. Det ger direkt feedback vid problem, vilket gör att man kan åtgärda dem direkt när de uppstår.

### Vad du skulle ha gjort annorlunda om du gjorde om projektet
Jag tycker att idén är mycket bra och jag skulle inte ändra den, men själva koden kan definitivt förbättras.

### Vilka möjligheter ser du med de kunskaper du har fått under kursen?
Jag har lärt mig att skapa tester för API, Database och använda GitHub Actions samt göra komplett från början till slut.