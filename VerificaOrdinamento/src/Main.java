import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        int[] array = new int[10];
        // Contatore per i numeri inseriti
        int count = 0;
        int num = 0;

        Scanner scanner = new Scanner(System.in);

        //inizio del while
        while (count < 10) {
            //primo inserimento
            System.out.println("Inserisci il un numero tra 0-30 ");
            num = scanner.nextInt();

            // Controllo dei vincoli, con continuazione fino al re inserimento
            if (num < 0 || num > 30) {
                System.out.println("Numero non valido. Deve essere tra 0 e 30.");
                continue;
            }

            // Controllo dei duplicati, con continuazione fino al re inserimento, e metodo controlloDup
            //se il metodo returna vero, allora si entra nel if, altrimenti no
            if (controlloDup(array, num, count)) {
                System.out.println("Numero già presente nell'array. Inserisci un numero diverso ");
                continue;
            }

            // Inserimento del numero nell'array
            array[count] = num;
            count++;
        }

        //stampa dei numeri nell'array
        System.out.println("\nNumeri inseriti nell'array:");
        for (int i = 0; i < 10; i++) {
            System.out.print(array[i] + " ");
        }

        array = ordinamento(array, num, count);

        System.out.println("\n\nEcco l'array ordinato: ");
        for (int i = 0; i < 10; i++) {
            System.out.print(array[i] + " ");
        }

    }

    // Metodo per verificare se un numero è già presente nell'array
    private static boolean controlloDup(int[] array, int num, int count)
    {
        for (int i = 0; i < count; i++)
        {
            if (array[i] == num)
            {
                return true;
            }
        }
        return false;
    }

    private static int[] ordinamento(int[] array, int num, int count)
    {
        // Escludere l'ultimo numero inserito
        int lastIndex = count - 1;

        //Salviamo tutti i numeri pari escluso quello nell'ultima cella dell'array (pk non va ordinato)
        int[] pariArray = new int[count];
        int pariCount = 0;

        for (int i = 0; i < 10; i++)
        {
            if (array[i] % 2 == 0)
            {
                pariArray[pariCount++] = array[i];
            }
        }

        // Ordinare i numeri pari in modo decrescente tramite buble sort
        for (int i = 0; i < pariCount - 1; i++)
        {
            for (int j = 0; j < pariCount - 1 - i; j++)
            {
                if (pariArray[j] < pariArray[j + 1])
                {
                    int temp = pariArray[j];
                    pariArray[j] = pariArray[j + 1];
                    pariArray[j + 1] = temp;
                }
            }
        }

        // Reinseriamo i numeri pari ordinati nelle posizioni originali dell'array iniziale
        int pariIndex = 0;

        for (int i = 0; i < 10; i++)
        {
            if (array[i] % 2 == 0)
            {
                array[i] = pariArray[pariIndex++];
            }
        }

        return array;
    }
}