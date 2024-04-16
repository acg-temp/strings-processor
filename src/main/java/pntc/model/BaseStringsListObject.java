package pntc.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
public class BaseStringsListObject {

    private List<String> stringsList = new ArrayList<>();
}
