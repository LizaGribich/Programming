package org.lab7.server;

import java.io.File;
import java.io.IOException;

public interface CollectionMapper<T> {
    T serialize(File file) throws IOException;
    T deserialize(File file) throws IOException;
}
