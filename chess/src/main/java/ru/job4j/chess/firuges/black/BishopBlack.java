package ru.job4j.chess.firuges.black;

import ru.job4j.chess.firuges.Cell;
import ru.job4j.chess.firuges.Figure;

/**
 * @author Petr Arsentev (parsentev@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class BishopBlack implements Figure {
    private final Cell position;

    public BishopBlack(final Cell position) {
        this.position = position;
    }

    @Override
    public Cell position() {
        return this.position;
    }

    @Override
    public Cell[] way(Cell source, Cell dest) {
        if (!isDiagonal(source, dest)) {
            throw new IllegalStateException(
                    String.format("Could not way by diagonal from %s to %s", source, dest)
            );
        }
        int size = dest.y - source.y;
        Cell[] steps = new Cell[size];
        int deltaX = 1;
        int deltaY = 1;
        boolean upRight = dest.x > source.x && dest.y > source.y;
        boolean upLeft = dest.x < source.x && dest.y > source.y;
        int x = source.x;
        int y = source.y;
        for (int index = 0; index < size; index++) {
            if (upRight) {
                x += deltaX;
                y += deltaY;
                steps[index] = Cell.findBy(x, y);
            } else if (upLeft) {
                x -= deltaX;
                y += deltaY;
                steps[index] = Cell.findBy(x, y);
            }
        }
        return steps;
    }

    public boolean isDiagonal(Cell source, Cell dest) {
        //TODO check diagonal
        return false;
    }

    @Override
    public Figure copy(Cell dest) {
        return new BishopBlack(dest);
    }
}
