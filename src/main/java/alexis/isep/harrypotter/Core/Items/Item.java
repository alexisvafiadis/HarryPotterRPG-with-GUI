package alexis.isep.harrypotter.Core.Items;

import alexis.isep.harrypotter.Core.Levels.Essentials.LevelMap;

public class Item {
    private LevelMap map;
    private char charTile;
    private Integer positionX;
    private Integer positionY;
    private ItemType itemType;

    public Item(ItemType itemType, int positionX, int positionY, LevelMap map, char charTile) {
        this.itemType = itemType;
        this.map = map;
        this.charTile = charTile;
        moveTo(positionX, positionY);
        //Decide damage multiplier of each item type.
    }

    public Item(ItemType itemType) {
        this.itemType = itemType;
    }

    public boolean moveTo(Integer positionX, Integer positionY) {
        if (this.positionX != null && this.positionY != null) {
            map.clearTile(this.positionX, this.positionY);
        }
        boolean isPositionAvailable = map.isPositionAvailable(positionX, positionY);
        if (isPositionAvailable) {
            this.positionX = positionX;
            this.positionY = positionY;
            map.setTile(positionX, positionY, charTile);
        }
        return isPositionAvailable;
    }

    public ItemType getItemType() {
        return itemType;
    }

    public double getPositionX() {
        return positionX;
    }

    public void setPositionX(Integer positionX) {
        this.positionX = positionX;
    }

    public double getPositionY() {
        return positionY;
    }

    public void setPositionY(Integer positionY) {
        this.positionY = positionY;
    }
}
