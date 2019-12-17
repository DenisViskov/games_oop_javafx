package ru.job4j.black;

import org.junit.Assert;
import org.junit.Test;
import ru.job4j.chess.firuges.Cell;
import ru.job4j.chess.firuges.Figure;
import ru.job4j.chess.firuges.black.BishopBlack;

public class BishopBlackTest {

    @Test
    public void positionTest() {
        BishopBlack bishopBlack = new BishopBlack(Cell.A1);
        Cell expected = Cell.A1;
        Cell out = bishopBlack.position();
        Assert.assertEquals(expected, out);
    }

    @Test
    public void copyTest() {
        Figure expected = new BishopBlack(Cell.A1);
        Figure out = expected.copy(expected.position());
        Assert.assertEquals(expected.position(), out.position());
    }

    @Test
    public void wayTest() {
        BishopBlack bishopBlack = new BishopBlack(Cell.C1);
        Cell[] out = bishopBlack.way(Cell.C1, Cell.G5);
        Cell[] expected = new Cell[]{
                Cell.D2, Cell.E3, Cell.F4, Cell.G5
        };
        Assert.assertEquals(expected, out);
    }
}
