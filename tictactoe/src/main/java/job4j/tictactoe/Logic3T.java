package job4j.tictactoe;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
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
        return this.fillBy(Figure3T::hasMarkX, 0, 0, 1, 0) ||
                this.fillBy(Figure3T::hasMarkX, 0, 0, 0, 1) ||
                this.fillBy(Figure3T::hasMarkX, 0, 0, 1, 1) ||
                this.fillBy(Figure3T::hasMarkX, 0, 1, 1, 0) ||
                this.fillBy(Figure3T::hasMarkX, 1, 0, 0, 1) ||
                this.fillBy(Figure3T::hasMarkX, this.table.length - 1, 0, 0, 1) ||
                this.fillBy(Figure3T::hasMarkX, 0, this.table.length - 1, 1, 0) ||
                this.fillBy(Figure3T::hasMarkX, this.table.length - 1, 0, -1, 1);
    }

    /**
     * Method realizes serching of winner O
     *
     * @return - true or false
     */
    public boolean isWinnerO() {
        return this.fillBy(Figure3T::hasMarkO, 0, 0, 1, 0) ||
                this.fillBy(Figure3T::hasMarkO, 0, 0, 0, 1) ||
                this.fillBy(Figure3T::hasMarkO, 0, 0, 1, 1) ||
                this.fillBy(Figure3T::hasMarkO, 0, 1, 1, 0) ||
                this.fillBy(Figure3T::hasMarkO, 1, 0, 0, 1) ||
                this.fillBy(Figure3T::hasMarkO, this.table.length - 1, 0, 0, 1) ||
                this.fillBy(Figure3T::hasMarkO, 0, this.table.length - 1, 1, 0) ||
                this.fillBy(Figure3T::hasMarkO, this.table.length - 1, 0, -1, 1);
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
     * Method has realize serching of sequence
     *
     * @param predicate - performs check of mark
     * @param startX    - start position
     * @param startY    - end position
     * @param deltaX    - move
     * @param deltaY    - move
     * @return - true or false
     */
    public boolean fillBy(Predicate<Figure3T> predicate, int startX, int startY, int deltaX, int deltaY) {
        boolean result = true;
        for (int index = 0; index != this.table.length; index++) {
            Figure3T cell = this.table[startX][startY];
            startX += deltaX;
            startY += deltaY;
            if (!predicate.test(cell)) {
                result = false;
                break;
            }
        }
        return result;
    }
}