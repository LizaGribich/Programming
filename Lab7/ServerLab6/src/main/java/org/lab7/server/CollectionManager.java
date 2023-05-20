package org.lab7.server;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import com.fasterxml.jackson.databind.SerializationFeature;
import org.lab7.server.models.Vehicle;

import java.io.File;
import java.io.IOException;

public class CollectionManager<E extends IDable> implements CollectionMapper<MapWrapper<Integer,E>> {
    private MapWrapper<Integer, E> collection;

    public CollectionManager(MapWrapper<Integer, E> collection) {
        this.collection = collection;
    }

    @Override
    public MapWrapper<Integer, E> serialize(File file) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);

        ObjectWriter writer = mapper.writer(new DefaultPrettyPrinter());
        writer.writeValue(file, collection);
        return null;
    }

    @Override
    public MapWrapper<Integer, E> deserialize(File file) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        E[] values = (E[]) objectMapper.readValue(file, Vehicle[].class);
        for (int i = 0; i < values.length; i++) {
            collection.put(values[i].detId(), values[i]);
        }
        return collection;
    }
}
