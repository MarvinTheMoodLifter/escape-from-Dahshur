package escape_from_dahshur.main;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Pyramid {
    private Room[][] gamemap;
    protected String pyramidDescription;
    protected String gameIntro;

    public Pyramid() {

        pyramidDescription =
                "███████ ███████  ██████  █████  ██████  ███████     ███████ ██████  " +
                        " ██████  ███    ███     ██████   █████  ██   ██ ███████ ██   ██ ██  " +
                        "  ██ ██████  "
                        + "\n"
                        + "██      ██      ██      ██   ██ ██   ██ ██          " +
                        "██      ██   ██ ██    ██ ████  ████     ██   ██ ██   ██ ██   " +
                        "██ ██      ██   ██ ██    ██ ██   ██ "
                        + "\n"
                        + "█████   ███████ ██      ███████ ██████  █████       █████   " +
                        "██████  ██    ██ ██ ████ ██     ██   " +
                        "██ ███████ ███████ ███████ ███████ ██    ██ ██████  "
                        + "\n"
                        + "██           ██ ██      ██   ██ ██      ██          ██      " +
                        "██   ██ ██    ██ ██  ██  ██     ██   " +
                        "██ ██   ██ ██   ██      ██ ██   ██ ██    ██ ██   ██ "
                        + "\n"
                        + "███████ ███████  ██████ ██   ██ ██      ███████     ██      ██   " +
                        "██  ██████  ██      ██     ██████  ██   ██ ██   ██ ███████ ██   " +
                        "██  ██████  ██   ██ "
                        + "\n"
                        + "                                                                 " +
                        "                                                                " +
                        "                    ";
        gameIntro =
                         "\r\n"
                        + "..."
                        + "\r\n"
                        + "..."
                        + "\r\n"
                        + "..."
                        + "\r\n"
                        + "..."
                        + "\r\n"
                        + "..."
                        + "\r\n"
                        + "vroooom \r\n" + //
                        "For hours now you have seen nothing but sand around you, the " +
                        "journey in the jeep has exhausted you, the heat is unbearable and " +
                        "the sunlight reflecting on the ground blinds you from all " +
                        "directions.\r\n" + //
                        "As you wipe the sweat from your forehead, you glimpse it in the " +
                        "distance, it's not long now, Dahshur.\r\n" + //
                        "After years of studies and expedition proposals you finally managed " +
                        "to get the expedition financed, a unique opportunity for an " +
                        "archaeologist like you to reveal the secrets of such a monumental " +
                        "complex.\r\n" + //
                        "\r\n" +         //
                        "Once you get out of the jeep you head to the area where the " +
                        "probable entrance was identified.\r\n" + //
                        "\r\n" +                                  //
                        "*svrrrrrsh* \r\n" +                      //
                        "\r\n" +                                  //
                        "After a bit of digging and lifting a large stone slab with a jack " +
                        "you find yourself in a room.\r\n" + //
                        "\r\n" +                             //
                        "\r\n" +                             //
                        "\r\n" +                             //
                        "After a few steps you hear a thud, the jack breaks and you find " +
                        "yourself inside, with no way out.\r\n" + //
                        "Panic starts to attack you, you feel a lump in your throat, it's " +
                        "certainly not pleasant knowing you're locked up alive in there, but " +
                        "after a few moments you manage to calm down. There will certainly " +
                        "be another exit, there always is in a necropolis like the one you " +
                        "are in, and above all there must be in a complex of this size.\r\n" + //
                        "";

        gamemap = new Room[3][3];
        for (int i = 0; i < gamemap.length; i++) {
            for (int j = 0; j < gamemap[i].length; j++) {
                gamemap[i][j] = new Room();
            }
        }
        wallInitializer();
        itemInizializer();
        NPCIinitializer();
        landscapeInitializer();
        roomDescInitializer();
    }

    public Room getRoom(int posx, int posy) {
        if (posx >= 0 && posx < gamemap.length && posy >= 0 &&
                posy < gamemap[posx].length) {
            return gamemap[posx][posy];
        } else {
            return null;
        }
    }
    public String getPyramidDesc() { return pyramidDescription; }
    public void setPyramidDesc(String desc) { pyramidDescription = desc; }
    public void printPyramidDesc() { System.out.println(pyramidDescription); }
    private void wallInitializer() {
        gamemap[0][0].setWall("down", true);
        gamemap[0][1].setWall("right", true);
        gamemap[0][2].setWall("left", true);
        gamemap[1][0].setWall("up", true);
        gamemap[2][0].setWall("right", true);
        gamemap[2][1].setWall("right", true);
        gamemap[2][1].setWall("left", true);
        gamemap[2][2].setWall("left", true);
    }
    private void itemInizializer() {

        gamemap[0][2].addItem(new Item("ankh", 10, 1, 0,
                "an ancient amulet of glittering gold, it " +
                        "used to be worshipped as the symbol of life"
                        + "\r\n"
                        + "     █ █  █ █     "
                        + "\n"
                        + "    █ ██████ █    "
                        + "\n"
                        + "   █  ██<>██  █   "
                        + "\n"
                        + "    █ ██████ █    "
                        + "\n"
                        + "     █ █  █ █     "
                        + "\n"
                        + "        ██        "
                        + "\n"));

        gamemap[1][2].addItem(new Item("key", 5, 1, 0,
                "a large metal key well decorated with " +
                        "incisions all over the handle and shaft"
                        + "\r\n"
                        + "   ██████|      "
                        + "\n"
                        + "  ██    ██|     "
                        + "\n"
                        + "   ██████|      "
                        + "\n"
                        + "     █|         "
                        + "\n"
                        + "     █|         "
                        + "\n"
                        + "     █|         "
                        + "\n"
                        + "     ███|       "
                        + "\n"
                        + "     █|         "
                        + "\n"
                        + "     ███|       "
                        + "\n"));

        gamemap[2][1].addItem(
                new Item("djed", 20, 1, 0,
                        "an ancient amulet, its symbol represents stability"
                                + "\r\n"
                                + "                   ████████|               "
                                + "\n"
                                + "                 ████|   █████|            "
                                + "\n"
                                + "                ████| ███| ████|           "
                                + "\n"
                                + "                 ████|   ████|             "
                                + "\n"
                                + "                   ██|                     "
                                + "\n"));

        gamemap[2][1].addItem(
                new Item("khol", 5, 1, 0,
                        "it kind of looks like a modern eyeliner, it's still used " +
                                "as eye makeup. The small wooden object is carvedin the " +
                                "shape of a diety an fully painted"
                                + "\r\n"
                                + "            ███|           "
                                + "\n"
                                + "             █|            "
                                + "\n"
                                + "             █|            "
                                + "\n"
                                + "             █|            "
                                + "\n"
                                + "            ███|           "
                                + "\n"
                                + "            ███|           "
                                + "\n"
                                + "           █████|          "
                                + "\n"
                                + "           █████|          "
                                + "\n"
                                + "           █████|          "
                                + "\n"
                                + "           █████|          "
                                + "\n"
                                + "           █████|          "
                                + "\n"));

        gamemap[0][2].addItem(
                new Item("mhyrr oil", 5, 5, 0,
                        "a small bottle of perfume, it's still full and emanates a " +
                                "pleasant smell"
                                + "\r\n"
                                + "           ████████████████████|            "
                                + "\n"
                                + "            ██████████████████|             "
                                + "\n"
                                + "           ████████████████████|            "
                                + "\n"
                                + "          ██████████████████████|           "
                                + "\n"
                                + "          ██████████████████████|           "
                                + "\n"
                                + "         ████████████████████████|          "
                                + "\n"
                                + "         ████████████████████████|          "
                                + "\n"
                                + "         ████████████████████████|          "
                                + "\n"
                                + "          ██████████████████████|           "
                                + "\n"
                                + "          ██████████████████████|           "
                                + "\n"
                                + "           ████████████████████|            "
                                + "\n"));

        gamemap[0][2].addItem(
                new Item("linen tunic", 10, 5, 1,
                        "just an average linen tunic, the only remarkable feature " +
                                "being the quality of its cloth"
                                + "\r\n"
                                + "█████████|███████████          ███████████|█████████"
                                + "\n"
                                + "█████████|████████████        ████████████|█████████"
                                + "\n"
                                + "█████████|██████████████|██|██████████████|█████████"
                                + "\n"
                                + "█████████|██████████████|██|██████████████|█████████"
                                + "\n"
                                + "         |██████████████|██|██████████████|         "
                                + "\n"
                                + "         |██████████████|██|██████████████|         "
                                + "\n"
                                + "         |██████████████|██|██████████████|         "
                                + "\n"
                                + "         |██████████████|██|██████████████|         "
                                + "\n"
                                + "         |██████████████|██|██████████████|         "
                                + "\n"
                                + "         |██████████████|██|██████████████|         "
                                + "\n"
                                + "         |██████████████|██|██████████████|         "
                                + "\n"
                                + "         |██████████████|██|██████████████|         "
                                + "\n"
                                + "         |██████████████|██|██████████████|         "
                                + "\n"
                                + "         |██████████████|██|██████████████|         "
                                + "\n"));

        gamemap[1][0].addItem(
                new Item("chisel and hammer", 1, 10, 15,
                        "Hammer and chisel, probably left behind by the last " +
                                "workers that worked on the decorations"
                                + "\r\n"
                                + "█████████|"
                                + "\n"
                                + "█████████|"
                                + "\n"
                                + "█████████|"
                                + "\n"
                                + "█████████|█████████████████████████|█████|████████|█"
                                + "\n"
                                + "█████████|█████████████████████████|█████|████████|█"
                                + "\n"
                                + "█████████|"
                                + "\n"
                                + "█████████|"
                                + "\n"
                                + "█████████|"
                                + "\n"
                                + "                                                  "
                                + "\n"
                                + "████|█████|█████████████"
                                + "\n"
                                + "████|█████|██████████████████████████████|██████|"
                                + "\n"
                                + "████|█████|█████████████"
                                + "\n"));

        gamemap[2][1].addItem(new Item(
                "razor", 1, 2, 10,
                "a sharp razor with a copper blade and a bone handle"
                        + "\r\n"
                        + "           |██████|███████████████████|                       " +
                        "                                               "
                        + "\n"
                        + "           " +
                        "|██████|██████████|" +
                        "██████████████████████████████████████████████████████████████" +
                        "██████|          "
                        + "\n"
                        + "           " +
                        "|██████|██████████|" +
                        "██████████████████████████████████████████████████████████████" +
                        "██████|          "
                        + "\n"
                        + "           " +
                        "|██████|" +
                        "███████████████████VVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVV" +
                        "VVVVVVVVVVVVVVVV            "
                        + "\n"
                        + "           |██████|███████|                                   " +
                        "                                               "
                        + "\n"
                        + "           |██████|███████|                                   " +
                        "                                               "
                        + "\n"
                        + "           |██████|███████|                                   " +
                        "                                               "
                        + "\n"
                        + "           |██████|███████|                                   " +
                        "                                               "
                        + "\n"
                        + "           |██████|███████|                                   " +
                        "                                               "
                        + "\n"
                        + "           |██████|███████|                                   " +
                        "                                               "
                        + "\n"
                        + "            |█████|██████|                                    " +
                        "                                               "
                        + "\n"
                        + "             |████|█████|                                     " +
                        "                                               "
                        + "\n"));

        gamemap[0][1].addItem(new Item(
                "book of the dead", 20, 2, 5,
                "an ancient papyrus filled with magical formulas whose original " +
                        "purpose was to aid the deceased on their journey to the afterlife"
                        + "\r\n"
                        + "           ███████████████████████|||         "
                        + "\n"
                        + "           █████     BOOK    █████|||         "
                        + "\n"
                        + "           █████      of     █████|||         "
                        + "\n"
                        + "           █████     DEAD    █████|||         "
                        + "\n"
                        + "           ███████████████████████|||         "
                        + "\n"
                        + "           ███████████████████████|||         "
                        + "\n"
                        + "           ███████████████████████|||         "
                        + "\n"
                        + "           ███████████████████████|||         "
                        + "\n"
                        + "           ███████████████████████|||         "
                        + "\n"
                        + "           ███████████████████████|||         "
                        + "\n"
                        + "           ███████████████████████|||         "
                        + "\n"
                        + "           ███████████████████████|||         "
                        + "\n"));

        gamemap[1][1].addItem(
                new Item("wine", 10, 10, 5,
                        "a big clay jar filled with wine"
                                + "\r\n"
                                + "                     ████████                     "
                                + "\n"
                                + "                       ████                       "
                                + "\n"
                                + "                       ████                       "
                                + "\n"
                                + "                       ████                       "
                                + "\n"
                                + "                       ████                       "
                                + "\n"
                                + "                    ██████████                    "
                                + "\n"
                                + "                  ██████████████                  "
                                + "\n"
                                + "              ██████████████████████              "
                                + "\n"
                                + "           ████████████████████████████           "
                                + "\n"
                                + "         |██████████████████████████████|         "
                                + "\n"
                                + "         |███  ████████████████████  ███|         "
                                + "\n"
                                + "         |███  ████████████████████  ███|         "
                                + "\n"
                                + "         |██   ████████████████████   ██|         "
                                + "\n"
                                + "         |██   ████████████████████   ██|         "
                                + "\n"
                                + "         |███  ████████████████████  ███|         "
                                + "\n"
                                + "         |████ ████████████████████ ████|         "
                                + "\n"
                                + "            ██████████████████████████            "
                                + "\n"
                                + "               ████████████████████               "
                                + "\n"));

        gamemap[1][1].addItem(
                new Item("meat", 3, 5, 1,
                        "rancid meat still sealed inside a clay jar, the smell is " +
                                "slowly leaking from the sealed container"
                                + "\r\n"
                                + "           |████████████████████████████|           "
                                + "\n"
                                + "           |████████████████████████████|          "
                                + "\n"
                                + "           |ZZZZZZZZZZZZZZZZZZZZZZZZZZZZ|           "
                                + "\n"
                                + "           |████████████████████████████|          "
                                + "\n"
                                + "           |████████████████████████████|           "
                                + "\n"
                                + "           |████████████████████████████|           "
                                + "\n"
                                + "           |████████████████████████████|           "
                                + "\n"
                                + "           |████████████████████████████|           "
                                + "\n"
                                + "           |████████████████████████████|           "
                                + "\n"
                                + "           |████████████████████████████|           "
                                + "\n"
                                + "           |████████████████████████████|           "
                                + "\n"));
    }
    private void NPCIinitializer() {
        gamemap[0][2].addEntity(
                new NPC("Osiris", 100, 35, 150, 2, 2,
                        "A creepy ancient mummy, the owner of the tomb. Now that you " +
                                "have desecrated its home it seeks vengance.",
                        false));
        gamemap[2][2].addEntity(
                new NPC("mummy", 50, 25, 150, 0, 2,
                        "A shadow stands in front of you, as you try to illuminate " +
                                "it with the torch you realize it is a humanoid being " +
                                "wrapped in bandages wearing typical Egyptian funeral " +
                                "clothes adorned with jewels and fine dyed linen. ",
                        false));
        gamemap[2][0].addEntity(new NPC(
                "lost explorer", 100, 10, 150, 0, 0,
                "A lost explorer that's now stuck inside of the ruins cursed to be " +
                        "unable to leave. Altough he speaks with you, you can barely tell " +
                        "wether or not he's still alive, he seems almost like a ghost.If you " +
                        "were to help him he would surely repay your kindness",
                true));
    }
    private void landscapeInitializer() {
        gamemap[1][2].addLandmark(new Landscape_Entity(
                "chest", 0, 1, 2,
                "a massive stone chestdecorated with several incisions on the " +
                        "surface, an odd lock you've never seen before prevents you from " +
                        "opening it. It seems you need some sort of key "
                        + "\r\n"
                        + "      ███|██████████████████████████████|███      "
                        + "\n"
                        + "     ████|██████████████████████████████|████     "
                        + "\n"
                        + "  ███████|██████████████████████████████|███████  "
                        + "\n"
                        + "█████████|██████████████████████████████|█████████"
                        + "\n"
                        + "█████████|██████████ █ ██ █ ████████████|█████████"
                        + "\n"
                        + "█████████|█████████ █      █ ███████████|█████████"
                        + "\n"
                        + "█████████|████████ ██      ██ ██████████|█████████"
                        + "\n"
                        + "=================================================="
                        + "\n"
                        + "█████████|█████████ █      █ ███████████|█████████"
                        + "\n"
                        + "█████████|██████████ █ ██ █ ████████████|█████████"
                        + "\n"
                        + "█████████|█████████████  ███████████████|█████████"
                        + "\n"
                        + "█████████|██████████████████████████████|█████████"
                        + "\n"
                        + "█████████|██████████████████████████████|█████████"
                        + "\n"
                        + "█████████|██████████████████████████████|█████████"
                        + "\n"));

        gamemap[1][1].addLandmark(new Landscape_Entity(
                "hole", 0, 1, 1,
                "a hole it looks deep, for some reason you feel drawn towards it. " +
                        "Peraphs it may not be a bad idea to jump in after all....."));
    }
    private void roomDescInitializer() {
        gamemap[2][0].setRoomDesc(
                "█████████|██████████████████████████████|█████████"
                        + "\n"
                        + "█████████|██████████████████████████████|█████████"
                        + "\n"
                        + "█████████|██████████████████████████████|█████████"
                        + "\n"
                        + "█████████|██████████████████████████████|█████████"
                        + "\n"
                        + "█████████|██████████████████████████████|█████████"
                        + "\n"
                        + "█████████|██████████████████████████████|█████████"
                        + "\n"
                        + "█████████|██████████████████████████████|█████████"
                        + "\n"
                        + "█████████|██████████████████████████████|█████████"
                        + "\n"
                        + "█████████|██████████████████████████████|█████████"
                        + "\n"
                        + "█████████|██████████████████████████████|█████████"
                        + "\n"
                        + "█████████|██████████████████████████████|█████████"
                        + "\n"
                        + "█████████|██████████████████████████████|█████████"
                        + "\n"
                        + "█████████|██████████        ████████████|█████████"
                        + "\n"
                        + "█████████|██████████        ████████████|█████████"
                        + "\n"
                        + "█████████|██████████        ████████████|█████████"
                        + "\n"
                        + "█████████|██████████        ████████████|█████████"
                        + "\r\n"
                        + "In front of you there is a room with shiny white sandstone walls, " +
                        "time seems not to have passed, on the ground the sand which has " +
                        "slowly infiltrated over the millennia through the small cracks is " +
                        "accumulating along the edges. The walls are all closed except for " +
                        "a passage in the north wall. The temperature is pleasant, it is " +
                        "certainly a good ten degrees cooler than outside.");
        gamemap[1][0].setRoomDesc(
                "█████████|██████████████████████████████|█████████"
                        + "\n"
                        + "█████████|██████████████████████████████|█████████"
                        + "\n"
                        + "█████████|██████████████████████████████|█████████"
                        + "\n"
                        + "█████████|██████████████████████████████|█████████"
                        + "\n"
                        + "█████████|██████████████████████████████|█████████"
                        + "\n"
                        + "█████████|██████████████████████████████|█████████"
                        + "\n"
                        + "█████████|██████████████████████████████|█████████"
                        + "\n"
                        + "█████████|██████████████████████████████|█████████"
                        + "\n"
                        + "█████████|██████████████████████████████|█████████"
                        + "\n"
                        + "█████████|██████████████████████████████|█████████"
                        + "\n"
                        + "█████████|██████████████████████████████|█████████"
                        + "\n"
                        + "█████████|██████████████████████████████|█████████"
                        + "\n"
                        + "█████████|██████████        ████████████|███   ███"
                        + "\n"
                        + "█████████|██████████        ████████████|███   ███"
                        + "\n"
                        + "█████████|██████████        ████████████|███ __███"
                        + "\n"
                        + "█████████|██████████        ████████████|███/  ███"
                        + "\r\n"
                        +
                        "The atmosphere is definitely different, the dimensions of this room " +
                        "are larger than the previous one. The walls are decorated with " +
                        "bas-reliefs, interrupted only by the small and narrow passages in " +
                        "the south and east walls. You are fascinated by the story they " +
                        "tell, they talk about a certain “Pdor, son of Kmer from the Instar " +
                        "tribe! Of the wasteland of the Sknir! One of the last seven essays! " +
                        "Purvurur, Garen, Pastararin, Giugiar, Taram, Fusciusc and Tarin”. ");
        gamemap[2][1].setRoomDesc(
                "█████████|██████████████████████████████|█████████"
                        + "\n"
                        + "█████████|██████████████████████████████|█████████"
                        + "\n"
                        + "█████████|██████████████████████████████|█████████"
                        + "\n"
                        + "█████████|██████████████████████████████|█████████"
                        + "\n"
                        + "█████████|██████████████████████████████|█████████"
                        + "\n"
                        + "█████████|██████████████████████████████|█████████"
                        + "\n"
                        + "█████████|██████████████████████████████|█████████"
                        + "\n"
                        + "█████████|██████████████████████████████|█████████"
                        + "\n"
                        + "█████████|██████████████████████████████|█████████"
                        + "\n"
                        + "█████████|██████████████████████████████|█████████"
                        + "\n"
                        + "█████████|██████████████████████████████|█████████"
                        + "\n"
                        + "█████████|██████████████████████████████|█████████"
                        + "\n"
                        + "█████████|██████████        ████████████|█████████"
                        + "\n"
                        + "█████████|██████████        ████████████|█████████"
                        + "\n"
                        + "█████████|██████████        ████████████|█████████"
                        + "\n"
                        + "█████████|██████████        ████████████|█████████"
                        + "\r\n"
                        + "How long have you been inside? By now it feels like you've been " +
                        "wandering for hours, the air gets heavier and heavier as you get " +
                        "deeper into the necropolis. A room opens before you completely " +
                        "devoid of decorations, the only things you notice are footprints " +
                        "in the sand.\r\n" + //
                        "You feel a shiver go up your whole body. The only entrance to the " +
                        "room is the one to the north where you entered.\r\n" + //
                        "");
        gamemap[1][1].setRoomDesc(
                "█████████|██████████████████████████████|█████████"
                        + "\n"
                        + "█████████|██████████████████████████████|█████████"
                        + "\n"
                        + "█████████|██████████████████████████████|█████████"
                        + "\n"
                        + "█████████|██████████████████████████████|█████████"
                        + "\n"
                        + "█████████|██████████████████████████████|█████████"
                        + "\n"
                        + "█████████|██████████████████████████████|█████████"
                        + "\n"
                        + "█████████|██████████████████████████████|█████████"
                        + "\n"
                        + "█████████|██████████████████████████████|█████████"
                        + "\n"
                        + "█████████|██████████████████████████████|█████████"
                        + "\n"
                        + "█████████|██████████████████████████████|█████████"
                        + "\n"
                        + "█████████|██████████████████████████████|█████████"
                        + "\n"
                        + "█████████|██████████████████████████████|█████████"
                        + "\n"
                        + "███   ███|██████████        ████████████|███   ███"
                        + "\n"
                        + "███   ███|██████████        ████████████|███   ███"
                        + "\n"
                        + "███__ ███|██████████        ████████████|███ __███"
                        + "\n"
                        + "███ \\███|██████████        ████████████|███/  ███"
                        + "\r\n"
                        + "This room has a strange shape, the walls are tapered downwards, " +
                        "and each have a passage, the ceilings are very very high and " +
                        "decorated with painted geometric elements, in the center of the " +
                        "room there is a strange hole, surrounded by beautiful painted " +
                        "tiles of bright colors.");
        gamemap[0][0].setRoomDesc(
                "█████████|██████████████████████████████|█████████"
                        + "\n"
                        + "█████████|██████████████████████████████|█████████"
                        + "\n"
                        + "█████████|██████████████████████████████|█████████"
                        + "\n"
                        + "█████████|██████████████████████████████|█████████"
                        + "\n"
                        + "█████████|██████████████████████████████|█████████"
                        + "\n"
                        + "█████████|██████                  ██████|█████████"
                        + "\n"
                        + "█████████|██████                  ██████|█████████"
                        + "\n"
                        + "█████████|██████                  ██████|█████████"
                        + "\n"
                        + "█████████|██████                  ██████|█████████"
                        + "\n"
                        + "█████████|██████                  ██████|█████████"
                        + "\n"
                        + "█████████|██████                  ██████|█████████"
                        + "\n"
                        + "█████████|██████                  ██████|█████████"
                        + "\n"
                        + "█████████|██████                  ██████|█████████"
                        + "\n"
                        + "█████████|██████                  ██████|█████████"
                        + "\n"
                        + "█████████|██████                  ██████|█████████"
                        + "\n"
                        + "█████████|██████                  ██████|█████████"
                        + "\n"
                        + "█████████|██████                  ██████|█████████"
                        + "\n"
                        + "█████████|██████                  ██████|█████████"
                        + "\n"
                        + "█████████|██████                  ██████|█████████"
                        + "\r\n"
                        + "In front of you stands an immense black door filled with " +
                        "hieroglyphics.\n  The contrast between the walls and the door is " +
                        "beautiful.\nWith your torch you struggle to illuminate the entire " +
                        "room.\n The room around you is bare as if to underline the " +
                        "importance of the door  ");
        gamemap[2][2].setRoomDesc(
                "█████████|██████████████████████████████|█████████"
                        + "\n"
                        + "█████████|██████████████████████████████|█████████"
                        + "\n"
                        + "█████████|██████████████████████████████|█████████"
                        + "\n"
                        + "█████████|██████████████████████████████|█████████"
                        + "\n"
                        + "█████████|██████████████████████████████|█████████"
                        + "\n"
                        + "█████████|██████████████████████████████|█████████"
                        + "\n"
                        + "█████████|██████████████████████████████|█████████"
                        + "\n"
                        + "█████████|██████████████████████████████|█████████"
                        + "\n"
                        + "█████████|██████████████████████████████|█████████"
                        + "\n"
                        + "█████████|██████████████████████████████|█████████"
                        + "\n"
                        + "█████████|██████████████████████████████|█████████"
                        + "\n"
                        + "█████████|██████████████████████████████|█████████"
                        + "\n"
                        + "█████████|██████████        ████████████|█████████"
                        + "\n"
                        + "█████████|██████████        ████████████|█████████"
                        + "\n"
                        + "█████████|██████████        ████████████|█████████"
                        + "\n"
                        + "█████████|██████████        ████████████|█████████"
                        + "\r\n"
                        + "*rustling* After the first few steps, you freeze, you hear a " +
                        "sound, a rustling, you hold your breath, there isn't much in the " +
                        "room where you are, but you glimpse a silhouette in one of the " +
                        "corners, hidden in the dim light, you scared, but doesn't seem to " +
                        "move. You try to illuminate the walls of the room, the only " +
                        "passage is the one you came from.");
        gamemap[0][2].setRoomDesc(
                "█████████|██████████████████████████████|█████████"
                        + "\n"
                        + "█████████|██████████████████████████████|█████████"
                        + "\n"
                        + "█████████|██████████████████████████████|█████████"
                        + "\n"
                        + "█████████|██████████████████████████████|█████████"
                        + "\n"
                        + "█████████|██████████████████████████████|█████████"
                        + "\n"
                        + "█████████|██████████████████████████████|█████████"
                        + "\n"
                        + "█████████|██████████████████████████████|█████████"
                        + "\n"
                        + "█████████|██████████████████████████████|█████████"
                        + "\n"
                        + "█████████|██████████████████████████████|█████████"
                        + "\n"
                        + "█████████|██████████████████████████████|█████████"
                        + "\n"
                        + "█████████|██████████████████████████████|█████████"
                        + "\n"
                        + "█████████|██████████████████████████████|█████████"
                        + "\n"
                        + "█████████|██████████        ████████████|█████████"
                        + "\n"
                        + "█████████|██████████        ████████████|█████████"
                        + "\n"
                        + "█████████|██████████        ████████████|█████████"
                        + "\n"
                        + "█████████|██████████        ████████████|█████████"
                        + "\r\n"
                        + "you feel surrounded, around you dozen of statues of guards fill " +
                        "the walls.\n In the center of the room stant a series of objects " +
                        "stacked on top of one another, giving the idea of being gift for " +
                        "the dead or the divinities, left there for millenia.\n This room " +
                        "is a dead end, you can only go back.");
        gamemap[1][2].setRoomDesc(
                "█████████|██████████████████████████████|█████████"
                        + "\n"
                        + "█████████|██████████████████████████████|█████████"
                        + "\n"
                        + "█████████|██████████████████████████████|█████████"
                        + "\n"
                        + "█████████|██████████████████████████████|█████████"
                        + "\n"
                        + "█████████|██████████████████████████████|█████████"
                        + "\n"
                        + "█████████|██████████████████████████████|█████████"
                        + "\n"
                        + "█████████|██████████████████████████████|█████████"
                        + "\n"
                        + "█████████|██████████████████████████████|█████████"
                        + "\n"
                        + "█████████|██████████████████████████████|█████████"
                        + "\n"
                        + "█████████|██████████████████████████████|█████████"
                        + "\n"
                        + "█████████|██████████████████████████████|█████████"
                        + "\n"
                        + "█████████|██████████████████████████████|█████████"
                        + "\n"
                        + "███   ███|██████████        ████████████|███   ███"
                        + "\n"
                        + "███   ███|██████████        ████████████|███   ███"
                        + "\n"
                        + "███__ ███|██████████        ████████████|███ __███"
                        + "\n"
                        + "███ \\███|██████████        ████████████|███/  ███"
                        + "\r\n"
                        + "When you enter this room you see exactly in the center a huge " +
                        "wooden chest and metal plates, on the walls a series of religious " +
                        "themed frescoes on the \"cycle of life and the afterlife\". " +
                        "Following the walls with your gaze you notice two other passages " +
                        "in addition to the one you came from. One to the south and one to " +
                        "the north.");
        gamemap[0][1].setRoomDesc(
                "█████████|██████████████████████████████|█████████"
                        + "\n"
                        + "█████████|██████████████████████████████|█████████"
                        + "\n"
                        + "█████████|██████████████████████████████|█████████"
                        + "\n"
                        + "█████████|██████████████████████████████|█████████"
                        + "\n"
                        + "█████████|██████████████████████████████|█████████"
                        + "\n"
                        + "█████████|██████████████████████████████|█████████"
                        + "\n"
                        + "█████████|██████████████████████████████|█████████"
                        + "\n"
                        + "█████████|██████████████████████████████|█████████"
                        + "\n"
                        + "█████████|██████████████████████████████|█████████"
                        + "\n"
                        + "█████████|██████████████████████████████|█████████"
                        + "\n"
                        + "█████████|██████████████████████████████|█████████"
                        + "\n"
                        + "█████████|██████████████████████████████|█████████"
                        + "\n"
                        + "███   ███|██████████        ████████████|█████████"
                        + "\n"
                        + "███   ███|██████████        ████████████|█████████"
                        + "\n"
                        + "███__ ███|██████████        ████████████|█████████"
                        + "\n"
                        + "███ \\███|██████████        ████████████|█████████"
                        + "\r\n"
                        + "a room totally bare of decorations, on the walls a series of " +
                        "niches containing several papyrus rolls, just looking at them you " +
                        "can feel them crumblin with age.\nEverything is fossilized in a " +
                        "bygone era, it seems like so little has passed since they were " +
                        "placed there, but it's just an illusion, you would just have to " +
                        "touch them for them to turn to dust with that idea of a distant " +
                        "era.\nFollowing the walls you notice two passages, the one where " +
                        "you came from and an opening on the west wall.");
    }
    public void describeRoom(int x, int y) {
        Room room = getRoom(x, y);
        if (room != null) {
            room.describeEntities();
            if (room.hasItem()) {
                System.out.print("You can see ");
                int count = room.getRoomItems().size();
                for (int i = 0; i < count; i++) {
                    Item item = room.getRoomItems().get(i);
                    if (i > 0 && i == count - 1) {
                        System.out.print(" and a " + item.getName());
                    } else if (i > 0) {
                        System.out.print(", a " + item.getName());
                    } else {
                        System.out.print("a " + item.getName());
                    }
                }
                System.out.println(".");
            }
        } else {
            System.out.println("No room found at the specified coordinates.");
        }
    }

    // Get gamemap
    public Room[][] getGamemap() { return gamemap; }

    public String toJson() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(this);
    }

    public static Pyramid fromJson(String json) {
        Gson gson = new Gson();
        return gson.fromJson(json, Pyramid.class);
    }

    public static Pyramid fromJsonFile(String filePath) throws IOException {
        Gson gson = new Gson();
        FileReader reader = new FileReader(filePath);
        Pyramid pyramid = gson.fromJson(reader, Pyramid.class);
        reader.close();
        return pyramid;
    }

    public void updateFrom(Pyramid other) {
        this.pyramidDescription = other.getPyramidDesc();
        this.gamemap = other.getGamemap();
    }

    public void printRoomLayout()
    {
        System.out.println("Room Layout:");
        for (int i = 0; i < gamemap.length; i++)
        {
            for (int j = 0; j < gamemap[i].length; j++)
            {
                if (gamemap[i][j] != null) { System.out.print("[" + i + "," + j + "]"); }
                else { System.out.print("[   ]"); }
                if (j < gamemap[i].length - 1) { System.out.print(" "); }
            }
            System.out.println();
        }
    }
}
