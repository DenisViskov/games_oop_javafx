package ru.job4j.black;

import org.junit.Assert;
import org.junit.Test;
import ru.job4j.chess.firuges.Cell;
import ru.job4j.chess.firuges.Figure;
import ru.job4j.chess.firuges.black.BishopBlack;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

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
        Cell[] out = bishopBlack.way(Cell.D8, Cell.G5);
        Cell[] expected = new Cell[]{
                Cell.E7, Cell.F6, Cell.G5
        };
        Assert.assertEquals(expected, out);
    }

    @Test
    public void wayThrowExeptionTest() {
        boolean result = false;
        IllegalStateException expected = new IllegalStateException
                (String.format("Could not way by diagonal from %s to %s", Cell.C1, Cell.G7));
        try {
            new BishopBlack(Cell.A1).way(Cell.C1, Cell.G7);
        } catch (IllegalStateException out) {
            if (expected.toString().equals(out.toString())) {
                result = true;
            }
        }
        Assert.assertTrue(result);
    }

    @Test
    public void isDiagonalTrueTest() {
        Cell source = Cell.D8;
        Cell dest = Cell.G5;
        boolean out = new BishopBlack(Cell.A1).isDiagonal(source, dest);
        Assert.assertTrue(out);
    }

    @Test
    public void isDiagonalFalseTest() {
        Cell source = Cell.C1;
        Cell dest = Cell.G7;
        boolean out = new BishopBlack(Cell.A1).isDiagonal(source, dest);
        Assert.assertFalse(out);
    }
}
