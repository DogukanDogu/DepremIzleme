import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;


import java.io.IOException;

public class ParseHTML {
    private String text = null;
    private static String address = null;

    public void setAddress(String address) {
        this.address = address;
    }

    public String getText() {
        return text;
    }

    public void connectWEBSite() {
        String url = address;

        Document doc = null;
        try {
            doc = Jsoup.connect(url).get();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        text = doc.body().getElementsByTag("pre").text();

    }
}
