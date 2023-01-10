package pl.edu.wszib.shop.model;

public non-sealed class Laptop extends Product {

    private double inches;
    private String resolution;
    private String processor;
    private String graphicsCard;
    private int ramGB;
    private boolean hasSystem;


    public Laptop(){}

    public Laptop(String brand, String model, double price, int numberOfUnits,
                  double inches, String resolution, String processor,
                  String graphicsCard, int ramGB, boolean hasSystem) {
        super(brand, model, price, numberOfUnits);
        this.inches = inches;
        this.resolution = resolution;
        this.processor = processor;
        this.graphicsCard = graphicsCard;
        this.ramGB = ramGB;
        this.hasSystem = hasSystem;
    }


    public double getInches() {
        return inches;
    }

    public void setInches(double inches) {
        this.inches = inches;
    }

    public String getResolution() {
        return resolution;
    }

    public void setResolution(String resolution) {
        this.resolution = resolution;
    }

    public String getProcessor() {
        return processor;
    }

    public void setProcessor(String processor) {
        this.processor = processor;
    }

    public String getGraphicsCard() {
        return graphicsCard;
    }

    public void setGraphicsCard(String graphicsCard) {
        this.graphicsCard = graphicsCard;
    }

    public int getRamGB() {
        return ramGB;
    }

    public void setRamGB(int ramGB) {
        this.ramGB = ramGB;
    }

    public boolean isHasSystem() {
        return hasSystem;
    }

    public void setHasSystem(boolean hasSystem) {
        this.hasSystem = hasSystem;
    }


    @Override
    public String toString() {
        return new StringBuilder("[Laptop] ").append(super.toString())
                .append("\n\tinches: ").append(this.inches)
                .append(" resolution: ").append(resolution)
                .append(" processor: ").append(processor)
                .append(" graphics card: ").append(graphicsCard)
                .append( "RAM (GB): ").append(ramGB)
                .toString();
    }
}
