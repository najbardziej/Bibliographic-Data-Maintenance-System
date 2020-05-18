# Bibliographic-Data-Maintenance-System

Zaimplementować system, który na podstawie bazy danych zapisanej w  pliku XML reprezentującej dane bibliograficzne pozwala:
- wygenerować dane w formatach możliwych do wczytania przez - BibTeX - MS Word - txt - rtf
- porządkować dane w bibliografii wg wybranego formatu (np. autor,  tytuł, rok albo autor, rok, tytuł i dowolne separatory) 

Zarówno format wyjściowy jak i format wyprowadzania powinny być możliwe do zdefiniowania przez użytkownika - otwartość na nowe potrzeby.

Może być potrzeba sięgnięcia przy sporządzaniu bibliografii do kilku takich plików oraz wybierania tylko niektórych pozycji z tak przygotowanych baz źródłowych. 

Do wprowadzania danych potrzebne jest opracowanie wygodnego w użyciu GUI."

Kryteria oceny:

- Narzędzia:

    - czy projekt jest Mavenowy i czy poprawnie buduje się z linii komend konsoli systemu operacyjnego (uruchomionej poza środowiskiem deweloperskim) 
    :heavy_check_mark: (Jenkins nam to zapewnia)

    - czy budowany jest przez Mavena wykonywalny jar możliwy do uruchomienia z konsoli systemu operacyjnego (uruchomionej poza środowiskiem deweloperskim) 
    :x:

    - czy zrealizowano co najmniej 5 przykładowych testów jednostkowych (z wyłączeniem testów warstwy danych) 
    :x:

    - czy testy jednostkowe mają status PASSED w trakcie procesu budowania projektu przez Mavena 
    :x:

    - czy projekt został umieszczony pod kontrolą wersji za pomocą Gita 
    :heavy_check_mark:

    - czy struktura gałęzi repozytorium jest prawidłowo zarządzana (gałąż integracyjna, gałęzie deweloperskie każdego uczestnika projektu, merge, opcjonalnie: rozwiązywanie konfliktów, odpowiednio długa historia wersji) 
    :x:

- Design:

    - czy skorzystano z interfejsów i/lub klas abstrakcyjnych do reprezentowania abstrakcji jako jednego z elementów programowania obiektowego
    :x:

    - czy skorzystano z polimorfizmu 
    :x:

    - czy wykorzystano co najmniej trzy wzorce projektowe GoF (Gang of Four) z wykluczeniem singletona 
    :x:

    - czy prawidłowo zdekomponowano odpowiedzialności 
    :x:

    - czy wykorzystano jakiś wzorzec architektoniczny, MVC, MVP lub inny, w tym wynikający z użytego frameworku (nie należy mylić wzorców architektonicznych z wzorcami projektowymi) 
    :x:
