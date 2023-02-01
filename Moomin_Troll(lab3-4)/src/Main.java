public class Main {
    public static void main(String[] args) {
        Troll troll = new Troll("");
        Life w = new Life(troll, 0);
        w.go();
    }

}