import java.io.*;
import java.util.Scanner;

public class Lab6_1{

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        System.out.print("unshih file iin neriig oruulna uu: ");
        String fileName = sc.nextLine().trim();

        // 1. Оролт хоосон эсэхийг шалгах
        if (fileName.isEmpty()) {
            System.err.println("Aldaa: file iin ner hooson baij bolohgui. programmaas garch baina.");
            return;
        }

        // 2. Файл унших болон бичих (Try-with-resources ашиглав)
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName));
             BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt"))) {

            System.out.println("file aas unshij baina: " + fileName);
            String line;

            while ((line = reader.readLine()) != null) {
                // Мөрийг PascalCase хэлбэрт оруулах
                String processedLine = toPascalCase(line);
                
                // Зөвхөн агуулгатай мөрүүдийг бичих (Сонголтоор)
                if (!processedLine.isEmpty()) {
                    writer.write(processedLine);
                    writer.newLine();
                }
            }
            
            System.out.println("output.txt-d bichij duuslaa.");

        } catch (FileNotFoundException e) {
            System.err.println("aldaa: file '" + fileName + "' oldsongui.");
        } catch (IOException e) {
            System.err.println("Aldaa: File bolovsruulahad aldaa garlaa: " + e.getMessage());
        } finally {
            sc.close();
        }
    }

    /**
     * Тэмдэгт мөрийг Паскал хэлбэрт (PascalCase) хөрвүүлэх функц
     * Жишээ: "сайн байна уу" -> "СайнБайнаУу"
     */
    public static String toPascalCase(String line) {
        if (line == null || line.trim().isEmpty()) {
            return "";
        }

        // Олон зай болон тусгай тэмдэгтүүдийг зохицуулахын тулд regex ашиглав
        String[] words = line.trim().split("\\s+");
        StringBuilder result = new StringBuilder();

        for (String word : words) {
            if (!word.isEmpty()) {
                // Эхний үсэг томоор, бусад нь жижгээр
                String capitalized = word.substring(0, 1).toUpperCase() + 
                                     word.substring(1).toLowerCase();
                result.append(capitalized);
            }
        }

        return result.toString();
    }
}