package entity;

public class Vector2 {
    public float x;
    public float y;

    public Vector2() { this.x = 0; this.y = 0; }
    public Vector2(float x, float y) { this.x = x; this.y = y; }

    public Vector2 add(Vector2 other) {
        this.x += other.x;
        this.y += other.y;
        return this; // Allows chaining: v1.add(v2).scale(2);
    }

    public Vector2 scale(float scalar) {
        this.x *= scalar;
        this.y *= scalar;
        return this;
    }

    // Magnitude / Length calculation
    public float length() {
        return (float) Math.sqrt(x * x + y * y);
    }

    // Normalize (turn into a unit vector of length 1)
    public Vector2 normalize() {
        float len = length();
        if (len != 0) {
            this.x /= len;
            this.y /= len;
        }
        return this;
    }
}
