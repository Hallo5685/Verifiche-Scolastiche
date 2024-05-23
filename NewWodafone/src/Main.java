import Utility.Contatto;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import static Utility.Contatto.*;

public class Main {
    public static void main(String[] args) {

        // Definizione del menu delle operazioni disponibili
        String[] operazioni = {"VODAFONE",
                "[1] Inserimento",
                "[2] Cancellazione",
                "[3] Visualizzazione",
                "[4] Salva file",
                "[5] Esci dal Programma",
                "[6]","[7]","[8]","[9]",
        };

        boolean Sitel = true; // Variabile di controllo per la lettura dei dati
        final int nMax = 3; // Numero massimo di contatti gestiti
        int contrattiVenduti = 0; // Contatore dei contatti venduti
        Contatto[] gestore = new Contatto[nMax]; // Array di contatti

        Scanner keyboard = new Scanner(System.in); // Scanner per la lettura degli input
        boolean fine = true; // Variabile di controllo del ciclo principale

        do {
            // Switch case per la gestione delle diverse opzioni del menu
            switch (menu(operazioni, keyboard)) {
                case 1:
                {
                    // Inserimento di un nuovo contatto
                    if (contrattiVenduti < nMax) {
                        gestore[contrattiVenduti] = leggiPersona(Sitel, keyboard);
                        contrattiVenduti++;
                    } else {
                        System.out.println("Non ci sono più contratti da vendere");
                    }
                    break;
                }

                case 2:
                {
                    // Cancellazione di un contatto
                    if (contrattiVenduti != 0)
                    {
                        int posizione = RicercaIndex(gestore, leggiPersona(false, keyboard), contrattiVenduti);
                        if (posizione != -1) {
                            contrattiVenduti = cancellazione(gestore, posizione, contrattiVenduti);
                        } else {
                            System.out.println("Contatto inesistente");
                        }
                    } else {
                        System.out.println("Non sono ancora presenti contratti venduti");
                    }

                    break;
                }

                case 3:
                {
                    // Visualizzazione dei contatti
                    if (contrattiVenduti > 0) {
                        visualizza(gestore, contrattiVenduti);
                    } else {
                        System.out.println("Non esiste nessun contatto");
                    }
                    break;
                }

                case 4:
                {
                    // Salvataggio dei contatti su file
                    try {
                        ScriviFile("archivio.csv", gestore, contrattiVenduti);
                    } catch (IOException ex) {
                        System.out.println(ex.toString());
                    }
                    break;
                }

                case 5:
                {
                    // Uscita dal programma
                    fine = false;
                    break;
                }

                case 6:
                {
                    // Cambio dell'attributo password
                    cambiaPassword(gestore, contrattiVenduti, keyboard);
                    break;
                }

                case 7:
                {
                    // Visualizzazione contatti con password diversa da "cervo12"
                    stampaNascosto(gestore, contrattiVenduti);
                    break;
                }
            }
        } while (fine);
    }

    private static Contatto leggiPersona(boolean Sitel, Scanner keyboard) {
        Contatto persona = new Contatto();

        System.out.println("\nInserisci il nome: ");
        persona.nome = keyboard.nextLine();

        System.out.println("\nInserisci il cognome: ");
        persona.cognome = keyboard.nextLine();

        System.out.println("\nInserisci il numero di telefono: ");
        persona.telefono = keyboard.nextLine();

        return persona;
    }

    private static int RicercaIndex(Contatto[] gestore, Contatto ricerca, int contrattiVenduti) {
        for (int i = 0; i < contrattiVenduti; i++) {
            if (ricerca.nome.equals(gestore[i].nome) && ricerca.cognome.equals(gestore[i].cognome)) {
                return i;
            }
        }
        return -1;
    }

    private static void visualizza(Contatto[] gestore, int contrattiVenduti) {

        for (int i = 0; i < contrattiVenduti; i++)
        {
           if(gestore[i].password.equals("cervo12"))
           {
               System.out.println("Name: " + gestore[i].nome);
               System.out.println("Surname: " + gestore[i].cognome);
               System.out.println("Telefon number: " + gestore[i].telefono);
           }

        }
        System.out.println("\n");
    }

    public static int cancellazione(Contatto[] gestore, int posizione, int contrattiVenduti) {

        if (posizione != gestore.length - 1)
        {
            for (int i = posizione; i < contrattiVenduti - 1; i++)
            {
                gestore[i] = gestore[i + 1];
            }
        }
        return --contrattiVenduti;
    }

    public static void ScriviFile(String fileName, Contatto[] gestore, int contrattiVenduti) throws IOException {
        FileWriter writer = new FileWriter(fileName);

        for (int i = 0; i < contrattiVenduti; i++) {
            writer.write(gestore[i].nome + ", " + gestore[i].cognome + ", " + gestore[i].telefono + ", " + gestore[i].password + "\n");
        }
        writer.flush();
        writer.close();
    }

    public static int menu(String[] opzioni, Scanner keyboard) {
        int scelta;

        do {
            System.out.println("=== " + opzioni[0] + " ===");
            for (int i = 1; i < opzioni.length; i++) {
                System.out.println(opzioni[i]);
            }
            scelta = Integer.parseInt(keyboard.nextLine());
            if (scelta < 1 || scelta > opzioni.length - 1) {
                System.out.println("Valore errato. Riprova");
            }
        } while (scelta < 1 || scelta > opzioni.length - 1);

        return scelta;
    }

    private static void cambiaPassword(Contatto[] gestore, int contrattiVenduti, Scanner keyboard) {

        if (contrattiVenduti > 0)
        {
            System.out.println("Inserisci il nome del contatto per cui vuoi cambiare la password: ");
            String nome = keyboard.nextLine();

            System.out.println("Inserisci il cognome del contatto per cui vuoi cambiare la password: ");
            String cognome = keyboard.nextLine();

            int indice = -1;

            for (int i = 0; i < contrattiVenduti; i++)
            {
                if (gestore[i].nome.equals(nome) && gestore[i].cognome.equals(cognome)) {
                    indice = i;
                    break;
                }
            }

            if (indice != -1)
            {
                System.out.println("Inserire la nuova password: ");
                gestore[indice].password = keyboard.nextLine();
                System.out.println("Password cambiata con successo.");

            } else {
                System.out.println("Contatto non trovato.");
            }

        } else {
            System.out.println("Non ci sono contatti da modificare.");
        }
    }

    private static void stampaNascosto(Contatto[] gestore, int contrattiVenduti)
    {
        for (int i = 0; i < contrattiVenduti; i++)
        {
            if (!gestore[i].password.equals("cervo12"))
            {
                System.out.println("Name: " + gestore[i].nome);
                System.out.println("Surname: " + gestore[i].cognome);
                System.out.println("Telefon number: " + gestore[i].telefono);

            }
        }
    }


}
