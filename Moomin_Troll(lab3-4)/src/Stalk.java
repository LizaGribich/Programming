public class Stalk extends Nature {

    public Stalk(Color Color_state) {
        super(Color_state);
    }

    @Override
    public void try_get_done(Weather w, Troll t) {
        System.out.println(t.get_Name() + " увидел высокий стебель.");
        if (w.get_Rain()) {
            System.out.println("Похоже, из-за дождя почва стала мягкой, поэтому получилось выдернуть этот стебель!");
            t.add_mood();
        } else if (t.pull()) {
            System.out.println("\"Мне хватило сил!\" - прокричал " + t.get_Name() + ", выдернув его из земли.");
            t.add_mood();
        } else {
            System.out.println("Стебель упругий, словно резиновый, и не выдергивается из земли!\n" + "Возможно, в другой раз получится...");
            t.rem_mood();
            if (t.get_Clothes() == Clothes.HAT) {
                System.out.println("Как бы невзначай он обвился он вокруг шляпы и снял ее.");
                t.set_Clothes(Clothes.NOTHING);
                t.rem_mood();
            }
        }
    }
}
