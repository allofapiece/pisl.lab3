package by.pisl.lab3.repository.preprocess;

import lombok.AllArgsConstructor;
import org.apache.commons.text.StringSubstitutor;

import java.util.Map;

/**
 * @version 1.0.0
 */
@AllArgsConstructor
public class TableQueryPreprocess implements QueryPreprocess {
    private String tableName;

    @Override
    public String preprocess(String query) {
        return StringSubstitutor.replace(query, Map.of("tableName", tableName), "{", "}");
    }
}
