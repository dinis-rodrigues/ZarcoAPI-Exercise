package com.example.ZarcoAPI.model;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
@Document(collection = "campaigns")
public class Campaign {
    @Id
    private String id;
    private double budget;
    private double paymentThreshold;
    private double cashbackPercentage;
}
