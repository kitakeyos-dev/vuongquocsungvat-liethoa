package me.kitakeyos;

import java.io.DataInputStream;
import java.io.InputStream;
import java.util.Vector;

public class ManagedInputStream extends DataInputStream {
    private final String resourceName;
    private static final Vector<ManagedInputStream> activeStreams = new Vector<>();
    private static final int MAX_ACTIVE_STREAMS = 10;

    private ManagedInputStream(InputStream inputStream, String resourceName) {
        super(inputStream);
        this.resourceName = resourceName;
    }

    public static InputStream openStream(String resourcePath) {
        InputStream inputStream = ManagedInputStream.class.getResourceAsStream(resourcePath);
        if (inputStream != null) {
            ManagedInputStream managedStream = new ManagedInputStream(inputStream, resourcePath);
            activeStreams.addElement(managedStream);

            if (activeStreams.size() > MAX_ACTIVE_STREAMS) {
                System.out.println("Current active streams: " + activeStreams.size());

                for (int i = 0; i < activeStreams.size(); i++) {
                    ManagedInputStream stream = activeStreams.elementAt(i);
                    try {
                        if (stream.available() == 0) {
                            System.out.println("Auto-closing stream: " + stream.resourceName);
                            stream.close();
                            i--;
                        }
                    } catch (Exception e) {
                        System.err.println("Error checking stream availability: " + e.getMessage());
                    }
                }

                System.out.println("New active stream count: " + activeStreams.size());

                if (activeStreams.size() > MAX_ACTIVE_STREAMS) {
                    ManagedInputStream oldestStream = activeStreams.elementAt(0);
                    try {
                        System.out.println("Auto-closing oldest stream: " + oldestStream.resourceName);
                        oldestStream.close();
                    } catch (Exception e) {
                        System.err.println("Error closing oldest stream: " + e.getMessage());
                    }
                }
            }

            return managedStream;
        }

        return null;
    }

    @Override
    public void close() {
        activeStreams.removeElement(this);
        try {
            super.close();
        } catch (Exception e) {
            System.err.println("Error while closing stream: " + e.getMessage());
        }
    }
}