package pntc.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseStringPairsListObject {

    private List<StringItem> stringsPairs = new ArrayList<>();
}
