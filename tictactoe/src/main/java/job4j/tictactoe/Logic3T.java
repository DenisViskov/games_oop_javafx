package job4j.tictactoe;


import java.util.ArrayList;
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
        return hasCombination('o');
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
                        .anyMatch(e -> Stream.of(e)
                                .allMatch(i -> figure == 'x' ? i.hasMarkX() : i.hasMarkO())),
                Arrays.stream(tableChanger("vertical"))
                        .anyMatch(e -> Stream.of(e)
                                .allMatch(i -> figure == 'x' ? i.hasMarkX() : i.hasMarkO())),
                Arrays.stream(tableChanger("diagonal"))
                        .anyMatch(e -> Stream.of(e)
                                .allMatch(i -> figure == 'x' ? i.hasMarkX() : i.hasMarkO())));
        return conditions.contains(true) ? true : false;
    }

    /**
     * Method is an addon for hasCombination method
     * He realizes operations of changes table on "vertical" or "diagonal"
     *
     * @param var - "vertical" or "diagonal"
     * @return - specific array of parametres
     */
    private Figure3T[][] tableChanger(String var) {
        Figure3T[][] result = new Figure3T[this.table.length][this.table.length];
        if (var.equals("vertical")) {
            for (int i = 0; i < this.table.length; i++) {
                for (int j = 0; j < this.table.length; j++) {
                    result[i][j] = this.table[j][i];
                }
            }
        } else if (var.equals("diagonal")) {
            return new Figure3T[][]{{this.table[0][0],
                    this.table[1][1],
                    this.table[2][2]},
                    {this.table[2][0],
                            this.table[1][1],
                            this.table[0][2]}};
        } else {
            throw new IllegalArgumentException("Uncorrect parametres");
        }
        return result;
    }
}