package ru.job4j;

import org.junit.Assert;
import org.junit.Test;
import ru.job4j.chess.Logic;
import ru.job4j.chess.firuges.Cell;
import ru.job4j.chess.firuges.Figure;
import ru.job4j.chess.firuges.black.BishopBlack;
import ru.job4j.chess.firuges.black.PawnBlack;

public class LogicTest {
    @Test
    public void MoveTest() {
        Figure bishopBlack = new BishopBlack(Cell.D8);
        Figure pawnBlack = new PawnBlack(Cell.D7);
        Logic logic = new Logic();
        logic.add(bishopBlack);
        logic.add(pawnBlack);
        boolean out = logic.move(Cell.D8, Cell.G5);
        Assert.assertTrue(out);
    }

    @Test
    public void notMoveTest() {
        Figure bishopBlack = new BishopBlack(Cell.D8);
        Figure pawnBlack = new PawnBlack(Cell.E7);
        Logic logic = new Logic();
        logic.add(bishopBlack);
        logic.add(pawnBlack);
        boolean out = logic.move(Cell.D8, Cell.G5);
        Assert.assertFalse(out);
    }
}
