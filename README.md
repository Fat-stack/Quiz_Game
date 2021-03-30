# Quiz_Game

Uppgiften på konceptuell nivå:



Ni ska bygga en applikation för frågesport där man på samma dator får turas om mellan två spelare att svara på frågor.

Ni ska skapa en klass som håller reda på frågor, svarsalternativ och vilket svarsalternativ som är rätt. Den ska också 
innehålla en metod som ska läsa in de serialiserade frågeobjekten från en fil.

Ni ska skapa den abstrakta klassen Person som ska innehålla en persons namn, ålder och epost.

Ni ska implementera den konkreta klassen Player som extendar Person och lägger till hantering av variablerna score och 
played_games. Varje gång spelaren spelar ett spel ska played_games ökas på med 1 och om användaren vinner matchen ska 
score ökas med 1.

Ni ska kunna hantera frågor, antingen genom ert huvudprogram eller genom ett hjälpprogram om ni tycker att det är lättare. 
Ni ska med hjälp av programmet/hjälpprogrammet kunna lista frågor, lägga till nya frågor, ta bort frågor och eventuellt 
redigera frågor. Lägger man till frågor ska programmet fråga efter frågan och minst tre svarsalternativ. Efter varje 
svarsalternativ ska användaren få ange om svarsalternativet är korrekt eller inte. Programmet ska spara frågorna till en 
fil med serialiserade objekt.

Själva frågesportspelet ska läsa in ett antal frågor från de serialiserade objekten och presentera varannan fråga för 
spelare 1 och varannan för spelare 2. Spelet ska hålla reda på hur många poäng respektiva spelare har samlat ihop.

Spelet ska gå på tid. Antingen ska man bara ha en viss tid på sig att svara på frågorna, eller så ska den sammanlagda 
tiden vara utslagsgivande om spelarna har svarat rätt på lika många frågor.

Applikationen ska använda flera trådar på valfritt sätt. Ni kan t ex låta en tråd läda in en fil eller hålla reda på 
tidtagningen.

Applikationen ska använda något/några design patterns. 

Ni behöver inte göra något grafiskt gränssnitt, det räcker att skriva ut text med t ex System.out.println eller liknande och läsa in från tangentbordet.
