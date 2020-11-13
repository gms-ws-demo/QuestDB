package io.questdb.cairo.replication;

import java.io.Closeable;

public interface ReplicationSlaveManager {
    SlaveWriter getSlaveWriter(int masterTableId);

    void releaseSlaveWriter(int masterTableId, SlaveWriter slaveWriter);

    public interface SlaveWriter extends Closeable {
        long getDataMap(long timestamp, int columnIndex, long offset, long size);

        boolean completeFrame();

        boolean markBlockNFrames(int nFrames);

        void commit();

        void cancel();

        @Override
        void close();
    }
}