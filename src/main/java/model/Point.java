package model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Point {
    int x;
    int y;
    boolean isHit;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
