package org.lab5;

import java.io.File;
import java.io.IOException;

public interface CollectionMapper<T> {
    public T serialize(File file) throws IOException;
    public T deserialize(File file) throws IOException;
}
