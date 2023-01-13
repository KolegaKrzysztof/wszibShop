package pl.edu.wszib.shop.model;

public non-sealed class Keyboard extends Product {

    private KeyboardType keyboardType;
    private boolean isWireless;
    private String color;

    public Keyboard() {
    }

    public Keyboard(String brand, String model, double price, int numberOfUnits,
                    KeyboardType keyboardType, boolean isWireless, String color) {
        super(brand, model, price, numberOfUnits);
        this.keyboardType = keyboardType;
        this.isWireless = isWireless;
        this.color = color;
    }

    public KeyboardType getKeyboardType() {
        return keyboardType;
    }

    public void setKeyboardType(KeyboardType keyboardType) {
        this.keyboardType = keyboardType;
    }

    public boolean isWireless() {
        return isWireless;
    }

    public void setWireless(boolean wireless) {
        isWireless = wireless;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return new StringBuilder("[Keyboard] ").append(super.toString())
                .append("\n\tkeyboard type: ").append(this.keyboardType)
                .append(" wireless: ").append(this.isWireless)
                .append(" color: ").append(this.color)
                .toString();
    }

    public enum KeyboardType {
        MEMBRANE,
        MECHANICAL
    }

}
