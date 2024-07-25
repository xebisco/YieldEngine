/*
 * Copyright [2022-2024] [Xebisco]
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.xebisco.yieldengine.concurrency;

import java.util.concurrent.atomic.AtomicBoolean;

public final class LockProcess {
    private final Object lockObject = new Object();
    private final AtomicBoolean running = new AtomicBoolean(true);

    public void unlock() {
        getRunning().set(false);
        synchronized (getLockObject()) {
            getLockObject().notifyAll();
        }
    }

    public void aWait() throws InterruptedException {
        if (getRunning().get()) {
            synchronized (getLockObject()) {
                getLockObject().wait();
            }
        }
    }

    public boolean isRunning() {
        return getRunning().get();
    }

    public Object getLockObject() {
        return lockObject;
    }

    private AtomicBoolean getRunning() {
        return running;
    }
}
