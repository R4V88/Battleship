package model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Colors {
    ANSI_RED("\u001B[31m"),
    ANSI_BLUE("\u001B[34m"),
    ANSI_GREEN("\u001B[32m"),
    ANSI_YELLOW("\u001B[33m"),
    ANSI_PURPLE("\u001B[35m"),
    ANSI_WHITE("\u001b[37m");
    private String color;
}
