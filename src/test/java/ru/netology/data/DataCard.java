package ru.netology.data;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DataCard {
    private String numberCard;
    private String month;
    private String year;
    private String owner;
    private String cvc;
}