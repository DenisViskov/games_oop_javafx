package ru.job4j.chess.firuges.black;

import ru.job4j.chess.firuges.Cell;
import ru.job4j.chess.firuges.Figure;

/**
 * Класс реализует функционал фигуры Слона в игре "Шахматы"
 *
 * @author Denis Viskov
 * @version 1.0
 * @since 18.12.19
 */
public class BishopBlack implements Figure {
    /**
     * Позиция
     */
    private final Cell position;

    public BishopBlack(final Cell position) {
        this.position = position;
    }

    /**
     * @return - возвращает позицию объекта
     */
    @Override
    public Cell position() {
        return this.position;
    }

    /**
     * Метод реализует функционал движения слона из одной точки в другую.
     * Выполняет проверку правильности переданных параметров, в случае неверно переданных данных
     * выбрасывает исключение IllegalStateException
     *
     * @param source - начало движения
     * @param dest   - конечная точка
     * @return - массив передвижений
     */
    @Override
    public Cell[] way(Cell source, Cell dest) {
        if (!isDiagonal(source, dest)) {
            throw new IllegalStateException(
                    String.format("Could not way by diagonal from %s to %s", source, dest)
            );
        }
        int size = source.y - dest.y;
        Cell[] steps = new Cell[size];
        for (int index = 0; index < size; index++) {
            steps[index] = index == 0 ? this.searchPosition(source, dest) : this.searchPosition(steps[index - 1], dest);
        }
        return steps;
    }

    /**
     * Выполняет проверку движения слона
     *
     * @param source - начало движения
     * @param dest   - конечная точка
     * @return - true или false
     */
    public boolean isDiagonal(Cell source, Cell dest) {
        boolean result = false;
        String turn = this.turnMove(source, dest);
        Cell compare = source;
        while (result == false) {
            if (turn.equals("Right")) {
                compare = this.searchPosition(compare, dest);
                if (compare == null) {
                    break;
                }
                result = compare.equals(dest) ? true : false;
            } else if (turn.equals("Left")) {
                compare = this.searchPosition(compare, dest);
                if (compare == null) {
                    break;
                }
                result = compare.equals(dest) ? true : false;
            } else {
                break;
            }
        }
        return result;
    }

    /**
     * Реализует копию фигуры
     *
     * @param dest - координаты
     * @return - новую копию фигуры
     */
    @Override
    public Figure copy(Cell dest) {
        return new BishopBlack(dest);
    }

    /**
     * Метод выполняет проверку направления движения фигуры слона
     *
     * @param source - начало движения
     * @param dest   - конец движения
     * @return - строку Right или Left
     */
    private String turnMove(Cell source, Cell dest) {
        String upRight = dest.x > source.x && dest.y < source.y ? "Right" : "false";
        String upLeft = dest.x < source.x && dest.y < source.y ? "Left" : "false";
        return upRight.equals("Right") ? upRight : upLeft;
    }

    /**
     * Метод производит поиск позиции объекта по его координатам
     *
     * @param source - начальная позиция
     * @param dest   - конечная позиция
     * @return - найденный объект
     */
    private Cell searchPosition(Cell source, Cell dest) {
        Cell result = null;
        int deltaX = 1;
        int deltaY = 1;
        int x = source.x;
        int y = source.y;
        if (this.turnMove(source, dest).equals("Right")) {
            x += deltaX;
            y -= deltaY;
            result = Cell.findBy(x, y);
        } else if (this.turnMove(source, dest).equals("Left")) {
            x -= deltaX;
            y -= deltaY;
            result = Cell.findBy(x, y);
        }
        return result;
    }
}
