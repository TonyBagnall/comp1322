package week11_examples.lecture11_1.enum_examples;

public enum Planet {
    MERCURY(3.301e23, 2_439.7),
    VENUS(4.867e24, 6_051.8),
    EARTH(5.972e24, 6_371.0),
    MARS(6.417e23, 3_389.5),
    JUPITER(1.898e27, 69_911),
    SATURN(5.683e26, 58_232),
    URANUS(8.681e25, 25_362),
    NEPTUNE(1.024e26, 24_622);

    private final double massKg;
    private final double radiusKm;

    Planet(double massKg, double radiusKm) {
        this.massKg = massKg;
        this.radiusKm = radiusKm;
    }

    public double getMassKg() {
        return massKg;
    }

    public double getRadiusKm() {
        return radiusKm;
    }

    public double surfaceGravity() {
        final double G = 6.67430e-11;
        double radiusMetres = radiusKm * 1000;
        return G * massKg / (radiusMetres * radiusMetres);
    }
}
