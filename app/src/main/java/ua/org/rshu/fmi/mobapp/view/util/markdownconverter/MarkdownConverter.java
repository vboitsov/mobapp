package ua.org.rshu.fmi.mobapp.view.util.markdownconverter;

/**
 * @author Vadim Boitsov <vadimboitsov1@gmail.com>
 */

public interface MarkdownConverter {

    /**
     * A method which converts a plain text with markdown into a html to display in a webview.
     * @param text a String object to convert.
     * @return a String object with html.
     */
    String getParsedHtml(String text);
}
