package by.pisl.lab3.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * @version 1.0.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class QuestionWrap {
    private Question question;

    private float stat1;

    private float stat2;

    private float stat3;
}
