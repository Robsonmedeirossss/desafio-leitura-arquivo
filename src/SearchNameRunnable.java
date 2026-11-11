import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class SearchNameRunnable implements Runnable{

    private final String fileName;
    private final String searchedName;
    private String currentPath;
    
    public SearchNameRunnable(String fileName, String searchedName) {
        this.fileName = fileName;
        this.searchedName = searchedName;
        this.currentPath = Path.of("").toAbsolutePath().toString();
    }

    @Override
    public void run() {

        Thread thread = Thread.currentThread();
        System.out.println("Thread é virtual? " + thread.isVirtual());
        System.out.println("Thread ID: " + thread.threadId());
        System.out.println("-------------------------------------");
        int incidents = 0;
        int lineNumber = 0;

        try (BufferedReader br = Files.newBufferedReader(Path.of(currentPath).resolve(fileName))) {

            String content;

            while ((content = br.readLine()) != null) {
                lineNumber++;
                if (content.contains(searchedName)) {
                    incidents++;
                    System.out.println(
                        String.format("[%s]: Linha: %d, conteudo: %s", fileName, lineNumber, content)
                    );
                }
            }

            System.out.println("Busca finalizada, no ocorrências: " + incidents);
            
        } catch (IOException e) {
            System.out.println("Erro ao processar o arquivo0000");
            e.printStackTrace();
        }
        
    }
    
}
