package nl.vosdevelopment.textvision;

public class Globals {
    // Main Activity
    public static OcrCaptureActivity mainActiviy;

    // ClipboardChange related globals
    public static ClipboardChange clipboardChange;
    public static final int clipBoardPasteThreshold = 5;

    // Constants used to pass extra data in the intent, used in OcrCaptureActivity
    public static final String AutoFocus = "AutoFocus";
    public static final String UseFlash = "UseFlash";
    public static final String TextBlockObject = "String";
}
