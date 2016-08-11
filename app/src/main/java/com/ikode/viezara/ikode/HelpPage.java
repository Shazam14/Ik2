package com.ikode.viezara.ikode;


import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;


public class HelpPage extends AppCompatActivity {

    ListView listView;
    SearchView searchBox;


    String[] titles={
            "What is the purpose of the Ikode SC Decoder?",//1
            "How does the Ikode SC  Decoder work?", //2
            "What is an Ikode Smartcode code for?", //3
            "Does the Ikode SC Decoder access my private files and folders on my mobile device?", //4
            "Why doesn’t the Ikode SC Decoder work on my phone?", //5
            "Does Ikode SC Decoder send messages to my phone contacts?", //6
            "What is the ID Token and why do I need it",//7
            "How do I change the email associated with the account?	",//8
            "Why are my email address and password are not working?", //9
            "What should I do if I don't receive my scan activation?", //10
            "What should I do if I don't receive my activation email? ", //11
            "What should I do if I don't receive a scan verification result?", //12
            "Can Ikode smartcodes be read by any other 3rd party mobile readers?", //13
            "How do I create an Ikode Smartcode with the IkodeSC Reader?", //14
            "What is NFC", //15
            "How does the Ikode Smartcode-NFC work", //16
            "Could someone change the Ikode Smartcode-NFC tag?", //17
            "What is the Ikode Smartcode-NFC tag?", //18
            "Will Ikode Smartcode-NFC tags replace Ikode Smartcodes-CODE"

    };

    String[] contents = {
            "The IkodeSC Reader is a truly unique barcode / smartcode decoder. On one hand it is the “Swiss Army Knife” in decoding almost all available types of barcodes, such as \n" +
                    "• One line (1D) barcodes:\n" +
                    "UPC-A and UPC-E\n" +
                    "EAN-8 and EAN-13\n" +
                    "Code 39, 93, 128\n" +
                    "ITF\n" +
                    "ITF\n" +
                    "\n"+
                    "• 2-dimensional (2D) barcodes\n" +
                    "QR Code\n" +
                    "Data Matrix\n" +
                    "PDF 417 (alpha quality)\n" +
                    "Aztec (beta quality)\n" +
                    "\n" +
                    "• In addition Near Field Communication (NFC)Ikode Smartcode-NFC tags\n" +
                    "\n" +
                    "• and Radio Frequency Identification Devices (RFID)our soon to be released Ikode Smartcode-RFID tags\n" +
                    "On the other hand the IkodeSC Reaoder, installed on your smart mobile phone is a tool that allows you to verify the authenticity of products, documents and services of participating brand and corporations in the fight to bust fraud.", //1
            "Launch the Ikode SC Decoder on your device. It will require an authentication (mobile number). Within seconds the camera will become active and is ready for scanning. Line up the phone so that the Code is completely inside the viewfinder marks of your camera. If lighting is a problem and your camera can’t focus on the code, please re-position the phone. After scanning the decoded Code data is immediately visible to you and prompts you to do the authenticity verification.", //2
            "An IkodeSC Reader is a visual cluster of black and white square that contain data which can be captured by your\n" +
                    "phone's camera and decoded / read by the reader app. The data is highly secure (it's visual result on your\n" +
                    "phone's screen is a garble of characters. The Ikode Smartcode contains additional (garbled) data that let's you\n" +
                    "connect via SMS, e-Mail, even Chat, to the company that issued the Ikode Smartcode to get an authenticity\n" +
                    "verification. Ikode Smartcode are typically printed on documents, product labels, but also certificates,\n" +
                    "identification cards of spare parts, etc. They allow you to do a very quick and easy verification whether a product,\n" +
                    "you might be interested in to purchase, is authentic and not a fake, or counterfeit. Fake and counterfeit products\n" +
                    "are usually of sub-standard quality and can be a safety threat, or even a health-threat to you. Though these fake\n" +
                    "or counterfeit products may appear to be cheaper, fraudsters make a very good profit from them as they use the\n" +
                    "cheapest possible materials and often cheap and unlawful child-labor.\n" +
                    "Ikode Smartcode and their tools (Ikode software and the IkodeSC Reader belong to the “#SayNoToFraud”\n" +
                    "movement that are there to assist you to know whether products are real (authentic) or fakes / counterfeit (thus\n" +
                    "can pose a safety and health risk to you).",//3
            "No, the IkodeSC Reader does not access any private files on your mobile device! If you are worried about the risk\n" +
                    "to your privacy please read our Privacy Policy contained and accessible by you in the IkodeSC Reader app on your\n" +
                    "mobile device. Your privacy is important to Ikona and we go to great lengths to protect it. Ikona AG, the company that has\n" +
                    "developed the IkodeSC Reader app, is based in Switzerland, where data protection laws are among the strictest\n" +
                    "in the world, mirroring the country's legendary private banking infrastructure.", //4
            "At this moment we support Android phones (like you Samsung mobile device and those of many other brands).\n" +
                    "But, soon to come, we will extend our range to iPhones and Windows phones. Check\n" +
                    "www.ikodesoftware.com/decoder.html for a list of mobile phone models and operating systems that we support.\n" +
                    "Phones can be tricky when it comes to their operating systems (OS). Please note that we do not support the\n" +
                    "older Android formats, before Android 4.1 named “Jelly Bean”, as well as the older Symbian phones.\n" +
                    "If you are interested to be informed by us when the IkodeSC Reader app will e available for iPhones and\n" +
                    "Windows-based phones, please access our www.ikodesoftware.com/contact_us.html page in our Website and\n" +
                    "let us know what mobile device make you have. The moment a version of our IkodeSC Reader app for your\n" +
                    "mobile phone will be available we will inform you by e-mail. Otherwise, check back with out Website on a regular\n" +
                    "basis as we will announce new releases of our app there.", //5
            "NO, absolutely not. Our Privacy Policy is very clear on this. Please read our Privacy Policy contained and\n" +
                    "accessible by you in the IkodeSC Reader app on your mobile device. Your privacy is our most important to us.", //6
            "The ID Token is like a 2nd-factor authentication banks are using for e-banking. Whereas passwords can create an\n" +
                    "issue due to the many passwords you may have to remember for logging in to the various services and\n" +
                    "applications you run on your mobile device, we opt for the ID Token that will partially be displayed on your log in\n" +
                    "screen with the request to complete it. This makes your task much easier, yet carries the same stringent security.\n" +
                    "For some functions on your IkodeSC Reader app you will need your password (i.e. to change some app settings).\n" +
                    "However, in such cases your password never leaves your mobile device thus remains completely safe.", //7
            "You can change your registration details, including your registered e-mail address any time. All you have to do is\n" +
                    "to access the “Settings” screen within your IkodeSC Reader app. You require your password for this. Once logged\n" +
                    "into your “Settings” you can change your e-mail address, or any other setting. When done, press the “Change”\n" +
                    "button. This will send the changes to our Ikode Exchange server and is added to our registration database.", //8
            "(Sorry, we do not have a workable answer to this question. Should you encounter such an issue the best way to\n" +
                    "get our immediate attention will be to send us a short information of your problem, using our\n" +
                    "www.ikodesoftware.com/contact_us.html page in our Website.", //9
            "Sorry, we do not have a workable answer to this question. Should you encounter such an issue the best way to\n" +
                    "get our immediate attention will be to send us a short information of your problem, using our\n" +
                    "www.ikodesoftware.com/contact_us.html page in our Website.", //10
            "Don't worry, we can help you! First check your e-mail's SPAM folder, depending on your e-mail settings the\n" +
                    "activation e-mail may end up there. If that is not the case, please resend your registration. Should this issue\n" +
                    "persist the best way to get our immediate attention will be to send us a short information of your problem, using\n" +
                    "our www.ikodesoftware.com/contact_us.html page in our Website.", //11
            "There could be several reasons why you do not receive an authenticity verification result. The verification is done\n" +
                    "by the company / organization that issued the Ikode Smartcodes – CODE, or -NFC. On top of this, they might be\n" +
                    "located in different countries with different carriers. At any point there might be a temporary bottleneck that\n" +
                    "creates the delay. However, our Ikode Exchange monitors all the authenticity verification requests and will be in\n" +
                    "the “know” if a communication bottleneck arises and will be on top of this issue the moment it is discovered. We\n" +
                    "do our utmost to solve such problems and get the verification result with the shortest possible delay to you.", //12
            "Yes, but within limits of functionality. There are a growing number of barcode decoder / reader apps available on\n" +
                    "the Internet. Our Ikode Smartcodes – CODE is governed by international barcode standards, thus any 3rd part\n" +
                    "reader can read the Ikode Smartcode - CODE. They could even send the decoded garbled message via SMS, email\n" +
                    "or Chat for an authenticity verification. However, there is more to the IkodeSC Reader that is unique to Ikode\n" +
                    "and Ikode Smartcodes - CODE. Our Ikode Smartcodes – NFC require a digital key to unlock. These features are\n" +
                    "inherent to IkodeSC Reader, only, and, of course, will not be available on 3rd party barcode scanners.", //13
            "To generate an Ikode Smartcode – CODE with your IkodeSC Readerapp is a function that will be released with the\n" +
                    "next version. We are currently in trials to ensure that when we release this feature there will be no unwanted\n" +
                    "bugs. Please do check back with our www.ikodesoftware.com Website on a regular basis. We will announce this\n" +
                    "forthcoming release soon.", //14
            "• Near field communication (NFC) is a set of communication protocols that enable two electronic devices,\n" +
                    "one of which is usually a portable device such as a smartphone, to establish communication by bringing\n" +
                    "them into proximity, typically a distance of 10 cm (3.9 in) or less.\n" +
                    "• NFC employs electromagnetic induction between two loop antennae when NFC devices—for example a\n" +
                    "smartphone and a \"NFC tag\"—exchange information, operating within the globally available unlicensed\n" +
                    "radio frequency ISM band of 13.56 MHz on ISO/IEC 18000-3 air interface at rates ranging from 106 to\n" +
                    "424 kbit/s.\n" +
                    "• Strong penetration in Asia and North America\n" +
                    "• Very easy to use. No battery needed", //15
            "• The NFC engine of the Ikode software writes a secure and digitally signed data block onto an NFC tag.\n" +
                    "• The NFC reader of the IkodeSC Reader-enabled devices to read information stored on inexpensive NFC\n" +
                    "tags embedded in product or items (i.e.labels, bottle caps, leather ware, garments, books covers, etc).", //16
            "• Ikode Smartcode-NFC tags can be locked so that once data has been written, it cannot be altered. For\n" +
                    "most tags this is a one way process so once the tag is locked it cannot be unlocked.\n" +
                    "• Encoding and locking are two separate actions. While unlocked the Ikode Smartcode-NFC tags can be reencoded\n" +
                    "numerous times.",
            "• Ikode Smartcode-NFC tags are passive data stores which can be read by the IkodeSC Reader istalled on a\n" +
                    "mobile device. They typically contain data (as of 2015 between 96 and 8,192 bytes) and are read-only.\n" +
                    "• Ikode Smartcode-NFC tags can be small stickers, which contain a small unpowered NFC chip. Ikode SC\n" +
                    "Reader equipped smartphone automation can be paired with NFC Tags or stickers that are programmed\n" +
                    "by the Ikode software's NFC engine amd be utilized immediately with an IkodeSC Reader-equipped\n" +
                    "smartphone and an NFC tag.\n" +
                    "Ikode Smartcodes-CODE | Ikode Smartcodes – NFC | Ikode SC Reader | Frequently Asked Questions\n" +
                    "• The Ikode software's NFC engine is compliant with the 2015 Signature Record Type Definition (RTD) 2.0\n" +
                    "and adds integrity and authenticity for Ikode Smartcode-NFC Tags. This specification allows the IkodeSC\n" +
                    "Reader-equipped smartphone to verify tag data and identify the tag author.", //17
            "• That's a logical and valid question. Ikona think that the answer is probably not.\n" +
                    "• We generally feel that Ikode Smartcodes-CODE and Ikode Smartcode-NFC tags sit alongside each other\n" +
                    "and both have their advantages and disadvantages. We think that the user experience with Ikode\n" +
                    "Smartcode-NFC tags is generally better and in the instances where the additional cost of using an Ikode\n" +
                    "Smartcode-NFC tag is less relevant to the overall cost (for example on a wristband, on wines and liquor,\n" +
                    "on leather good, fashion apparel and consumer electronics), it would be our preference.\n" +
                    "• However, Ikode Smartcodes-CODE don't require the user to be so physically close, are free to print and\n" +
                    "are able to be read by most current Android smartphones (albeit with the FREE IkodeSC Reader app)." //18
    };

    ArrayAdapter<String> adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_page);

        listView = (ListView)findViewById(R.id.list);
        searchBox = (SearchView) findViewById(R.id.searchbox);


        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, titles);
        listView.setAdapter(adapter);

        searchBox.setOnQueryTextListener(new SearchView.OnQueryTextListener() {


            public boolean onQueryTextSubmit(String query) {
                // TODO Auto-generated method stub
                return false;
            }

            public boolean onQueryTextChange(String query) {
                // TODO Auto-generated method stub
                adapter.getFilter().filter(query);
                return false;
            }
        });


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String chosenTitle = ((TextView) view).getText().toString();

                String titleValue = getValue(chosenTitle);

                TextView tx = new TextView(HelpPage.this);
                tx.setText(titleValue);
                tx.setGravity(Gravity.TOP);


                new AlertDialog.Builder(HelpPage.this).setTitle("MESSAGE").setMessage("CONTENT: ").setView(tx,25, 0, 25, 0).setNegativeButton("CLOSE", new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // TODO Auto-generated method stub
                            }
                        }).show();
            }
        });


        //Toolbar toolbar = (Toolbar) findViewById(R.id.ikode_bar);
       // setSupportActionBar(toolbar);

        //test for returning to previous activity
       // getSupportActionBar().setHomeButtonEnabled(true);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        /*
        try {
            parseData();
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "ERROR: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }
        */


        //ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.Layout.simple_List_item_1, searches );
        /*SearchManager searchManager = (SearchManager)
                getSystemService(Context.SEARCH_SERVICE);

        ComponentName componentName = new ComponentName(context, HelpPage.class);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName));*/

    }
    private String getValue(String title) {

        String content = "";
        for(int i = 0; i < titles.length; i++) {
            if(titles[i].equals(title)) {
                content = contents[i];
            }
        }

        return content;
    }
/*
    private void parseData () throws Exception {
        InputSource source = new InputSource(getResources().openRawResource(R.raw.searchable_items));
        XPath xpath = XPathFactory.newInstance().newXPath();
        String expression = "//name/title['What is the purpose of the Ikode SC Decoder?']";
        NodeList nodes = (NodeList) xpath.evaluate(expression, source, XPathConstants.NODESET);
        Toast.makeText(getApplicationContext(), "COUNT: " + String.valueOf(nodes.getLength()), Toast.LENGTH_LONG).show();

        String keyword = "What is the purpose of the Ikode SC Decoder?";

        if (nodes != null && nodes.getLength() > 0) {
            searches.clear();
            int len = nodes.getLength();

            for (int i = 0; i < len; ++i) {
                Node node = nodes.item(i);
                searches.add(node.getTextContent());
                Toast.makeText(getApplicationContext(), "COUNT: " + node.getTextContent(), Toast.LENGTH_LONG).show();
            }
        }
    }
    */
    //to go back to same task *gmbg* not sure on what menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    //to go back to same task *gmbg*
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        if (id == android.R.id.home) {
            NavUtils.navigateUpFromSameTask(this);
        }
        return super.onOptionsItemSelected(item);
    }


}
