import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Comparator;
import java.util.NoSuchElementException;

import static org.junit.Assert.*;

class BSTTest {
    private Bst<Integer> tree;
    private Bst<String> bst;

    @BeforeEach
    void init() {
        System.out.println("INITIALIZING DATA");
        tree = new Bst<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }
        });
        bst = new Bst<>(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        });
    }

    @Test
    void testAdd() {
        System.out.println("Test Add");
        Throwable e = null;

        tree.add(14);
        tree.add(17);
        assertTrue(tree.exists(17));
        assertTrue(tree.exists(14));

        try {
            tree.add(14);
        } catch (Throwable ex) {
            e = ex;
        }

        assertTrue(e instanceof IllegalArgumentException);
    }

    @Test
    void testExists() {
        System.out.println("Test Exists");

        tree.add(14);
        tree.add(17);
        assertTrue(tree.exists(17));
        assertTrue(tree.exists(14));
        assertFalse(tree.exists(24));
    }

    @Test
    void removeFirst() {
        System.out.println("Test Remove First");

        tree.add(14);
        tree.add(17);
        tree.removeFirst();
        assertFalse(tree.exists(14));
    }

    @Test
    void remove() {
        System.out.println("Test Remove");

        tree.add(14);
        tree.add(17);
        tree.add(23);
        tree.remove(17);
        assertFalse(tree.exists(17));
    }

    @Test
    void removeError() {
        System.out.println("Test Remove Error");
        Throwable e = null;

        tree.add(14);
        tree.add(17);
        tree.add(23);

        try {
            tree.remove(144);
        } catch (Throwable ex) {
            e = ex;
        }
        assertTrue(e instanceof NoSuchElementException);
    }

    @Test
    public void testAddOne() {
        bst.add("Test");
        assertEquals(1, bst.size());
        assertEquals(1, bst.depth());
    }

    @Test
    public void testRemoveMultipleScenario2() {
        bst.add("Test4");
        bst.add("Test2");
        bst.add("Test1");
        bst.add("Test3");
        bst.add("Test5");
        bst.remove("Test2");
        assertEquals(4, bst.size());
        assertEquals(3, bst.depth());
        bst.remove("Test5");
        assertEquals(3, bst.size());
        assertEquals(3, bst.depth());
    }

    @Test
    public void testGetOne() {
        bst.add("Test");
        assertEquals("Test", bst.getFirst());
        assertEquals(1, bst.size());
        assertEquals(1, bst.depth());
    }

    @Test
    public void testGetMultiple() {
        bst.add("Test2");
        assertEquals("Test2", bst.getFirst());
        assertEquals(1, bst.size());
        assertEquals(1, bst.depth());
        bst.add("Test3");
        bst.add("Test1");
        assertEquals("Test2", bst.getFirst());
        assertEquals("Test2", bst.getFirst());
        assertEquals(3, bst.size());
        assertEquals(2, bst.depth());
    }

    @Test
    public void testGetOnEmpty() {
        Throwable e = null;

        try {
            bst.getFirst();
        } catch (Throwable ex) {
            e = ex;
        }

        assertTrue(e instanceof NoSuchElementException);
    }

    @Test
    public void testDepthOnEmpty() {
        assertEquals(0, bst.depth());
    }

    @Test
    public void testIsEmpty() {
        assertTrue(bst.isEmpty());
        bst.add("Test");
        assertFalse(bst.isEmpty());
    }

    @Test
    void testSaveAndRestore() {
        bst.add("Test4");
        bst.add("Test2");
        bst.add("Test1");
        bst.add("Test3");
        bst.add("Test5");

        try {
            bst.save(new FileOutputStream("test.bin"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        bst.remove("Test4");
        bst.remove("Test2");
        bst.remove("Test1");
        bst.remove("Test3");
        bst.remove("Test5");

        try {
            bst.restore(new FileInputStream("test.bin"));
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        assertEquals(5, bst.size());
    }

    @Test
    void testPrint() {
        tree.print();
        tree.add(3);
        tree.print();
    }

    @Test
    void RemoveFirstError() {
        Throwable e = null;

        try {
            tree.remove(3);
        } catch (Throwable ex) {
            e = ex;
        }

        assertTrue(e instanceof NoSuchElementException);
    }

    @Test
    void AnotherRemove() {
        tree.add(20);
        tree.add(5);

        tree.remove(5);
        assertFalse(tree.exists(5));
    }

    @Test
    void TestSearch() {
        tree.add(20);
        tree.add(5);
        Throwable e = null;

        assertEquals((Object) 20, tree.search(20));
        try {
            tree.search(23);
        } catch (Throwable ex) {
            e = ex;
        }

        assertTrue(e instanceof NoSuchElementException);
    }

    @Test
    void testOutOfMemory() {
        BstMock mock = new BstMock();
        Throwable e = null;

        try {
            mock.add(3);
        } catch (Throwable ex) {
            e = ex;
        }

        assertTrue(e instanceof OutOfMemoryError);
    }

    @Test
    void testFileError() {
        BstMock mock = new BstMock();
        Throwable e = null;

        try {
            mock.save(new FileOutputStream("test.bin"));
        } catch (Throwable ex) {
            e = ex;
        }

        assertTrue(e instanceof IOException);
    }

    @Test
    void testFormatError() {
        BstMock mock = new BstMock();
        Throwable e = null;

        try {
            mock.restore(new FileInputStream("test.bin"));
        } catch (Throwable ex) {
            e = ex;
        }

        assertTrue(e instanceof ClassNotFoundException);
    }
}