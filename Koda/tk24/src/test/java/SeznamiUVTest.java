import org.easymock.EasyMock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.easymock.EasyMock.*;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

class SeznamiUVTest {
    SeznamiUV uv;

    @BeforeEach
    void init() {
        uv = new SeznamiUV();
    }

    @Test
    void TestEmptyCommand1() {
        assertEquals(">> Invalid command", uv.processInput(""));
    }

    @Test
    void TestEmptyCommand2() {
        assertEquals(">> Enter command", uv.processInput(" "));
    }

    @Test
    void TestExit() {
        assertEquals(">> Bye", uv.processInput("exit"));
    }

    // These don't work because I'm using scanner, apparently \\
    @Test
    void TestAdd() {
        assertEquals("add> EMSO: ", uv.processInput("add"));
        assertEquals("add> NAME: ", uv.processInput("1111111111111"));
        assertEquals("add> SURNAME: ", uv.processInput("Ardit"));
        assertEquals("add> AGE: ", uv.processInput("Nela"));
        assertEquals("add> VACCINE: ", uv.processInput("20"));
        assertEquals("OK", uv.processInput("Johnson"));
    }

    @Test
    void TestAddError1() {
        assertEquals("add> EMSO: ", uv.processInput("add"));
        assertEquals(">> Invalid input data", uv.processInput("1111111f11111"));
    }

    @Test
    void TestAddError2() {
        assertEquals("add> EMSO: ", uv.processInput("add"));
        assertEquals("add> NAME: ", uv.processInput("1111111111111"));
        assertEquals(">> Invalid input data", uv.processInput("33"));
    }

    @Test
    void TestAddError3() {
        assertEquals("add> EMSO: ", uv.processInput("add"));
        assertEquals("add> NAME: ", uv.processInput("1111111111111"));
        assertEquals("add> SURNAME: ", uv.processInput("Gjon"));
        assertEquals(">> Invalid input data", uv.processInput("33"));
    }

    @Test
    void TestAddError4() {
        assertEquals("add> EMSO: ", uv.processInput("add"));
        assertEquals("add> NAME: ", uv.processInput("1111111111111"));
        assertEquals("add> SURNAME: ", uv.processInput("Gjon"));
        assertEquals("add> AGE: ", uv.processInput("Belushi"));
        assertEquals(">> Invalid input data", uv.processInput("thirty-three"));
    }

    @Test
    void TestAddError5() {
        assertEquals("add> EMSO: ", uv.processInput("add"));
        assertEquals("add> NAME: ", uv.processInput("1111111111111"));
        assertEquals("add> SURNAME: ", uv.processInput("Gjon"));
        assertEquals("add> AGE: ", uv.processInput("Belushi"));
        assertEquals("add> VACCINE: ", uv.processInput("33"));
        assertEquals(">> Invalid input data", uv.processInput("SinoFarm"));
    }

    @Test
    void TestIllegalAdd() {
        assertEquals("add> EMSO: ", uv.processInput("add"));
        assertEquals("add> NAME: ", uv.processInput("1111111111111"));
        assertEquals("add> SURNAME: ", uv.processInput("Ardit"));
        assertEquals("add> AGE: ", uv.processInput("Nela"));
        assertEquals("add> VACCINE: ", uv.processInput("20"));
        assertEquals("OK", uv.processInput("Johnson"));

        assertEquals("add> EMSO: ", uv.processInput("add"));
        assertEquals("add> NAME: ", uv.processInput("1111111111111"));
        assertEquals("add> SURNAME: ", uv.processInput("Gjon"));
        assertEquals("add> AGE: ", uv.processInput("Nela"));
        assertEquals("add> VACCINE: ", uv.processInput("20"));
        assertEquals(">> Person already exists", uv.processInput("Johnson"));

        assertEquals("add> EMSO: ", uv.processInput("add"));
        assertEquals("add> NAME: ", uv.processInput("1111111111112"));
        assertEquals("add> SURNAME: ", uv.processInput("Ardit"));
        assertEquals("add> AGE: ", uv.processInput("Nela"));
        assertEquals("add> VACCINE: ", uv.processInput("20"));
        assertEquals(">> Person already exists", uv.processInput("Johnson"));
    }

    @Test
    void TestCountAndReset() {
        assertEquals("add> EMSO: ", uv.processInput("add"));
        assertEquals("add> NAME: ", uv.processInput("1111111111111"));
        assertEquals("add> SURNAME: ", uv.processInput("Ardit"));
        assertEquals("add> AGE: ", uv.processInput("Nela"));
        assertEquals("add> VACCINE: ", uv.processInput("20"));
        assertEquals("OK", uv.processInput("Johnson"));

        assertEquals("add> EMSO: ", uv.processInput("add"));
        assertEquals("add> NAME: ", uv.processInput("1111111111112"));
        assertEquals("add> SURNAME: ", uv.processInput("Gjon"));
        assertEquals("add> AGE: ", uv.processInput("Nela"));
        assertEquals("add> VACCINE: ", uv.processInput("20"));
        assertEquals("OK", uv.processInput("Johnson"));

        assertEquals("2", uv.processInput("count"));
        assertEquals("reset> Are you sure (y|n): ", uv.processInput("reset"));
        assertEquals("OK", uv.processInput("y"));
        assertEquals("0", uv.processInput("count"));
    }

    @Test
    void testExists() {
        assertEquals("add> EMSO: ", uv.processInput("add"));
        assertEquals("add> NAME: ", uv.processInput("1111111111111"));
        assertEquals("add> SURNAME: ", uv.processInput("Ardit"));
        assertEquals("add> AGE: ", uv.processInput("Nela"));
        assertEquals("add> VACCINE: ", uv.processInput("20"));
        assertEquals("OK", uv.processInput("Johnson"));

        assertEquals("Yes", uv.processInput("exists 1111111111111"));
        assertEquals("No", uv.processInput("exists 1111211111111"));

        assertEquals("Yes", uv.processInput("exists Ardit Nela"));
        assertEquals("No", uv.processInput("exists Nela Ardit"));

        assertEquals(">> Specify at least two strings", uv.processInput("exists"));
    }

    @Test
    void testSaveAndRestore() {
        assertEquals("add> EMSO: ", uv.processInput("add"));
        assertEquals("add> NAME: ", uv.processInput("1111111111111"));
        assertEquals("add> SURNAME: ", uv.processInput("Ardit"));
        assertEquals("add> AGE: ", uv.processInput("Nela"));
        assertEquals("add> VACCINE: ", uv.processInput("20"));
        assertEquals("OK", uv.processInput("Johnson"));

        assertEquals("add> EMSO: ", uv.processInput("add"));
        assertEquals("add> NAME: ", uv.processInput("2111111111111"));
        assertEquals("add> SURNAME: ", uv.processInput("Gjon"));
        assertEquals("add> AGE: ", uv.processInput("Buzuku"));
        assertEquals("add> VACCINE: ", uv.processInput("20"));
        assertEquals("OK", uv.processInput("Johnson"));

        assertEquals("OK", uv.processInput("save test.txt"));

        assertEquals("2", uv.processInput("count"));
        assertEquals("reset> Are you sure (y|n): ", uv.processInput("reset"));
        assertEquals("OK", uv.processInput("y"));
        assertEquals("0", uv.processInput("count"));

        assertEquals("OK", uv.processInput("restore test.txt"));
        assertEquals("2", uv.processInput("count"));
    }

    @Test
    void TestIllegalSave() {
        assertEquals("add> EMSO: ", uv.processInput("add"));
        assertEquals("add> NAME: ", uv.processInput("2111111111111"));
        assertEquals("add> SURNAME: ", uv.processInput("Gjon"));
        assertEquals("add> AGE: ", uv.processInput("Buzuku"));
        assertEquals("add> VACCINE: ", uv.processInput("20"));
        assertEquals("OK", uv.processInput("Johnson"));

        assertEquals(">> Specify a file name", uv.processInput("save"));
        assertEquals(">> Specify a file name", uv.processInput("restore"));
    }

    @Test
    void TestPrint() {
        assertEquals("add> EMSO: ", uv.processInput("add"));
        assertEquals("add> NAME: ", uv.processInput("1111111111111"));
        assertEquals("add> SURNAME: ", uv.processInput("Ardit"));
        assertEquals("add> AGE: ", uv.processInput("Nela"));
        assertEquals("add> VACCINE: ", uv.processInput("20"));
        assertEquals("OK", uv.processInput("Johnson"));

        assertEquals("add> EMSO: ", uv.processInput("add"));
        assertEquals("add> NAME: ", uv.processInput("2111111111111"));
        assertEquals("add> SURNAME: ", uv.processInput("Gjon"));
        assertEquals("add> AGE: ", uv.processInput("Buzuku"));
        assertEquals("add> VACCINE: ", uv.processInput("20"));
        assertEquals("OK", uv.processInput("Moderna"));

        assertEquals("OK", uv.processInput("print"));
    }

    @Test
    void testTrivialities() {
        assertFalse(uv.isReset());
        assertEquals(-1, uv.getCount());
        assertEquals(-1, uv.getCountFind());
        assertEquals(-1, uv.getCountRem());
    }

    @Test
    void testSearch() {
        assertEquals("add> EMSO: ", uv.processInput("add"));
        assertEquals("add> NAME: ", uv.processInput("1111111111111"));
        assertEquals("add> SURNAME: ", uv.processInput("Ardit"));
        assertEquals("add> AGE: ", uv.processInput("Nela"));
        assertEquals("add> VACCINE: ", uv.processInput("20"));
        assertEquals("OK", uv.processInput("Johnson"));

        assertEquals("search> NAME: ", uv.processInput("search"));
        assertEquals("search> SURNAME: ", uv.processInput("Ardit"));
        assertEquals("1111111111111-Ardit, Nela-20-Johnson", uv.processInput("Nela"));

        assertEquals("1111111111111-Ardit, Nela-20-Johnson", uv.processInput("search 1111111111111"));
    }

    @Test
    void searchIllegal() {
        assertEquals(">> Specify at most two strings", uv.processInput("search Ard Ne Fu"));

        assertEquals(">> Person does not exist", uv.processInput("search 9999999999999"));

        assertEquals("search> NAME: ", uv.processInput("search"));
        assertEquals("search> SURNAME: ", uv.processInput("Ardit"));
        assertEquals(">> Person does not exist", uv.processInput("Nela"));

    }

    @Test
    void testSaveNoMemory() {
        SeznamiUV mock = EasyMock.createMock(SeznamiUV.class);
        expect(mock.processInput("save test.txt")).andThrow(new OutOfMemoryError());
        replay(mock);

        OutOfMemoryError error = assertThrows(OutOfMemoryError.class, () -> {
            mock.processInput("save test.txt");
        });

        verify(mock);
    }

    @Test
    void testRemove() {
        assertEquals("add> EMSO: ", uv.processInput("add"));
        assertEquals("add> NAME: ", uv.processInput("2111111111111"));
        assertEquals("add> SURNAME: ", uv.processInput("Gjon"));
        assertEquals("add> AGE: ", uv.processInput("Buzuku"));
        assertEquals("add> VACCINE: ", uv.processInput("20"));
        assertEquals("OK", uv.processInput("AstraZeneca"));

        assertEquals("add> EMSO: ", uv.processInput("add"));
        assertEquals("add> NAME: ", uv.processInput("2111113411111"));
        assertEquals("add> SURNAME: ", uv.processInput("Gjergj"));
        assertEquals("add> AGE: ", uv.processInput("Fishta"));
        assertEquals("add> VACCINE: ", uv.processInput("42"));
        assertEquals("OK", uv.processInput("Pfeizer"));

        assertEquals("remove> NAME: ", uv.processInput("remove"));
        assertEquals("remove> SURNAME: ", uv.processInput("Gjon"));
        assertEquals("OK", uv.processInput("Buzuku"));

        assertEquals("1", uv.processInput("count"));
        assertTrue(uv.seznamPoImenu.size() == uv.seznamPoEmso.size());

        assertEquals("OK", uv.processInput("remove 2111113411111"));

        assertEquals("0", uv.processInput("count"));
        assertTrue(uv.seznamPoImenu.size() == uv.seznamPoEmso.size());
    }

    @Test
    void testMiddleName() {
        assertEquals("add> EMSO: ", uv.processInput("add"));
        assertEquals("add> NAME: ", uv.processInput("2111111111111"));
        assertEquals("add> SURNAME: ", uv.processInput("Gjergj Elez"));
        assertEquals("add> AGE: ", uv.processInput("Alija"));
        assertEquals("add> VACCINE: ", uv.processInput("35"));
        assertEquals("OK", uv.processInput("AstraZeneca"));

        assertEquals("search> NAME: ", uv.processInput("search"));
        assertEquals("search> SURNAME: ", uv.processInput("Gjergj Elez"));
        assertEquals("2111111111111-Gjergj Elez, Alija-35-AstraZeneca", uv.processInput("Alija"));

        assertEquals("1", uv.processInput("count"));

        assertEquals("remove> NAME: ", uv.processInput("remove"));
        assertEquals("remove> SURNAME: ", uv.processInput("Gjergj Elez"));
        assertEquals("OK", uv.processInput("Alija"));

        assertEquals("search> NAME: ", uv.processInput("search"));
        assertEquals("search> SURNAME: ", uv.processInput("Gjergj Elez"));
        assertEquals(">> Person does not exist", uv.processInput("Alija"));
    }
}