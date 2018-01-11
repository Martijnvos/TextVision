package nl.vosdevelopment.textvision;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.support.design.widget.Snackbar;
import android.util.Log;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

public class ClipboardChange {
    private ClipboardManager clipboard;
    private ConcurrentHashMap<String, Integer> recognisedText;

    public ClipboardChange(ClipboardManager clipboardManager) {
        this.clipboard = clipboardManager;
        recognisedText = new ConcurrentHashMap<>();
    }

    public void setRecognisedText(String text) {
        boolean changedValue = false;

        if(recognisedText.size() > 0) {
            for (String key : recognisedText.keySet()) {
                if (key.equals(text)) {
                    int value = recognisedText.get(key);
                    recognisedText.put(key, value + 1);

                    if (recognisedText.get(key) >= Globals.clipBoardPasteThreshold) setClipboardItem(key);

                    changedValue = true;
                    break;
                }
            }
        }

        if (!changedValue) recognisedText.put(text, 1);
    }

    public void setClipboardItem(final String text) {
        ClipData clip = ClipData.newPlainText("Recognised text", text);
        clipboard.setPrimaryClip(clip);
        Log.d("ClipboardChange", "Clipboard added: " + text);

        // Remove key corresponding to the word from the hashmap
        recognisedText.remove(text);

        Globals.mainActiviy.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Snackbar.make(Globals.mainActiviy.findViewById(R.id.topLayout), text + " added to clipboard", Snackbar.LENGTH_SHORT)
                        .show();
            }
        });
    }
}
