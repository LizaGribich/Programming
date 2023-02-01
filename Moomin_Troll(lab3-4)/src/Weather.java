public class Weather {
    private boolean Rain;
    private boolean Sun;
    private boolean Warm;
    private boolean Wind;

    public Weather(boolean Rain, boolean Sun, boolean Warm, boolean Wind) {
        this.Rain = Rain;
        this.Sun = Sun;
        this.Warm = Warm;
        this.Wind = Wind;
    }

    public boolean get_Rain() {
        return Rain;
    }

    public boolean get_Sun() {
        return Sun;
    }

    public boolean get_Warm() {
        return Warm;
    }

    public boolean get_Wind() {
        return Wind;
    }

    public void say_Weather() {
        String[] Day = new String[4];
        if (this.get_Rain()) {
            Day[0] = "дождливым ";
        } else {
            Day[0] = "недождливым ";
        }

        if (this.get_Sun()) {
            Day[1] = "солнечным ";
        } else {
            Day[1] = "облачным ";
        }

        if (this.get_Warm()) {
            Day[2] = "тёплым ";
        } else {
            Day[2] = "прохладным ";
        }

        if (this.get_Wind()) {
            Day[3] = "ветреным.";
        } else {
            Day[3] = "безветренным.";
        }

        System.out.println("День был " + Day[0] + Day[1] + Day[2] + Day[3]);
    }

}
