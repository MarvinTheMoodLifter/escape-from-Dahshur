public class Game 
{
    public static void main(String[] args) 
    {
        Pyramid pyramid = new Pyramid();
        Room room = pyramid.getRoom(0, 0);
        if (room != null) 
        {
            room.addItem(new Item("Sword", 10, 5, 20));
            System.out.println("Item added to room: " + room.hasItem());
            room.printItems();
        }
    }
}
