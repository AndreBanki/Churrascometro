package com.banki.utils;

import android.os.Handler;

/**
 * Inspirado em: Github, author: Jeffrey F. Cole
 * http://stackoverflow.com/questions/12071090/triggering-event-continuously-when-button-is-pressed-down-in-android
 */
class RepetitiveUpdater implements Runnable{

    private IncrementButton button;

    public RepetitiveUpdater(IncrementButton button) {
        this.button = button;
    }

    @Override
    public void run() {
        button.incrementAndRepeat();
    }
}
