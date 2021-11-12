import java.io.*;

public class App {
    private static String linuxFilePath="/home/sebaarch/ProyectosU/TalleresPrograAvanzada/Taller2/";
    public static void main(String[] args) throws IOException {
        SistemaUCR sistema = new SistemaUCRImpl();
        lecturaEstudiantes(sistema);


    }

    private static void lecturaEstudiantes(SistemaUCR sistema) throws IOException{
        BufferedReader buffer = new BufferedReader(new InputStreamReader
                (new FileInputStream(linuxFilePath+"estudiantes.txt"),
                        "utf-8"));
        String linea;
        while ((linea = buffer.readLine()) != null){
            System.out.println(linea);
        }
    }
}
