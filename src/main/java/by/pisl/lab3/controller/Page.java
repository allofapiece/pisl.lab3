package by.pisl.lab3.controller;

import lombok.Builder;
import lombok.Data;

/**
 * @version 1.0.0
 */
@Data
@Builder
public class Page {
    private String page;

    private boolean redirect;
}
