import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {
        String[] files = {
            "signatarios-fev-2002.txt",
            "signatarios-fev-2016.txt",
            "signatarios-out-2014.txt",
        };
        String searchedName = "Robson";

        long startTime = System.currentTimeMillis();
        System.out.println("-------------------------------");
        System.out.println("Iniciando busca pelo nome: " + searchedName + "...");

        try (ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor()) {

            for (String file: files) {
                SearchNameRunnable runnable = new SearchNameRunnable(file, searchedName);
                executor.execute(runnable);
            }

        } 

        long endTime = System.currentTimeMillis();
        System.out.println();
        System.out.println("Tempo de busca: " + (endTime - startTime) + "ms");

    }
}
