package pl.stqa.pft.addressbook.Generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.thoughtworks.xstream.XStream;
import pl.stqa.pft.addressbook.model.ContactData;
import pl.stqa.pft.addressbook.model.GroupData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class ContactDataGenerator {
    @Parameter(names = "-c", description = "Contact count")
    public int count;

    @Parameter (names = "-f", description = "Target file")
    public String file;

    @Parameter (names = "-d", description = "Data format")
    public String format;


    public static void main(String[] args) throws IOException {
        ContactDataGenerator generator = new ContactDataGenerator();
        JCommander jCommander = new JCommander(generator);
        try {
            jCommander.parse(args);
        } catch (ParameterException ex) {
            jCommander.usage();    //wyświetlamy wyjatek na consoli
            return;
        }
        generator.run();//uruchamiamy generator
    }

    private void run() throws IOException {
        List<ContactData> contacts = generateContacts(count);//wygenerowanie danych
        if (format.equals("csv")){
            saveAsCsv(contacts, new File(file));     //zapisanie danych w pliku
        } else if (format.equals("xml")) {
            saveAsXml(contacts, new File(file));
        } else {
            System.out.println("Unrecognized format" + format);
        }

    }

    private void saveAsXml(List<ContactData> contacts, File file) throws IOException{
        XStream xstream = new XStream();
        xstream.processAnnotations(ContactData.class);
        String xml = xstream.toXML(contacts);
        Writer writer = new FileWriter(file);
        writer.write(xml);
        writer.close();
    }

    private void saveAsCsv(List<ContactData> contacts, File file) throws IOException {
        System.out.println(new File(".").getAbsolutePath());
        Writer writer = new FileWriter(file); //otwieramy plik do zapisu
        for (ContactData contact : contacts) {//przechodzimy w pętli po wszytskich grupach które znajdują się na liście groups
            writer.write(String.format("%s;%s;%s\n", contact.getFirstname(), contact.getLastname(), contact.getAddress()
                    , contact.getTelephonehome(), contact.getEmail(), contact.getPhoto()));//zapisanie pliku
            // contact.getGroup(),
        }
        writer.close();
    }

    private List<ContactData> generateContacts(int count) {
        List<ContactData> contacts = new ArrayList<ContactData>();
        for (int i = 0; i < count; i++) {
            contacts.add(new ContactData().withFirstname(String.format("fistname %s", i)).withLastname(String.format("lastname %s", i))
                    .withAddress(String.format("address %s", i)).withTelephonehome(String.format("telephonehome %s", i))
                    .withEmail(String.format("email %s", i))//.withGroup(String.format("test %s", i))
                    .withPhoto(new File("src/test/resources/scrum.jpg")));
        }
        return contacts;
    }
}


