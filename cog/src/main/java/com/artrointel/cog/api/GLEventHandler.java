package com.artrointel.cog.api;

import androidx.annotation.AnyThread;

import java.util.ArrayList;
import java.util.Iterator;

public abstract class GLEventHandler {

    ArrayList<Runnable> mEvents = new ArrayList<>();

    public void handleEvents() {
        synchronized (mEvents) {
            Iterator it = mEvents.iterator();
            while(it.hasNext()) {
                Runnable event = (Runnable) it.next();
                handleEvent(event);
            }
            mEvents.clear();
        }
    }

    public abstract void handleEvent(Runnable e);

    @AnyThread
    public void queueGLEvent(Runnable glEvent) {
        synchronized (mEvents) {
            mEvents.add(glEvent);
        }
    }
}
