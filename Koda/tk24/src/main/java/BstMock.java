import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class BstMock implements Seznam {
    @Override
    public void add(Object e) {
        throw new OutOfMemoryError();
    }

    @Override
    public Object removeFirst() {
        return null;
    }

    @Override
    public Object remove(Object e) {
        return null;
    }

    @Override
    public Object getFirst() {
        return null;
    }

    @Override
    public boolean exists(Object e) {
        return false;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public int depth() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public void print() {

    }

    @Override
    public void save(OutputStream outputStream) throws IOException {
        throw new IOException();
    }

    @Override
    public void restore(InputStream inputStream) throws IOException, ClassNotFoundException {
        throw new ClassNotFoundException();
    }
}
