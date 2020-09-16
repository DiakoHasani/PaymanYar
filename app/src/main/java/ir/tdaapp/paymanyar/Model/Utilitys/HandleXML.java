package ir.tdaapp.paymanyar.Model.Utilitys;

import android.content.Context;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import ir.tdaapp.paymanyar.Model.Repositorys.DataBase.Tbl_NewsPaper;
import ir.tdaapp.paymanyar.Model.Services.S_HandleXML;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_NewsPaper;
import ir.tdaapp.paymanyar.Model.ViewModels.VM_NewsPaperFilter;

//برای گرفتن داده از نوع آر اس اس در سرور
public class HandleXML {

    private String urlString;
    private S_HandleXML s_handleXML;
    private XmlPullParserFactory xmlFactoryObject;
    public volatile boolean parsingComplete = true;
    Context context;
    Tbl_NewsPaper tbl_newsPaper;
    List<VM_NewsPaperFilter> newsPaperFilter;

    public HandleXML(Context context, String urlString, S_HandleXML s_handleXML) {
        this.urlString = urlString;
        this.s_handleXML = s_handleXML;
        this.context = context;
        tbl_newsPaper = new Tbl_NewsPaper(context);
        newsPaperFilter = tbl_newsPaper.getNewsPaperFilters();
    }

    boolean showNewsPaper(String title) {

        VM_NewsPaperFilter paperFilter;

        for (int i = 0; i < newsPaperFilter.size(); i++) {
            try {

                paperFilter = newsPaperFilter.get(i);

                if (title.contains(paperFilter.getTitle()))
                    return true;

            } catch (Exception e) {
            }
        }

        return false;
    }

    public void parseXMLAndStoreIt(XmlPullParser myParser) {

        //در اینجا لیست روزنامه ها ست می شود
        List<VM_NewsPaper> newsPapers = new ArrayList<>();

        String[] array;

        /*
         * این فایلی که از سرور گرفته می شود باید تمامی تگ های آن خوانده شود
         * من دراینجا تنها با تگ آیتم کار دارم نیازی به بقیه تگ ها نیست و باقی تگ های ما در اول فایل قرار دارند و تا زمانی که به تگ آیتم نرسد متغیر زیر فالس عملیات گرفتن داده ها انجام نمی شود و زمانی که تگ آیتم برسد متغیر زیر ترو می شود و عملیات م شروع می شود
         * */
        boolean startItem = false;

        //در اینجا سطرها یکی یکی خوانده می شود و در متغیر زیر قرار داده می شوند
        int event;

        //در اینجا مقدارهای که خوانده شده قرار می گیرد و سپس در ویومدل ست می شود
        String text = null;

        try {
            event = myParser.getEventType();

            //در اینجا یک شی از روزنامه ما ایجاد می کند
            VM_NewsPaper newsPaper = new VM_NewsPaper();

            while (event != XmlPullParser.END_DOCUMENT) {

                //در اینجا نام تگی که روی آن قرار دارد ست می شود
                String name = myParser.getName();

                //در اینجا اگر روی تگ آیتم قرار گیرد مقدار متغیر زیر را ترو می کند
                if (name != null) {
                    if (name.equals("item")) {
                        startItem = true;
                    }
                }

                //در اینجا اگر به تگ آیتم رسیده باشد عملیات گرفتن اخبار انجام می شود
                if (startItem) {

                    switch (event) {
                        case XmlPullParser.START_TAG:

                            //اینجا زمانی که یک تگ شروع می شود فراخوانی می شود و اگر آن تگ آیتم باشد یک شی جدید از اخبار ایجاد می کند
                            if (name != null) {
                                if (name.equals("item")) {
                                    newsPaper = new VM_NewsPaper();
                                }
                            }

                            break;

                        case XmlPullParser.TEXT:
                            //در اینجا مقادیر تگ ها گرفته می شود
                            text = myParser.getText();
                            break;

                        case XmlPullParser.END_TAG:

                            //زمانی که به پایان یک تگ برسیم اینجا فراخوانی می شود و اگر آن تگ آیتم باشد شی ساخته شده از خبر به لیست اخبار اضافه می شود
                            if (name != null) {
                                if (name.equals("item")) {
                                    if (showNewsPaper(newsPaper.getTitle()))
                                        newsPapers.add(newsPaper);
                                }
                            }

                            if (name.equals("title")) {
                                newsPaper.setTitle(text);
                            } else if (name.equals("content:encoded")) {

                                //در اینجا آدرس عکس گرفته می شود و در شی روزنامه قرار می گیرد
                                array = text.split("\"");
                                int index_src = 0;

                                for (int i = 0; i < array.length; i++) {
                                    if (array[i].equalsIgnoreCase(" src=")) {
                                        index_src = i;
                                        break;
                                    }
                                }

                                newsPaper.setImage(array[index_src + 1]);
                            } else if (name.equals("link")) {
                                newsPaper.setLink(text);
                            } else if (name.equals("guid")) {
                                newsPaper.setGuid(text);
                            }
                            break;
                    }

                }

                event = myParser.next();
            }

            parsingComplete = false;
        } catch (Exception e) {
            e.printStackTrace();
            s_handleXML.onError(e);
        }

        s_handleXML.onSuccess(newsPapers);
    }

    public void fetchXML() {

        try {

            URL url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setReadTimeout(10000 /* milliseconds */);
            conn.setConnectTimeout(15000 /* milliseconds */);
            conn.setRequestMethod("GET");
            conn.setDoInput(true);

            // Starts the query
            conn.connect();
            InputStream stream = conn.getInputStream();

            xmlFactoryObject = XmlPullParserFactory.newInstance();
            XmlPullParser myparser = xmlFactoryObject.newPullParser();

            myparser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
            myparser.setInput(stream, null);

            parseXMLAndStoreIt(myparser);
            stream.close();

        } catch (Exception e) {
            s_handleXML.onError(e);
        }
    }
}
