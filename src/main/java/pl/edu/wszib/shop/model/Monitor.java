package pl.edu.wszib.shop.model;

public non-sealed class Monitor extends Product{

    private double inches;
    private String resolution;
    private int hz;
    private String matrixType;

    public Monitor() {
    }

    public Monitor(String brand, String model, double price, int numberOfUnits,
                   double inches,String resolution, int hz, String matrixType) {
        super(brand, model, price, numberOfUnits);
        this.inches = inches;
        this.resolution = resolution;
        this.hz = hz;
        this.matrixType = matrixType;
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

    public int getHz() {
        return hz;
    }

    public void setHz(int hz) {
        this.hz = hz;
    }

    public String getMatrixType() {
        return matrixType;
    }

    public void setMatrixType(String matrixType) {
        this.matrixType = matrixType;
    }

    @Override
    public String toString() {
        return new StringBuilder("[Monitor] ").append(super.toString())
                .append("\n\tinches: ").append(this.inches)
                .append(" resolution: ").append(this.resolution)
                .append(" hz: ").append(this.hz)
                .append(" matrix type: ").append(this.matrixType)
                .toString();
    }
}
