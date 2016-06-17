import java.util.*;
public class Uncertainty {
  public static void main(String[] args){
    String messages[] = {"", "", "", "", "", ""}
    Scanner scanner = new Scanner(System.in);
    System.out.print(messages[0]);
    int numPlayers = scanner.nextInt();
    System.out.print(messages[1]);
    String mapFileName = scanner.nextLine();
    System.out.print(messages[2]);
    int numPlayers = scanner.nextInt();
    System.out.print(messages[3]);
    int playerBudget = scanner.nextInt();
    System.out.print(messages[4]);
    Map map;
    if(mapFileName == "") map = new Map();
    else map = new Map(mapFileName);
    UncertaintyView view = newUncertaintyView();
  }
}
