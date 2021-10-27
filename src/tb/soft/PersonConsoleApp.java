package tb.soft;

import java.util.*;

/**
 * Program: Aplikacja działająca w oknie konsoli, która umożliwia testowanie 
 *          operacji wykonywanych na obiektach klasy Person.
 *    Plik: PersonConsoleApp.java
 *          
 *   Autor: Paweł Rogaliński
 *    Data: październik 2018 r.
 */
public class PersonConsoleApp {

	private static final String GREETING_MESSAGE = 
			"Program Person - wersja konsolowa\n" + 
	        "Autor: Paweł Rogaliński\n" +
			"Data:  październik 2018 r.\n";

	private static final String MENU = 
			"    M E N U   G Ł Ó W N E  \n" +
			"1 - Podaj dane nowej osoby \n" +
			"2 - Usuń osobę ArrayList        \n" +
					"13 Usun osobę LinkedList \n" +
					"14 Usun osobę HashSet \n" +
					"15 Usun osobę TreeSet \n" +
					"16 Usun osobę HashMap \n" +
					"17 Usun osobę TreeMap \n" +
			"3 - Modyfikuj dane osoby ArrayList  \n" +
					"6 Modyfikuj dane osoby LInkedList  \n" +
					"18 Modyfikuj dane osoby HashSet  \n" +
					"19 Modyfikuj dane osoby TreeSet  \n" +
					"20 Modyfikuj dane osoby HashMap  \n" +
					"21 Modyfikuj dane osoby TreeMap  \n" +
			"6 - Modyfikuj dane osoby LinkedList \n" +
					"7 Pokaż zawartość ArrayList \n" +
					"8 Pokaż zawartość LinkedList \n" +
					"9 Pokaz zawartość HashSet \n" +
					"10 Pokaż zawartość TreeSet \n" +
					"11 Pokaż zawartość HashMap \n" +
					"12 Pokaz zawartość TreeMap \n" +
			"0 - Zakończ program        \n";
	
	private static final String CHANGE_MENU = 
			"   Co zmienić?     \n" + 
	        "1 - Imię           \n" + 
			"2 - Nazwisko       \n" + 
	        "3 - Rok urodzenia  \n" + 
			"4 - Stanowisko     \n" +
	        "0 - Powrót do menu głównego\n";

	
	/**
	 * ConsoleUserDialog to pomocnicza klasa zawierająca zestaw
	 * prostych metod do realizacji dialogu z użytkownikiem
	 * w oknie konsoli tekstowej.
	 */
	private static final ConsoleUserDialog UI = new ConsoleUserDialog();
	
	
	public static void main(String[] args) {
		// Utworzenie obiektu aplikacji konsolowej
		// oraz uruchomienie głównej pętli aplikacji.
		PersonConsoleApp application = new PersonConsoleApp();
		application.runMainLoop();
	} 

	
	/*
	 *  Referencja do obiektu, który zawiera dane aktualnej osoby.
	 */
	//private Person currentPerson = null;
	private ArrayList<Person> arrayPersons = new ArrayList<>();
	private LinkedList<Person> linkedPersons = new LinkedList<>();
	private HashSet<Person> hashsetPersons = new HashSet<>();
	private TreeSet<Person> treeSetPersons = new TreeSet<>();
	private HashMap<String, Person> hashMapPersons = new HashMap<>();
	private TreeMap<String, Person> treeMapPersons = new TreeMap<>();

	/*
	 *  Metoda runMainLoop wykonuje główną pętlę aplikacji.
	 *  UWAGA: Ta metoda zawiera nieskończoną pętlę,
	 *         w której program się zatrzymuje aż do zakończenia
	 *         działania za pomocą metody System.exit(0); 
	 */
	public void runMainLoop() {
		UI.printMessage(GREETING_MESSAGE);

		while (true) {
			UI.clearConsole();
			//showCurrentPerson();

			try {
				Scanner scan = new Scanner(System.in);
				switch (UI.enterInt(MENU + "==>> ")) {
				case 1:{
					// utworzenie nowej osoby
					Person p = createNewPerson();
					if (p!= null) {
						arrayPersons.add(p);
						linkedPersons.add(p);
						hashsetPersons.add(p);
						treeSetPersons.add(p);
						hashMapPersons.put(p.getLastName(), p);
						treeMapPersons.put(p.getLastName(), p);
					}

					break;
				}
				case 2:{
					// remove Array
					UI.printInfoMessage("Usuń osobę na zadanej pozycji");
					arrayPersons.remove(scan.nextInt());
					UI.printInfoMessage("Dane aktualnej osoby zostały usunięte");
					break;
				}
				case 13:{//remove Linked
					UI.printInfoMessage("Usuń osobę na zadanej pozycji");
					linkedPersons.remove(scan.nextInt());
					UI.printInfoMessage("Dane aktualnej osoby zostały usunięte");
					break;
				}
				case 14: {//remove HashSet
					UI.printInfoMessage("Usuń osobę po nazwisku");
					Iterator<Person> iterator = hashsetPersons.iterator();
					while(iterator.hasNext()) {
						Person e = iterator.next();
						if (e.getLastName().equals( scan.next())) {
							iterator.remove();
							break;
						}
					}
					UI.printInfoMessage("Dane aktualnej osoby zostały usunięte");
					break;
				}
				case 15: {//remove TreeSet
					UI.printInfoMessage("Usuń osobę po nazwisku");
					Iterator<Person> iterator = treeSetPersons.iterator();
					while(iterator.hasNext()) {
						Person e = iterator.next();
						if (e.getLastName().equals( scan.next())) {
							iterator.remove();
							break;
						}
					}
					UI.printInfoMessage("Dane aktualnej osoby zostały usunięte");
					break;
				}
				case 16: {//remove HashMap
					UI.printInfoMessage("Usuń osobę po nazwisku");
					hashMapPersons.remove(scan.next());
					UI.printInfoMessage("Dane aktualnej osoby zostały usunięte");
					break;
				}
				case 17: {//remove TreeMap
					UI.printInfoMessage("Usuń osobę po nazwisku");
					treeMapPersons.remove(scan.next());
					UI.printInfoMessage("Dane aktualnej osoby zostały usunięte");
					break;
				}

				case 3:
					// zmiana danych dla aktualnej osoby Array
					if (arrayPersons.size()==0) throw new PersonException("Żadna osoba nie została utworzona.");
					UI.printInfoMessage("Zmień dane dla osoby na zadanej pozycji");
					int e = scan.nextInt();
					changePersonData(arrayPersons.get(e));
					UI.printInfoMessage("Dane aktualnej osoby zostały zmienione");
					break;
				/*case 4: {
					// odczyt danych z pliku tekstowego.
					String file_name = UI.enterString("Podaj nazwę pliku: ");
					arrayPersons.add(Person.readFromFile(file_name));
					UI.printInfoMessage("Dane aktualnej osoby zostały wczytane z pliku " + file_name);
					break;
				}*/
				/*case 5: {
					// zapis danych aktualnej osoby do pliku tekstowego 
					String file_name = UI.enterString("Podaj nazwę pliku: ");
					Person.printToFile(file_name, arrayPersons.get(scan.nextInt()));
					UI.printInfoMessage("Dane aktualnej osoby zostały zapisane do pliku " + file_name);
					break;
				}*/
				case 6:{
					// zmiana danych Linked
					//Scanner scan = new Scanner(System.in);
					if (arrayPersons.size()==0) throw new PersonException("Żadna osoba nie została utworzona.");
					UI.printInfoMessage("Zmień dane dla osoby na zadanej pozycji");
					int g = scan.nextInt();
					changePersonData(linkedPersons.get(g));
					UI.printInfoMessage("Dane aktualnej osoby zostały zmienione");
					break;
				}
				case 18:{// zmiana danych hashset
					if (hashsetPersons.size()==0) throw new PersonException("Żadna osoba nie została utworzona.");
					UI.printInfoMessage("Zmień dane dla osoby o zadanym nazwisku");
					Iterator<Person> iterator = hashsetPersons.iterator();
					while(iterator.hasNext()){
						if(iterator.next().getLastName().equals(scan.next())){
							changePersonData(iterator.next());
							break;
						}
					}
					UI.printInfoMessage("Dane aktualnej osoby zostały zmienione");
					break;
				}
				case 19:{// zmiana danych treeset
					if (treeSetPersons.size()==0) throw new PersonException("Żadna osoba nie została utworzona.");
					UI.printInfoMessage("Zmień dane dla osoby o zadanym nazwisku");
					Iterator<Person> iterator = treeSetPersons.iterator();
					while(iterator.hasNext()){
						if(iterator.next().getLastName().equals(scan.next())){
							changePersonData(iterator.next());
							break;
						}
					}
					UI.printInfoMessage("Dane aktualnej osoby zostały zmienione");
					break;
				}
				case 20:{// zmiana danych hashmap
					if (hashMapPersons.size()==0) throw new PersonException("Żadna osoba nie została utworzona.");
					UI.printInfoMessage("Zmień dane dla osoby o zadanym nazwisku");
					changePersonData(hashMapPersons.get(scan.next()));
					UI.printInfoMessage("Dane aktualnej osoby zostały zmienione");
					break;
				}
				case 21:{// zmiana danych treemap
					if (treeMapPersons.size()==0) throw new PersonException("Żadna osoba nie została utworzona.");
					UI.printInfoMessage("Zmień dane dla osoby o zadanym nazwisku");
					changePersonData(treeMapPersons.get(scan.next()));
					UI.printInfoMessage("Dane aktualnej osoby zostały zmienione");
					break;
				}
				case 7:{//show
					System.out.println("Osoby:\n");
					for (Person arrayListPerson : arrayPersons){
						showPerson(arrayListPerson);
					}
					break;
				}
				case 8:{//show
					for (Person linkedListPerson : linkedPersons){
						showPerson(linkedListPerson);
					}
					break;
				}
				case 9:{//show
					for (Person hashsetPerson : hashsetPersons){
						showPerson(hashsetPerson);
					}
					break;
				}
				case 10:{//show
					for (Person treesetPerson : treeSetPersons){
						showPerson(treesetPerson);
					}
					break;
				}
				case 11:{//show
					for(Person hashmapPerson : hashMapPersons.values()){
						showPerson(hashmapPerson);
					}
					break;
				}
				case 12:{//show
					for(Person treeMapPerson : treeMapPersons.values()) {
						showPerson(treeMapPerson);
					}
					break;
				}
					case 0:
					// zakończenie działania programu
					UI.printInfoMessage("\nProgram zakończył działanie!");
					System.exit(0);
				} // koniec instrukcji switch
			} catch (PersonException e) { 
				// Tu są wychwytywane wyjątki zgłaszane przez metody klasy Person,
				// gdy nie są spełnione ograniczenia nałożone na dopuszczalne wartości
				// poszczególnych atrybutów.
				// Drukowanie komunikatu o błędzie zgłoszonym za pomocą wyjątku PersonException.
				UI.printErrorMessage(e.getMessage());
			}
		} // koniec pętli while
	}



	/*
	 *  Metoda wyświetla w oknie konsoli dane aktualnej osoby 
	 *  pamiętanej w zmiennej currentPerson.
	 */
	void showCurrentPerson() {
		if (arrayPersons != null) {
			Scanner scan = new Scanner(System.in);
			showPerson(arrayPersons.get(scan.nextInt()));
			scan.close();
		}
	} 

	
	/* 
	 * Metoda wyświetla w oknie konsoli dane osoby reprezentowanej 
	 * przez obiekt klasy Person
	 */ 
	static void showPerson(Person person) {
		StringBuilder sb = new StringBuilder();
		
		if (person != null) {
			sb.append("Aktualna osoba: \n")
			  .append("      Imię: ").append(person.getFirstName()).append("\n")
			  .append("  Nazwisko: ").append(person.getLastName()).append("\n")
			  .append("   Rok ur.: ").append(person.getBirthYear()).append("\n")
			  .append("Stanowisko: ").append(person.getJob()).append("\n");
		} else
			sb.append( "Brak danych osoby\n" );
		UI.printMessage( sb.toString() );
	}

	
	/* 
	 * Metoda wczytuje w konsoli dane nowej osoby, tworzy nowy obiekt
	 * klasy Person i wypełnia atrybuty wczytanymi danymi.
	 * Walidacja poprawności danych odbywa się w konstruktorze i setterach
	 * klasy Person. Jeśli zostaną wykryte niepoprawne dane,
	 * to zostanie zgłoszony wyjątek, który zawiera komunikat o błędzie.
	 */
	static Person createNewPerson(){
		String first_name = UI.enterString("Podaj imię: ");
		String last_name = UI.enterString("Podaj nazwisko: ");
		String birth_year = UI.enterString("Podaj rok ur.: ");
		UI.printMessage("Dozwolone stanowiska:" + Arrays.deepToString(PersonJob.values()));
		String job_name = UI.enterString("Podaj stanowisko: ");
		Person person;
		try { 
			// Utworzenie nowego obiektu klasy Person oraz
			// ustawienie wartości wszystkich atrybutów.
			person = new Person(first_name, last_name);
			person.setBirthYear(birth_year);
			person.setJob(job_name);
		} catch (PersonException e) {    
			// Tu są wychwytywane wyjątki zgłaszane przez metody klasy Person,
			// gdy nie są spełnione ograniczenia nałożone na dopuszczalne wartości
			// poszczególnych atrybutów.
			// Drukowanie komunikatu o błędzie zgłoszonym za pomocą wyjątku PersonException.
			UI.printErrorMessage(e.getMessage());
			return null;
		}
		return person;
	}
	
	
	/* 
	 * Metoda pozwala wczytać nowe dane dla poszczególnych atrybutów 
	 * obiekty person i zmienia je poprzez wywołanie odpowiednich setterów z klasy Person.
	 * Walidacja poprawności wczytanych danych odbywa się w setterach
	 * klasy Person. Jeśli zostaną wykryte niepoprawne dane,
	 * to zostanie zgłoszony wyjątek, który zawiera komunikat o błędzie.
	 */
	static void changePersonData(Person person)
	{
		while (true) {
			UI.clearConsole();
			showPerson(person);

			try {		
				switch (UI.enterInt(CHANGE_MENU + "==>> ")) {
				case 1:
					person.setFirstName(UI.enterString("Podaj imię: "));
					break;
				case 2:
					person.setLastName(UI.enterString("Podaj nazwisko: "));
					break;
				case 3:
					person.setBirthYear(UI.enterString("Podaj rok ur.: "));
					break;
				case 4:
					UI.printMessage("Dozwolone stanowiska:" + Arrays.deepToString(PersonJob.values()));
					person.setJob(UI.enterString("Podaj stanowisko: "));
					break;
				case 0: return;
				}  // koniec instrukcji switch
			} catch (PersonException e) {     
				// Tu są wychwytywane wyjątki zgłaszane przez metody klasy Person,
				// gdy nie są spełnione ograniczenia nałożone na dopuszczalne wartości
				// poszczególnych atrybutów.
				// Drukowanie komunikatu o błędzie zgłoszonym za pomocą wyjątku PersonException.
				UI.printErrorMessage(e.getMessage());
			}
		}
	}
	
	
}  // koniec klasy PersonConsoleApp
