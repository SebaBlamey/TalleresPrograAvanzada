import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Scanner;

public class pruebas {
    public static void main(String[] args) {
        /*String formato = "dd/MM/yyyy";

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(formato);
        String formattedDate = formatter.format(LocalDate.of(2000,11,21));
        System.out.println(d1);

         */
        Date d1 = new Date(2021,4,21);
        Date inicio1 = new Date(2021,3,8);
        Date inicio2 = new Date(2021,5,2);

        Date Mitad1 = new Date(2021,5,3);
        Date Mitad2 = new Date(2021,7,11);

        Date Final1 = new Date(2021, 7,12);
        Date Final2 = new Date(2021, 7,25);

        Date CierreSemestre = new Date(2021,7,26);

        if(d1.after(inicio1) && d1.before(inicio2)){
            System.out.println("Siii");
        }else{
            System.out.println("Nooo");
        }
        Scanner entrada = new Scanner(System.in);
        System.out.println("x: ");
        String dia = entrada.nextLine();
        int dia1 = Integer.parseInt(dia);
        System.out.println(dia1);
    }
}
