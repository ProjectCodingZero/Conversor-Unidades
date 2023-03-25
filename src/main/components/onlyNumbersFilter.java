package components;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

public final class onlyNumbersFilter extends DocumentFilter {
    @Override
    public void insertString(DocumentFilter.FilterBypass fb, int offset, String text, AttributeSet attr) throws BadLocationException {
        String newText = fb.getDocument().getText(0, fb.getDocument().getLength()) + text;
        //text.matches("[0-9].+"
        if (newText.matches("\\d*\\.?\\d*")) {
            super.insertString(fb, offset, text, attr);
        }
    }
    @Override
    public void replace(DocumentFilter.FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
        String newText = fb.getDocument().getText(0, fb.getDocument().getLength()) + text;
        if (newText.matches("\\d*\\.?\\d*")) {
            super.replace(fb, offset, length, text, attrs);
        }
    }
}

