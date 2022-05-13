package model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Ship {
    Integer shipLength;
    List<Point> points;
    Boolean isSunk = false;

    public Ship(Integer shipLength, List<Point> points) {
        this.shipLength = shipLength;
        this.points = points;
    }
}
