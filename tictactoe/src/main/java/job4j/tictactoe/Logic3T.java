package job4j.tictactoe;


import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class Logic3T {
    /**
     * Map of table
     */
    private final Figure3T[][] table;

    public Logic3T(Figure3T[][] table) {
        this.table = table;
    }

    /**
     * Method realizes serching of winner X
     *
     * @return - true or false
     */
    public boolean isWinnerX() {
        return hasCombination('x');
    }

    /**
     * Method realizes serching of winner O
     *
     * @return - true or false
     */
    public boolean isWinnerO() {
        return false;
    }

    /**
     * Method realizes serching of gaps in table
     *
     * @return
     */
    public boolean hasGap() {
        return Arrays.stream(this.table)
                .flatMap(e -> Stream.of(e))
                .anyMatch(e -> !e.hasMarkO() && !e.hasMarkX());
    }

    /**
     * Method has realizes searching of winner 4 combinations of turns
     *
     * @return - true or false
     */
    public boolean hasCombination(char figure) {
        List<Boolean> conditions = List.of(Arrays.stream(this.table)
                .anyMatch(e -> Stream.of(e).allMatch(i -> figure == 'x' ? i.hasMarkX() : i.hasMarkO())));

        return true;
    }
}