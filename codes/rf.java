import java.io.*;  // Import the File class
import java.util.Scanner;

public class rf { 

  public static void main(String[] args) {
    long startTime = System.nanoTime();
    File myObj = new File("test51.txt");
    if (myObj.exists()) {

        int numTestCases;

        try {
            File file = new File("myans.txt");
            file.createNewFile();
            FileWriter fw = new FileWriter(file);
            Scanner sc = new Scanner(myObj);

            numTestCases = sc.nextInt();
            while(numTestCases-->0){
                int size;
                size = sc.nextInt();
                A2DynamicMem obj = new A2DynamicMem(size,3);
                int numCommands = sc.nextInt();
                while(numCommands-->0) {
                    String command;
                    command = sc.next();
                    int argument;
                    argument = sc.nextInt();
                    int result = -5;
                    boolean toPrint = true;
                    switch (command) {
                        case "Allocate":
                            result = obj.Allocate(argument);
                            break;
                        case "Free":
                            result = obj.Free(argument);
                            break;
                        case "Defragment":
                            obj.Defragment();
                            toPrint=false;
                        default:
                            break;
                    }
                    if(toPrint){
                        System.out.println(result);
                        BufferedWriter bw = new BufferedWriter(fw);
                        bw.write(result + "\r\n");
                        bw.flush();
                    }
                }
            }


            
        }

        catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    } 

    else {
      System.out.println("The file does not exist.");
    }
    long stopTime = System.nanoTime();
    System.out.println((stopTime - startTime)/1000000000.0);
  }
}